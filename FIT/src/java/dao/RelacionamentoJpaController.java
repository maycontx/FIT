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
import model.Relacionamento;

/**
 *
 * @author asdfrofl
 */
public class RelacionamentoJpaController implements Serializable {

    public RelacionamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Relacionamento relacionamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Atleta idatleta = relacionamento.getIdatleta();
            if (idatleta != null) {
                idatleta = em.getReference(idatleta.getClass(), idatleta.getIdatleta());
                relacionamento.setIdatleta(idatleta);
            }
            Profissional idprofissional = relacionamento.getIdprofissional();
            if (idprofissional != null) {
                idprofissional = em.getReference(idprofissional.getClass(), idprofissional.getIdprofissional());
                relacionamento.setIdprofissional(idprofissional);
            }
            em.persist(relacionamento);
            if (idatleta != null) {
                idatleta.getRelacionamentoList().add(relacionamento);
                idatleta = em.merge(idatleta);
            }
            if (idprofissional != null) {
                idprofissional.getRelacionamentoList().add(relacionamento);
                idprofissional = em.merge(idprofissional);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Relacionamento relacionamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Relacionamento persistentRelacionamento = em.find(Relacionamento.class, relacionamento.getIdrelacionamento());
            Atleta idatletaOld = persistentRelacionamento.getIdatleta();
            Atleta idatletaNew = relacionamento.getIdatleta();
            Profissional idprofissionalOld = persistentRelacionamento.getIdprofissional();
            Profissional idprofissionalNew = relacionamento.getIdprofissional();
            if (idatletaNew != null) {
                idatletaNew = em.getReference(idatletaNew.getClass(), idatletaNew.getIdatleta());
                relacionamento.setIdatleta(idatletaNew);
            }
            if (idprofissionalNew != null) {
                idprofissionalNew = em.getReference(idprofissionalNew.getClass(), idprofissionalNew.getIdprofissional());
                relacionamento.setIdprofissional(idprofissionalNew);
            }
            relacionamento = em.merge(relacionamento);
            if (idatletaOld != null && !idatletaOld.equals(idatletaNew)) {
                idatletaOld.getRelacionamentoList().remove(relacionamento);
                idatletaOld = em.merge(idatletaOld);
            }
            if (idatletaNew != null && !idatletaNew.equals(idatletaOld)) {
                idatletaNew.getRelacionamentoList().add(relacionamento);
                idatletaNew = em.merge(idatletaNew);
            }
            if (idprofissionalOld != null && !idprofissionalOld.equals(idprofissionalNew)) {
                idprofissionalOld.getRelacionamentoList().remove(relacionamento);
                idprofissionalOld = em.merge(idprofissionalOld);
            }
            if (idprofissionalNew != null && !idprofissionalNew.equals(idprofissionalOld)) {
                idprofissionalNew.getRelacionamentoList().add(relacionamento);
                idprofissionalNew = em.merge(idprofissionalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = relacionamento.getIdrelacionamento();
                if (findRelacionamento(id) == null) {
                    throw new NonexistentEntityException("The relacionamento with id " + id + " no longer exists.");
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
            Relacionamento relacionamento;
            try {
                relacionamento = em.getReference(Relacionamento.class, id);
                relacionamento.getIdrelacionamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relacionamento with id " + id + " no longer exists.", enfe);
            }
            Atleta idatleta = relacionamento.getIdatleta();
            if (idatleta != null) {
                idatleta.getRelacionamentoList().remove(relacionamento);
                idatleta = em.merge(idatleta);
            }
            Profissional idprofissional = relacionamento.getIdprofissional();
            if (idprofissional != null) {
                idprofissional.getRelacionamentoList().remove(relacionamento);
                idprofissional = em.merge(idprofissional);
            }
            em.remove(relacionamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Relacionamento> findRelacionamentoEntities() {
        return findRelacionamentoEntities(true, -1, -1);
    }

    public List<Relacionamento> findRelacionamentoEntities(int maxResults, int firstResult) {
        return findRelacionamentoEntities(false, maxResults, firstResult);
    }

    private List<Relacionamento> findRelacionamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Relacionamento.class));
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

    public Relacionamento findRelacionamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Relacionamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelacionamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Relacionamento> rt = cq.from(Relacionamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
