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
import model.Usuariogrupo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Grupo;
import model.Solicitacaogrupo;

/**
 *
 * @author asdfrofl
 */
public class GrupoJpaController implements Serializable {

    public GrupoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grupo grupo) {
        if (grupo.getUsuariogrupoList() == null) {
            grupo.setUsuariogrupoList(new ArrayList<Usuariogrupo>());
        }
        if (grupo.getSolicitacaogrupoList() == null) {
            grupo.setSolicitacaogrupoList(new ArrayList<Solicitacaogrupo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = grupo.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                grupo.setIdusuario(idusuario);
            }
            List<Usuariogrupo> attachedUsuariogrupoList = new ArrayList<Usuariogrupo>();
            for (Usuariogrupo usuariogrupoListUsuariogrupoToAttach : grupo.getUsuariogrupoList()) {
                usuariogrupoListUsuariogrupoToAttach = em.getReference(usuariogrupoListUsuariogrupoToAttach.getClass(), usuariogrupoListUsuariogrupoToAttach.getIdusuariogrupo());
                attachedUsuariogrupoList.add(usuariogrupoListUsuariogrupoToAttach);
            }
            grupo.setUsuariogrupoList(attachedUsuariogrupoList);
            List<Solicitacaogrupo> attachedSolicitacaogrupoList = new ArrayList<Solicitacaogrupo>();
            for (Solicitacaogrupo solicitacaogrupoListSolicitacaogrupoToAttach : grupo.getSolicitacaogrupoList()) {
                solicitacaogrupoListSolicitacaogrupoToAttach = em.getReference(solicitacaogrupoListSolicitacaogrupoToAttach.getClass(), solicitacaogrupoListSolicitacaogrupoToAttach.getIdsolicitacao());
                attachedSolicitacaogrupoList.add(solicitacaogrupoListSolicitacaogrupoToAttach);
            }
            grupo.setSolicitacaogrupoList(attachedSolicitacaogrupoList);
            em.persist(grupo);
            if (idusuario != null) {
                idusuario.getGrupoList().add(grupo);
                idusuario = em.merge(idusuario);
            }
            for (Usuariogrupo usuariogrupoListUsuariogrupo : grupo.getUsuariogrupoList()) {
                Grupo oldIdgrupoOfUsuariogrupoListUsuariogrupo = usuariogrupoListUsuariogrupo.getIdgrupo();
                usuariogrupoListUsuariogrupo.setIdgrupo(grupo);
                usuariogrupoListUsuariogrupo = em.merge(usuariogrupoListUsuariogrupo);
                if (oldIdgrupoOfUsuariogrupoListUsuariogrupo != null) {
                    oldIdgrupoOfUsuariogrupoListUsuariogrupo.getUsuariogrupoList().remove(usuariogrupoListUsuariogrupo);
                    oldIdgrupoOfUsuariogrupoListUsuariogrupo = em.merge(oldIdgrupoOfUsuariogrupoListUsuariogrupo);
                }
            }
            for (Solicitacaogrupo solicitacaogrupoListSolicitacaogrupo : grupo.getSolicitacaogrupoList()) {
                Grupo oldIdgrupoOfSolicitacaogrupoListSolicitacaogrupo = solicitacaogrupoListSolicitacaogrupo.getIdgrupo();
                solicitacaogrupoListSolicitacaogrupo.setIdgrupo(grupo);
                solicitacaogrupoListSolicitacaogrupo = em.merge(solicitacaogrupoListSolicitacaogrupo);
                if (oldIdgrupoOfSolicitacaogrupoListSolicitacaogrupo != null) {
                    oldIdgrupoOfSolicitacaogrupoListSolicitacaogrupo.getSolicitacaogrupoList().remove(solicitacaogrupoListSolicitacaogrupo);
                    oldIdgrupoOfSolicitacaogrupoListSolicitacaogrupo = em.merge(oldIdgrupoOfSolicitacaogrupoListSolicitacaogrupo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupo grupo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo persistentGrupo = em.find(Grupo.class, grupo.getIdgrupo());
            Usuario idusuarioOld = persistentGrupo.getIdusuario();
            Usuario idusuarioNew = grupo.getIdusuario();
            List<Usuariogrupo> usuariogrupoListOld = persistentGrupo.getUsuariogrupoList();
            List<Usuariogrupo> usuariogrupoListNew = grupo.getUsuariogrupoList();
            List<Solicitacaogrupo> solicitacaogrupoListOld = persistentGrupo.getSolicitacaogrupoList();
            List<Solicitacaogrupo> solicitacaogrupoListNew = grupo.getSolicitacaogrupoList();
            List<String> illegalOrphanMessages = null;
            for (Usuariogrupo usuariogrupoListOldUsuariogrupo : usuariogrupoListOld) {
                if (!usuariogrupoListNew.contains(usuariogrupoListOldUsuariogrupo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuariogrupo " + usuariogrupoListOldUsuariogrupo + " since its idgrupo field is not nullable.");
                }
            }
            for (Solicitacaogrupo solicitacaogrupoListOldSolicitacaogrupo : solicitacaogrupoListOld) {
                if (!solicitacaogrupoListNew.contains(solicitacaogrupoListOldSolicitacaogrupo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitacaogrupo " + solicitacaogrupoListOldSolicitacaogrupo + " since its idgrupo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                grupo.setIdusuario(idusuarioNew);
            }
            List<Usuariogrupo> attachedUsuariogrupoListNew = new ArrayList<Usuariogrupo>();
            for (Usuariogrupo usuariogrupoListNewUsuariogrupoToAttach : usuariogrupoListNew) {
                usuariogrupoListNewUsuariogrupoToAttach = em.getReference(usuariogrupoListNewUsuariogrupoToAttach.getClass(), usuariogrupoListNewUsuariogrupoToAttach.getIdusuariogrupo());
                attachedUsuariogrupoListNew.add(usuariogrupoListNewUsuariogrupoToAttach);
            }
            usuariogrupoListNew = attachedUsuariogrupoListNew;
            grupo.setUsuariogrupoList(usuariogrupoListNew);
            List<Solicitacaogrupo> attachedSolicitacaogrupoListNew = new ArrayList<Solicitacaogrupo>();
            for (Solicitacaogrupo solicitacaogrupoListNewSolicitacaogrupoToAttach : solicitacaogrupoListNew) {
                solicitacaogrupoListNewSolicitacaogrupoToAttach = em.getReference(solicitacaogrupoListNewSolicitacaogrupoToAttach.getClass(), solicitacaogrupoListNewSolicitacaogrupoToAttach.getIdsolicitacao());
                attachedSolicitacaogrupoListNew.add(solicitacaogrupoListNewSolicitacaogrupoToAttach);
            }
            solicitacaogrupoListNew = attachedSolicitacaogrupoListNew;
            grupo.setSolicitacaogrupoList(solicitacaogrupoListNew);
            grupo = em.merge(grupo);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getGrupoList().remove(grupo);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getGrupoList().add(grupo);
                idusuarioNew = em.merge(idusuarioNew);
            }
            for (Usuariogrupo usuariogrupoListNewUsuariogrupo : usuariogrupoListNew) {
                if (!usuariogrupoListOld.contains(usuariogrupoListNewUsuariogrupo)) {
                    Grupo oldIdgrupoOfUsuariogrupoListNewUsuariogrupo = usuariogrupoListNewUsuariogrupo.getIdgrupo();
                    usuariogrupoListNewUsuariogrupo.setIdgrupo(grupo);
                    usuariogrupoListNewUsuariogrupo = em.merge(usuariogrupoListNewUsuariogrupo);
                    if (oldIdgrupoOfUsuariogrupoListNewUsuariogrupo != null && !oldIdgrupoOfUsuariogrupoListNewUsuariogrupo.equals(grupo)) {
                        oldIdgrupoOfUsuariogrupoListNewUsuariogrupo.getUsuariogrupoList().remove(usuariogrupoListNewUsuariogrupo);
                        oldIdgrupoOfUsuariogrupoListNewUsuariogrupo = em.merge(oldIdgrupoOfUsuariogrupoListNewUsuariogrupo);
                    }
                }
            }
            for (Solicitacaogrupo solicitacaogrupoListNewSolicitacaogrupo : solicitacaogrupoListNew) {
                if (!solicitacaogrupoListOld.contains(solicitacaogrupoListNewSolicitacaogrupo)) {
                    Grupo oldIdgrupoOfSolicitacaogrupoListNewSolicitacaogrupo = solicitacaogrupoListNewSolicitacaogrupo.getIdgrupo();
                    solicitacaogrupoListNewSolicitacaogrupo.setIdgrupo(grupo);
                    solicitacaogrupoListNewSolicitacaogrupo = em.merge(solicitacaogrupoListNewSolicitacaogrupo);
                    if (oldIdgrupoOfSolicitacaogrupoListNewSolicitacaogrupo != null && !oldIdgrupoOfSolicitacaogrupoListNewSolicitacaogrupo.equals(grupo)) {
                        oldIdgrupoOfSolicitacaogrupoListNewSolicitacaogrupo.getSolicitacaogrupoList().remove(solicitacaogrupoListNewSolicitacaogrupo);
                        oldIdgrupoOfSolicitacaogrupoListNewSolicitacaogrupo = em.merge(oldIdgrupoOfSolicitacaogrupoListNewSolicitacaogrupo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = grupo.getIdgrupo();
                if (findGrupo(id) == null) {
                    throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.");
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
            Grupo grupo;
            try {
                grupo = em.getReference(Grupo.class, id);
                grupo.getIdgrupo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Usuariogrupo> usuariogrupoListOrphanCheck = grupo.getUsuariogrupoList();
            for (Usuariogrupo usuariogrupoListOrphanCheckUsuariogrupo : usuariogrupoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Grupo (" + grupo + ") cannot be destroyed since the Usuariogrupo " + usuariogrupoListOrphanCheckUsuariogrupo + " in its usuariogrupoList field has a non-nullable idgrupo field.");
            }
            List<Solicitacaogrupo> solicitacaogrupoListOrphanCheck = grupo.getSolicitacaogrupoList();
            for (Solicitacaogrupo solicitacaogrupoListOrphanCheckSolicitacaogrupo : solicitacaogrupoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Grupo (" + grupo + ") cannot be destroyed since the Solicitacaogrupo " + solicitacaogrupoListOrphanCheckSolicitacaogrupo + " in its solicitacaogrupoList field has a non-nullable idgrupo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario idusuario = grupo.getIdusuario();
            if (idusuario != null) {
                idusuario.getGrupoList().remove(grupo);
                idusuario = em.merge(idusuario);
            }
            em.remove(grupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupo> findGrupoEntities() {
        return findGrupoEntities(true, -1, -1);
    }

    public List<Grupo> findGrupoEntities(int maxResults, int firstResult) {
        return findGrupoEntities(false, maxResults, firstResult);
    }

    private List<Grupo> findGrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grupo.class));
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

    public Grupo findGrupo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grupo> rt = cq.from(Grupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
