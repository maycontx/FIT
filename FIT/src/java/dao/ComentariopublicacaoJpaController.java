/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Usuario;
import model.Publicacao;
import model.Comentariopublicacao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Evento;

/**
 *
 * @author asdfrofl
 */
public class ComentariopublicacaoJpaController implements Serializable {

    public ComentariopublicacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comentariopublicacao comentariopublicacao) {
        if (comentariopublicacao.getComentariopublicacaoList() == null) {
            comentariopublicacao.setComentariopublicacaoList(new ArrayList<Comentariopublicacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = comentariopublicacao.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                comentariopublicacao.setIdusuario(idusuario);
            }
            Publicacao idpublicacao = comentariopublicacao.getIdpublicacao();
            if (idpublicacao != null) {
                idpublicacao = em.getReference(idpublicacao.getClass(), idpublicacao.getIdpublicacao());
                comentariopublicacao.setIdpublicacao(idpublicacao);
            }
            Comentariopublicacao idpai = comentariopublicacao.getIdpai();
            if (idpai != null) {
                idpai = em.getReference(idpai.getClass(), idpai.getIdcomentariopublicacao());
                comentariopublicacao.setIdpai(idpai);
            }
            List<Comentariopublicacao> attachedComentariopublicacaoList = new ArrayList<Comentariopublicacao>();
            for (Comentariopublicacao comentariopublicacaoListComentariopublicacaoToAttach : comentariopublicacao.getComentariopublicacaoList()) {
                comentariopublicacaoListComentariopublicacaoToAttach = em.getReference(comentariopublicacaoListComentariopublicacaoToAttach.getClass(), comentariopublicacaoListComentariopublicacaoToAttach.getIdcomentariopublicacao());
                attachedComentariopublicacaoList.add(comentariopublicacaoListComentariopublicacaoToAttach);
            }
            comentariopublicacao.setComentariopublicacaoList(attachedComentariopublicacaoList);
            em.persist(comentariopublicacao);
            if (idusuario != null) {
                idusuario.getComentariopublicacaoList().add(comentariopublicacao);
                idusuario = em.merge(idusuario);
            }
            if (idpublicacao != null) {
                idpublicacao.getComentariopublicacaoList().add(comentariopublicacao);
                idpublicacao = em.merge(idpublicacao);
            }
            if (idpai != null) {
                idpai.getComentariopublicacaoList().add(comentariopublicacao);
                idpai = em.merge(idpai);
            }
            for (Comentariopublicacao comentariopublicacaoListComentariopublicacao : comentariopublicacao.getComentariopublicacaoList()) {
                Comentariopublicacao oldIdpaiOfComentariopublicacaoListComentariopublicacao = comentariopublicacaoListComentariopublicacao.getIdpai();
                comentariopublicacaoListComentariopublicacao.setIdpai(comentariopublicacao);
                comentariopublicacaoListComentariopublicacao = em.merge(comentariopublicacaoListComentariopublicacao);
                if (oldIdpaiOfComentariopublicacaoListComentariopublicacao != null) {
                    oldIdpaiOfComentariopublicacaoListComentariopublicacao.getComentariopublicacaoList().remove(comentariopublicacaoListComentariopublicacao);
                    oldIdpaiOfComentariopublicacaoListComentariopublicacao = em.merge(oldIdpaiOfComentariopublicacaoListComentariopublicacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comentariopublicacao comentariopublicacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comentariopublicacao persistentComentariopublicacao = em.find(Comentariopublicacao.class, comentariopublicacao.getIdcomentariopublicacao());
            Usuario idusuarioOld = persistentComentariopublicacao.getIdusuario();
            Usuario idusuarioNew = comentariopublicacao.getIdusuario();
            Publicacao idpublicacaoOld = persistentComentariopublicacao.getIdpublicacao();
            Publicacao idpublicacaoNew = comentariopublicacao.getIdpublicacao();
            Comentariopublicacao idpaiOld = persistentComentariopublicacao.getIdpai();
            Comentariopublicacao idpaiNew = comentariopublicacao.getIdpai();
            List<Comentariopublicacao> comentariopublicacaoListOld = persistentComentariopublicacao.getComentariopublicacaoList();
            List<Comentariopublicacao> comentariopublicacaoListNew = comentariopublicacao.getComentariopublicacaoList();
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                comentariopublicacao.setIdusuario(idusuarioNew);
            }
            if (idpublicacaoNew != null) {
                idpublicacaoNew = em.getReference(idpublicacaoNew.getClass(), idpublicacaoNew.getIdpublicacao());
                comentariopublicacao.setIdpublicacao(idpublicacaoNew);
            }
            if (idpaiNew != null) {
                idpaiNew = em.getReference(idpaiNew.getClass(), idpaiNew.getIdcomentariopublicacao());
                comentariopublicacao.setIdpai(idpaiNew);
            }
            List<Comentariopublicacao> attachedComentariopublicacaoListNew = new ArrayList<Comentariopublicacao>();
            for (Comentariopublicacao comentariopublicacaoListNewComentariopublicacaoToAttach : comentariopublicacaoListNew) {
                comentariopublicacaoListNewComentariopublicacaoToAttach = em.getReference(comentariopublicacaoListNewComentariopublicacaoToAttach.getClass(), comentariopublicacaoListNewComentariopublicacaoToAttach.getIdcomentariopublicacao());
                attachedComentariopublicacaoListNew.add(comentariopublicacaoListNewComentariopublicacaoToAttach);
            }
            comentariopublicacaoListNew = attachedComentariopublicacaoListNew;
            comentariopublicacao.setComentariopublicacaoList(comentariopublicacaoListNew);
            comentariopublicacao = em.merge(comentariopublicacao);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getComentariopublicacaoList().remove(comentariopublicacao);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getComentariopublicacaoList().add(comentariopublicacao);
                idusuarioNew = em.merge(idusuarioNew);
            }
            if (idpublicacaoOld != null && !idpublicacaoOld.equals(idpublicacaoNew)) {
                idpublicacaoOld.getComentariopublicacaoList().remove(comentariopublicacao);
                idpublicacaoOld = em.merge(idpublicacaoOld);
            }
            if (idpublicacaoNew != null && !idpublicacaoNew.equals(idpublicacaoOld)) {
                idpublicacaoNew.getComentariopublicacaoList().add(comentariopublicacao);
                idpublicacaoNew = em.merge(idpublicacaoNew);
            }
            if (idpaiOld != null && !idpaiOld.equals(idpaiNew)) {
                idpaiOld.getComentariopublicacaoList().remove(comentariopublicacao);
                idpaiOld = em.merge(idpaiOld);
            }
            if (idpaiNew != null && !idpaiNew.equals(idpaiOld)) {
                idpaiNew.getComentariopublicacaoList().add(comentariopublicacao);
                idpaiNew = em.merge(idpaiNew);
            }
            for (Comentariopublicacao comentariopublicacaoListOldComentariopublicacao : comentariopublicacaoListOld) {
                if (!comentariopublicacaoListNew.contains(comentariopublicacaoListOldComentariopublicacao)) {
                    comentariopublicacaoListOldComentariopublicacao.setIdpai(null);
                    comentariopublicacaoListOldComentariopublicacao = em.merge(comentariopublicacaoListOldComentariopublicacao);
                }
            }
            for (Comentariopublicacao comentariopublicacaoListNewComentariopublicacao : comentariopublicacaoListNew) {
                if (!comentariopublicacaoListOld.contains(comentariopublicacaoListNewComentariopublicacao)) {
                    Comentariopublicacao oldIdpaiOfComentariopublicacaoListNewComentariopublicacao = comentariopublicacaoListNewComentariopublicacao.getIdpai();
                    comentariopublicacaoListNewComentariopublicacao.setIdpai(comentariopublicacao);
                    comentariopublicacaoListNewComentariopublicacao = em.merge(comentariopublicacaoListNewComentariopublicacao);
                    if (oldIdpaiOfComentariopublicacaoListNewComentariopublicacao != null && !oldIdpaiOfComentariopublicacaoListNewComentariopublicacao.equals(comentariopublicacao)) {
                        oldIdpaiOfComentariopublicacaoListNewComentariopublicacao.getComentariopublicacaoList().remove(comentariopublicacaoListNewComentariopublicacao);
                        oldIdpaiOfComentariopublicacaoListNewComentariopublicacao = em.merge(oldIdpaiOfComentariopublicacaoListNewComentariopublicacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comentariopublicacao.getIdcomentariopublicacao();
                if (findComentariopublicacao(id) == null) {
                    throw new NonexistentEntityException("The comentariopublicacao with id " + id + " no longer exists.");
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
            Comentariopublicacao comentariopublicacao;
            try {
                comentariopublicacao = em.getReference(Comentariopublicacao.class, id);
                comentariopublicacao.getIdcomentariopublicacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comentariopublicacao with id " + id + " no longer exists.", enfe);
            }
            Usuario idusuario = comentariopublicacao.getIdusuario();
            if (idusuario != null) {
                idusuario.getComentariopublicacaoList().remove(comentariopublicacao);
                idusuario = em.merge(idusuario);
            }
            Publicacao idpublicacao = comentariopublicacao.getIdpublicacao();
            if (idpublicacao != null) {
                idpublicacao.getComentariopublicacaoList().remove(comentariopublicacao);
                idpublicacao = em.merge(idpublicacao);
            }
            Comentariopublicacao idpai = comentariopublicacao.getIdpai();
            if (idpai != null) {
                idpai.getComentariopublicacaoList().remove(comentariopublicacao);
                idpai = em.merge(idpai);
            }
            List<Comentariopublicacao> comentariopublicacaoList = comentariopublicacao.getComentariopublicacaoList();
            for (Comentariopublicacao comentariopublicacaoListComentariopublicacao : comentariopublicacaoList) {
                comentariopublicacaoListComentariopublicacao.setIdpai(null);
                comentariopublicacaoListComentariopublicacao = em.merge(comentariopublicacaoListComentariopublicacao);
            }
            em.remove(comentariopublicacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comentariopublicacao> findComentariopublicacaoEntities() {
        return findComentariopublicacaoEntities(true, -1, -1);
    }

    public List<Comentariopublicacao> findComentariopublicacaoEntities(int maxResults, int firstResult) {
        return findComentariopublicacaoEntities(false, maxResults, firstResult);
    }

    private List<Comentariopublicacao> findComentariopublicacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comentariopublicacao.class));
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

    public Comentariopublicacao findComentariopublicacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comentariopublicacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getComentariopublicacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comentariopublicacao> rt = cq.from(Comentariopublicacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Comentariopublicacao> findCommentByDad(Comentariopublicacao comment){
        
        String query = "SELECT c FROM Comentariopublicacao c WHERE c.idpai = :comment"; 
        
        Query q = getEntityManager().createQuery(query);
        q.setParameter("comment", comment);       
        
        try{
            return (List<Comentariopublicacao>) q.getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    
}
