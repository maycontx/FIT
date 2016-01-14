/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Seguidor;
import model.Usuario;

/**
 *
 * @author asdfrofl
 */
public class SeguidorJpaController implements Serializable {

    public SeguidorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Seguidor seguidor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idseguidor = seguidor.getIdseguidor();
            if (idseguidor != null) {
                idseguidor = em.getReference(idseguidor.getClass(), idseguidor.getIdusuario());
                seguidor.setIdseguidor(idseguidor);
            }
            Usuario idseguido = seguidor.getIdseguido();
            if (idseguido != null) {
                idseguido = em.getReference(idseguido.getClass(), idseguido.getIdusuario());
                seguidor.setIdseguido(idseguido);
            }
            em.persist(seguidor);
            if (idseguidor != null) {
                idseguidor.getSeguidorList().add(seguidor);
                idseguidor = em.merge(idseguidor);
            }
            if (idseguido != null) {
                idseguido.getSeguidorList().add(seguidor);
                idseguido = em.merge(idseguido);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Seguidor seguidor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Seguidor persistentSeguidor = em.find(Seguidor.class, seguidor.getIdtabela());
            Usuario idseguidorOld = persistentSeguidor.getIdseguidor();
            Usuario idseguidorNew = seguidor.getIdseguidor();
            Usuario idseguidoOld = persistentSeguidor.getIdseguido();
            Usuario idseguidoNew = seguidor.getIdseguido();
            if (idseguidorNew != null) {
                idseguidorNew = em.getReference(idseguidorNew.getClass(), idseguidorNew.getIdusuario());
                seguidor.setIdseguidor(idseguidorNew);
            }
            if (idseguidoNew != null) {
                idseguidoNew = em.getReference(idseguidoNew.getClass(), idseguidoNew.getIdusuario());
                seguidor.setIdseguido(idseguidoNew);
            }
            seguidor = em.merge(seguidor);
            if (idseguidorOld != null && !idseguidorOld.equals(idseguidorNew)) {
                idseguidorOld.getSeguidorList().remove(seguidor);
                idseguidorOld = em.merge(idseguidorOld);
            }
            if (idseguidorNew != null && !idseguidorNew.equals(idseguidorOld)) {
                idseguidorNew.getSeguidorList().add(seguidor);
                idseguidorNew = em.merge(idseguidorNew);
            }
            if (idseguidoOld != null && !idseguidoOld.equals(idseguidoNew)) {
                idseguidoOld.getSeguidorList().remove(seguidor);
                idseguidoOld = em.merge(idseguidoOld);
            }
            if (idseguidoNew != null && !idseguidoNew.equals(idseguidoOld)) {
                idseguidoNew.getSeguidorList().add(seguidor);
                idseguidoNew = em.merge(idseguidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = seguidor.getIdtabela();
                if (findSeguidor(id) == null) {
                    throw new NonexistentEntityException("The seguidor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Seguidor seguidor;
            try {
                seguidor = em.getReference(Seguidor.class, id);
                seguidor.getIdtabela();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seguidor with id " + id + " no longer exists.", enfe);
            }
            Usuario idseguidor = seguidor.getIdseguidor();
            if (idseguidor != null) {
                idseguidor.getSeguidorList().remove(seguidor);
                idseguidor = em.merge(idseguidor);
            }
            Usuario idseguido = seguidor.getIdseguido();
            if (idseguido != null) {
                idseguido.getSeguidorList().remove(seguidor);
                idseguido = em.merge(idseguido);
            }
            em.remove(seguidor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Seguidor> findSeguidorEntities() {
        return findSeguidorEntities(true, -1, -1);
    }

    public List<Seguidor> findSeguidorEntities(int maxResults, int firstResult) {
        return findSeguidorEntities(false, maxResults, firstResult);
    }

    private List<Seguidor> findSeguidorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Seguidor.class));
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

    public Seguidor findSeguidor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Seguidor.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeguidorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Seguidor> rt = cq.from(Seguidor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
