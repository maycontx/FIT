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
import model.Usuario;
import model.Grupo;
import model.Usuariogrupo;

/**
 *
 * @author asdfrofl
 */
public class UsuariogrupoJpaController implements Serializable {

    public UsuariogrupoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuariogrupo usuariogrupo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = usuariogrupo.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                usuariogrupo.setIdusuario(idusuario);
            }
            Grupo idgrupo = usuariogrupo.getIdgrupo();
            if (idgrupo != null) {
                idgrupo = em.getReference(idgrupo.getClass(), idgrupo.getIdgrupo());
                usuariogrupo.setIdgrupo(idgrupo);
            }
            em.persist(usuariogrupo);
            if (idusuario != null) {
                idusuario.getUsuariogrupoList().add(usuariogrupo);
                idusuario = em.merge(idusuario);
            }
            if (idgrupo != null) {
                idgrupo.getUsuariogrupoList().add(usuariogrupo);
                idgrupo = em.merge(idgrupo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuariogrupo usuariogrupo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuariogrupo persistentUsuariogrupo = em.find(Usuariogrupo.class, usuariogrupo.getIdusuariogrupo());
            Usuario idusuarioOld = persistentUsuariogrupo.getIdusuario();
            Usuario idusuarioNew = usuariogrupo.getIdusuario();
            Grupo idgrupoOld = persistentUsuariogrupo.getIdgrupo();
            Grupo idgrupoNew = usuariogrupo.getIdgrupo();
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                usuariogrupo.setIdusuario(idusuarioNew);
            }
            if (idgrupoNew != null) {
                idgrupoNew = em.getReference(idgrupoNew.getClass(), idgrupoNew.getIdgrupo());
                usuariogrupo.setIdgrupo(idgrupoNew);
            }
            usuariogrupo = em.merge(usuariogrupo);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getUsuariogrupoList().remove(usuariogrupo);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getUsuariogrupoList().add(usuariogrupo);
                idusuarioNew = em.merge(idusuarioNew);
            }
            if (idgrupoOld != null && !idgrupoOld.equals(idgrupoNew)) {
                idgrupoOld.getUsuariogrupoList().remove(usuariogrupo);
                idgrupoOld = em.merge(idgrupoOld);
            }
            if (idgrupoNew != null && !idgrupoNew.equals(idgrupoOld)) {
                idgrupoNew.getUsuariogrupoList().add(usuariogrupo);
                idgrupoNew = em.merge(idgrupoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuariogrupo.getIdusuariogrupo();
                if (findUsuariogrupo(id) == null) {
                    throw new NonexistentEntityException("The usuariogrupo with id " + id + " no longer exists.");
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
            Usuariogrupo usuariogrupo;
            try {
                usuariogrupo = em.getReference(Usuariogrupo.class, id);
                usuariogrupo.getIdusuariogrupo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuariogrupo with id " + id + " no longer exists.", enfe);
            }
            Usuario idusuario = usuariogrupo.getIdusuario();
            if (idusuario != null) {
                idusuario.getUsuariogrupoList().remove(usuariogrupo);
                idusuario = em.merge(idusuario);
            }
            Grupo idgrupo = usuariogrupo.getIdgrupo();
            if (idgrupo != null) {
                idgrupo.getUsuariogrupoList().remove(usuariogrupo);
                idgrupo = em.merge(idgrupo);
            }
            em.remove(usuariogrupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuariogrupo> findUsuariogrupoEntities() {
        return findUsuariogrupoEntities(true, -1, -1);
    }

    public List<Usuariogrupo> findUsuariogrupoEntities(int maxResults, int firstResult) {
        return findUsuariogrupoEntities(false, maxResults, firstResult);
    }

    private List<Usuariogrupo> findUsuariogrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuariogrupo.class));
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

    public Usuariogrupo findUsuariogrupo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuariogrupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariogrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuariogrupo> rt = cq.from(Usuariogrupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
