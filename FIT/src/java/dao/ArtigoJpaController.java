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
import model.Artigo;
import model.Blog;
import model.Texto;

/**
 *
 * @author asdfrofl
 */
public class ArtigoJpaController implements Serializable {

    public ArtigoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Artigo artigo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Blog idblog = artigo.getIdblog();
            if (idblog != null) {
                idblog = em.getReference(idblog.getClass(), idblog.getIdblog());
                artigo.setIdblog(idblog);
            }
            Texto idtexto = artigo.getIdtexto();
            if (idtexto != null) {
                idtexto = em.getReference(idtexto.getClass(), idtexto.getIdtexto());
                artigo.setIdtexto(idtexto);
            }
            em.persist(artigo);
            if (idblog != null) {
                idblog.getArtigoList().add(artigo);
                idblog = em.merge(idblog);
            }
            if (idtexto != null) {
                idtexto.getArtigoList().add(artigo);
                idtexto = em.merge(idtexto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Artigo artigo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Artigo persistentArtigo = em.find(Artigo.class, artigo.getIdartigo());
            Blog idblogOld = persistentArtigo.getIdblog();
            Blog idblogNew = artigo.getIdblog();
            Texto idtextoOld = persistentArtigo.getIdtexto();
            Texto idtextoNew = artigo.getIdtexto();
            if (idblogNew != null) {
                idblogNew = em.getReference(idblogNew.getClass(), idblogNew.getIdblog());
                artigo.setIdblog(idblogNew);
            }
            if (idtextoNew != null) {
                idtextoNew = em.getReference(idtextoNew.getClass(), idtextoNew.getIdtexto());
                artigo.setIdtexto(idtextoNew);
            }
            artigo = em.merge(artigo);
            if (idblogOld != null && !idblogOld.equals(idblogNew)) {
                idblogOld.getArtigoList().remove(artigo);
                idblogOld = em.merge(idblogOld);
            }
            if (idblogNew != null && !idblogNew.equals(idblogOld)) {
                idblogNew.getArtigoList().add(artigo);
                idblogNew = em.merge(idblogNew);
            }
            if (idtextoOld != null && !idtextoOld.equals(idtextoNew)) {
                idtextoOld.getArtigoList().remove(artigo);
                idtextoOld = em.merge(idtextoOld);
            }
            if (idtextoNew != null && !idtextoNew.equals(idtextoOld)) {
                idtextoNew.getArtigoList().add(artigo);
                idtextoNew = em.merge(idtextoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = artigo.getIdartigo();
                if (findArtigo(id) == null) {
                    throw new NonexistentEntityException("The artigo with id " + id + " no longer exists.");
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
            Artigo artigo;
            try {
                artigo = em.getReference(Artigo.class, id);
                artigo.getIdartigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The artigo with id " + id + " no longer exists.", enfe);
            }
            Blog idblog = artigo.getIdblog();
            if (idblog != null) {
                idblog.getArtigoList().remove(artigo);
                idblog = em.merge(idblog);
            }
            Texto idtexto = artigo.getIdtexto();
            if (idtexto != null) {
                idtexto.getArtigoList().remove(artigo);
                idtexto = em.merge(idtexto);
            }
            em.remove(artigo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Artigo> findArtigoEntities() {
        return findArtigoEntities(true, -1, -1);
    }

    public List<Artigo> findArtigoEntities(int maxResults, int firstResult) {
        return findArtigoEntities(false, maxResults, firstResult);
    }

    private List<Artigo> findArtigoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Artigo.class));
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

    public Artigo findArtigo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Artigo.class, id);
        } finally {
            em.close();
        }
    }

    public int getArtigoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Artigo> rt = cq.from(Artigo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
