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
import model.Atleta;
import model.Mensagemcomum;

/**
 *
 * @author asdfrofl
 */
public class MensagemcomumJpaController implements Serializable {

    public MensagemcomumJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mensagemcomum mensagemcomum) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Atleta idAtletaEmissor = mensagemcomum.getIdAtletaEmissor();
            if (idAtletaEmissor != null) {
                idAtletaEmissor = em.getReference(idAtletaEmissor.getClass(), idAtletaEmissor.getIdatleta());
                mensagemcomum.setIdAtletaEmissor(idAtletaEmissor);
            }
            Atleta idAtletaDestinatario = mensagemcomum.getIdAtletaDestinatario();
            if (idAtletaDestinatario != null) {
                idAtletaDestinatario = em.getReference(idAtletaDestinatario.getClass(), idAtletaDestinatario.getIdatleta());
                mensagemcomum.setIdAtletaDestinatario(idAtletaDestinatario);
            }
            em.persist(mensagemcomum);
            if (idAtletaEmissor != null) {
                idAtletaEmissor.getMensagemcomumList().add(mensagemcomum);
                idAtletaEmissor = em.merge(idAtletaEmissor);
            }
            if (idAtletaDestinatario != null) {
                idAtletaDestinatario.getMensagemcomumList().add(mensagemcomum);
                idAtletaDestinatario = em.merge(idAtletaDestinatario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mensagemcomum mensagemcomum) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mensagemcomum persistentMensagemcomum = em.find(Mensagemcomum.class, mensagemcomum.getIdmensagemComum());
            Atleta idAtletaEmissorOld = persistentMensagemcomum.getIdAtletaEmissor();
            Atleta idAtletaEmissorNew = mensagemcomum.getIdAtletaEmissor();
            Atleta idAtletaDestinatarioOld = persistentMensagemcomum.getIdAtletaDestinatario();
            Atleta idAtletaDestinatarioNew = mensagemcomum.getIdAtletaDestinatario();
            if (idAtletaEmissorNew != null) {
                idAtletaEmissorNew = em.getReference(idAtletaEmissorNew.getClass(), idAtletaEmissorNew.getIdatleta());
                mensagemcomum.setIdAtletaEmissor(idAtletaEmissorNew);
            }
            if (idAtletaDestinatarioNew != null) {
                idAtletaDestinatarioNew = em.getReference(idAtletaDestinatarioNew.getClass(), idAtletaDestinatarioNew.getIdatleta());
                mensagemcomum.setIdAtletaDestinatario(idAtletaDestinatarioNew);
            }
            mensagemcomum = em.merge(mensagemcomum);
            if (idAtletaEmissorOld != null && !idAtletaEmissorOld.equals(idAtletaEmissorNew)) {
                idAtletaEmissorOld.getMensagemcomumList().remove(mensagemcomum);
                idAtletaEmissorOld = em.merge(idAtletaEmissorOld);
            }
            if (idAtletaEmissorNew != null && !idAtletaEmissorNew.equals(idAtletaEmissorOld)) {
                idAtletaEmissorNew.getMensagemcomumList().add(mensagemcomum);
                idAtletaEmissorNew = em.merge(idAtletaEmissorNew);
            }
            if (idAtletaDestinatarioOld != null && !idAtletaDestinatarioOld.equals(idAtletaDestinatarioNew)) {
                idAtletaDestinatarioOld.getMensagemcomumList().remove(mensagemcomum);
                idAtletaDestinatarioOld = em.merge(idAtletaDestinatarioOld);
            }
            if (idAtletaDestinatarioNew != null && !idAtletaDestinatarioNew.equals(idAtletaDestinatarioOld)) {
                idAtletaDestinatarioNew.getMensagemcomumList().add(mensagemcomum);
                idAtletaDestinatarioNew = em.merge(idAtletaDestinatarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mensagemcomum.getIdmensagemComum();
                if (findMensagemcomum(id) == null) {
                    throw new NonexistentEntityException("The mensagemcomum with id " + id + " no longer exists.");
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
            Mensagemcomum mensagemcomum;
            try {
                mensagemcomum = em.getReference(Mensagemcomum.class, id);
                mensagemcomum.getIdmensagemComum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mensagemcomum with id " + id + " no longer exists.", enfe);
            }
            Atleta idAtletaEmissor = mensagemcomum.getIdAtletaEmissor();
            if (idAtletaEmissor != null) {
                idAtletaEmissor.getMensagemcomumList().remove(mensagemcomum);
                idAtletaEmissor = em.merge(idAtletaEmissor);
            }
            Atleta idAtletaDestinatario = mensagemcomum.getIdAtletaDestinatario();
            if (idAtletaDestinatario != null) {
                idAtletaDestinatario.getMensagemcomumList().remove(mensagemcomum);
                idAtletaDestinatario = em.merge(idAtletaDestinatario);
            }
            em.remove(mensagemcomum);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mensagemcomum> findMensagemcomumEntities() {
        return findMensagemcomumEntities(true, -1, -1);
    }

    public List<Mensagemcomum> findMensagemcomumEntities(int maxResults, int firstResult) {
        return findMensagemcomumEntities(false, maxResults, firstResult);
    }

    private List<Mensagemcomum> findMensagemcomumEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mensagemcomum.class));
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

    public Mensagemcomum findMensagemcomum(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mensagemcomum.class, id);
        } finally {
            em.close();
        }
    }

    public int getMensagemcomumCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mensagemcomum> rt = cq.from(Mensagemcomum.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
