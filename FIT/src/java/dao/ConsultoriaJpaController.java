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
import model.Profissional;
import model.Consultoriarealizada;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Consultoria;

/**
 *
 * @author asdfrofl
 */
public class ConsultoriaJpaController implements Serializable {

    public ConsultoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Consultoria consultoria) {
        if (consultoria.getConsultoriarealizadaList() == null) {
            consultoria.setConsultoriarealizadaList(new ArrayList<Consultoriarealizada>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profissional idprofissional = consultoria.getIdprofissional();
            if (idprofissional != null) {
                idprofissional = em.getReference(idprofissional.getClass(), idprofissional.getIdprofissional());
                consultoria.setIdprofissional(idprofissional);
            }
            List<Consultoriarealizada> attachedConsultoriarealizadaList = new ArrayList<Consultoriarealizada>();
            for (Consultoriarealizada consultoriarealizadaListConsultoriarealizadaToAttach : consultoria.getConsultoriarealizadaList()) {
                consultoriarealizadaListConsultoriarealizadaToAttach = em.getReference(consultoriarealizadaListConsultoriarealizadaToAttach.getClass(), consultoriarealizadaListConsultoriarealizadaToAttach.getIdconsultoriarealizada());
                attachedConsultoriarealizadaList.add(consultoriarealizadaListConsultoriarealizadaToAttach);
            }
            consultoria.setConsultoriarealizadaList(attachedConsultoriarealizadaList);
            em.persist(consultoria);
            if (idprofissional != null) {
                idprofissional.getConsultoriaList().add(consultoria);
                idprofissional = em.merge(idprofissional);
            }
            for (Consultoriarealizada consultoriarealizadaListConsultoriarealizada : consultoria.getConsultoriarealizadaList()) {
                Consultoria oldIdconsultoriaOfConsultoriarealizadaListConsultoriarealizada = consultoriarealizadaListConsultoriarealizada.getIdconsultoria();
                consultoriarealizadaListConsultoriarealizada.setIdconsultoria(consultoria);
                consultoriarealizadaListConsultoriarealizada = em.merge(consultoriarealizadaListConsultoriarealizada);
                if (oldIdconsultoriaOfConsultoriarealizadaListConsultoriarealizada != null) {
                    oldIdconsultoriaOfConsultoriarealizadaListConsultoriarealizada.getConsultoriarealizadaList().remove(consultoriarealizadaListConsultoriarealizada);
                    oldIdconsultoriaOfConsultoriarealizadaListConsultoriarealizada = em.merge(oldIdconsultoriaOfConsultoriarealizadaListConsultoriarealizada);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Consultoria consultoria) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consultoria persistentConsultoria = em.find(Consultoria.class, consultoria.getIdconsultoria());
            Profissional idprofissionalOld = persistentConsultoria.getIdprofissional();
            Profissional idprofissionalNew = consultoria.getIdprofissional();
            List<Consultoriarealizada> consultoriarealizadaListOld = persistentConsultoria.getConsultoriarealizadaList();
            List<Consultoriarealizada> consultoriarealizadaListNew = consultoria.getConsultoriarealizadaList();
            List<String> illegalOrphanMessages = null;
            for (Consultoriarealizada consultoriarealizadaListOldConsultoriarealizada : consultoriarealizadaListOld) {
                if (!consultoriarealizadaListNew.contains(consultoriarealizadaListOldConsultoriarealizada)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Consultoriarealizada " + consultoriarealizadaListOldConsultoriarealizada + " since its idconsultoria field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idprofissionalNew != null) {
                idprofissionalNew = em.getReference(idprofissionalNew.getClass(), idprofissionalNew.getIdprofissional());
                consultoria.setIdprofissional(idprofissionalNew);
            }
            List<Consultoriarealizada> attachedConsultoriarealizadaListNew = new ArrayList<Consultoriarealizada>();
            for (Consultoriarealizada consultoriarealizadaListNewConsultoriarealizadaToAttach : consultoriarealizadaListNew) {
                consultoriarealizadaListNewConsultoriarealizadaToAttach = em.getReference(consultoriarealizadaListNewConsultoriarealizadaToAttach.getClass(), consultoriarealizadaListNewConsultoriarealizadaToAttach.getIdconsultoriarealizada());
                attachedConsultoriarealizadaListNew.add(consultoriarealizadaListNewConsultoriarealizadaToAttach);
            }
            consultoriarealizadaListNew = attachedConsultoriarealizadaListNew;
            consultoria.setConsultoriarealizadaList(consultoriarealizadaListNew);
            consultoria = em.merge(consultoria);
            if (idprofissionalOld != null && !idprofissionalOld.equals(idprofissionalNew)) {
                idprofissionalOld.getConsultoriaList().remove(consultoria);
                idprofissionalOld = em.merge(idprofissionalOld);
            }
            if (idprofissionalNew != null && !idprofissionalNew.equals(idprofissionalOld)) {
                idprofissionalNew.getConsultoriaList().add(consultoria);
                idprofissionalNew = em.merge(idprofissionalNew);
            }
            for (Consultoriarealizada consultoriarealizadaListNewConsultoriarealizada : consultoriarealizadaListNew) {
                if (!consultoriarealizadaListOld.contains(consultoriarealizadaListNewConsultoriarealizada)) {
                    Consultoria oldIdconsultoriaOfConsultoriarealizadaListNewConsultoriarealizada = consultoriarealizadaListNewConsultoriarealizada.getIdconsultoria();
                    consultoriarealizadaListNewConsultoriarealizada.setIdconsultoria(consultoria);
                    consultoriarealizadaListNewConsultoriarealizada = em.merge(consultoriarealizadaListNewConsultoriarealizada);
                    if (oldIdconsultoriaOfConsultoriarealizadaListNewConsultoriarealizada != null && !oldIdconsultoriaOfConsultoriarealizadaListNewConsultoriarealizada.equals(consultoria)) {
                        oldIdconsultoriaOfConsultoriarealizadaListNewConsultoriarealizada.getConsultoriarealizadaList().remove(consultoriarealizadaListNewConsultoriarealizada);
                        oldIdconsultoriaOfConsultoriarealizadaListNewConsultoriarealizada = em.merge(oldIdconsultoriaOfConsultoriarealizadaListNewConsultoriarealizada);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = consultoria.getIdconsultoria();
                if (findConsultoria(id) == null) {
                    throw new NonexistentEntityException("The consultoria with id " + id + " no longer exists.");
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
            Consultoria consultoria;
            try {
                consultoria = em.getReference(Consultoria.class, id);
                consultoria.getIdconsultoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The consultoria with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Consultoriarealizada> consultoriarealizadaListOrphanCheck = consultoria.getConsultoriarealizadaList();
            for (Consultoriarealizada consultoriarealizadaListOrphanCheckConsultoriarealizada : consultoriarealizadaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Consultoria (" + consultoria + ") cannot be destroyed since the Consultoriarealizada " + consultoriarealizadaListOrphanCheckConsultoriarealizada + " in its consultoriarealizadaList field has a non-nullable idconsultoria field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Profissional idprofissional = consultoria.getIdprofissional();
            if (idprofissional != null) {
                idprofissional.getConsultoriaList().remove(consultoria);
                idprofissional = em.merge(idprofissional);
            }
            em.remove(consultoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Consultoria> findConsultoriaEntities() {
        return findConsultoriaEntities(true, -1, -1);
    }

    public List<Consultoria> findConsultoriaEntities(int maxResults, int firstResult) {
        return findConsultoriaEntities(false, maxResults, firstResult);
    }

    private List<Consultoria> findConsultoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Consultoria.class));
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

    public Consultoria findConsultoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Consultoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getConsultoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Consultoria> rt = cq.from(Consultoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
