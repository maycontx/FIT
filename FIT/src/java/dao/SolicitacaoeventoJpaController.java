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
import model.Evento;
import model.Solicitacaoevento;
import model.Usuario;

/**
 *
 * @author asdfrofl
 */
public class SolicitacaoeventoJpaController implements Serializable {

    public SolicitacaoeventoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicitacaoevento solicitacaoevento) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Evento idevento = solicitacaoevento.getIdevento();
            if (idevento != null) {
                idevento = em.getReference(idevento.getClass(), idevento.getIdevento());
                solicitacaoevento.setIdevento(idevento);
            }
            Usuario idusuario = solicitacaoevento.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                solicitacaoevento.setIdusuario(idusuario);
            }
            em.persist(solicitacaoevento);
            if (idevento != null) {
                idevento.getSolicitacaoeventoList().add(solicitacaoevento);
                idevento = em.merge(idevento);
            }
            if (idusuario != null) {
                idusuario.getSolicitacaoeventoList().add(solicitacaoevento);
                idusuario = em.merge(idusuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSolicitacaoevento(solicitacaoevento.getIdsolicitacao()) != null) {
                throw new PreexistingEntityException("Solicitacaoevento " + solicitacaoevento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitacaoevento solicitacaoevento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitacaoevento persistentSolicitacaoevento = em.find(Solicitacaoevento.class, solicitacaoevento.getIdsolicitacao());
            Evento ideventoOld = persistentSolicitacaoevento.getIdevento();
            Evento ideventoNew = solicitacaoevento.getIdevento();
            Usuario idusuarioOld = persistentSolicitacaoevento.getIdusuario();
            Usuario idusuarioNew = solicitacaoevento.getIdusuario();
            if (ideventoNew != null) {
                ideventoNew = em.getReference(ideventoNew.getClass(), ideventoNew.getIdevento());
                solicitacaoevento.setIdevento(ideventoNew);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                solicitacaoevento.setIdusuario(idusuarioNew);
            }
            solicitacaoevento = em.merge(solicitacaoevento);
            if (ideventoOld != null && !ideventoOld.equals(ideventoNew)) {
                ideventoOld.getSolicitacaoeventoList().remove(solicitacaoevento);
                ideventoOld = em.merge(ideventoOld);
            }
            if (ideventoNew != null && !ideventoNew.equals(ideventoOld)) {
                ideventoNew.getSolicitacaoeventoList().add(solicitacaoevento);
                ideventoNew = em.merge(ideventoNew);
            }
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getSolicitacaoeventoList().remove(solicitacaoevento);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getSolicitacaoeventoList().add(solicitacaoevento);
                idusuarioNew = em.merge(idusuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitacaoevento.getIdsolicitacao();
                if (findSolicitacaoevento(id) == null) {
                    throw new NonexistentEntityException("The solicitacaoevento with id " + id + " no longer exists.");
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
            Solicitacaoevento solicitacaoevento;
            try {
                solicitacaoevento = em.getReference(Solicitacaoevento.class, id);
                solicitacaoevento.getIdsolicitacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitacaoevento with id " + id + " no longer exists.", enfe);
            }
            Evento idevento = solicitacaoevento.getIdevento();
            if (idevento != null) {
                idevento.getSolicitacaoeventoList().remove(solicitacaoevento);
                idevento = em.merge(idevento);
            }
            Usuario idusuario = solicitacaoevento.getIdusuario();
            if (idusuario != null) {
                idusuario.getSolicitacaoeventoList().remove(solicitacaoevento);
                idusuario = em.merge(idusuario);
            }
            em.remove(solicitacaoevento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitacaoevento> findSolicitacaoeventoEntities() {
        return findSolicitacaoeventoEntities(true, -1, -1);
    }

    public List<Solicitacaoevento> findSolicitacaoeventoEntities(int maxResults, int firstResult) {
        return findSolicitacaoeventoEntities(false, maxResults, firstResult);
    }

    private List<Solicitacaoevento> findSolicitacaoeventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Solicitacaoevento.class));
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

    public Solicitacaoevento findSolicitacaoevento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitacaoevento.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitacaoeventoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Solicitacaoevento> rt = cq.from(Solicitacaoevento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
