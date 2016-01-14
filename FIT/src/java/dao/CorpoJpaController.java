/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Atleta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Corpo;

/**
 *
 * @author asdfrofl
 */
public class CorpoJpaController implements Serializable {

    public CorpoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Corpo corpo) {
        if (corpo.getAtletaList() == null) {
            corpo.setAtletaList(new ArrayList<Atleta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Atleta> attachedAtletaList = new ArrayList<Atleta>();
            for (Atleta atletaListAtletaToAttach : corpo.getAtletaList()) {
                atletaListAtletaToAttach = em.getReference(atletaListAtletaToAttach.getClass(), atletaListAtletaToAttach.getIdatleta());
                attachedAtletaList.add(atletaListAtletaToAttach);
            }
            corpo.setAtletaList(attachedAtletaList);
            em.persist(corpo);
            for (Atleta atletaListAtleta : corpo.getAtletaList()) {
                Corpo oldIdcorpoOfAtletaListAtleta = atletaListAtleta.getIdcorpo();
                atletaListAtleta.setIdcorpo(corpo);
                atletaListAtleta = em.merge(atletaListAtleta);
                if (oldIdcorpoOfAtletaListAtleta != null) {
                    oldIdcorpoOfAtletaListAtleta.getAtletaList().remove(atletaListAtleta);
                    oldIdcorpoOfAtletaListAtleta = em.merge(oldIdcorpoOfAtletaListAtleta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Corpo corpo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Corpo persistentCorpo = em.find(Corpo.class, corpo.getIdcorpo());
            List<Atleta> atletaListOld = persistentCorpo.getAtletaList();
            List<Atleta> atletaListNew = corpo.getAtletaList();
            List<String> illegalOrphanMessages = null;
            for (Atleta atletaListOldAtleta : atletaListOld) {
                if (!atletaListNew.contains(atletaListOldAtleta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Atleta " + atletaListOldAtleta + " since its idcorpo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Atleta> attachedAtletaListNew = new ArrayList<Atleta>();
            for (Atleta atletaListNewAtletaToAttach : atletaListNew) {
                atletaListNewAtletaToAttach = em.getReference(atletaListNewAtletaToAttach.getClass(), atletaListNewAtletaToAttach.getIdatleta());
                attachedAtletaListNew.add(atletaListNewAtletaToAttach);
            }
            atletaListNew = attachedAtletaListNew;
            corpo.setAtletaList(atletaListNew);
            corpo = em.merge(corpo);
            for (Atleta atletaListNewAtleta : atletaListNew) {
                if (!atletaListOld.contains(atletaListNewAtleta)) {
                    Corpo oldIdcorpoOfAtletaListNewAtleta = atletaListNewAtleta.getIdcorpo();
                    atletaListNewAtleta.setIdcorpo(corpo);
                    atletaListNewAtleta = em.merge(atletaListNewAtleta);
                    if (oldIdcorpoOfAtletaListNewAtleta != null && !oldIdcorpoOfAtletaListNewAtleta.equals(corpo)) {
                        oldIdcorpoOfAtletaListNewAtleta.getAtletaList().remove(atletaListNewAtleta);
                        oldIdcorpoOfAtletaListNewAtleta = em.merge(oldIdcorpoOfAtletaListNewAtleta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = corpo.getIdcorpo();
                if (findCorpo(id) == null) {
                    throw new NonexistentEntityException("The corpo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Corpo corpo;
            try {
                corpo = em.getReference(Corpo.class, id);
                corpo.getIdcorpo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The corpo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Atleta> atletaListOrphanCheck = corpo.getAtletaList();
            for (Atleta atletaListOrphanCheckAtleta : atletaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Corpo (" + corpo + ") cannot be destroyed since the Atleta " + atletaListOrphanCheckAtleta + " in its atletaList field has a non-nullable idcorpo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(corpo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Corpo> findCorpoEntities() {
        return findCorpoEntities(true, -1, -1);
    }

    public List<Corpo> findCorpoEntities(int maxResults, int firstResult) {
        return findCorpoEntities(false, maxResults, firstResult);
    }

    private List<Corpo> findCorpoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Corpo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Corpo findCorpo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Corpo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCorpoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Corpo> rt = cq.from(Corpo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
