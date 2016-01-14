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
import model.Chatconsultoria;
import model.Consultoriarealizada;
import model.Usuario;

/**
 *
 * @author asdfrofl
 */
public class ChatconsultoriaJpaController implements Serializable {

    public ChatconsultoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Chatconsultoria chatconsultoria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consultoriarealizada idconsultoriarealizada = chatconsultoria.getIdconsultoriarealizada();
            if (idconsultoriarealizada != null) {
                idconsultoriarealizada = em.getReference(idconsultoriarealizada.getClass(), idconsultoriarealizada.getIdconsultoriarealizada());
                chatconsultoria.setIdconsultoriarealizada(idconsultoriarealizada);
            }
            Usuario idemissor = chatconsultoria.getIdemissor();
            if (idemissor != null) {
                idemissor = em.getReference(idemissor.getClass(), idemissor.getIdusuario());
                chatconsultoria.setIdemissor(idemissor);
            }
            Usuario iddestinatario = chatconsultoria.getIddestinatario();
            if (iddestinatario != null) {
                iddestinatario = em.getReference(iddestinatario.getClass(), iddestinatario.getIdusuario());
                chatconsultoria.setIddestinatario(iddestinatario);
            }
            em.persist(chatconsultoria);
            if (idconsultoriarealizada != null) {
                idconsultoriarealizada.getChatconsultoriaList().add(chatconsultoria);
                idconsultoriarealizada = em.merge(idconsultoriarealizada);
            }
            if (idemissor != null) {
                idemissor.getChatconsultoriaList().add(chatconsultoria);
                idemissor = em.merge(idemissor);
            }
            if (iddestinatario != null) {
                iddestinatario.getChatconsultoriaList().add(chatconsultoria);
                iddestinatario = em.merge(iddestinatario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Chatconsultoria chatconsultoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Chatconsultoria persistentChatconsultoria = em.find(Chatconsultoria.class, chatconsultoria.getIdchatconsultoria());
            Consultoriarealizada idconsultoriarealizadaOld = persistentChatconsultoria.getIdconsultoriarealizada();
            Consultoriarealizada idconsultoriarealizadaNew = chatconsultoria.getIdconsultoriarealizada();
            Usuario idemissorOld = persistentChatconsultoria.getIdemissor();
            Usuario idemissorNew = chatconsultoria.getIdemissor();
            Usuario iddestinatarioOld = persistentChatconsultoria.getIddestinatario();
            Usuario iddestinatarioNew = chatconsultoria.getIddestinatario();
            if (idconsultoriarealizadaNew != null) {
                idconsultoriarealizadaNew = em.getReference(idconsultoriarealizadaNew.getClass(), idconsultoriarealizadaNew.getIdconsultoriarealizada());
                chatconsultoria.setIdconsultoriarealizada(idconsultoriarealizadaNew);
            }
            if (idemissorNew != null) {
                idemissorNew = em.getReference(idemissorNew.getClass(), idemissorNew.getIdusuario());
                chatconsultoria.setIdemissor(idemissorNew);
            }
            if (iddestinatarioNew != null) {
                iddestinatarioNew = em.getReference(iddestinatarioNew.getClass(), iddestinatarioNew.getIdusuario());
                chatconsultoria.setIddestinatario(iddestinatarioNew);
            }
            chatconsultoria = em.merge(chatconsultoria);
            if (idconsultoriarealizadaOld != null && !idconsultoriarealizadaOld.equals(idconsultoriarealizadaNew)) {
                idconsultoriarealizadaOld.getChatconsultoriaList().remove(chatconsultoria);
                idconsultoriarealizadaOld = em.merge(idconsultoriarealizadaOld);
            }
            if (idconsultoriarealizadaNew != null && !idconsultoriarealizadaNew.equals(idconsultoriarealizadaOld)) {
                idconsultoriarealizadaNew.getChatconsultoriaList().add(chatconsultoria);
                idconsultoriarealizadaNew = em.merge(idconsultoriarealizadaNew);
            }
            if (idemissorOld != null && !idemissorOld.equals(idemissorNew)) {
                idemissorOld.getChatconsultoriaList().remove(chatconsultoria);
                idemissorOld = em.merge(idemissorOld);
            }
            if (idemissorNew != null && !idemissorNew.equals(idemissorOld)) {
                idemissorNew.getChatconsultoriaList().add(chatconsultoria);
                idemissorNew = em.merge(idemissorNew);
            }
            if (iddestinatarioOld != null && !iddestinatarioOld.equals(iddestinatarioNew)) {
                iddestinatarioOld.getChatconsultoriaList().remove(chatconsultoria);
                iddestinatarioOld = em.merge(iddestinatarioOld);
            }
            if (iddestinatarioNew != null && !iddestinatarioNew.equals(iddestinatarioOld)) {
                iddestinatarioNew.getChatconsultoriaList().add(chatconsultoria);
                iddestinatarioNew = em.merge(iddestinatarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = chatconsultoria.getIdchatconsultoria();
                if (findChatconsultoria(id) == null) {
                    throw new NonexistentEntityException("The chatconsultoria with id " + id + " no longer exists.");
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
            Chatconsultoria chatconsultoria;
            try {
                chatconsultoria = em.getReference(Chatconsultoria.class, id);
                chatconsultoria.getIdchatconsultoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The chatconsultoria with id " + id + " no longer exists.", enfe);
            }
            Consultoriarealizada idconsultoriarealizada = chatconsultoria.getIdconsultoriarealizada();
            if (idconsultoriarealizada != null) {
                idconsultoriarealizada.getChatconsultoriaList().remove(chatconsultoria);
                idconsultoriarealizada = em.merge(idconsultoriarealizada);
            }
            Usuario idemissor = chatconsultoria.getIdemissor();
            if (idemissor != null) {
                idemissor.getChatconsultoriaList().remove(chatconsultoria);
                idemissor = em.merge(idemissor);
            }
            Usuario iddestinatario = chatconsultoria.getIddestinatario();
            if (iddestinatario != null) {
                iddestinatario.getChatconsultoriaList().remove(chatconsultoria);
                iddestinatario = em.merge(iddestinatario);
            }
            em.remove(chatconsultoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Chatconsultoria> findChatconsultoriaEntities() {
        return findChatconsultoriaEntities(true, -1, -1);
    }

    public List<Chatconsultoria> findChatconsultoriaEntities(int maxResults, int firstResult) {
        return findChatconsultoriaEntities(false, maxResults, firstResult);
    }

    private List<Chatconsultoria> findChatconsultoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Chatconsultoria.class));
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

    public Chatconsultoria findChatconsultoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Chatconsultoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getChatconsultoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Chatconsultoria> rt = cq.from(Chatconsultoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
