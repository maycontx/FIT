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
import model.Imagem;

/**
 *
 * @author asdfrofl
 */
public class ImagemJpaController implements Serializable {

    public ImagemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Imagem imagem) {
        if (imagem.getPublicacaoList() == null) {
            imagem.setPublicacaoList(new ArrayList<Publicacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Publicacao> attachedPublicacaoList = new ArrayList<Publicacao>();
            for (Publicacao publicacaoListPublicacaoToAttach : imagem.getPublicacaoList()) {
                publicacaoListPublicacaoToAttach = em.getReference(publicacaoListPublicacaoToAttach.getClass(), publicacaoListPublicacaoToAttach.getIdpublicacao());
                attachedPublicacaoList.add(publicacaoListPublicacaoToAttach);
            }
            imagem.setPublicacaoList(attachedPublicacaoList);
            em.persist(imagem);
            for (Publicacao publicacaoListPublicacao : imagem.getPublicacaoList()) {
                Imagem oldIdimagemOfPublicacaoListPublicacao = publicacaoListPublicacao.getIdimagem();
                publicacaoListPublicacao.setIdimagem(imagem);
                publicacaoListPublicacao = em.merge(publicacaoListPublicacao);
                if (oldIdimagemOfPublicacaoListPublicacao != null) {
                    oldIdimagemOfPublicacaoListPublicacao.getPublicacaoList().remove(publicacaoListPublicacao);
                    oldIdimagemOfPublicacaoListPublicacao = em.merge(oldIdimagemOfPublicacaoListPublicacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Imagem imagem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Imagem persistentImagem = em.find(Imagem.class, imagem.getIdimagem());
            List<Publicacao> publicacaoListOld = persistentImagem.getPublicacaoList();
            List<Publicacao> publicacaoListNew = imagem.getPublicacaoList();
            List<Publicacao> attachedPublicacaoListNew = new ArrayList<Publicacao>();
            for (Publicacao publicacaoListNewPublicacaoToAttach : publicacaoListNew) {
                publicacaoListNewPublicacaoToAttach = em.getReference(publicacaoListNewPublicacaoToAttach.getClass(), publicacaoListNewPublicacaoToAttach.getIdpublicacao());
                attachedPublicacaoListNew.add(publicacaoListNewPublicacaoToAttach);
            }
            publicacaoListNew = attachedPublicacaoListNew;
            imagem.setPublicacaoList(publicacaoListNew);
            imagem = em.merge(imagem);
            for (Publicacao publicacaoListOldPublicacao : publicacaoListOld) {
                if (!publicacaoListNew.contains(publicacaoListOldPublicacao)) {
                    publicacaoListOldPublicacao.setIdimagem(null);
                    publicacaoListOldPublicacao = em.merge(publicacaoListOldPublicacao);
                }
            }
            for (Publicacao publicacaoListNewPublicacao : publicacaoListNew) {
                if (!publicacaoListOld.contains(publicacaoListNewPublicacao)) {
                    Imagem oldIdimagemOfPublicacaoListNewPublicacao = publicacaoListNewPublicacao.getIdimagem();
                    publicacaoListNewPublicacao.setIdimagem(imagem);
                    publicacaoListNewPublicacao = em.merge(publicacaoListNewPublicacao);
                    if (oldIdimagemOfPublicacaoListNewPublicacao != null && !oldIdimagemOfPublicacaoListNewPublicacao.equals(imagem)) {
                        oldIdimagemOfPublicacaoListNewPublicacao.getPublicacaoList().remove(publicacaoListNewPublicacao);
                        oldIdimagemOfPublicacaoListNewPublicacao = em.merge(oldIdimagemOfPublicacaoListNewPublicacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = imagem.getIdimagem();
                if (findImagem(id) == null) {
                    throw new NonexistentEntityException("The imagem with id " + id + " no longer exists.");
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
            Imagem imagem;
            try {
                imagem = em.getReference(Imagem.class, id);
                imagem.getIdimagem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The imagem with id " + id + " no longer exists.", enfe);
            }
            List<Publicacao> publicacaoList = imagem.getPublicacaoList();
            for (Publicacao publicacaoListPublicacao : publicacaoList) {
                publicacaoListPublicacao.setIdimagem(null);
                publicacaoListPublicacao = em.merge(publicacaoListPublicacao);
            }
            em.remove(imagem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Imagem> findImagemEntities() {
        return findImagemEntities(true, -1, -1);
    }

    public List<Imagem> findImagemEntities(int maxResults, int firstResult) {
        return findImagemEntities(false, maxResults, firstResult);
    }

    private List<Imagem> findImagemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Imagem.class));
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

    public Imagem findImagem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Imagem.class, id);
        } finally {
            em.close();
        }
    }

    public int getImagemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Imagem> rt = cq.from(Imagem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
