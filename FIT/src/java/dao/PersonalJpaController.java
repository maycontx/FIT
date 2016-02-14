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
import model.Atleta;
import model.Personal;
import model.Profissional;
import model.Usuario;

/**
 *
 * @author asdfrofl
 */
public class PersonalJpaController implements Serializable {

    public PersonalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personal personal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profissional idprofissional = personal.getIdprofissional();
            if (idprofissional != null) {
                idprofissional = em.getReference(idprofissional.getClass(), idprofissional.getIdprofissional());
                personal.setIdprofissional(idprofissional);
            }
            em.persist(personal);
            if (idprofissional != null) {
                idprofissional.getPersonalList().add(personal);
                idprofissional = em.merge(idprofissional);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Personal personal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personal persistentPersonal = em.find(Personal.class, personal.getIdpersonal());
            Profissional idprofissionalOld = persistentPersonal.getIdprofissional();
            Profissional idprofissionalNew = personal.getIdprofissional();
            if (idprofissionalNew != null) {
                idprofissionalNew = em.getReference(idprofissionalNew.getClass(), idprofissionalNew.getIdprofissional());
                personal.setIdprofissional(idprofissionalNew);
            }
            personal = em.merge(personal);
            if (idprofissionalOld != null && !idprofissionalOld.equals(idprofissionalNew)) {
                idprofissionalOld.getPersonalList().remove(personal);
                idprofissionalOld = em.merge(idprofissionalOld);
            }
            if (idprofissionalNew != null && !idprofissionalNew.equals(idprofissionalOld)) {
                idprofissionalNew.getPersonalList().add(personal);
                idprofissionalNew = em.merge(idprofissionalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = personal.getIdpersonal();
                if (findPersonal(id) == null) {
                    throw new NonexistentEntityException("The personal with id " + id + " no longer exists.");
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
            Personal personal;
            try {
                personal = em.getReference(Personal.class, id);
                personal.getIdpersonal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personal with id " + id + " no longer exists.", enfe);
            }
            Profissional idprofissional = personal.getIdprofissional();
            if (idprofissional != null) {
                idprofissional.getPersonalList().remove(personal);
                idprofissional = em.merge(idprofissional);
            }
            em.remove(personal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Personal> findPersonalEntities() {
        return findPersonalEntities(true, -1, -1);
    }

    public List<Personal> findPersonalEntities(int maxResults, int firstResult) {
        return findPersonalEntities(false, maxResults, firstResult);
    }

    private List<Personal> findPersonalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personal.class));
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

    public Personal findPersonal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personal.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personal> rt = cq.from(Personal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Personal findPersonalByProfissional(Profissional prof){
        
        String query = "SELECT person FROM Personal person WHERE person.idprofissional.idprofissional = :prof"; 
        
        Query q = getEntityManager().createQuery(query);
        
        q.setParameter("prof", prof.getIdprofissional());       
        
        try{
            return (Personal) q.getSingleResult();
        }catch(Exception ex){
            return null;
        }
    }
    
}
