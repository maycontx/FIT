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
import model.Consultoria;
import model.Usuario;
import model.Pagamento;
import model.Chatconsultoria;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Avaliacaoartigo;
import model.Consultoriarealizada;

/**
 *
 * @author asdfrofl
 */
public class ConsultoriarealizadaJpaController implements Serializable {

    public ConsultoriarealizadaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Consultoriarealizada consultoriarealizada) {
        if (consultoriarealizada.getChatconsultoriaList() == null) {
            consultoriarealizada.setChatconsultoriaList(new ArrayList<Chatconsultoria>());
        }
        if (consultoriarealizada.getAvaliacaoartigoList() == null) {
            consultoriarealizada.setAvaliacaoartigoList(new ArrayList<Avaliacaoartigo>());
        }
        if (consultoriarealizada.getPagamentoList() == null) {
            consultoriarealizada.setPagamentoList(new ArrayList<Pagamento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consultoria idconsultoria = consultoriarealizada.getIdconsultoria();
            if (idconsultoria != null) {
                idconsultoria = em.getReference(idconsultoria.getClass(), idconsultoria.getIdconsultoria());
                consultoriarealizada.setIdconsultoria(idconsultoria);
            }
            Usuario idusuario = consultoriarealizada.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                consultoriarealizada.setIdusuario(idusuario);
            }
            Pagamento idpagamento = consultoriarealizada.getIdpagamento();
            if (idpagamento != null) {
                idpagamento = em.getReference(idpagamento.getClass(), idpagamento.getIdpagamento());
                consultoriarealizada.setIdpagamento(idpagamento);
            }
            List<Chatconsultoria> attachedChatconsultoriaList = new ArrayList<Chatconsultoria>();
            for (Chatconsultoria chatconsultoriaListChatconsultoriaToAttach : consultoriarealizada.getChatconsultoriaList()) {
                chatconsultoriaListChatconsultoriaToAttach = em.getReference(chatconsultoriaListChatconsultoriaToAttach.getClass(), chatconsultoriaListChatconsultoriaToAttach.getIdchatconsultoria());
                attachedChatconsultoriaList.add(chatconsultoriaListChatconsultoriaToAttach);
            }
            consultoriarealizada.setChatconsultoriaList(attachedChatconsultoriaList);
            List<Avaliacaoartigo> attachedAvaliacaoartigoList = new ArrayList<Avaliacaoartigo>();
            for (Avaliacaoartigo avaliacaoartigoListAvaliacaoartigoToAttach : consultoriarealizada.getAvaliacaoartigoList()) {
                avaliacaoartigoListAvaliacaoartigoToAttach = em.getReference(avaliacaoartigoListAvaliacaoartigoToAttach.getClass(), avaliacaoartigoListAvaliacaoartigoToAttach.getIdavaliacaoconsultoria());
                attachedAvaliacaoartigoList.add(avaliacaoartigoListAvaliacaoartigoToAttach);
            }
            consultoriarealizada.setAvaliacaoartigoList(attachedAvaliacaoartigoList);
            List<Pagamento> attachedPagamentoList = new ArrayList<Pagamento>();
            for (Pagamento pagamentoListPagamentoToAttach : consultoriarealizada.getPagamentoList()) {
                pagamentoListPagamentoToAttach = em.getReference(pagamentoListPagamentoToAttach.getClass(), pagamentoListPagamentoToAttach.getIdpagamento());
                attachedPagamentoList.add(pagamentoListPagamentoToAttach);
            }
            consultoriarealizada.setPagamentoList(attachedPagamentoList);
            em.persist(consultoriarealizada);
            if (idconsultoria != null) {
                idconsultoria.getConsultoriarealizadaList().add(consultoriarealizada);
                idconsultoria = em.merge(idconsultoria);
            }
            if (idusuario != null) {
                idusuario.getConsultoriarealizadaList().add(consultoriarealizada);
                idusuario = em.merge(idusuario);
            }
            if (idpagamento != null) {
                idpagamento.getConsultoriarealizadaList().add(consultoriarealizada);
                idpagamento = em.merge(idpagamento);
            }
            for (Chatconsultoria chatconsultoriaListChatconsultoria : consultoriarealizada.getChatconsultoriaList()) {
                Consultoriarealizada oldIdconsultoriarealizadaOfChatconsultoriaListChatconsultoria = chatconsultoriaListChatconsultoria.getIdconsultoriarealizada();
                chatconsultoriaListChatconsultoria.setIdconsultoriarealizada(consultoriarealizada);
                chatconsultoriaListChatconsultoria = em.merge(chatconsultoriaListChatconsultoria);
                if (oldIdconsultoriarealizadaOfChatconsultoriaListChatconsultoria != null) {
                    oldIdconsultoriarealizadaOfChatconsultoriaListChatconsultoria.getChatconsultoriaList().remove(chatconsultoriaListChatconsultoria);
                    oldIdconsultoriarealizadaOfChatconsultoriaListChatconsultoria = em.merge(oldIdconsultoriarealizadaOfChatconsultoriaListChatconsultoria);
                }
            }
            for (Avaliacaoartigo avaliacaoartigoListAvaliacaoartigo : consultoriarealizada.getAvaliacaoartigoList()) {
                Consultoriarealizada oldIdconsultoriarealizadaOfAvaliacaoartigoListAvaliacaoartigo = avaliacaoartigoListAvaliacaoartigo.getIdconsultoriarealizada();
                avaliacaoartigoListAvaliacaoartigo.setIdconsultoriarealizada(consultoriarealizada);
                avaliacaoartigoListAvaliacaoartigo = em.merge(avaliacaoartigoListAvaliacaoartigo);
                if (oldIdconsultoriarealizadaOfAvaliacaoartigoListAvaliacaoartigo != null) {
                    oldIdconsultoriarealizadaOfAvaliacaoartigoListAvaliacaoartigo.getAvaliacaoartigoList().remove(avaliacaoartigoListAvaliacaoartigo);
                    oldIdconsultoriarealizadaOfAvaliacaoartigoListAvaliacaoartigo = em.merge(oldIdconsultoriarealizadaOfAvaliacaoartigoListAvaliacaoartigo);
                }
            }
            for (Pagamento pagamentoListPagamento : consultoriarealizada.getPagamentoList()) {
                Consultoriarealizada oldIdconsultoriarealizadaOfPagamentoListPagamento = pagamentoListPagamento.getIdconsultoriarealizada();
                pagamentoListPagamento.setIdconsultoriarealizada(consultoriarealizada);
                pagamentoListPagamento = em.merge(pagamentoListPagamento);
                if (oldIdconsultoriarealizadaOfPagamentoListPagamento != null) {
                    oldIdconsultoriarealizadaOfPagamentoListPagamento.getPagamentoList().remove(pagamentoListPagamento);
                    oldIdconsultoriarealizadaOfPagamentoListPagamento = em.merge(oldIdconsultoriarealizadaOfPagamentoListPagamento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Consultoriarealizada consultoriarealizada) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consultoriarealizada persistentConsultoriarealizada = em.find(Consultoriarealizada.class, consultoriarealizada.getIdconsultoriarealizada());
            Consultoria idconsultoriaOld = persistentConsultoriarealizada.getIdconsultoria();
            Consultoria idconsultoriaNew = consultoriarealizada.getIdconsultoria();
            Usuario idusuarioOld = persistentConsultoriarealizada.getIdusuario();
            Usuario idusuarioNew = consultoriarealizada.getIdusuario();
            Pagamento idpagamentoOld = persistentConsultoriarealizada.getIdpagamento();
            Pagamento idpagamentoNew = consultoriarealizada.getIdpagamento();
            List<Chatconsultoria> chatconsultoriaListOld = persistentConsultoriarealizada.getChatconsultoriaList();
            List<Chatconsultoria> chatconsultoriaListNew = consultoriarealizada.getChatconsultoriaList();
            List<Avaliacaoartigo> avaliacaoartigoListOld = persistentConsultoriarealizada.getAvaliacaoartigoList();
            List<Avaliacaoartigo> avaliacaoartigoListNew = consultoriarealizada.getAvaliacaoartigoList();
            List<Pagamento> pagamentoListOld = persistentConsultoriarealizada.getPagamentoList();
            List<Pagamento> pagamentoListNew = consultoriarealizada.getPagamentoList();
            List<String> illegalOrphanMessages = null;
            for (Chatconsultoria chatconsultoriaListOldChatconsultoria : chatconsultoriaListOld) {
                if (!chatconsultoriaListNew.contains(chatconsultoriaListOldChatconsultoria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Chatconsultoria " + chatconsultoriaListOldChatconsultoria + " since its idconsultoriarealizada field is not nullable.");
                }
            }
            for (Avaliacaoartigo avaliacaoartigoListOldAvaliacaoartigo : avaliacaoartigoListOld) {
                if (!avaliacaoartigoListNew.contains(avaliacaoartigoListOldAvaliacaoartigo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Avaliacaoartigo " + avaliacaoartigoListOldAvaliacaoartigo + " since its idconsultoriarealizada field is not nullable.");
                }
            }
            for (Pagamento pagamentoListOldPagamento : pagamentoListOld) {
                if (!pagamentoListNew.contains(pagamentoListOldPagamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pagamento " + pagamentoListOldPagamento + " since its idconsultoriarealizada field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idconsultoriaNew != null) {
                idconsultoriaNew = em.getReference(idconsultoriaNew.getClass(), idconsultoriaNew.getIdconsultoria());
                consultoriarealizada.setIdconsultoria(idconsultoriaNew);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                consultoriarealizada.setIdusuario(idusuarioNew);
            }
            if (idpagamentoNew != null) {
                idpagamentoNew = em.getReference(idpagamentoNew.getClass(), idpagamentoNew.getIdpagamento());
                consultoriarealizada.setIdpagamento(idpagamentoNew);
            }
            List<Chatconsultoria> attachedChatconsultoriaListNew = new ArrayList<Chatconsultoria>();
            for (Chatconsultoria chatconsultoriaListNewChatconsultoriaToAttach : chatconsultoriaListNew) {
                chatconsultoriaListNewChatconsultoriaToAttach = em.getReference(chatconsultoriaListNewChatconsultoriaToAttach.getClass(), chatconsultoriaListNewChatconsultoriaToAttach.getIdchatconsultoria());
                attachedChatconsultoriaListNew.add(chatconsultoriaListNewChatconsultoriaToAttach);
            }
            chatconsultoriaListNew = attachedChatconsultoriaListNew;
            consultoriarealizada.setChatconsultoriaList(chatconsultoriaListNew);
            List<Avaliacaoartigo> attachedAvaliacaoartigoListNew = new ArrayList<Avaliacaoartigo>();
            for (Avaliacaoartigo avaliacaoartigoListNewAvaliacaoartigoToAttach : avaliacaoartigoListNew) {
                avaliacaoartigoListNewAvaliacaoartigoToAttach = em.getReference(avaliacaoartigoListNewAvaliacaoartigoToAttach.getClass(), avaliacaoartigoListNewAvaliacaoartigoToAttach.getIdavaliacaoconsultoria());
                attachedAvaliacaoartigoListNew.add(avaliacaoartigoListNewAvaliacaoartigoToAttach);
            }
            avaliacaoartigoListNew = attachedAvaliacaoartigoListNew;
            consultoriarealizada.setAvaliacaoartigoList(avaliacaoartigoListNew);
            List<Pagamento> attachedPagamentoListNew = new ArrayList<Pagamento>();
            for (Pagamento pagamentoListNewPagamentoToAttach : pagamentoListNew) {
                pagamentoListNewPagamentoToAttach = em.getReference(pagamentoListNewPagamentoToAttach.getClass(), pagamentoListNewPagamentoToAttach.getIdpagamento());
                attachedPagamentoListNew.add(pagamentoListNewPagamentoToAttach);
            }
            pagamentoListNew = attachedPagamentoListNew;
            consultoriarealizada.setPagamentoList(pagamentoListNew);
            consultoriarealizada = em.merge(consultoriarealizada);
            if (idconsultoriaOld != null && !idconsultoriaOld.equals(idconsultoriaNew)) {
                idconsultoriaOld.getConsultoriarealizadaList().remove(consultoriarealizada);
                idconsultoriaOld = em.merge(idconsultoriaOld);
            }
            if (idconsultoriaNew != null && !idconsultoriaNew.equals(idconsultoriaOld)) {
                idconsultoriaNew.getConsultoriarealizadaList().add(consultoriarealizada);
                idconsultoriaNew = em.merge(idconsultoriaNew);
            }
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getConsultoriarealizadaList().remove(consultoriarealizada);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getConsultoriarealizadaList().add(consultoriarealizada);
                idusuarioNew = em.merge(idusuarioNew);
            }
            if (idpagamentoOld != null && !idpagamentoOld.equals(idpagamentoNew)) {
                idpagamentoOld.getConsultoriarealizadaList().remove(consultoriarealizada);
                idpagamentoOld = em.merge(idpagamentoOld);
            }
            if (idpagamentoNew != null && !idpagamentoNew.equals(idpagamentoOld)) {
                idpagamentoNew.getConsultoriarealizadaList().add(consultoriarealizada);
                idpagamentoNew = em.merge(idpagamentoNew);
            }
            for (Chatconsultoria chatconsultoriaListNewChatconsultoria : chatconsultoriaListNew) {
                if (!chatconsultoriaListOld.contains(chatconsultoriaListNewChatconsultoria)) {
                    Consultoriarealizada oldIdconsultoriarealizadaOfChatconsultoriaListNewChatconsultoria = chatconsultoriaListNewChatconsultoria.getIdconsultoriarealizada();
                    chatconsultoriaListNewChatconsultoria.setIdconsultoriarealizada(consultoriarealizada);
                    chatconsultoriaListNewChatconsultoria = em.merge(chatconsultoriaListNewChatconsultoria);
                    if (oldIdconsultoriarealizadaOfChatconsultoriaListNewChatconsultoria != null && !oldIdconsultoriarealizadaOfChatconsultoriaListNewChatconsultoria.equals(consultoriarealizada)) {
                        oldIdconsultoriarealizadaOfChatconsultoriaListNewChatconsultoria.getChatconsultoriaList().remove(chatconsultoriaListNewChatconsultoria);
                        oldIdconsultoriarealizadaOfChatconsultoriaListNewChatconsultoria = em.merge(oldIdconsultoriarealizadaOfChatconsultoriaListNewChatconsultoria);
                    }
                }
            }
            for (Avaliacaoartigo avaliacaoartigoListNewAvaliacaoartigo : avaliacaoartigoListNew) {
                if (!avaliacaoartigoListOld.contains(avaliacaoartigoListNewAvaliacaoartigo)) {
                    Consultoriarealizada oldIdconsultoriarealizadaOfAvaliacaoartigoListNewAvaliacaoartigo = avaliacaoartigoListNewAvaliacaoartigo.getIdconsultoriarealizada();
                    avaliacaoartigoListNewAvaliacaoartigo.setIdconsultoriarealizada(consultoriarealizada);
                    avaliacaoartigoListNewAvaliacaoartigo = em.merge(avaliacaoartigoListNewAvaliacaoartigo);
                    if (oldIdconsultoriarealizadaOfAvaliacaoartigoListNewAvaliacaoartigo != null && !oldIdconsultoriarealizadaOfAvaliacaoartigoListNewAvaliacaoartigo.equals(consultoriarealizada)) {
                        oldIdconsultoriarealizadaOfAvaliacaoartigoListNewAvaliacaoartigo.getAvaliacaoartigoList().remove(avaliacaoartigoListNewAvaliacaoartigo);
                        oldIdconsultoriarealizadaOfAvaliacaoartigoListNewAvaliacaoartigo = em.merge(oldIdconsultoriarealizadaOfAvaliacaoartigoListNewAvaliacaoartigo);
                    }
                }
            }
            for (Pagamento pagamentoListNewPagamento : pagamentoListNew) {
                if (!pagamentoListOld.contains(pagamentoListNewPagamento)) {
                    Consultoriarealizada oldIdconsultoriarealizadaOfPagamentoListNewPagamento = pagamentoListNewPagamento.getIdconsultoriarealizada();
                    pagamentoListNewPagamento.setIdconsultoriarealizada(consultoriarealizada);
                    pagamentoListNewPagamento = em.merge(pagamentoListNewPagamento);
                    if (oldIdconsultoriarealizadaOfPagamentoListNewPagamento != null && !oldIdconsultoriarealizadaOfPagamentoListNewPagamento.equals(consultoriarealizada)) {
                        oldIdconsultoriarealizadaOfPagamentoListNewPagamento.getPagamentoList().remove(pagamentoListNewPagamento);
                        oldIdconsultoriarealizadaOfPagamentoListNewPagamento = em.merge(oldIdconsultoriarealizadaOfPagamentoListNewPagamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = consultoriarealizada.getIdconsultoriarealizada();
                if (findConsultoriarealizada(id) == null) {
                    throw new NonexistentEntityException("The consultoriarealizada with id " + id + " no longer exists.");
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
            Consultoriarealizada consultoriarealizada;
            try {
                consultoriarealizada = em.getReference(Consultoriarealizada.class, id);
                consultoriarealizada.getIdconsultoriarealizada();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The consultoriarealizada with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Chatconsultoria> chatconsultoriaListOrphanCheck = consultoriarealizada.getChatconsultoriaList();
            for (Chatconsultoria chatconsultoriaListOrphanCheckChatconsultoria : chatconsultoriaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Consultoriarealizada (" + consultoriarealizada + ") cannot be destroyed since the Chatconsultoria " + chatconsultoriaListOrphanCheckChatconsultoria + " in its chatconsultoriaList field has a non-nullable idconsultoriarealizada field.");
            }
            List<Avaliacaoartigo> avaliacaoartigoListOrphanCheck = consultoriarealizada.getAvaliacaoartigoList();
            for (Avaliacaoartigo avaliacaoartigoListOrphanCheckAvaliacaoartigo : avaliacaoartigoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Consultoriarealizada (" + consultoriarealizada + ") cannot be destroyed since the Avaliacaoartigo " + avaliacaoartigoListOrphanCheckAvaliacaoartigo + " in its avaliacaoartigoList field has a non-nullable idconsultoriarealizada field.");
            }
            List<Pagamento> pagamentoListOrphanCheck = consultoriarealizada.getPagamentoList();
            for (Pagamento pagamentoListOrphanCheckPagamento : pagamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Consultoriarealizada (" + consultoriarealizada + ") cannot be destroyed since the Pagamento " + pagamentoListOrphanCheckPagamento + " in its pagamentoList field has a non-nullable idconsultoriarealizada field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Consultoria idconsultoria = consultoriarealizada.getIdconsultoria();
            if (idconsultoria != null) {
                idconsultoria.getConsultoriarealizadaList().remove(consultoriarealizada);
                idconsultoria = em.merge(idconsultoria);
            }
            Usuario idusuario = consultoriarealizada.getIdusuario();
            if (idusuario != null) {
                idusuario.getConsultoriarealizadaList().remove(consultoriarealizada);
                idusuario = em.merge(idusuario);
            }
            Pagamento idpagamento = consultoriarealizada.getIdpagamento();
            if (idpagamento != null) {
                idpagamento.getConsultoriarealizadaList().remove(consultoriarealizada);
                idpagamento = em.merge(idpagamento);
            }
            em.remove(consultoriarealizada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Consultoriarealizada> findConsultoriarealizadaEntities() {
        return findConsultoriarealizadaEntities(true, -1, -1);
    }

    public List<Consultoriarealizada> findConsultoriarealizadaEntities(int maxResults, int firstResult) {
        return findConsultoriarealizadaEntities(false, maxResults, firstResult);
    }

    private List<Consultoriarealizada> findConsultoriarealizadaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Consultoriarealizada.class));
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

    public Consultoriarealizada findConsultoriarealizada(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Consultoriarealizada.class, id);
        } finally {
            em.close();
        }
    }

    public int getConsultoriarealizadaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Consultoriarealizada> rt = cq.from(Consultoriarealizada.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
