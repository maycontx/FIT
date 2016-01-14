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
import model.Consultoriarealizada;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Pagamento;

/**
 *
 * @author asdfrofl
 */
public class PagamentoJpaController implements Serializable {

    public PagamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagamento pagamento) throws IllegalOrphanException {
        if (pagamento.getConsultoriarealizadaList() == null) {
            pagamento.setConsultoriarealizadaList(new ArrayList<Consultoriarealizada>());
        }
        List<String> illegalOrphanMessages = null;
        Consultoriarealizada idconsultoriarealizadaOrphanCheck = pagamento.getIdconsultoriarealizada();
        if (idconsultoriarealizadaOrphanCheck != null) {
            Pagamento oldIdpagamentoOfIdconsultoriarealizada = idconsultoriarealizadaOrphanCheck.getIdpagamento();
            if (oldIdpagamentoOfIdconsultoriarealizada != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Consultoriarealizada " + idconsultoriarealizadaOrphanCheck + " already has an item of type Pagamento whose idconsultoriarealizada column cannot be null. Please make another selection for the idconsultoriarealizada field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = pagamento.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                pagamento.setIdusuario(idusuario);
            }
            Consultoriarealizada idconsultoriarealizada = pagamento.getIdconsultoriarealizada();
            if (idconsultoriarealizada != null) {
                idconsultoriarealizada = em.getReference(idconsultoriarealizada.getClass(), idconsultoriarealizada.getIdconsultoriarealizada());
                pagamento.setIdconsultoriarealizada(idconsultoriarealizada);
            }
            List<Consultoriarealizada> attachedConsultoriarealizadaList = new ArrayList<Consultoriarealizada>();
            for (Consultoriarealizada consultoriarealizadaListConsultoriarealizadaToAttach : pagamento.getConsultoriarealizadaList()) {
                consultoriarealizadaListConsultoriarealizadaToAttach = em.getReference(consultoriarealizadaListConsultoriarealizadaToAttach.getClass(), consultoriarealizadaListConsultoriarealizadaToAttach.getIdconsultoriarealizada());
                attachedConsultoriarealizadaList.add(consultoriarealizadaListConsultoriarealizadaToAttach);
            }
            pagamento.setConsultoriarealizadaList(attachedConsultoriarealizadaList);
            em.persist(pagamento);
            if (idusuario != null) {
                idusuario.getPagamentoList().add(pagamento);
                idusuario = em.merge(idusuario);
            }
            if (idconsultoriarealizada != null) {
                idconsultoriarealizada.setIdpagamento(pagamento);
                idconsultoriarealizada = em.merge(idconsultoriarealizada);
            }
            for (Consultoriarealizada consultoriarealizadaListConsultoriarealizada : pagamento.getConsultoriarealizadaList()) {
                Pagamento oldIdpagamentoOfConsultoriarealizadaListConsultoriarealizada = consultoriarealizadaListConsultoriarealizada.getIdpagamento();
                consultoriarealizadaListConsultoriarealizada.setIdpagamento(pagamento);
                consultoriarealizadaListConsultoriarealizada = em.merge(consultoriarealizadaListConsultoriarealizada);
                if (oldIdpagamentoOfConsultoriarealizadaListConsultoriarealizada != null) {
                    oldIdpagamentoOfConsultoriarealizadaListConsultoriarealizada.getConsultoriarealizadaList().remove(consultoriarealizadaListConsultoriarealizada);
                    oldIdpagamentoOfConsultoriarealizadaListConsultoriarealizada = em.merge(oldIdpagamentoOfConsultoriarealizadaListConsultoriarealizada);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagamento pagamento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagamento persistentPagamento = em.find(Pagamento.class, pagamento.getIdpagamento());
            Usuario idusuarioOld = persistentPagamento.getIdusuario();
            Usuario idusuarioNew = pagamento.getIdusuario();
            Consultoriarealizada idconsultoriarealizadaOld = persistentPagamento.getIdconsultoriarealizada();
            Consultoriarealizada idconsultoriarealizadaNew = pagamento.getIdconsultoriarealizada();
            List<Consultoriarealizada> consultoriarealizadaListOld = persistentPagamento.getConsultoriarealizadaList();
            List<Consultoriarealizada> consultoriarealizadaListNew = pagamento.getConsultoriarealizadaList();
            List<String> illegalOrphanMessages = null;
            if (idconsultoriarealizadaOld != null && !idconsultoriarealizadaOld.equals(idconsultoriarealizadaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Consultoriarealizada " + idconsultoriarealizadaOld + " since its idpagamento field is not nullable.");
            }
            if (idconsultoriarealizadaNew != null && !idconsultoriarealizadaNew.equals(idconsultoriarealizadaOld)) {
                Pagamento oldIdpagamentoOfIdconsultoriarealizada = idconsultoriarealizadaNew.getIdpagamento();
                if (oldIdpagamentoOfIdconsultoriarealizada != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Consultoriarealizada " + idconsultoriarealizadaNew + " already has an item of type Pagamento whose idconsultoriarealizada column cannot be null. Please make another selection for the idconsultoriarealizada field.");
                }
            }
            for (Consultoriarealizada consultoriarealizadaListOldConsultoriarealizada : consultoriarealizadaListOld) {
                if (!consultoriarealizadaListNew.contains(consultoriarealizadaListOldConsultoriarealizada)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Consultoriarealizada " + consultoriarealizadaListOldConsultoriarealizada + " since its idpagamento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                pagamento.setIdusuario(idusuarioNew);
            }
            if (idconsultoriarealizadaNew != null) {
                idconsultoriarealizadaNew = em.getReference(idconsultoriarealizadaNew.getClass(), idconsultoriarealizadaNew.getIdconsultoriarealizada());
                pagamento.setIdconsultoriarealizada(idconsultoriarealizadaNew);
            }
            List<Consultoriarealizada> attachedConsultoriarealizadaListNew = new ArrayList<Consultoriarealizada>();
            for (Consultoriarealizada consultoriarealizadaListNewConsultoriarealizadaToAttach : consultoriarealizadaListNew) {
                consultoriarealizadaListNewConsultoriarealizadaToAttach = em.getReference(consultoriarealizadaListNewConsultoriarealizadaToAttach.getClass(), consultoriarealizadaListNewConsultoriarealizadaToAttach.getIdconsultoriarealizada());
                attachedConsultoriarealizadaListNew.add(consultoriarealizadaListNewConsultoriarealizadaToAttach);
            }
            consultoriarealizadaListNew = attachedConsultoriarealizadaListNew;
            pagamento.setConsultoriarealizadaList(consultoriarealizadaListNew);
            pagamento = em.merge(pagamento);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getPagamentoList().remove(pagamento);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getPagamentoList().add(pagamento);
                idusuarioNew = em.merge(idusuarioNew);
            }
            if (idconsultoriarealizadaNew != null && !idconsultoriarealizadaNew.equals(idconsultoriarealizadaOld)) {
                idconsultoriarealizadaNew.setIdpagamento(pagamento);
                idconsultoriarealizadaNew = em.merge(idconsultoriarealizadaNew);
            }
            for (Consultoriarealizada consultoriarealizadaListNewConsultoriarealizada : consultoriarealizadaListNew) {
                if (!consultoriarealizadaListOld.contains(consultoriarealizadaListNewConsultoriarealizada)) {
                    Pagamento oldIdpagamentoOfConsultoriarealizadaListNewConsultoriarealizada = consultoriarealizadaListNewConsultoriarealizada.getIdpagamento();
                    consultoriarealizadaListNewConsultoriarealizada.setIdpagamento(pagamento);
                    consultoriarealizadaListNewConsultoriarealizada = em.merge(consultoriarealizadaListNewConsultoriarealizada);
                    if (oldIdpagamentoOfConsultoriarealizadaListNewConsultoriarealizada != null && !oldIdpagamentoOfConsultoriarealizadaListNewConsultoriarealizada.equals(pagamento)) {
                        oldIdpagamentoOfConsultoriarealizadaListNewConsultoriarealizada.getConsultoriarealizadaList().remove(consultoriarealizadaListNewConsultoriarealizada);
                        oldIdpagamentoOfConsultoriarealizadaListNewConsultoriarealizada = em.merge(oldIdpagamentoOfConsultoriarealizadaListNewConsultoriarealizada);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pagamento.getIdpagamento();
                if (findPagamento(id) == null) {
                    throw new NonexistentEntityException("The pagamento with id " + id + " no longer exists.");
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
            Pagamento pagamento;
            try {
                pagamento = em.getReference(Pagamento.class, id);
                pagamento.getIdpagamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagamento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Consultoriarealizada idconsultoriarealizadaOrphanCheck = pagamento.getIdconsultoriarealizada();
            if (idconsultoriarealizadaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pagamento (" + pagamento + ") cannot be destroyed since the Consultoriarealizada " + idconsultoriarealizadaOrphanCheck + " in its idconsultoriarealizada field has a non-nullable idpagamento field.");
            }
            List<Consultoriarealizada> consultoriarealizadaListOrphanCheck = pagamento.getConsultoriarealizadaList();
            for (Consultoriarealizada consultoriarealizadaListOrphanCheckConsultoriarealizada : consultoriarealizadaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pagamento (" + pagamento + ") cannot be destroyed since the Consultoriarealizada " + consultoriarealizadaListOrphanCheckConsultoriarealizada + " in its consultoriarealizadaList field has a non-nullable idpagamento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario idusuario = pagamento.getIdusuario();
            if (idusuario != null) {
                idusuario.getPagamentoList().remove(pagamento);
                idusuario = em.merge(idusuario);
            }
            em.remove(pagamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pagamento> findPagamentoEntities() {
        return findPagamentoEntities(true, -1, -1);
    }

    public List<Pagamento> findPagamentoEntities(int maxResults, int firstResult) {
        return findPagamentoEntities(false, maxResults, firstResult);
    }

    private List<Pagamento> findPagamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagamento.class));
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

    public Pagamento findPagamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagamento> rt = cq.from(Pagamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
