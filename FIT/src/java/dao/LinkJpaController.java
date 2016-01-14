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
import model.Link;

/**
 *
 * @author asdfrofl
 */
public class LinkJpaController implements Serializable {

    public LinkJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Link link) {
        if (link.getPublicacaoList() == null) {
            link.setPublicacaoList(new ArrayList<Publicacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Publicacao> attachedPublicacaoList = new ArrayList<Publicacao>();
            for (Publicacao publicacaoListPublicacaoToAttach : link.getPublicacaoList()) {
                publicacaoListPublicacaoToAttach = em.getReference(publicacaoListPublicacaoToAttach.getClass(), publicacaoListPublicacaoToAttach.getIdpublicacao());
                attachedPublicacaoList.add(publicacaoListPublicacaoToAttach);
            }
            link.setPublicacaoList(attachedPublicacaoList);
            em.persist(link);
            for (Publicacao publicacaoListPublicacao : link.getPublicacaoList()) {
                Link oldIdlinkOfPublicacaoListPublicacao = publicacaoListPublicacao.getIdlink();
                publicacaoListPublicacao.setIdlink(link);
                publicacaoListPublicacao = em.merge(publicacaoListPublicacao);
                if (oldIdlinkOfPublicacaoListPublicacao != null) {
                    oldIdlinkOfPublicacaoListPublicacao.getPublicacaoList().remove(publicacaoListPublicacao);
                    oldIdlinkOfPublicacaoListPublicacao = em.merge(oldIdlinkOfPublicacaoListPublicacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Link link) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Link persistentLink = em.find(Link.class, link.getIdlink());
            List<Publicacao> publicacaoListOld = persistentLink.getPublicacaoList();
            List<Publicacao> publicacaoListNew = link.getPublicacaoList();
            List<Publicacao> attachedPublicacaoListNew = new ArrayList<Publicacao>();
            for (Publicacao publicacaoListNewPublicacaoToAttach : publicacaoListNew) {
                publicacaoListNewPublicacaoToAttach = em.getReference(publicacaoListNewPublicacaoToAttach.getClass(), publicacaoListNewPublicacaoToAttach.getIdpublicacao());
                attachedPublicacaoListNew.add(publicacaoListNewPublicacaoToAttach);
            }
            publicacaoListNew = attachedPublicacaoListNew;
            link.setPublicacaoList(publicacaoListNew);
            link = em.merge(link);
            for (Publicacao publicacaoListOldPublicacao : publicacaoListOld) {
                if (!publicacaoListNew.contains(publicacaoListOldPublicacao)) {
                    publicacaoListOldPublicacao.setIdlink(null);
                    publicacaoListOldPublicacao = em.merge(publicacaoListOldPublicacao);
                }
            }
            for (Publicacao publicacaoListNewPublicacao : publicacaoListNew) {
                if (!publicacaoListOld.contains(publicacaoListNewPublicacao)) {
                    Link oldIdlinkOfPublicacaoListNewPublicacao = publicacaoListNewPublicacao.getIdlink();
                    publicacaoListNewPublicacao.setIdlink(link);
                    publicacaoListNewPublicacao = em.merge(publicacaoListNewPublicacao);
                    if (oldIdlinkOfPublicacaoListNewPublicacao != null && !oldIdlinkOfPublicacaoListNewPublicacao.equals(link)) {
                        oldIdlinkOfPublicacaoListNewPublicacao.getPublicacaoList().remove(publicacaoListNewPublicacao);
                        oldIdlinkOfPublicacaoListNewPublicacao = em.merge(oldIdlinkOfPublicacaoListNewPublicacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = link.getIdlink();
                if (findLink(id) == null) {
                    throw new NonexistentEntityException("The link with id " + id + " no longer exists.");
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
            Link link;
            try {
                link = em.getReference(Link.class, id);
                link.getIdlink();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The link with id " + id + " no longer exists.", enfe);
            }
            List<Publicacao> publicacaoList = link.getPublicacaoList();
            for (Publicacao publicacaoListPublicacao : publicacaoList) {
                publicacaoListPublicacao.setIdlink(null);
                publicacaoListPublicacao = em.merge(publicacaoListPublicacao);
            }
            em.remove(link);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Link> findLinkEntities() {
        return findLinkEntities(true, -1, -1);
    }

    public List<Link> findLinkEntities(int maxResults, int firstResult) {
        return findLinkEntities(false, maxResults, firstResult);
    }

    private List<Link> findLinkEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Link.class));
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

    public Link findLink(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Link.class, id);
        } finally {
            em.close();
        }
    }

    public int getLinkCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Link> rt = cq.from(Link.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
