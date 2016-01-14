/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Usuario;
import model.Comentariolocal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Local;

/**
 *
 * @author asdfrofl
 */
public class LocalJpaController implements Serializable {

    public LocalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Local local) throws PreexistingEntityException, Exception {
        if (local.getComentariolocalList() == null) {
            local.setComentariolocalList(new ArrayList<Comentariolocal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = local.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                local.setIdusuario(idusuario);
            }
            List<Comentariolocal> attachedComentariolocalList = new ArrayList<Comentariolocal>();
            for (Comentariolocal comentariolocalListComentariolocalToAttach : local.getComentariolocalList()) {
                comentariolocalListComentariolocalToAttach = em.getReference(comentariolocalListComentariolocalToAttach.getClass(), comentariolocalListComentariolocalToAttach.getIdcomentario());
                attachedComentariolocalList.add(comentariolocalListComentariolocalToAttach);
            }
            local.setComentariolocalList(attachedComentariolocalList);
            em.persist(local);
            if (idusuario != null) {
                idusuario.getLocalList().add(local);
                idusuario = em.merge(idusuario);
            }
            for (Comentariolocal comentariolocalListComentariolocal : local.getComentariolocalList()) {
                Local oldIdlocalOfComentariolocalListComentariolocal = comentariolocalListComentariolocal.getIdlocal();
                comentariolocalListComentariolocal.setIdlocal(local);
                comentariolocalListComentariolocal = em.merge(comentariolocalListComentariolocal);
                if (oldIdlocalOfComentariolocalListComentariolocal != null) {
                    oldIdlocalOfComentariolocalListComentariolocal.getComentariolocalList().remove(comentariolocalListComentariolocal);
                    oldIdlocalOfComentariolocalListComentariolocal = em.merge(oldIdlocalOfComentariolocalListComentariolocal);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLocal(local.getIdlocal()) != null) {
                throw new PreexistingEntityException("Local " + local + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Local local) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Local persistentLocal = em.find(Local.class, local.getIdlocal());
            Usuario idusuarioOld = persistentLocal.getIdusuario();
            Usuario idusuarioNew = local.getIdusuario();
            List<Comentariolocal> comentariolocalListOld = persistentLocal.getComentariolocalList();
            List<Comentariolocal> comentariolocalListNew = local.getComentariolocalList();
            List<String> illegalOrphanMessages = null;
            for (Comentariolocal comentariolocalListOldComentariolocal : comentariolocalListOld) {
                if (!comentariolocalListNew.contains(comentariolocalListOldComentariolocal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comentariolocal " + comentariolocalListOldComentariolocal + " since its idlocal field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                local.setIdusuario(idusuarioNew);
            }
            List<Comentariolocal> attachedComentariolocalListNew = new ArrayList<Comentariolocal>();
            for (Comentariolocal comentariolocalListNewComentariolocalToAttach : comentariolocalListNew) {
                comentariolocalListNewComentariolocalToAttach = em.getReference(comentariolocalListNewComentariolocalToAttach.getClass(), comentariolocalListNewComentariolocalToAttach.getIdcomentario());
                attachedComentariolocalListNew.add(comentariolocalListNewComentariolocalToAttach);
            }
            comentariolocalListNew = attachedComentariolocalListNew;
            local.setComentariolocalList(comentariolocalListNew);
            local = em.merge(local);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getLocalList().remove(local);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getLocalList().add(local);
                idusuarioNew = em.merge(idusuarioNew);
            }
            for (Comentariolocal comentariolocalListNewComentariolocal : comentariolocalListNew) {
                if (!comentariolocalListOld.contains(comentariolocalListNewComentariolocal)) {
                    Local oldIdlocalOfComentariolocalListNewComentariolocal = comentariolocalListNewComentariolocal.getIdlocal();
                    comentariolocalListNewComentariolocal.setIdlocal(local);
                    comentariolocalListNewComentariolocal = em.merge(comentariolocalListNewComentariolocal);
                    if (oldIdlocalOfComentariolocalListNewComentariolocal != null && !oldIdlocalOfComentariolocalListNewComentariolocal.equals(local)) {
                        oldIdlocalOfComentariolocalListNewComentariolocal.getComentariolocalList().remove(comentariolocalListNewComentariolocal);
                        oldIdlocalOfComentariolocalListNewComentariolocal = em.merge(oldIdlocalOfComentariolocalListNewComentariolocal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = local.getIdlocal();
                if (findLocal(id) == null) {
                    throw new NonexistentEntityException("The local with id " + id + " no longer exists.");
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
            Local local;
            try {
                local = em.getReference(Local.class, id);
                local.getIdlocal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The local with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Comentariolocal> comentariolocalListOrphanCheck = local.getComentariolocalList();
            for (Comentariolocal comentariolocalListOrphanCheckComentariolocal : comentariolocalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Local (" + local + ") cannot be destroyed since the Comentariolocal " + comentariolocalListOrphanCheckComentariolocal + " in its comentariolocalList field has a non-nullable idlocal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario idusuario = local.getIdusuario();
            if (idusuario != null) {
                idusuario.getLocalList().remove(local);
                idusuario = em.merge(idusuario);
            }
            em.remove(local);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Local> findLocalEntities() {
        return findLocalEntities(true, -1, -1);
    }

    public List<Local> findLocalEntities(int maxResults, int firstResult) {
        return findLocalEntities(false, maxResults, firstResult);
    }

    private List<Local> findLocalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Local.class));
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

    public Local findLocal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Local.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Local> rt = cq.from(Local.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
