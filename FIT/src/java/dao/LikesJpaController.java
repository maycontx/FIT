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
import model.Likes;
import model.Usuario;
import model.Publicacao;

/**
 *
 * @author asdfrofl
 */
public class LikesJpaController implements Serializable {

    public LikesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Likes likes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = likes.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                likes.setIdusuario(idusuario);
            }
            Publicacao idpublicacao = likes.getIdpublicacao();
            if (idpublicacao != null) {
                idpublicacao = em.getReference(idpublicacao.getClass(), idpublicacao.getIdpublicacao());
                likes.setIdpublicacao(idpublicacao);
            }
            em.persist(likes);
            if (idusuario != null) {
                idusuario.getLikesList().add(likes);
                idusuario = em.merge(idusuario);
            }
            if (idpublicacao != null) {
                idpublicacao.getLikesList().add(likes);
                idpublicacao = em.merge(idpublicacao);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Likes likes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Likes persistentLikes = em.find(Likes.class, likes.getIdlikes());
            Usuario idusuarioOld = persistentLikes.getIdusuario();
            Usuario idusuarioNew = likes.getIdusuario();
            Publicacao idpublicacaoOld = persistentLikes.getIdpublicacao();
            Publicacao idpublicacaoNew = likes.getIdpublicacao();
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                likes.setIdusuario(idusuarioNew);
            }
            if (idpublicacaoNew != null) {
                idpublicacaoNew = em.getReference(idpublicacaoNew.getClass(), idpublicacaoNew.getIdpublicacao());
                likes.setIdpublicacao(idpublicacaoNew);
            }
            likes = em.merge(likes);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getLikesList().remove(likes);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getLikesList().add(likes);
                idusuarioNew = em.merge(idusuarioNew);
            }
            if (idpublicacaoOld != null && !idpublicacaoOld.equals(idpublicacaoNew)) {
                idpublicacaoOld.getLikesList().remove(likes);
                idpublicacaoOld = em.merge(idpublicacaoOld);
            }
            if (idpublicacaoNew != null && !idpublicacaoNew.equals(idpublicacaoOld)) {
                idpublicacaoNew.getLikesList().add(likes);
                idpublicacaoNew = em.merge(idpublicacaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = likes.getIdlikes();
                if (findLikes(id) == null) {
                    throw new NonexistentEntityException("The likes with id " + id + " no longer exists.");
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
            Likes likes;
            try {
                likes = em.getReference(Likes.class, id);
                likes.getIdlikes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The likes with id " + id + " no longer exists.", enfe);
            }
            Usuario idusuario = likes.getIdusuario();
            if (idusuario != null) {
                idusuario.getLikesList().remove(likes);
                idusuario = em.merge(idusuario);
            }
            Publicacao idpublicacao = likes.getIdpublicacao();
            if (idpublicacao != null) {
                idpublicacao.getLikesList().remove(likes);
                idpublicacao = em.merge(idpublicacao);
            }
            em.remove(likes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Likes> findLikesEntities() {
        return findLikesEntities(true, -1, -1);
    }

    public List<Likes> findLikesEntities(int maxResults, int firstResult) {
        return findLikesEntities(false, maxResults, firstResult);
    }

    private List<Likes> findLikesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Likes.class));
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

    public Likes findLikes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Likes.class, id);
        } finally {
            em.close();
        }
    }

    public int getLikesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Likes> rt = cq.from(Likes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Likes> findLikesByPublication(int idpublicacao){
        
        String query = "SELECT l FROM Likes l WHERE "
                + "  l.idpublicacao.idpublicacao = :idpublicacao"; 
        
        Query q = getEntityManager().createQuery(query);
        q.setParameter("idpublicacao", idpublicacao);       
        
        try{
            return (List<Likes>) q.getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    
    // ENCONTRAR LIKE POR USUARIO E PUBLICACAO
    public Likes findLikesByUserAndPost(Publicacao post, Usuario user){
        
        String query = "SELECT l FROM Likes l WHERE "
                + "  l.idpublicacao = :idpublicacao AND l.idusuario = :iduser"; 
        
        Query q = getEntityManager().createQuery(query);
        
        q.setParameter("idpublicacao", post);
        q.setParameter("iduser", user); 
        
        try{
            return (Likes) q.getSingleResult();
        }catch(Exception ex){
            return null;
        }
    }
    
}
