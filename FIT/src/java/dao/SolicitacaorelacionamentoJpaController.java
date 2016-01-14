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
import model.Profissional;
import model.Solicitacaorelacionamento;

/**
 *
 * @author asdfrofl
 */
public class SolicitacaorelacionamentoJpaController implements Serializable {

    public SolicitacaorelacionamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicitacaorelacionamento solicitacaorelacionamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Atleta idatleta = solicitacaorelacionamento.getIdatleta();
            if (idatleta != null) {
                idatleta = em.getReference(idatleta.getClass(), idatleta.getIdatleta());
                solicitacaorelacionamento.setIdatleta(idatleta);
            }
            Profissional idprofissional = solicitacaorelacionamento.getIdprofissional();
            if (idprofissional != null) {
                idprofissional = em.getReference(idprofissional.getClass(), idprofissional.getIdprofissional());
                solicitacaorelacionamento.setIdprofissional(idprofissional);
            }
            em.persist(solicitacaorelacionamento);
            if (idatleta != null) {
                idatleta.getSolicitacaorelacionamentoList().add(solicitacaorelacionamento);
                idatleta = em.merge(idatleta);
            }
            if (idprofissional != null) {
                idprofissional.getSolicitacaorelacionamentoList().add(solicitacaorelacionamento);
                idprofissional = em.merge(idprofissional);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitacaorelacionamento solicitacaorelacionamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitacaorelacionamento persistentSolicitacaorelacionamento = em.find(Solicitacaorelacionamento.class, solicitacaorelacionamento.getIdsolicitacao());
            Atleta idatletaOld = persistentSolicitacaorelacionamento.getIdatleta();
            Atleta idatletaNew = solicitacaorelacionamento.getIdatleta();
            Profissional idprofissionalOld = persistentSolicitacaorelacionamento.getIdprofissional();
            Profissional idprofissionalNew = solicitacaorelacionamento.getIdprofissional();
            if (idatletaNew != null) {
                idatletaNew = em.getReference(idatletaNew.getClass(), idatletaNew.getIdatleta());
                solicitacaorelacionamento.setIdatleta(idatletaNew);
            }
            if (idprofissionalNew != null) {
                idprofissionalNew = em.getReference(idprofissionalNew.getClass(), idprofissionalNew.getIdprofissional());
                solicitacaorelacionamento.setIdprofissional(idprofissionalNew);
            }
            solicitacaorelacionamento = em.merge(solicitacaorelacionamento);
            if (idatletaOld != null && !idatletaOld.equals(idatletaNew)) {
                idatletaOld.getSolicitacaorelacionamentoList().remove(solicitacaorelacionamento);
                idatletaOld = em.merge(idatletaOld);
            }
            if (idatletaNew != null && !idatletaNew.equals(idatletaOld)) {
                idatletaNew.getSolicitacaorelacionamentoList().add(solicitacaorelacionamento);
                idatletaNew = em.merge(idatletaNew);
            }
            if (idprofissionalOld != null && !idprofissionalOld.equals(idprofissionalNew)) {
                idprofissionalOld.getSolicitacaorelacionamentoList().remove(solicitacaorelacionamento);
                idprofissionalOld = em.merge(idprofissionalOld);
            }
            if (idprofissionalNew != null && !idprofissionalNew.equals(idprofissionalOld)) {
                idprofissionalNew.getSolicitacaorelacionamentoList().add(solicitacaorelacionamento);
                idprofissionalNew = em.merge(idprofissionalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitacaorelacionamento.getIdsolicitacao();
                if (findSolicitacaorelacionamento(id) == null) {
                    throw new NonexistentEntityException("The solicitacaorelacionamento with id " + id + " no longer exists.");
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
            Solicitacaorelacionamento solicitacaorelacionamento;
            try {
                solicitacaorelacionamento = em.getReference(Solicitacaorelacionamento.class, id);
                solicitacaorelacionamento.getIdsolicitacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitacaorelacionamento with id " + id + " no longer exists.", enfe);
            }
            Atleta idatleta = solicitacaorelacionamento.getIdatleta();
            if (idatleta != null) {
                idatleta.getSolicitacaorelacionamentoList().remove(solicitacaorelacionamento);
                idatleta = em.merge(idatleta);
            }
            Profissional idprofissional = solicitacaorelacionamento.getIdprofissional();
            if (idprofissional != null) {
                idprofissional.getSolicitacaorelacionamentoList().remove(solicitacaorelacionamento);
                idprofissional = em.merge(idprofissional);
            }
            em.remove(solicitacaorelacionamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitacaorelacionamento> findSolicitacaorelacionamentoEntities() {
        return findSolicitacaorelacionamentoEntities(true, -1, -1);
    }

    public List<Solicitacaorelacionamento> findSolicitacaorelacionamentoEntities(int maxResults, int firstResult) {
        return findSolicitacaorelacionamentoEntities(false, maxResults, firstResult);
    }

    private List<Solicitacaorelacionamento> findSolicitacaorelacionamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Solicitacaorelacionamento.class));
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

    public Solicitacaorelacionamento findSolicitacaorelacionamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitacaorelacionamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitacaorelacionamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Solicitacaorelacionamento> rt = cq.from(Solicitacaorelacionamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
