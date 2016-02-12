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
import model.Usuario;
import model.Solicitacaoevento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Evento;
import model.Usuarioevento;

/**
 *
 * @author asdfrofl
 */
public class EventoJpaController implements Serializable {

    public EventoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Evento evento) {
        if (evento.getSolicitacaoeventoList() == null) {
            evento.setSolicitacaoeventoList(new ArrayList<Solicitacaoevento>());
        }
        if (evento.getUsuarioeventoList() == null) {
            evento.setUsuarioeventoList(new ArrayList<Usuarioevento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = evento.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                evento.setIdusuario(idusuario);
            }
            List<Solicitacaoevento> attachedSolicitacaoeventoList = new ArrayList<Solicitacaoevento>();
            for (Solicitacaoevento solicitacaoeventoListSolicitacaoeventoToAttach : evento.getSolicitacaoeventoList()) {
                solicitacaoeventoListSolicitacaoeventoToAttach = em.getReference(solicitacaoeventoListSolicitacaoeventoToAttach.getClass(), solicitacaoeventoListSolicitacaoeventoToAttach.getIdsolicitacao());
                attachedSolicitacaoeventoList.add(solicitacaoeventoListSolicitacaoeventoToAttach);
            }
            evento.setSolicitacaoeventoList(attachedSolicitacaoeventoList);
            List<Usuarioevento> attachedUsuarioeventoList = new ArrayList<Usuarioevento>();
            for (Usuarioevento usuarioeventoListUsuarioeventoToAttach : evento.getUsuarioeventoList()) {
                usuarioeventoListUsuarioeventoToAttach = em.getReference(usuarioeventoListUsuarioeventoToAttach.getClass(), usuarioeventoListUsuarioeventoToAttach.getIdusuarioevento());
                attachedUsuarioeventoList.add(usuarioeventoListUsuarioeventoToAttach);
            }
            evento.setUsuarioeventoList(attachedUsuarioeventoList);
            em.persist(evento);
            if (idusuario != null) {
                idusuario.getEventoList().add(evento);
                idusuario = em.merge(idusuario);
            }
            for (Solicitacaoevento solicitacaoeventoListSolicitacaoevento : evento.getSolicitacaoeventoList()) {
                Evento oldIdeventoOfSolicitacaoeventoListSolicitacaoevento = solicitacaoeventoListSolicitacaoevento.getIdevento();
                solicitacaoeventoListSolicitacaoevento.setIdevento(evento);
                solicitacaoeventoListSolicitacaoevento = em.merge(solicitacaoeventoListSolicitacaoevento);
                if (oldIdeventoOfSolicitacaoeventoListSolicitacaoevento != null) {
                    oldIdeventoOfSolicitacaoeventoListSolicitacaoevento.getSolicitacaoeventoList().remove(solicitacaoeventoListSolicitacaoevento);
                    oldIdeventoOfSolicitacaoeventoListSolicitacaoevento = em.merge(oldIdeventoOfSolicitacaoeventoListSolicitacaoevento);
                }
            }
            for (Usuarioevento usuarioeventoListUsuarioevento : evento.getUsuarioeventoList()) {
                Evento oldIdeventoOfUsuarioeventoListUsuarioevento = usuarioeventoListUsuarioevento.getIdevento();
                usuarioeventoListUsuarioevento.setIdevento(evento);
                usuarioeventoListUsuarioevento = em.merge(usuarioeventoListUsuarioevento);
                if (oldIdeventoOfUsuarioeventoListUsuarioevento != null) {
                    oldIdeventoOfUsuarioeventoListUsuarioevento.getUsuarioeventoList().remove(usuarioeventoListUsuarioevento);
                    oldIdeventoOfUsuarioeventoListUsuarioevento = em.merge(oldIdeventoOfUsuarioeventoListUsuarioevento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Evento evento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Evento persistentEvento = em.find(Evento.class, evento.getIdevento());
            Usuario idusuarioOld = persistentEvento.getIdusuario();
            Usuario idusuarioNew = evento.getIdusuario();
            List<Solicitacaoevento> solicitacaoeventoListOld = persistentEvento.getSolicitacaoeventoList();
            List<Solicitacaoevento> solicitacaoeventoListNew = evento.getSolicitacaoeventoList();
            List<Usuarioevento> usuarioeventoListOld = persistentEvento.getUsuarioeventoList();
            List<Usuarioevento> usuarioeventoListNew = evento.getUsuarioeventoList();
            List<String> illegalOrphanMessages = null;
            for (Solicitacaoevento solicitacaoeventoListOldSolicitacaoevento : solicitacaoeventoListOld) {
                if (!solicitacaoeventoListNew.contains(solicitacaoeventoListOldSolicitacaoevento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitacaoevento " + solicitacaoeventoListOldSolicitacaoevento + " since its idevento field is not nullable.");
                }
            }
            for (Usuarioevento usuarioeventoListOldUsuarioevento : usuarioeventoListOld) {
                if (!usuarioeventoListNew.contains(usuarioeventoListOldUsuarioevento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuarioevento " + usuarioeventoListOldUsuarioevento + " since its idevento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                evento.setIdusuario(idusuarioNew);
            }
            List<Solicitacaoevento> attachedSolicitacaoeventoListNew = new ArrayList<Solicitacaoevento>();
            for (Solicitacaoevento solicitacaoeventoListNewSolicitacaoeventoToAttach : solicitacaoeventoListNew) {
                solicitacaoeventoListNewSolicitacaoeventoToAttach = em.getReference(solicitacaoeventoListNewSolicitacaoeventoToAttach.getClass(), solicitacaoeventoListNewSolicitacaoeventoToAttach.getIdsolicitacao());
                attachedSolicitacaoeventoListNew.add(solicitacaoeventoListNewSolicitacaoeventoToAttach);
            }
            solicitacaoeventoListNew = attachedSolicitacaoeventoListNew;
            evento.setSolicitacaoeventoList(solicitacaoeventoListNew);
            List<Usuarioevento> attachedUsuarioeventoListNew = new ArrayList<Usuarioevento>();
            for (Usuarioevento usuarioeventoListNewUsuarioeventoToAttach : usuarioeventoListNew) {
                usuarioeventoListNewUsuarioeventoToAttach = em.getReference(usuarioeventoListNewUsuarioeventoToAttach.getClass(), usuarioeventoListNewUsuarioeventoToAttach.getIdusuarioevento());
                attachedUsuarioeventoListNew.add(usuarioeventoListNewUsuarioeventoToAttach);
            }
            usuarioeventoListNew = attachedUsuarioeventoListNew;
            evento.setUsuarioeventoList(usuarioeventoListNew);
            evento = em.merge(evento);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getEventoList().remove(evento);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getEventoList().add(evento);
                idusuarioNew = em.merge(idusuarioNew);
            }
            for (Solicitacaoevento solicitacaoeventoListNewSolicitacaoevento : solicitacaoeventoListNew) {
                if (!solicitacaoeventoListOld.contains(solicitacaoeventoListNewSolicitacaoevento)) {
                    Evento oldIdeventoOfSolicitacaoeventoListNewSolicitacaoevento = solicitacaoeventoListNewSolicitacaoevento.getIdevento();
                    solicitacaoeventoListNewSolicitacaoevento.setIdevento(evento);
                    solicitacaoeventoListNewSolicitacaoevento = em.merge(solicitacaoeventoListNewSolicitacaoevento);
                    if (oldIdeventoOfSolicitacaoeventoListNewSolicitacaoevento != null && !oldIdeventoOfSolicitacaoeventoListNewSolicitacaoevento.equals(evento)) {
                        oldIdeventoOfSolicitacaoeventoListNewSolicitacaoevento.getSolicitacaoeventoList().remove(solicitacaoeventoListNewSolicitacaoevento);
                        oldIdeventoOfSolicitacaoeventoListNewSolicitacaoevento = em.merge(oldIdeventoOfSolicitacaoeventoListNewSolicitacaoevento);
                    }
                }
            }
            for (Usuarioevento usuarioeventoListNewUsuarioevento : usuarioeventoListNew) {
                if (!usuarioeventoListOld.contains(usuarioeventoListNewUsuarioevento)) {
                    Evento oldIdeventoOfUsuarioeventoListNewUsuarioevento = usuarioeventoListNewUsuarioevento.getIdevento();
                    usuarioeventoListNewUsuarioevento.setIdevento(evento);
                    usuarioeventoListNewUsuarioevento = em.merge(usuarioeventoListNewUsuarioevento);
                    if (oldIdeventoOfUsuarioeventoListNewUsuarioevento != null && !oldIdeventoOfUsuarioeventoListNewUsuarioevento.equals(evento)) {
                        oldIdeventoOfUsuarioeventoListNewUsuarioevento.getUsuarioeventoList().remove(usuarioeventoListNewUsuarioevento);
                        oldIdeventoOfUsuarioeventoListNewUsuarioevento = em.merge(oldIdeventoOfUsuarioeventoListNewUsuarioevento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = evento.getIdevento();
                if (findEvento(id) == null) {
                    throw new NonexistentEntityException("The evento with id " + id + " no longer exists.");
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
            Evento evento;
            try {
                evento = em.getReference(Evento.class, id);
                evento.getIdevento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The evento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Solicitacaoevento> solicitacaoeventoListOrphanCheck = evento.getSolicitacaoeventoList();
            for (Solicitacaoevento solicitacaoeventoListOrphanCheckSolicitacaoevento : solicitacaoeventoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Evento (" + evento + ") cannot be destroyed since the Solicitacaoevento " + solicitacaoeventoListOrphanCheckSolicitacaoevento + " in its solicitacaoeventoList field has a non-nullable idevento field.");
            }
            List<Usuarioevento> usuarioeventoListOrphanCheck = evento.getUsuarioeventoList();
            for (Usuarioevento usuarioeventoListOrphanCheckUsuarioevento : usuarioeventoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Evento (" + evento + ") cannot be destroyed since the Usuarioevento " + usuarioeventoListOrphanCheckUsuarioevento + " in its usuarioeventoList field has a non-nullable idevento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario idusuario = evento.getIdusuario();
            if (idusuario != null) {
                idusuario.getEventoList().remove(evento);
                idusuario = em.merge(idusuario);
            }
            em.remove(evento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Evento> findEventoEntities() {
        return findEventoEntities(true, -1, -1);
    }

    public List<Evento> findEventoEntities(int maxResults, int firstResult) {
        return findEventoEntities(false, maxResults, firstResult);
    }

    private List<Evento> findEventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Evento.class));
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

    public Evento findEvento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Evento.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Evento> rt = cq.from(Evento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Evento> findEventsByUser(int id){
        
        String query = "SELECT e FROM Evento e, Usuarioevento u WHERE "
                + " u.idusuario.idusuario = :id and u.idevento.idevento = e.idevento "; 
        
        Query q = getEntityManager().createQuery(query);
        q.setParameter("id", id);       
        
        try{
            return (List<Evento>) q.getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    
}
