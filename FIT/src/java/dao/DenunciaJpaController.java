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
import model.Denuncia;
import model.Usuario;

/**
 *
 * @author asdfrofl
 */
public class DenunciaJpaController implements Serializable {

    public DenunciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Denuncia denuncia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = denuncia.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                denuncia.setIdusuario(idusuario);
            }
            em.persist(denuncia);
            if (idusuario != null) {
                idusuario.getDenunciaList().add(denuncia);
                idusuario = em.merge(idusuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Denuncia denuncia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Denuncia persistentDenuncia = em.find(Denuncia.class, denuncia.getIddenuncia());
            Usuario idusuarioOld = persistentDenuncia.getIdusuario();
            Usuario idusuarioNew = denuncia.getIdusuario();
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                denuncia.setIdusuario(idusuarioNew);
            }
            denuncia = em.merge(denuncia);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getDenunciaList().remove(denuncia);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getDenunciaList().add(denuncia);
                idusuarioNew = em.merge(idusuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = denuncia.getIddenuncia();
                if (findDenuncia(id) == null) {
                    throw new NonexistentEntityException("The denuncia with id " + id + " no longer exists.");
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
            Denuncia denuncia;
            try {
                denuncia = em.getReference(Denuncia.class, id);
                denuncia.getIddenuncia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The denuncia with id " + id + " no longer exists.", enfe);
            }
            Usuario idusuario = denuncia.getIdusuario();
            if (idusuario != null) {
                idusuario.getDenunciaList().remove(denuncia);
                idusuario = em.merge(idusuario);
            }
            em.remove(denuncia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Denuncia> findDenunciaEntities() {
        return findDenunciaEntities(true, -1, -1);
    }

    public List<Denuncia> findDenunciaEntities(int maxResults, int firstResult) {
        return findDenunciaEntities(false, maxResults, firstResult);
    }

    private List<Denuncia> findDenunciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Denuncia.class));
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

    public Denuncia findDenuncia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Denuncia.class, id);
        } finally {
            em.close();
        }
    }

    public int getDenunciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Denuncia> rt = cq.from(Denuncia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
