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
import model.Usuario;
import model.Evento;
import model.Usuarioevento;

/**
 *
 * @author asdfrofl
 */
public class UsuarioeventoJpaController implements Serializable {

    public UsuarioeventoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarioevento usuarioevento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = usuarioevento.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                usuarioevento.setIdusuario(idusuario);
            }
            Evento idevento = usuarioevento.getIdevento();
            if (idevento != null) {
                idevento = em.getReference(idevento.getClass(), idevento.getIdevento());
                usuarioevento.setIdevento(idevento);
            }
            em.persist(usuarioevento);
            if (idusuario != null) {
                idusuario.getUsuarioeventoList().add(usuarioevento);
                idusuario = em.merge(idusuario);
            }
            if (idevento != null) {
                idevento.getUsuarioeventoList().add(usuarioevento);
                idevento = em.merge(idevento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarioevento usuarioevento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarioevento persistentUsuarioevento = em.find(Usuarioevento.class, usuarioevento.getIdusuarioevento());
            Usuario idusuarioOld = persistentUsuarioevento.getIdusuario();
            Usuario idusuarioNew = usuarioevento.getIdusuario();
            Evento ideventoOld = persistentUsuarioevento.getIdevento();
            Evento ideventoNew = usuarioevento.getIdevento();
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                usuarioevento.setIdusuario(idusuarioNew);
            }
            if (ideventoNew != null) {
                ideventoNew = em.getReference(ideventoNew.getClass(), ideventoNew.getIdevento());
                usuarioevento.setIdevento(ideventoNew);
            }
            usuarioevento = em.merge(usuarioevento);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getUsuarioeventoList().remove(usuarioevento);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getUsuarioeventoList().add(usuarioevento);
                idusuarioNew = em.merge(idusuarioNew);
            }
            if (ideventoOld != null && !ideventoOld.equals(ideventoNew)) {
                ideventoOld.getUsuarioeventoList().remove(usuarioevento);
                ideventoOld = em.merge(ideventoOld);
            }
            if (ideventoNew != null && !ideventoNew.equals(ideventoOld)) {
                ideventoNew.getUsuarioeventoList().add(usuarioevento);
                ideventoNew = em.merge(ideventoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarioevento.getIdusuarioevento();
                if (findUsuarioevento(id) == null) {
                    throw new NonexistentEntityException("The usuarioevento with id " + id + " no longer exists.");
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
            Usuarioevento usuarioevento;
            try {
                usuarioevento = em.getReference(Usuarioevento.class, id);
                usuarioevento.getIdusuarioevento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioevento with id " + id + " no longer exists.", enfe);
            }
            Usuario idusuario = usuarioevento.getIdusuario();
            if (idusuario != null) {
                idusuario.getUsuarioeventoList().remove(usuarioevento);
                idusuario = em.merge(idusuario);
            }
            Evento idevento = usuarioevento.getIdevento();
            if (idevento != null) {
                idevento.getUsuarioeventoList().remove(usuarioevento);
                idevento = em.merge(idevento);
            }
            em.remove(usuarioevento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarioevento> findUsuarioeventoEntities() {
        return findUsuarioeventoEntities(true, -1, -1);
    }

    public List<Usuarioevento> findUsuarioeventoEntities(int maxResults, int firstResult) {
        return findUsuarioeventoEntities(false, maxResults, firstResult);
    }

    private List<Usuarioevento> findUsuarioeventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarioevento.class));
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

    public Usuarioevento findUsuarioevento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarioevento.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioeventoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarioevento> rt = cq.from(Usuarioevento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
