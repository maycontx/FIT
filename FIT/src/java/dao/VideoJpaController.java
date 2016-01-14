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
import model.Publicacao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Video;

/**
 *
 * @author asdfrofl
 */
public class VideoJpaController implements Serializable {

    public VideoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Video video) {
        if (video.getPublicacaoList() == null) {
            video.setPublicacaoList(new ArrayList<Publicacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Publicacao> attachedPublicacaoList = new ArrayList<Publicacao>();
            for (Publicacao publicacaoListPublicacaoToAttach : video.getPublicacaoList()) {
                publicacaoListPublicacaoToAttach = em.getReference(publicacaoListPublicacaoToAttach.getClass(), publicacaoListPublicacaoToAttach.getIdpublicacao());
                attachedPublicacaoList.add(publicacaoListPublicacaoToAttach);
            }
            video.setPublicacaoList(attachedPublicacaoList);
            em.persist(video);
            for (Publicacao publicacaoListPublicacao : video.getPublicacaoList()) {
                Video oldIdvideoOfPublicacaoListPublicacao = publicacaoListPublicacao.getIdvideo();
                publicacaoListPublicacao.setIdvideo(video);
                publicacaoListPublicacao = em.merge(publicacaoListPublicacao);
                if (oldIdvideoOfPublicacaoListPublicacao != null) {
                    oldIdvideoOfPublicacaoListPublicacao.getPublicacaoList().remove(publicacaoListPublicacao);
                    oldIdvideoOfPublicacaoListPublicacao = em.merge(oldIdvideoOfPublicacaoListPublicacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Video video) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Video persistentVideo = em.find(Video.class, video.getIdvideo());
            List<Publicacao> publicacaoListOld = persistentVideo.getPublicacaoList();
            List<Publicacao> publicacaoListNew = video.getPublicacaoList();
            List<Publicacao> attachedPublicacaoListNew = new ArrayList<Publicacao>();
            for (Publicacao publicacaoListNewPublicacaoToAttach : publicacaoListNew) {
                publicacaoListNewPublicacaoToAttach = em.getReference(publicacaoListNewPublicacaoToAttach.getClass(), publicacaoListNewPublicacaoToAttach.getIdpublicacao());
                attachedPublicacaoListNew.add(publicacaoListNewPublicacaoToAttach);
            }
            publicacaoListNew = attachedPublicacaoListNew;
            video.setPublicacaoList(publicacaoListNew);
            video = em.merge(video);
            for (Publicacao publicacaoListOldPublicacao : publicacaoListOld) {
                if (!publicacaoListNew.contains(publicacaoListOldPublicacao)) {
                    publicacaoListOldPublicacao.setIdvideo(null);
                    publicacaoListOldPublicacao = em.merge(publicacaoListOldPublicacao);
                }
            }
            for (Publicacao publicacaoListNewPublicacao : publicacaoListNew) {
                if (!publicacaoListOld.contains(publicacaoListNewPublicacao)) {
                    Video oldIdvideoOfPublicacaoListNewPublicacao = publicacaoListNewPublicacao.getIdvideo();
                    publicacaoListNewPublicacao.setIdvideo(video);
                    publicacaoListNewPublicacao = em.merge(publicacaoListNewPublicacao);
                    if (oldIdvideoOfPublicacaoListNewPublicacao != null && !oldIdvideoOfPublicacaoListNewPublicacao.equals(video)) {
                        oldIdvideoOfPublicacaoListNewPublicacao.getPublicacaoList().remove(publicacaoListNewPublicacao);
                        oldIdvideoOfPublicacaoListNewPublicacao = em.merge(oldIdvideoOfPublicacaoListNewPublicacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = video.getIdvideo();
                if (findVideo(id) == null) {
                    throw new NonexistentEntityException("The video with id " + id + " no longer exists.");
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
            Video video;
            try {
                video = em.getReference(Video.class, id);
                video.getIdvideo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The video with id " + id + " no longer exists.", enfe);
            }
            List<Publicacao> publicacaoList = video.getPublicacaoList();
            for (Publicacao publicacaoListPublicacao : publicacaoList) {
                publicacaoListPublicacao.setIdvideo(null);
                publicacaoListPublicacao = em.merge(publicacaoListPublicacao);
            }
            em.remove(video);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Video> findVideoEntities() {
        return findVideoEntities(true, -1, -1);
    }

    public List<Video> findVideoEntities(int maxResults, int firstResult) {
        return findVideoEntities(false, maxResults, firstResult);
    }

    private List<Video> findVideoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Video.class));
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

    public Video findVideo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Video.class, id);
        } finally {
            em.close();
        }
    }

    public int getVideoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Video> rt = cq.from(Video.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
