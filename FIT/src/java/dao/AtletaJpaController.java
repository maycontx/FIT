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
import model.Corpo;
import model.Mensagemcomum;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Atleta;
import model.Profissional;
import model.Solicitacaorelacionamento;
import model.Relacionamento;

/**
 *
 * @author asdfrofl
 */
public class AtletaJpaController implements Serializable {

    public AtletaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Atleta atleta) {
        if (atleta.getMensagemcomumList() == null) {
            atleta.setMensagemcomumList(new ArrayList<Mensagemcomum>());
        }
        if (atleta.getMensagemcomumList1() == null) {
            atleta.setMensagemcomumList1(new ArrayList<Mensagemcomum>());
        }
        if (atleta.getSolicitacaorelacionamentoList() == null) {
            atleta.setSolicitacaorelacionamentoList(new ArrayList<Solicitacaorelacionamento>());
        }
        if (atleta.getRelacionamentoList() == null) {
            atleta.setRelacionamentoList(new ArrayList<Relacionamento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = atleta.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                atleta.setIdusuario(idusuario);
            }
            Corpo idcorpo = atleta.getIdcorpo();
            if (idcorpo != null) {
                idcorpo = em.getReference(idcorpo.getClass(), idcorpo.getIdcorpo());
                atleta.setIdcorpo(idcorpo);
            }
            List<Mensagemcomum> attachedMensagemcomumList = new ArrayList<Mensagemcomum>();
            for (Mensagemcomum mensagemcomumListMensagemcomumToAttach : atleta.getMensagemcomumList()) {
                mensagemcomumListMensagemcomumToAttach = em.getReference(mensagemcomumListMensagemcomumToAttach.getClass(), mensagemcomumListMensagemcomumToAttach.getIdmensagemComum());
                attachedMensagemcomumList.add(mensagemcomumListMensagemcomumToAttach);
            }
            atleta.setMensagemcomumList(attachedMensagemcomumList);
            List<Mensagemcomum> attachedMensagemcomumList1 = new ArrayList<Mensagemcomum>();
            for (Mensagemcomum mensagemcomumList1MensagemcomumToAttach : atleta.getMensagemcomumList1()) {
                mensagemcomumList1MensagemcomumToAttach = em.getReference(mensagemcomumList1MensagemcomumToAttach.getClass(), mensagemcomumList1MensagemcomumToAttach.getIdmensagemComum());
                attachedMensagemcomumList1.add(mensagemcomumList1MensagemcomumToAttach);
            }
            atleta.setMensagemcomumList1(attachedMensagemcomumList1);
            List<Solicitacaorelacionamento> attachedSolicitacaorelacionamentoList = new ArrayList<Solicitacaorelacionamento>();
            for (Solicitacaorelacionamento solicitacaorelacionamentoListSolicitacaorelacionamentoToAttach : atleta.getSolicitacaorelacionamentoList()) {
                solicitacaorelacionamentoListSolicitacaorelacionamentoToAttach = em.getReference(solicitacaorelacionamentoListSolicitacaorelacionamentoToAttach.getClass(), solicitacaorelacionamentoListSolicitacaorelacionamentoToAttach.getIdsolicitacao());
                attachedSolicitacaorelacionamentoList.add(solicitacaorelacionamentoListSolicitacaorelacionamentoToAttach);
            }
            atleta.setSolicitacaorelacionamentoList(attachedSolicitacaorelacionamentoList);
            List<Relacionamento> attachedRelacionamentoList = new ArrayList<Relacionamento>();
            for (Relacionamento relacionamentoListRelacionamentoToAttach : atleta.getRelacionamentoList()) {
                relacionamentoListRelacionamentoToAttach = em.getReference(relacionamentoListRelacionamentoToAttach.getClass(), relacionamentoListRelacionamentoToAttach.getIdrelacionamento());
                attachedRelacionamentoList.add(relacionamentoListRelacionamentoToAttach);
            }
            atleta.setRelacionamentoList(attachedRelacionamentoList);
            em.persist(atleta);
            if (idusuario != null) {
                idusuario.getAtletaList().add(atleta);
                idusuario = em.merge(idusuario);
            }
            if (idcorpo != null) {
                idcorpo.getAtletaList().add(atleta);
                idcorpo = em.merge(idcorpo);
            }
            for (Mensagemcomum mensagemcomumListMensagemcomum : atleta.getMensagemcomumList()) {
                Atleta oldIdAtletaEmissorOfMensagemcomumListMensagemcomum = mensagemcomumListMensagemcomum.getIdAtletaEmissor();
                mensagemcomumListMensagemcomum.setIdAtletaEmissor(atleta);
                mensagemcomumListMensagemcomum = em.merge(mensagemcomumListMensagemcomum);
                if (oldIdAtletaEmissorOfMensagemcomumListMensagemcomum != null) {
                    oldIdAtletaEmissorOfMensagemcomumListMensagemcomum.getMensagemcomumList().remove(mensagemcomumListMensagemcomum);
                    oldIdAtletaEmissorOfMensagemcomumListMensagemcomum = em.merge(oldIdAtletaEmissorOfMensagemcomumListMensagemcomum);
                }
            }
            for (Mensagemcomum mensagemcomumList1Mensagemcomum : atleta.getMensagemcomumList1()) {
                Atleta oldIdAtletaDestinatarioOfMensagemcomumList1Mensagemcomum = mensagemcomumList1Mensagemcomum.getIdAtletaDestinatario();
                mensagemcomumList1Mensagemcomum.setIdAtletaDestinatario(atleta);
                mensagemcomumList1Mensagemcomum = em.merge(mensagemcomumList1Mensagemcomum);
                if (oldIdAtletaDestinatarioOfMensagemcomumList1Mensagemcomum != null) {
                    oldIdAtletaDestinatarioOfMensagemcomumList1Mensagemcomum.getMensagemcomumList1().remove(mensagemcomumList1Mensagemcomum);
                    oldIdAtletaDestinatarioOfMensagemcomumList1Mensagemcomum = em.merge(oldIdAtletaDestinatarioOfMensagemcomumList1Mensagemcomum);
                }
            }
            for (Solicitacaorelacionamento solicitacaorelacionamentoListSolicitacaorelacionamento : atleta.getSolicitacaorelacionamentoList()) {
                Atleta oldIdatletaOfSolicitacaorelacionamentoListSolicitacaorelacionamento = solicitacaorelacionamentoListSolicitacaorelacionamento.getIdatleta();
                solicitacaorelacionamentoListSolicitacaorelacionamento.setIdatleta(atleta);
                solicitacaorelacionamentoListSolicitacaorelacionamento = em.merge(solicitacaorelacionamentoListSolicitacaorelacionamento);
                if (oldIdatletaOfSolicitacaorelacionamentoListSolicitacaorelacionamento != null) {
                    oldIdatletaOfSolicitacaorelacionamentoListSolicitacaorelacionamento.getSolicitacaorelacionamentoList().remove(solicitacaorelacionamentoListSolicitacaorelacionamento);
                    oldIdatletaOfSolicitacaorelacionamentoListSolicitacaorelacionamento = em.merge(oldIdatletaOfSolicitacaorelacionamentoListSolicitacaorelacionamento);
                }
            }
            for (Relacionamento relacionamentoListRelacionamento : atleta.getRelacionamentoList()) {
                Atleta oldIdatletaOfRelacionamentoListRelacionamento = relacionamentoListRelacionamento.getIdatleta();
                relacionamentoListRelacionamento.setIdatleta(atleta);
                relacionamentoListRelacionamento = em.merge(relacionamentoListRelacionamento);
                if (oldIdatletaOfRelacionamentoListRelacionamento != null) {
                    oldIdatletaOfRelacionamentoListRelacionamento.getRelacionamentoList().remove(relacionamentoListRelacionamento);
                    oldIdatletaOfRelacionamentoListRelacionamento = em.merge(oldIdatletaOfRelacionamentoListRelacionamento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Atleta atleta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Atleta persistentAtleta = em.find(Atleta.class, atleta.getIdatleta());
            Usuario idusuarioOld = persistentAtleta.getIdusuario();
            Usuario idusuarioNew = atleta.getIdusuario();
            Corpo idcorpoOld = persistentAtleta.getIdcorpo();
            Corpo idcorpoNew = atleta.getIdcorpo();
            List<Mensagemcomum> mensagemcomumListOld = persistentAtleta.getMensagemcomumList();
            List<Mensagemcomum> mensagemcomumListNew = atleta.getMensagemcomumList();
            List<Mensagemcomum> mensagemcomumList1Old = persistentAtleta.getMensagemcomumList1();
            List<Mensagemcomum> mensagemcomumList1New = atleta.getMensagemcomumList1();
            List<Solicitacaorelacionamento> solicitacaorelacionamentoListOld = persistentAtleta.getSolicitacaorelacionamentoList();
            List<Solicitacaorelacionamento> solicitacaorelacionamentoListNew = atleta.getSolicitacaorelacionamentoList();
            List<Relacionamento> relacionamentoListOld = persistentAtleta.getRelacionamentoList();
            List<Relacionamento> relacionamentoListNew = atleta.getRelacionamentoList();
            List<String> illegalOrphanMessages = null;
            for (Mensagemcomum mensagemcomumListOldMensagemcomum : mensagemcomumListOld) {
                if (!mensagemcomumListNew.contains(mensagemcomumListOldMensagemcomum)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mensagemcomum " + mensagemcomumListOldMensagemcomum + " since its idAtletaEmissor field is not nullable.");
                }
            }
            for (Mensagemcomum mensagemcomumList1OldMensagemcomum : mensagemcomumList1Old) {
                if (!mensagemcomumList1New.contains(mensagemcomumList1OldMensagemcomum)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mensagemcomum " + mensagemcomumList1OldMensagemcomum + " since its idAtletaDestinatario field is not nullable.");
                }
            }
            for (Solicitacaorelacionamento solicitacaorelacionamentoListOldSolicitacaorelacionamento : solicitacaorelacionamentoListOld) {
                if (!solicitacaorelacionamentoListNew.contains(solicitacaorelacionamentoListOldSolicitacaorelacionamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitacaorelacionamento " + solicitacaorelacionamentoListOldSolicitacaorelacionamento + " since its idatleta field is not nullable.");
                }
            }
            for (Relacionamento relacionamentoListOldRelacionamento : relacionamentoListOld) {
                if (!relacionamentoListNew.contains(relacionamentoListOldRelacionamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Relacionamento " + relacionamentoListOldRelacionamento + " since its idatleta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                atleta.setIdusuario(idusuarioNew);
            }
            if (idcorpoNew != null) {
                idcorpoNew = em.getReference(idcorpoNew.getClass(), idcorpoNew.getIdcorpo());
                atleta.setIdcorpo(idcorpoNew);
            }
            List<Mensagemcomum> attachedMensagemcomumListNew = new ArrayList<Mensagemcomum>();
            for (Mensagemcomum mensagemcomumListNewMensagemcomumToAttach : mensagemcomumListNew) {
                mensagemcomumListNewMensagemcomumToAttach = em.getReference(mensagemcomumListNewMensagemcomumToAttach.getClass(), mensagemcomumListNewMensagemcomumToAttach.getIdmensagemComum());
                attachedMensagemcomumListNew.add(mensagemcomumListNewMensagemcomumToAttach);
            }
            mensagemcomumListNew = attachedMensagemcomumListNew;
            atleta.setMensagemcomumList(mensagemcomumListNew);
            List<Mensagemcomum> attachedMensagemcomumList1New = new ArrayList<Mensagemcomum>();
            for (Mensagemcomum mensagemcomumList1NewMensagemcomumToAttach : mensagemcomumList1New) {
                mensagemcomumList1NewMensagemcomumToAttach = em.getReference(mensagemcomumList1NewMensagemcomumToAttach.getClass(), mensagemcomumList1NewMensagemcomumToAttach.getIdmensagemComum());
                attachedMensagemcomumList1New.add(mensagemcomumList1NewMensagemcomumToAttach);
            }
            mensagemcomumList1New = attachedMensagemcomumList1New;
            atleta.setMensagemcomumList1(mensagemcomumList1New);
            List<Solicitacaorelacionamento> attachedSolicitacaorelacionamentoListNew = new ArrayList<Solicitacaorelacionamento>();
            for (Solicitacaorelacionamento solicitacaorelacionamentoListNewSolicitacaorelacionamentoToAttach : solicitacaorelacionamentoListNew) {
                solicitacaorelacionamentoListNewSolicitacaorelacionamentoToAttach = em.getReference(solicitacaorelacionamentoListNewSolicitacaorelacionamentoToAttach.getClass(), solicitacaorelacionamentoListNewSolicitacaorelacionamentoToAttach.getIdsolicitacao());
                attachedSolicitacaorelacionamentoListNew.add(solicitacaorelacionamentoListNewSolicitacaorelacionamentoToAttach);
            }
            solicitacaorelacionamentoListNew = attachedSolicitacaorelacionamentoListNew;
            atleta.setSolicitacaorelacionamentoList(solicitacaorelacionamentoListNew);
            List<Relacionamento> attachedRelacionamentoListNew = new ArrayList<Relacionamento>();
            for (Relacionamento relacionamentoListNewRelacionamentoToAttach : relacionamentoListNew) {
                relacionamentoListNewRelacionamentoToAttach = em.getReference(relacionamentoListNewRelacionamentoToAttach.getClass(), relacionamentoListNewRelacionamentoToAttach.getIdrelacionamento());
                attachedRelacionamentoListNew.add(relacionamentoListNewRelacionamentoToAttach);
            }
            relacionamentoListNew = attachedRelacionamentoListNew;
            atleta.setRelacionamentoList(relacionamentoListNew);
            atleta = em.merge(atleta);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getAtletaList().remove(atleta);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getAtletaList().add(atleta);
                idusuarioNew = em.merge(idusuarioNew);
            }
            if (idcorpoOld != null && !idcorpoOld.equals(idcorpoNew)) {
                idcorpoOld.getAtletaList().remove(atleta);
                idcorpoOld = em.merge(idcorpoOld);
            }
            if (idcorpoNew != null && !idcorpoNew.equals(idcorpoOld)) {
                idcorpoNew.getAtletaList().add(atleta);
                idcorpoNew = em.merge(idcorpoNew);
            }
            for (Mensagemcomum mensagemcomumListNewMensagemcomum : mensagemcomumListNew) {
                if (!mensagemcomumListOld.contains(mensagemcomumListNewMensagemcomum)) {
                    Atleta oldIdAtletaEmissorOfMensagemcomumListNewMensagemcomum = mensagemcomumListNewMensagemcomum.getIdAtletaEmissor();
                    mensagemcomumListNewMensagemcomum.setIdAtletaEmissor(atleta);
                    mensagemcomumListNewMensagemcomum = em.merge(mensagemcomumListNewMensagemcomum);
                    if (oldIdAtletaEmissorOfMensagemcomumListNewMensagemcomum != null && !oldIdAtletaEmissorOfMensagemcomumListNewMensagemcomum.equals(atleta)) {
                        oldIdAtletaEmissorOfMensagemcomumListNewMensagemcomum.getMensagemcomumList().remove(mensagemcomumListNewMensagemcomum);
                        oldIdAtletaEmissorOfMensagemcomumListNewMensagemcomum = em.merge(oldIdAtletaEmissorOfMensagemcomumListNewMensagemcomum);
                    }
                }
            }
            for (Mensagemcomum mensagemcomumList1NewMensagemcomum : mensagemcomumList1New) {
                if (!mensagemcomumList1Old.contains(mensagemcomumList1NewMensagemcomum)) {
                    Atleta oldIdAtletaDestinatarioOfMensagemcomumList1NewMensagemcomum = mensagemcomumList1NewMensagemcomum.getIdAtletaDestinatario();
                    mensagemcomumList1NewMensagemcomum.setIdAtletaDestinatario(atleta);
                    mensagemcomumList1NewMensagemcomum = em.merge(mensagemcomumList1NewMensagemcomum);
                    if (oldIdAtletaDestinatarioOfMensagemcomumList1NewMensagemcomum != null && !oldIdAtletaDestinatarioOfMensagemcomumList1NewMensagemcomum.equals(atleta)) {
                        oldIdAtletaDestinatarioOfMensagemcomumList1NewMensagemcomum.getMensagemcomumList1().remove(mensagemcomumList1NewMensagemcomum);
                        oldIdAtletaDestinatarioOfMensagemcomumList1NewMensagemcomum = em.merge(oldIdAtletaDestinatarioOfMensagemcomumList1NewMensagemcomum);
                    }
                }
            }
            for (Solicitacaorelacionamento solicitacaorelacionamentoListNewSolicitacaorelacionamento : solicitacaorelacionamentoListNew) {
                if (!solicitacaorelacionamentoListOld.contains(solicitacaorelacionamentoListNewSolicitacaorelacionamento)) {
                    Atleta oldIdatletaOfSolicitacaorelacionamentoListNewSolicitacaorelacionamento = solicitacaorelacionamentoListNewSolicitacaorelacionamento.getIdatleta();
                    solicitacaorelacionamentoListNewSolicitacaorelacionamento.setIdatleta(atleta);
                    solicitacaorelacionamentoListNewSolicitacaorelacionamento = em.merge(solicitacaorelacionamentoListNewSolicitacaorelacionamento);
                    if (oldIdatletaOfSolicitacaorelacionamentoListNewSolicitacaorelacionamento != null && !oldIdatletaOfSolicitacaorelacionamentoListNewSolicitacaorelacionamento.equals(atleta)) {
                        oldIdatletaOfSolicitacaorelacionamentoListNewSolicitacaorelacionamento.getSolicitacaorelacionamentoList().remove(solicitacaorelacionamentoListNewSolicitacaorelacionamento);
                        oldIdatletaOfSolicitacaorelacionamentoListNewSolicitacaorelacionamento = em.merge(oldIdatletaOfSolicitacaorelacionamentoListNewSolicitacaorelacionamento);
                    }
                }
            }
            for (Relacionamento relacionamentoListNewRelacionamento : relacionamentoListNew) {
                if (!relacionamentoListOld.contains(relacionamentoListNewRelacionamento)) {
                    Atleta oldIdatletaOfRelacionamentoListNewRelacionamento = relacionamentoListNewRelacionamento.getIdatleta();
                    relacionamentoListNewRelacionamento.setIdatleta(atleta);
                    relacionamentoListNewRelacionamento = em.merge(relacionamentoListNewRelacionamento);
                    if (oldIdatletaOfRelacionamentoListNewRelacionamento != null && !oldIdatletaOfRelacionamentoListNewRelacionamento.equals(atleta)) {
                        oldIdatletaOfRelacionamentoListNewRelacionamento.getRelacionamentoList().remove(relacionamentoListNewRelacionamento);
                        oldIdatletaOfRelacionamentoListNewRelacionamento = em.merge(oldIdatletaOfRelacionamentoListNewRelacionamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = atleta.getIdatleta();
                if (findAtleta(id) == null) {
                    throw new NonexistentEntityException("The atleta with id " + id + " no longer exists.");
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
            Atleta atleta;
            try {
                atleta = em.getReference(Atleta.class, id);
                atleta.getIdatleta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The atleta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Mensagemcomum> mensagemcomumListOrphanCheck = atleta.getMensagemcomumList();
            for (Mensagemcomum mensagemcomumListOrphanCheckMensagemcomum : mensagemcomumListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Atleta (" + atleta + ") cannot be destroyed since the Mensagemcomum " + mensagemcomumListOrphanCheckMensagemcomum + " in its mensagemcomumList field has a non-nullable idAtletaEmissor field.");
            }
            List<Mensagemcomum> mensagemcomumList1OrphanCheck = atleta.getMensagemcomumList1();
            for (Mensagemcomum mensagemcomumList1OrphanCheckMensagemcomum : mensagemcomumList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Atleta (" + atleta + ") cannot be destroyed since the Mensagemcomum " + mensagemcomumList1OrphanCheckMensagemcomum + " in its mensagemcomumList1 field has a non-nullable idAtletaDestinatario field.");
            }
            List<Solicitacaorelacionamento> solicitacaorelacionamentoListOrphanCheck = atleta.getSolicitacaorelacionamentoList();
            for (Solicitacaorelacionamento solicitacaorelacionamentoListOrphanCheckSolicitacaorelacionamento : solicitacaorelacionamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Atleta (" + atleta + ") cannot be destroyed since the Solicitacaorelacionamento " + solicitacaorelacionamentoListOrphanCheckSolicitacaorelacionamento + " in its solicitacaorelacionamentoList field has a non-nullable idatleta field.");
            }
            List<Relacionamento> relacionamentoListOrphanCheck = atleta.getRelacionamentoList();
            for (Relacionamento relacionamentoListOrphanCheckRelacionamento : relacionamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Atleta (" + atleta + ") cannot be destroyed since the Relacionamento " + relacionamentoListOrphanCheckRelacionamento + " in its relacionamentoList field has a non-nullable idatleta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario idusuario = atleta.getIdusuario();
            if (idusuario != null) {
                idusuario.getAtletaList().remove(atleta);
                idusuario = em.merge(idusuario);
            }
            Corpo idcorpo = atleta.getIdcorpo();
            if (idcorpo != null) {
                idcorpo.getAtletaList().remove(atleta);
                idcorpo = em.merge(idcorpo);
            }
            em.remove(atleta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Atleta> findAtletaEntities() {
        return findAtletaEntities(true, -1, -1);
    }

    public List<Atleta> findAtletaEntities(int maxResults, int firstResult) {
        return findAtletaEntities(false, maxResults, firstResult);
    }

    private List<Atleta> findAtletaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Atleta.class));
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

    public Atleta findAtleta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Atleta.class, id);
        } finally {
            em.close();
        }
    }

    public int getAtletaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Atleta> rt = cq.from(Atleta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Atleta findAthByUser(Usuario user){
        
        String query = "SELECT ath FROM Atleta ath WHERE ath.idusuario.idusuario = :user"; 
        
        Query q = getEntityManager().createQuery(query);
        
        q.setParameter("user", user.getIdusuario());       
        
        try{
            return (Atleta) q.getSingleResult();
        }catch(Exception ex){
            return null;
        }
    }
    
}
