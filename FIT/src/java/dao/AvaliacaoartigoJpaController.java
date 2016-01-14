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
import model.Avaliacaoartigo;
import model.Usuario;
import model.Consultoriarealizada;

/**
 *
 * @author asdfrofl
 */
public class AvaliacaoartigoJpaController implements Serializable {

    public AvaliacaoartigoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Avaliacaoartigo avaliacaoartigo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = avaliacaoartigo.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                avaliacaoartigo.setIdusuario(idusuario);
            }
            Consultoriarealizada idconsultoriarealizada = avaliacaoartigo.getIdconsultoriarealizada();
            if (idconsultoriarealizada != null) {
                idconsultoriarealizada = em.getReference(idconsultoriarealizada.getClass(), idconsultoriarealizada.getIdconsultoriarealizada());
                avaliacaoartigo.setIdconsultoriarealizada(idconsultoriarealizada);
            }
            em.persist(avaliacaoartigo);
            if (idusuario != null) {
                idusuario.getAvaliacaoartigoList().add(avaliacaoartigo);
                idusuario = em.merge(idusuario);
            }
            if (idconsultoriarealizada != null) {
                idconsultoriarealizada.getAvaliacaoartigoList().add(avaliacaoartigo);
                idconsultoriarealizada = em.merge(idconsultoriarealizada);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Avaliacaoartigo avaliacaoartigo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Avaliacaoartigo persistentAvaliacaoartigo = em.find(Avaliacaoartigo.class, avaliacaoartigo.getIdavaliacaoconsultoria());
            Usuario idusuarioOld = persistentAvaliacaoartigo.getIdusuario();
            Usuario idusuarioNew = avaliacaoartigo.getIdusuario();
            Consultoriarealizada idconsultoriarealizadaOld = persistentAvaliacaoartigo.getIdconsultoriarealizada();
            Consultoriarealizada idconsultoriarealizadaNew = avaliacaoartigo.getIdconsultoriarealizada();
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                avaliacaoartigo.setIdusuario(idusuarioNew);
            }
            if (idconsultoriarealizadaNew != null) {
                idconsultoriarealizadaNew = em.getReference(idconsultoriarealizadaNew.getClass(), idconsultoriarealizadaNew.getIdconsultoriarealizada());
                avaliacaoartigo.setIdconsultoriarealizada(idconsultoriarealizadaNew);
            }
            avaliacaoartigo = em.merge(avaliacaoartigo);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getAvaliacaoartigoList().remove(avaliacaoartigo);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getAvaliacaoartigoList().add(avaliacaoartigo);
                idusuarioNew = em.merge(idusuarioNew);
            }
            if (idconsultoriarealizadaOld != null && !idconsultoriarealizadaOld.equals(idconsultoriarealizadaNew)) {
                idconsultoriarealizadaOld.getAvaliacaoartigoList().remove(avaliacaoartigo);
                idconsultoriarealizadaOld = em.merge(idconsultoriarealizadaOld);
            }
            if (idconsultoriarealizadaNew != null && !idconsultoriarealizadaNew.equals(idconsultoriarealizadaOld)) {
                idconsultoriarealizadaNew.getAvaliacaoartigoList().add(avaliacaoartigo);
                idconsultoriarealizadaNew = em.merge(idconsultoriarealizadaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = avaliacaoartigo.getIdavaliacaoconsultoria();
                if (findAvaliacaoartigo(id) == null) {
                    throw new NonexistentEntityException("The avaliacaoartigo with id " + id + " no longer exists.");
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
            Avaliacaoartigo avaliacaoartigo;
            try {
                avaliacaoartigo = em.getReference(Avaliacaoartigo.class, id);
                avaliacaoartigo.getIdavaliacaoconsultoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The avaliacaoartigo with id " + id + " no longer exists.", enfe);
            }
            Usuario idusuario = avaliacaoartigo.getIdusuario();
            if (idusuario != null) {
                idusuario.getAvaliacaoartigoList().remove(avaliacaoartigo);
                idusuario = em.merge(idusuario);
            }
            Consultoriarealizada idconsultoriarealizada = avaliacaoartigo.getIdconsultoriarealizada();
            if (idconsultoriarealizada != null) {
                idconsultoriarealizada.getAvaliacaoartigoList().remove(avaliacaoartigo);
                idconsultoriarealizada = em.merge(idconsultoriarealizada);
            }
            em.remove(avaliacaoartigo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Avaliacaoartigo> findAvaliacaoartigoEntities() {
        return findAvaliacaoartigoEntities(true, -1, -1);
    }

    public List<Avaliacaoartigo> findAvaliacaoartigoEntities(int maxResults, int firstResult) {
        return findAvaliacaoartigoEntities(false, maxResults, firstResult);
    }

    private List<Avaliacaoartigo> findAvaliacaoartigoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Avaliacaoartigo.class));
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

    public Avaliacaoartigo findAvaliacaoartigo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Avaliacaoartigo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAvaliacaoartigoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Avaliacaoartigo> rt = cq.from(Avaliacaoartigo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
