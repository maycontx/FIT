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
import model.Artigo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Blog;

/**
 *
 * @author asdfrofl
 */
public class BlogJpaController implements Serializable {

    public BlogJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Blog blog) {
        if (blog.getArtigoList() == null) {
            blog.setArtigoList(new ArrayList<Artigo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario autor = blog.getAutor();
            if (autor != null) {
                autor = em.getReference(autor.getClass(), autor.getIdusuario());
                blog.setAutor(autor);
            }
            List<Artigo> attachedArtigoList = new ArrayList<Artigo>();
            for (Artigo artigoListArtigoToAttach : blog.getArtigoList()) {
                artigoListArtigoToAttach = em.getReference(artigoListArtigoToAttach.getClass(), artigoListArtigoToAttach.getIdartigo());
                attachedArtigoList.add(artigoListArtigoToAttach);
            }
            blog.setArtigoList(attachedArtigoList);
            em.persist(blog);
            if (autor != null) {
                autor.getBlogList().add(blog);
                autor = em.merge(autor);
            }
            for (Artigo artigoListArtigo : blog.getArtigoList()) {
                Blog oldIdblogOfArtigoListArtigo = artigoListArtigo.getIdblog();
                artigoListArtigo.setIdblog(blog);
                artigoListArtigo = em.merge(artigoListArtigo);
                if (oldIdblogOfArtigoListArtigo != null) {
                    oldIdblogOfArtigoListArtigo.getArtigoList().remove(artigoListArtigo);
                    oldIdblogOfArtigoListArtigo = em.merge(oldIdblogOfArtigoListArtigo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Blog blog) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Blog persistentBlog = em.find(Blog.class, blog.getIdblog());
            Usuario autorOld = persistentBlog.getAutor();
            Usuario autorNew = blog.getAutor();
            List<Artigo> artigoListOld = persistentBlog.getArtigoList();
            List<Artigo> artigoListNew = blog.getArtigoList();
            List<String> illegalOrphanMessages = null;
            for (Artigo artigoListOldArtigo : artigoListOld) {
                if (!artigoListNew.contains(artigoListOldArtigo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Artigo " + artigoListOldArtigo + " since its idblog field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (autorNew != null) {
                autorNew = em.getReference(autorNew.getClass(), autorNew.getIdusuario());
                blog.setAutor(autorNew);
            }
            List<Artigo> attachedArtigoListNew = new ArrayList<Artigo>();
            for (Artigo artigoListNewArtigoToAttach : artigoListNew) {
                artigoListNewArtigoToAttach = em.getReference(artigoListNewArtigoToAttach.getClass(), artigoListNewArtigoToAttach.getIdartigo());
                attachedArtigoListNew.add(artigoListNewArtigoToAttach);
            }
            artigoListNew = attachedArtigoListNew;
            blog.setArtigoList(artigoListNew);
            blog = em.merge(blog);
            if (autorOld != null && !autorOld.equals(autorNew)) {
                autorOld.getBlogList().remove(blog);
                autorOld = em.merge(autorOld);
            }
            if (autorNew != null && !autorNew.equals(autorOld)) {
                autorNew.getBlogList().add(blog);
                autorNew = em.merge(autorNew);
            }
            for (Artigo artigoListNewArtigo : artigoListNew) {
                if (!artigoListOld.contains(artigoListNewArtigo)) {
                    Blog oldIdblogOfArtigoListNewArtigo = artigoListNewArtigo.getIdblog();
                    artigoListNewArtigo.setIdblog(blog);
                    artigoListNewArtigo = em.merge(artigoListNewArtigo);
                    if (oldIdblogOfArtigoListNewArtigo != null && !oldIdblogOfArtigoListNewArtigo.equals(blog)) {
                        oldIdblogOfArtigoListNewArtigo.getArtigoList().remove(artigoListNewArtigo);
                        oldIdblogOfArtigoListNewArtigo = em.merge(oldIdblogOfArtigoListNewArtigo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = blog.getIdblog();
                if (findBlog(id) == null) {
                    throw new NonexistentEntityException("The blog with id " + id + " no longer exists.");
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
            Blog blog;
            try {
                blog = em.getReference(Blog.class, id);
                blog.getIdblog();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The blog with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Artigo> artigoListOrphanCheck = blog.getArtigoList();
            for (Artigo artigoListOrphanCheckArtigo : artigoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Blog (" + blog + ") cannot be destroyed since the Artigo " + artigoListOrphanCheckArtigo + " in its artigoList field has a non-nullable idblog field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario autor = blog.getAutor();
            if (autor != null) {
                autor.getBlogList().remove(blog);
                autor = em.merge(autor);
            }
            em.remove(blog);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Blog> findBlogEntities() {
        return findBlogEntities(true, -1, -1);
    }

    public List<Blog> findBlogEntities(int maxResults, int firstResult) {
        return findBlogEntities(false, maxResults, firstResult);
    }

    private List<Blog> findBlogEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Blog.class));
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

    public Blog findBlog(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Blog.class, id);
        } finally {
            em.close();
        }
    }

    public int getBlogCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Blog> rt = cq.from(Blog.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
