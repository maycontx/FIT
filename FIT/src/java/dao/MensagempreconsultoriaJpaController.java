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
import model.Mensagempreconsultoria;
import model.Usuario;
import model.Profissional;

/**
 *
 * @author asdfrofl
 */
public class MensagempreconsultoriaJpaController implements Serializable {

    public MensagempreconsultoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mensagempreconsultoria mensagempreconsultoria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = mensagempreconsultoria.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                mensagempreconsultoria.setIdusuario(idusuario);
            }
            Profissional idprofissional = mensagempreconsultoria.getIdprofissional();
            if (idprofissional != null) {
                idprofissional = em.getReference(idprofissional.getClass(), idprofissional.getIdprofissional());
                mensagempreconsultoria.setIdprofissional(idprofissional);
            }
            em.persist(mensagempreconsultoria);
            if (idusuario != null) {
                idusuario.getMensagempreconsultoriaList().add(mensagempreconsultoria);
                idusuario = em.merge(idusuario);
            }
            if (idprofissional != null) {
                idprofissional.getMensagempreconsultoriaList().add(mensagempreconsultoria);
                idprofissional = em.merge(idprofissional);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mensagempreconsultoria mensagempreconsultoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mensagempreconsultoria persistentMensagempreconsultoria = em.find(Mensagempreconsultoria.class, mensagempreconsultoria.getIdmensagempreconsultoria());
            Usuario idusuarioOld = persistentMensagempreconsultoria.getIdusuario();
            Usuario idusuarioNew = mensagempreconsultoria.getIdusuario();
            Profissional idprofissionalOld = persistentMensagempreconsultoria.getIdprofissional();
            Profissional idprofissionalNew = mensagempreconsultoria.getIdprofissional();
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                mensagempreconsultoria.setIdusuario(idusuarioNew);
            }
            if (idprofissionalNew != null) {
                idprofissionalNew = em.getReference(idprofissionalNew.getClass(), idprofissionalNew.getIdprofissional());
                mensagempreconsultoria.setIdprofissional(idprofissionalNew);
            }
            mensagempreconsultoria = em.merge(mensagempreconsultoria);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getMensagempreconsultoriaList().remove(mensagempreconsultoria);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getMensagempreconsultoriaList().add(mensagempreconsultoria);
                idusuarioNew = em.merge(idusuarioNew);
            }
            if (idprofissionalOld != null && !idprofissionalOld.equals(idprofissionalNew)) {
                idprofissionalOld.getMensagempreconsultoriaList().remove(mensagempreconsultoria);
                idprofissionalOld = em.merge(idprofissionalOld);
            }
            if (idprofissionalNew != null && !idprofissionalNew.equals(idprofissionalOld)) {
                idprofissionalNew.getMensagempreconsultoriaList().add(mensagempreconsultoria);
                idprofissionalNew = em.merge(idprofissionalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mensagempreconsultoria.getIdmensagempreconsultoria();
                if (findMensagempreconsultoria(id) == null) {
                    throw new NonexistentEntityException("The mensagempreconsultoria with id " + id + " no longer exists.");
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
            Mensagempreconsultoria mensagempreconsultoria;
            try {
                mensagempreconsultoria = em.getReference(Mensagempreconsultoria.class, id);
                mensagempreconsultoria.getIdmensagempreconsultoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mensagempreconsultoria with id " + id + " no longer exists.", enfe);
            }
            Usuario idusuario = mensagempreconsultoria.getIdusuario();
            if (idusuario != null) {
                idusuario.getMensagempreconsultoriaList().remove(mensagempreconsultoria);
                idusuario = em.merge(idusuario);
            }
            Profissional idprofissional = mensagempreconsultoria.getIdprofissional();
            if (idprofissional != null) {
                idprofissional.getMensagempreconsultoriaList().remove(mensagempreconsultoria);
                idprofissional = em.merge(idprofissional);
            }
            em.remove(mensagempreconsultoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mensagempreconsultoria> findMensagempreconsultoriaEntities() {
        return findMensagempreconsultoriaEntities(true, -1, -1);
    }

    public List<Mensagempreconsultoria> findMensagempreconsultoriaEntities(int maxResults, int firstResult) {
        return findMensagempreconsultoriaEntities(false, maxResults, firstResult);
    }

    private List<Mensagempreconsultoria> findMensagempreconsultoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mensagempreconsultoria.class));
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

    public Mensagempreconsultoria findMensagempreconsultoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mensagempreconsultoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getMensagempreconsultoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mensagempreconsultoria> rt = cq.from(Mensagempreconsultoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
