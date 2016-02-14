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
import model.Nutricionista;
import model.Personal;
import model.Profissional;

/**
 *
 * @author asdfrofl
 */
public class NutricionistaJpaController implements Serializable {

    public NutricionistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nutricionista nutricionista) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profissional idprofissional = nutricionista.getIdprofissional();
            if (idprofissional != null) {
                idprofissional = em.getReference(idprofissional.getClass(), idprofissional.getIdprofissional());
                nutricionista.setIdprofissional(idprofissional);
            }
            em.persist(nutricionista);
            if (idprofissional != null) {
                idprofissional.getNutricionistaList().add(nutricionista);
                idprofissional = em.merge(idprofissional);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nutricionista nutricionista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nutricionista persistentNutricionista = em.find(Nutricionista.class, nutricionista.getIdnutricionista());
            Profissional idprofissionalOld = persistentNutricionista.getIdprofissional();
            Profissional idprofissionalNew = nutricionista.getIdprofissional();
            if (idprofissionalNew != null) {
                idprofissionalNew = em.getReference(idprofissionalNew.getClass(), idprofissionalNew.getIdprofissional());
                nutricionista.setIdprofissional(idprofissionalNew);
            }
            nutricionista = em.merge(nutricionista);
            if (idprofissionalOld != null && !idprofissionalOld.equals(idprofissionalNew)) {
                idprofissionalOld.getNutricionistaList().remove(nutricionista);
                idprofissionalOld = em.merge(idprofissionalOld);
            }
            if (idprofissionalNew != null && !idprofissionalNew.equals(idprofissionalOld)) {
                idprofissionalNew.getNutricionistaList().add(nutricionista);
                idprofissionalNew = em.merge(idprofissionalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nutricionista.getIdnutricionista();
                if (findNutricionista(id) == null) {
                    throw new NonexistentEntityException("The nutricionista with id " + id + " no longer exists.");
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
            Nutricionista nutricionista;
            try {
                nutricionista = em.getReference(Nutricionista.class, id);
                nutricionista.getIdnutricionista();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nutricionista with id " + id + " no longer exists.", enfe);
            }
            Profissional idprofissional = nutricionista.getIdprofissional();
            if (idprofissional != null) {
                idprofissional.getNutricionistaList().remove(nutricionista);
                idprofissional = em.merge(idprofissional);
            }
            em.remove(nutricionista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nutricionista> findNutricionistaEntities() {
        return findNutricionistaEntities(true, -1, -1);
    }

    public List<Nutricionista> findNutricionistaEntities(int maxResults, int firstResult) {
        return findNutricionistaEntities(false, maxResults, firstResult);
    }

    private List<Nutricionista> findNutricionistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nutricionista.class));
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

    public Nutricionista findNutricionista(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nutricionista.class, id);
        } finally {
            em.close();
        }
    }

    public int getNutricionistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nutricionista> rt = cq.from(Nutricionista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Nutricionista findNutriByProfissional(Profissional prof){
        
        String query = "SELECT nutri FROM Nutricionista nutri WHERE nutri.idprofissional.idprofissional = :prof"; 
        
        Query q = getEntityManager().createQuery(query);
        
        q.setParameter("prof", prof.getIdprofissional());       
        
        try{
            return (Nutricionista) q.getSingleResult();
        }catch(Exception ex){
            return null;
        }
    }
    
}
