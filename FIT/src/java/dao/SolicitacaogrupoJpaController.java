/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Grupo;
import model.Solicitacaogrupo;
import model.Usuario;

/**
 *
 * @author asdfrofl
 */
public class SolicitacaogrupoJpaController implements Serializable {

    public SolicitacaogrupoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicitacaogrupo solicitacaogrupo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo idgrupo = solicitacaogrupo.getIdgrupo();
            if (idgrupo != null) {
                idgrupo = em.getReference(idgrupo.getClass(), idgrupo.getIdgrupo());
                solicitacaogrupo.setIdgrupo(idgrupo);
            }
            Usuario idusuario = solicitacaogrupo.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                solicitacaogrupo.setIdusuario(idusuario);
            }
            em.persist(solicitacaogrupo);
            if (idgrupo != null) {
                idgrupo.getSolicitacaogrupoList().add(solicitacaogrupo);
                idgrupo = em.merge(idgrupo);
            }
            if (idusuario != null) {
                idusuario.getSolicitacaogrupoList().add(solicitacaogrupo);
                idusuario = em.merge(idusuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSolicitacaogrupo(solicitacaogrupo.getIdsolicitacao()) != null) {
                throw new PreexistingEntityException("Solicitacaogrupo " + solicitacaogrupo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitacaogrupo solicitacaogrupo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitacaogrupo persistentSolicitacaogrupo = em.find(Solicitacaogrupo.class, solicitacaogrupo.getIdsolicitacao());
            Grupo idgrupoOld = persistentSolicitacaogrupo.getIdgrupo();
            Grupo idgrupoNew = solicitacaogrupo.getIdgrupo();
            Usuario idusuarioOld = persistentSolicitacaogrupo.getIdusuario();
            Usuario idusuarioNew = solicitacaogrupo.getIdusuario();
            if (idgrupoNew != null) {
                idgrupoNew = em.getReference(idgrupoNew.getClass(), idgrupoNew.getIdgrupo());
                solicitacaogrupo.setIdgrupo(idgrupoNew);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                solicitacaogrupo.setIdusuario(idusuarioNew);
            }
            solicitacaogrupo = em.merge(solicitacaogrupo);
            if (idgrupoOld != null && !idgrupoOld.equals(idgrupoNew)) {
                idgrupoOld.getSolicitacaogrupoList().remove(solicitacaogrupo);
                idgrupoOld = em.merge(idgrupoOld);
            }
            if (idgrupoNew != null && !idgrupoNew.equals(idgrupoOld)) {
                idgrupoNew.getSolicitacaogrupoList().add(solicitacaogrupo);
                idgrupoNew = em.merge(idgrupoNew);
            }
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getSolicitacaogrupoList().remove(solicitacaogrupo);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getSolicitacaogrupoList().add(solicitacaogrupo);
                idusuarioNew = em.merge(idusuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitacaogrupo.getIdsolicitacao();
                if (findSolicitacaogrupo(id) == null) {
                    throw new NonexistentEntityException("The solicitacaogrupo with id " + id + " no longer exists.");
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
            Solicitacaogrupo solicitacaogrupo;
            try {
                solicitacaogrupo = em.getReference(Solicitacaogrupo.class, id);
                solicitacaogrupo.getIdsolicitacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitacaogrupo with id " + id + " no longer exists.", enfe);
            }
            Grupo idgrupo = solicitacaogrupo.getIdgrupo();
            if (idgrupo != null) {
                idgrupo.getSolicitacaogrupoList().remove(solicitacaogrupo);
                idgrupo = em.merge(idgrupo);
            }
            Usuario idusuario = solicitacaogrupo.getIdusuario();
            if (idusuario != null) {
                idusuario.getSolicitacaogrupoList().remove(solicitacaogrupo);
                idusuario = em.merge(idusuario);
            }
            em.remove(solicitacaogrupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitacaogrupo> findSolicitacaogrupoEntities() {
        return findSolicitacaogrupoEntities(true, -1, -1);
    }

    public List<Solicitacaogrupo> findSolicitacaogrupoEntities(int maxResults, int firstResult) {
        return findSolicitacaogrupoEntities(false, maxResults, firstResult);
    }

    private List<Solicitacaogrupo> findSolicitacaogrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Solicitacaogrupo.class));
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

    public Solicitacaogrupo findSolicitacaogrupo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitacaogrupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitacaogrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Solicitacaogrupo> rt = cq.from(Solicitacaogrupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
