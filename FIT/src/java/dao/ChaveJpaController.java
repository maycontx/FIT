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
import model.Chave;
import model.Publicacao;

/**
 *
 * @author asdfrofl
 */
public class ChaveJpaController implements Serializable {

    public ChaveJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Chave chave) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Publicacao idpublicacao = chave.getIdpublicacao();
            if (idpublicacao != null) {
                idpublicacao = em.getReference(idpublicacao.getClass(), idpublicacao.getIdpublicacao());
                chave.setIdpublicacao(idpublicacao);
            }
            em.persist(chave);
            if (idpublicacao != null) {
                idpublicacao.getChaveList().add(chave);
                idpublicacao = em.merge(idpublicacao);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Chave chave) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Chave persistentChave = em.find(Chave.class, chave.getIdchave());
            Publicacao idpublicacaoOld = persistentChave.getIdpublicacao();
            Publicacao idpublicacaoNew = chave.getIdpublicacao();
            if (idpublicacaoNew != null) {
                idpublicacaoNew = em.getReference(idpublicacaoNew.getClass(), idpublicacaoNew.getIdpublicacao());
                chave.setIdpublicacao(idpublicacaoNew);
            }
            chave = em.merge(chave);
            if (idpublicacaoOld != null && !idpublicacaoOld.equals(idpublicacaoNew)) {
                idpublicacaoOld.getChaveList().remove(chave);
                idpublicacaoOld = em.merge(idpublicacaoOld);
            }
            if (idpublicacaoNew != null && !idpublicacaoNew.equals(idpublicacaoOld)) {
                idpublicacaoNew.getChaveList().add(chave);
                idpublicacaoNew = em.merge(idpublicacaoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = chave.getIdchave();
                if (findChave(id) == null) {
                    throw new NonexistentEntityException("The chave with id " + id + " no longer exists.");
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
            Chave chave;
            try {
                chave = em.getReference(Chave.class, id);
                chave.getIdchave();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The chave with id " + id + " no longer exists.", enfe);
            }
            Publicacao idpublicacao = chave.getIdpublicacao();
            if (idpublicacao != null) {
                idpublicacao.getChaveList().remove(chave);
                idpublicacao = em.merge(idpublicacao);
            }
            em.remove(chave);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Chave> findChaveEntities() {
        return findChaveEntities(true, -1, -1);
    }

    public List<Chave> findChaveEntities(int maxResults, int firstResult) {
        return findChaveEntities(false, maxResults, firstResult);
    }

    private List<Chave> findChaveEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Chave.class));
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

    public Chave findChave(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Chave.class, id);
        } finally {
            em.close();
        }
    }

    public int getChaveCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Chave> rt = cq.from(Chave.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
