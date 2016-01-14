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
import model.Comentariolocal;
import model.Local;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author asdfrofl
 */
public class ComentariolocalJpaController implements Serializable {

    public ComentariolocalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comentariolocal comentariolocal) {
        if (comentariolocal.getComentariolocalList() == null) {
            comentariolocal.setComentariolocalList(new ArrayList<Comentariolocal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comentariolocal idpai = comentariolocal.getIdpai();
            if (idpai != null) {
                idpai = em.getReference(idpai.getClass(), idpai.getIdcomentario());
                comentariolocal.setIdpai(idpai);
            }
            Local idlocal = comentariolocal.getIdlocal();
            if (idlocal != null) {
                idlocal = em.getReference(idlocal.getClass(), idlocal.getIdlocal());
                comentariolocal.setIdlocal(idlocal);
            }
            Usuario idusuario = comentariolocal.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                comentariolocal.setIdusuario(idusuario);
            }
            List<Comentariolocal> attachedComentariolocalList = new ArrayList<Comentariolocal>();
            for (Comentariolocal comentariolocalListComentariolocalToAttach : comentariolocal.getComentariolocalList()) {
                comentariolocalListComentariolocalToAttach = em.getReference(comentariolocalListComentariolocalToAttach.getClass(), comentariolocalListComentariolocalToAttach.getIdcomentario());
                attachedComentariolocalList.add(comentariolocalListComentariolocalToAttach);
            }
            comentariolocal.setComentariolocalList(attachedComentariolocalList);
            em.persist(comentariolocal);
            if (idpai != null) {
                idpai.getComentariolocalList().add(comentariolocal);
                idpai = em.merge(idpai);
            }
            if (idlocal != null) {
                idlocal.getComentariolocalList().add(comentariolocal);
                idlocal = em.merge(idlocal);
            }
            if (idusuario != null) {
                idusuario.getComentariolocalList().add(comentariolocal);
                idusuario = em.merge(idusuario);
            }
            for (Comentariolocal comentariolocalListComentariolocal : comentariolocal.getComentariolocalList()) {
                Comentariolocal oldIdpaiOfComentariolocalListComentariolocal = comentariolocalListComentariolocal.getIdpai();
                comentariolocalListComentariolocal.setIdpai(comentariolocal);
                comentariolocalListComentariolocal = em.merge(comentariolocalListComentariolocal);
                if (oldIdpaiOfComentariolocalListComentariolocal != null) {
                    oldIdpaiOfComentariolocalListComentariolocal.getComentariolocalList().remove(comentariolocalListComentariolocal);
                    oldIdpaiOfComentariolocalListComentariolocal = em.merge(oldIdpaiOfComentariolocalListComentariolocal);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comentariolocal comentariolocal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comentariolocal persistentComentariolocal = em.find(Comentariolocal.class, comentariolocal.getIdcomentario());
            Comentariolocal idpaiOld = persistentComentariolocal.getIdpai();
            Comentariolocal idpaiNew = comentariolocal.getIdpai();
            Local idlocalOld = persistentComentariolocal.getIdlocal();
            Local idlocalNew = comentariolocal.getIdlocal();
            Usuario idusuarioOld = persistentComentariolocal.getIdusuario();
            Usuario idusuarioNew = comentariolocal.getIdusuario();
            List<Comentariolocal> comentariolocalListOld = persistentComentariolocal.getComentariolocalList();
            List<Comentariolocal> comentariolocalListNew = comentariolocal.getComentariolocalList();
            if (idpaiNew != null) {
                idpaiNew = em.getReference(idpaiNew.getClass(), idpaiNew.getIdcomentario());
                comentariolocal.setIdpai(idpaiNew);
            }
            if (idlocalNew != null) {
                idlocalNew = em.getReference(idlocalNew.getClass(), idlocalNew.getIdlocal());
                comentariolocal.setIdlocal(idlocalNew);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                comentariolocal.setIdusuario(idusuarioNew);
            }
            List<Comentariolocal> attachedComentariolocalListNew = new ArrayList<Comentariolocal>();
            for (Comentariolocal comentariolocalListNewComentariolocalToAttach : comentariolocalListNew) {
                comentariolocalListNewComentariolocalToAttach = em.getReference(comentariolocalListNewComentariolocalToAttach.getClass(), comentariolocalListNewComentariolocalToAttach.getIdcomentario());
                attachedComentariolocalListNew.add(comentariolocalListNewComentariolocalToAttach);
            }
            comentariolocalListNew = attachedComentariolocalListNew;
            comentariolocal.setComentariolocalList(comentariolocalListNew);
            comentariolocal = em.merge(comentariolocal);
            if (idpaiOld != null && !idpaiOld.equals(idpaiNew)) {
                idpaiOld.getComentariolocalList().remove(comentariolocal);
                idpaiOld = em.merge(idpaiOld);
            }
            if (idpaiNew != null && !idpaiNew.equals(idpaiOld)) {
                idpaiNew.getComentariolocalList().add(comentariolocal);
                idpaiNew = em.merge(idpaiNew);
            }
            if (idlocalOld != null && !idlocalOld.equals(idlocalNew)) {
                idlocalOld.getComentariolocalList().remove(comentariolocal);
                idlocalOld = em.merge(idlocalOld);
            }
            if (idlocalNew != null && !idlocalNew.equals(idlocalOld)) {
                idlocalNew.getComentariolocalList().add(comentariolocal);
                idlocalNew = em.merge(idlocalNew);
            }
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getComentariolocalList().remove(comentariolocal);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getComentariolocalList().add(comentariolocal);
                idusuarioNew = em.merge(idusuarioNew);
            }
            for (Comentariolocal comentariolocalListOldComentariolocal : comentariolocalListOld) {
                if (!comentariolocalListNew.contains(comentariolocalListOldComentariolocal)) {
                    comentariolocalListOldComentariolocal.setIdpai(null);
                    comentariolocalListOldComentariolocal = em.merge(comentariolocalListOldComentariolocal);
                }
            }
            for (Comentariolocal comentariolocalListNewComentariolocal : comentariolocalListNew) {
                if (!comentariolocalListOld.contains(comentariolocalListNewComentariolocal)) {
                    Comentariolocal oldIdpaiOfComentariolocalListNewComentariolocal = comentariolocalListNewComentariolocal.getIdpai();
                    comentariolocalListNewComentariolocal.setIdpai(comentariolocal);
                    comentariolocalListNewComentariolocal = em.merge(comentariolocalListNewComentariolocal);
                    if (oldIdpaiOfComentariolocalListNewComentariolocal != null && !oldIdpaiOfComentariolocalListNewComentariolocal.equals(comentariolocal)) {
                        oldIdpaiOfComentariolocalListNewComentariolocal.getComentariolocalList().remove(comentariolocalListNewComentariolocal);
                        oldIdpaiOfComentariolocalListNewComentariolocal = em.merge(oldIdpaiOfComentariolocalListNewComentariolocal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comentariolocal.getIdcomentario();
                if (findComentariolocal(id) == null) {
                    throw new NonexistentEntityException("The comentariolocal with id " + id + " no longer exists.");
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
            Comentariolocal comentariolocal;
            try {
                comentariolocal = em.getReference(Comentariolocal.class, id);
                comentariolocal.getIdcomentario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comentariolocal with id " + id + " no longer exists.", enfe);
            }
            Comentariolocal idpai = comentariolocal.getIdpai();
            if (idpai != null) {
                idpai.getComentariolocalList().remove(comentariolocal);
                idpai = em.merge(idpai);
            }
            Local idlocal = comentariolocal.getIdlocal();
            if (idlocal != null) {
                idlocal.getComentariolocalList().remove(comentariolocal);
                idlocal = em.merge(idlocal);
            }
            Usuario idusuario = comentariolocal.getIdusuario();
            if (idusuario != null) {
                idusuario.getComentariolocalList().remove(comentariolocal);
                idusuario = em.merge(idusuario);
            }
            List<Comentariolocal> comentariolocalList = comentariolocal.getComentariolocalList();
            for (Comentariolocal comentariolocalListComentariolocal : comentariolocalList) {
                comentariolocalListComentariolocal.setIdpai(null);
                comentariolocalListComentariolocal = em.merge(comentariolocalListComentariolocal);
            }
            em.remove(comentariolocal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comentariolocal> findComentariolocalEntities() {
        return findComentariolocalEntities(true, -1, -1);
    }

    public List<Comentariolocal> findComentariolocalEntities(int maxResults, int firstResult) {
        return findComentariolocalEntities(false, maxResults, firstResult);
    }

    private List<Comentariolocal> findComentariolocalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comentariolocal.class));
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

    public Comentariolocal findComentariolocal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comentariolocal.class, id);
        } finally {
            em.close();
        }
    }

    public int getComentariolocalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comentariolocal> rt = cq.from(Comentariolocal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
