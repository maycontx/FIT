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
import model.Compartilhamento;
import model.Usuario;
import model.Publicacao;
import model.Texto;

/**
 *
 * @author asdfrofl
 */
public class CompartilhamentoJpaController implements Serializable {

    public CompartilhamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compartilhamento compartilhamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = compartilhamento.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                compartilhamento.setIdusuario(idusuario);
            }
            Publicacao idpublicacao = compartilhamento.getIdpublicacao();
            if (idpublicacao != null) {
                idpublicacao = em.getReference(idpublicacao.getClass(), idpublicacao.getIdpublicacao());
                compartilhamento.setIdpublicacao(idpublicacao);
            }
            Texto idtexto = compartilhamento.getIdtexto();
            if (idtexto != null) {
                idtexto = em.getReference(idtexto.getClass(), idtexto.getIdtexto());
                compartilhamento.setIdtexto(idtexto);
            }
            em.persist(compartilhamento);
            if (idusuario != null) {
                idusuario.getCompartilhamentoList().add(compartilhamento);
                idusuario = em.merge(idusuario);
            }
            if (idpublicacao != null) {
                idpublicacao.getCompartilhamentoList().add(compartilhamento);
                idpublicacao = em.merge(idpublicacao);
            }
            if (idtexto != null) {
                idtexto.getCompartilhamentoList().add(compartilhamento);
                idtexto = em.merge(idtexto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compartilhamento compartilhamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compartilhamento persistentCompartilhamento = em.find(Compartilhamento.class, compartilhamento.getIdcompartilhamento());
            Usuario idusuarioOld = persistentCompartilhamento.getIdusuario();
            Usuario idusuarioNew = compartilhamento.getIdusuario();
            Publicacao idpublicacaoOld = persistentCompartilhamento.getIdpublicacao();
            Publicacao idpublicacaoNew = compartilhamento.getIdpublicacao();
            Texto idtextoOld = persistentCompartilhamento.getIdtexto();
            Texto idtextoNew = compartilhamento.getIdtexto();
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                compartilhamento.setIdusuario(idusuarioNew);
            }
            if (idpublicacaoNew != null) {
                idpublicacaoNew = em.getReference(idpublicacaoNew.getClass(), idpublicacaoNew.getIdpublicacao());
                compartilhamento.setIdpublicacao(idpublicacaoNew);
            }
            if (idtextoNew != null) {
                idtextoNew = em.getReference(idtextoNew.getClass(), idtextoNew.getIdtexto());
                compartilhamento.setIdtexto(idtextoNew);
            }
            compartilhamento = em.merge(compartilhamento);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getCompartilhamentoList().remove(compartilhamento);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getCompartilhamentoList().add(compartilhamento);
                idusuarioNew = em.merge(idusuarioNew);
            }
            if (idpublicacaoOld != null && !idpublicacaoOld.equals(idpublicacaoNew)) {
                idpublicacaoOld.getCompartilhamentoList().remove(compartilhamento);
                idpublicacaoOld = em.merge(idpublicacaoOld);
            }
            if (idpublicacaoNew != null && !idpublicacaoNew.equals(idpublicacaoOld)) {
                idpublicacaoNew.getCompartilhamentoList().add(compartilhamento);
                idpublicacaoNew = em.merge(idpublicacaoNew);
            }
            if (idtextoOld != null && !idtextoOld.equals(idtextoNew)) {
                idtextoOld.getCompartilhamentoList().remove(compartilhamento);
                idtextoOld = em.merge(idtextoOld);
            }
            if (idtextoNew != null && !idtextoNew.equals(idtextoOld)) {
                idtextoNew.getCompartilhamentoList().add(compartilhamento);
                idtextoNew = em.merge(idtextoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = compartilhamento.getIdcompartilhamento();
                if (findCompartilhamento(id) == null) {
                    throw new NonexistentEntityException("The compartilhamento with id " + id + " no longer exists.");
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
            Compartilhamento compartilhamento;
            try {
                compartilhamento = em.getReference(Compartilhamento.class, id);
                compartilhamento.getIdcompartilhamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compartilhamento with id " + id + " no longer exists.", enfe);
            }
            Usuario idusuario = compartilhamento.getIdusuario();
            if (idusuario != null) {
                idusuario.getCompartilhamentoList().remove(compartilhamento);
                idusuario = em.merge(idusuario);
            }
            Publicacao idpublicacao = compartilhamento.getIdpublicacao();
            if (idpublicacao != null) {
                idpublicacao.getCompartilhamentoList().remove(compartilhamento);
                idpublicacao = em.merge(idpublicacao);
            }
            Texto idtexto = compartilhamento.getIdtexto();
            if (idtexto != null) {
                idtexto.getCompartilhamentoList().remove(compartilhamento);
                idtexto = em.merge(idtexto);
            }
            em.remove(compartilhamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compartilhamento> findCompartilhamentoEntities() {
        return findCompartilhamentoEntities(true, -1, -1);
    }

    public List<Compartilhamento> findCompartilhamentoEntities(int maxResults, int firstResult) {
        return findCompartilhamentoEntities(false, maxResults, firstResult);
    }

    private List<Compartilhamento> findCompartilhamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compartilhamento.class));
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

    public Compartilhamento findCompartilhamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compartilhamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompartilhamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compartilhamento> rt = cq.from(Compartilhamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Compartilhamento> findSharesByPublication(int idpublicacao){
        
        String query = "SELECT c FROM Compartilhamento c WHERE "
                + "  c.idpublicacao.idpublicacao = :idpublicacao"; 
        
        Query q = getEntityManager().createQuery(query);
        q.setParameter("idpublicacao", idpublicacao);       
        
        try{
            return (List<Compartilhamento>) q.getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    
}
