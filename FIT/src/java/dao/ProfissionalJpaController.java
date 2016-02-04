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
import model.Mensagempreconsultoria;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Atleta;
import model.Consultoria;
import model.Personal;
import model.Solicitacaorelacionamento;
import model.Nutricionista;
import model.Profissional;
import model.Relacionamento;

/**
 *
 * @author asdfrofl
 */
public class ProfissionalJpaController implements Serializable {

    public ProfissionalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long create(Profissional profissional) {
        if (profissional.getMensagempreconsultoriaList() == null) {
            profissional.setMensagempreconsultoriaList(new ArrayList<Mensagempreconsultoria>());
        }
        if (profissional.getConsultoriaList() == null) {
            profissional.setConsultoriaList(new ArrayList<Consultoria>());
        }
        if (profissional.getPersonalList() == null) {
            profissional.setPersonalList(new ArrayList<Personal>());
        }
        if (profissional.getSolicitacaorelacionamentoList() == null) {
            profissional.setSolicitacaorelacionamentoList(new ArrayList<Solicitacaorelacionamento>());
        }
        if (profissional.getNutricionistaList() == null) {
            profissional.setNutricionistaList(new ArrayList<Nutricionista>());
        }
        if (profissional.getRelacionamentoList() == null) {
            profissional.setRelacionamentoList(new ArrayList<Relacionamento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = profissional.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                profissional.setIdusuario(idusuario);
            }
            List<Mensagempreconsultoria> attachedMensagempreconsultoriaList = new ArrayList<Mensagempreconsultoria>();
            for (Mensagempreconsultoria mensagempreconsultoriaListMensagempreconsultoriaToAttach : profissional.getMensagempreconsultoriaList()) {
                mensagempreconsultoriaListMensagempreconsultoriaToAttach = em.getReference(mensagempreconsultoriaListMensagempreconsultoriaToAttach.getClass(), mensagempreconsultoriaListMensagempreconsultoriaToAttach.getIdmensagempreconsultoria());
                attachedMensagempreconsultoriaList.add(mensagempreconsultoriaListMensagempreconsultoriaToAttach);
            }
            profissional.setMensagempreconsultoriaList(attachedMensagempreconsultoriaList);
            List<Consultoria> attachedConsultoriaList = new ArrayList<Consultoria>();
            for (Consultoria consultoriaListConsultoriaToAttach : profissional.getConsultoriaList()) {
                consultoriaListConsultoriaToAttach = em.getReference(consultoriaListConsultoriaToAttach.getClass(), consultoriaListConsultoriaToAttach.getIdconsultoria());
                attachedConsultoriaList.add(consultoriaListConsultoriaToAttach);
            }
            profissional.setConsultoriaList(attachedConsultoriaList);
            List<Personal> attachedPersonalList = new ArrayList<Personal>();
            for (Personal personalListPersonalToAttach : profissional.getPersonalList()) {
                personalListPersonalToAttach = em.getReference(personalListPersonalToAttach.getClass(), personalListPersonalToAttach.getIdpersonal());
                attachedPersonalList.add(personalListPersonalToAttach);
            }
            profissional.setPersonalList(attachedPersonalList);
            List<Solicitacaorelacionamento> attachedSolicitacaorelacionamentoList = new ArrayList<Solicitacaorelacionamento>();
            for (Solicitacaorelacionamento solicitacaorelacionamentoListSolicitacaorelacionamentoToAttach : profissional.getSolicitacaorelacionamentoList()) {
                solicitacaorelacionamentoListSolicitacaorelacionamentoToAttach = em.getReference(solicitacaorelacionamentoListSolicitacaorelacionamentoToAttach.getClass(), solicitacaorelacionamentoListSolicitacaorelacionamentoToAttach.getIdsolicitacao());
                attachedSolicitacaorelacionamentoList.add(solicitacaorelacionamentoListSolicitacaorelacionamentoToAttach);
            }
            profissional.setSolicitacaorelacionamentoList(attachedSolicitacaorelacionamentoList);
            List<Nutricionista> attachedNutricionistaList = new ArrayList<Nutricionista>();
            for (Nutricionista nutricionistaListNutricionistaToAttach : profissional.getNutricionistaList()) {
                nutricionistaListNutricionistaToAttach = em.getReference(nutricionistaListNutricionistaToAttach.getClass(), nutricionistaListNutricionistaToAttach.getIdnutricionista());
                attachedNutricionistaList.add(nutricionistaListNutricionistaToAttach);
            }
            profissional.setNutricionistaList(attachedNutricionistaList);
            List<Relacionamento> attachedRelacionamentoList = new ArrayList<Relacionamento>();
            for (Relacionamento relacionamentoListRelacionamentoToAttach : profissional.getRelacionamentoList()) {
                relacionamentoListRelacionamentoToAttach = em.getReference(relacionamentoListRelacionamentoToAttach.getClass(), relacionamentoListRelacionamentoToAttach.getIdrelacionamento());
                attachedRelacionamentoList.add(relacionamentoListRelacionamentoToAttach);
            }
            profissional.setRelacionamentoList(attachedRelacionamentoList);
            em.persist(profissional);
            if (idusuario != null) {
                idusuario.getProfissionalList().add(profissional);
                idusuario = em.merge(idusuario);
            }
            for (Mensagempreconsultoria mensagempreconsultoriaListMensagempreconsultoria : profissional.getMensagempreconsultoriaList()) {
                Profissional oldIdprofissionalOfMensagempreconsultoriaListMensagempreconsultoria = mensagempreconsultoriaListMensagempreconsultoria.getIdprofissional();
                mensagempreconsultoriaListMensagempreconsultoria.setIdprofissional(profissional);
                mensagempreconsultoriaListMensagempreconsultoria = em.merge(mensagempreconsultoriaListMensagempreconsultoria);
                if (oldIdprofissionalOfMensagempreconsultoriaListMensagempreconsultoria != null) {
                    oldIdprofissionalOfMensagempreconsultoriaListMensagempreconsultoria.getMensagempreconsultoriaList().remove(mensagempreconsultoriaListMensagempreconsultoria);
                    oldIdprofissionalOfMensagempreconsultoriaListMensagempreconsultoria = em.merge(oldIdprofissionalOfMensagempreconsultoriaListMensagempreconsultoria);
                }
            }
            for (Consultoria consultoriaListConsultoria : profissional.getConsultoriaList()) {
                Profissional oldIdprofissionalOfConsultoriaListConsultoria = consultoriaListConsultoria.getIdprofissional();
                consultoriaListConsultoria.setIdprofissional(profissional);
                consultoriaListConsultoria = em.merge(consultoriaListConsultoria);
                if (oldIdprofissionalOfConsultoriaListConsultoria != null) {
                    oldIdprofissionalOfConsultoriaListConsultoria.getConsultoriaList().remove(consultoriaListConsultoria);
                    oldIdprofissionalOfConsultoriaListConsultoria = em.merge(oldIdprofissionalOfConsultoriaListConsultoria);
                }
            }
            for (Personal personalListPersonal : profissional.getPersonalList()) {
                Profissional oldIdprofissionalOfPersonalListPersonal = personalListPersonal.getIdprofissional();
                personalListPersonal.setIdprofissional(profissional);
                personalListPersonal = em.merge(personalListPersonal);
                if (oldIdprofissionalOfPersonalListPersonal != null) {
                    oldIdprofissionalOfPersonalListPersonal.getPersonalList().remove(personalListPersonal);
                    oldIdprofissionalOfPersonalListPersonal = em.merge(oldIdprofissionalOfPersonalListPersonal);
                }
            }
            for (Solicitacaorelacionamento solicitacaorelacionamentoListSolicitacaorelacionamento : profissional.getSolicitacaorelacionamentoList()) {
                Profissional oldIdprofissionalOfSolicitacaorelacionamentoListSolicitacaorelacionamento = solicitacaorelacionamentoListSolicitacaorelacionamento.getIdprofissional();
                solicitacaorelacionamentoListSolicitacaorelacionamento.setIdprofissional(profissional);
                solicitacaorelacionamentoListSolicitacaorelacionamento = em.merge(solicitacaorelacionamentoListSolicitacaorelacionamento);
                if (oldIdprofissionalOfSolicitacaorelacionamentoListSolicitacaorelacionamento != null) {
                    oldIdprofissionalOfSolicitacaorelacionamentoListSolicitacaorelacionamento.getSolicitacaorelacionamentoList().remove(solicitacaorelacionamentoListSolicitacaorelacionamento);
                    oldIdprofissionalOfSolicitacaorelacionamentoListSolicitacaorelacionamento = em.merge(oldIdprofissionalOfSolicitacaorelacionamentoListSolicitacaorelacionamento);
                }
            }
            for (Nutricionista nutricionistaListNutricionista : profissional.getNutricionistaList()) {
                Profissional oldIdprofissionalOfNutricionistaListNutricionista = nutricionistaListNutricionista.getIdprofissional();
                nutricionistaListNutricionista.setIdprofissional(profissional);
                nutricionistaListNutricionista = em.merge(nutricionistaListNutricionista);
                if (oldIdprofissionalOfNutricionistaListNutricionista != null) {
                    oldIdprofissionalOfNutricionistaListNutricionista.getNutricionistaList().remove(nutricionistaListNutricionista);
                    oldIdprofissionalOfNutricionistaListNutricionista = em.merge(oldIdprofissionalOfNutricionistaListNutricionista);
                }
            }
            for (Relacionamento relacionamentoListRelacionamento : profissional.getRelacionamentoList()) {
                Profissional oldIdprofissionalOfRelacionamentoListRelacionamento = relacionamentoListRelacionamento.getIdprofissional();
                relacionamentoListRelacionamento.setIdprofissional(profissional);
                relacionamentoListRelacionamento = em.merge(relacionamentoListRelacionamento);
                if (oldIdprofissionalOfRelacionamentoListRelacionamento != null) {
                    oldIdprofissionalOfRelacionamentoListRelacionamento.getRelacionamentoList().remove(relacionamentoListRelacionamento);
                    oldIdprofissionalOfRelacionamentoListRelacionamento = em.merge(oldIdprofissionalOfRelacionamentoListRelacionamento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return profissional.getIdprofissional();
    }

    public void edit(Profissional profissional) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profissional persistentProfissional = em.find(Profissional.class, profissional.getIdprofissional());
            Usuario idusuarioOld = persistentProfissional.getIdusuario();
            Usuario idusuarioNew = profissional.getIdusuario();
            List<Mensagempreconsultoria> mensagempreconsultoriaListOld = persistentProfissional.getMensagempreconsultoriaList();
            List<Mensagempreconsultoria> mensagempreconsultoriaListNew = profissional.getMensagempreconsultoriaList();
            List<Consultoria> consultoriaListOld = persistentProfissional.getConsultoriaList();
            List<Consultoria> consultoriaListNew = profissional.getConsultoriaList();
            List<Personal> personalListOld = persistentProfissional.getPersonalList();
            List<Personal> personalListNew = profissional.getPersonalList();
            List<Solicitacaorelacionamento> solicitacaorelacionamentoListOld = persistentProfissional.getSolicitacaorelacionamentoList();
            List<Solicitacaorelacionamento> solicitacaorelacionamentoListNew = profissional.getSolicitacaorelacionamentoList();
            List<Nutricionista> nutricionistaListOld = persistentProfissional.getNutricionistaList();
            List<Nutricionista> nutricionistaListNew = profissional.getNutricionistaList();
            List<Relacionamento> relacionamentoListOld = persistentProfissional.getRelacionamentoList();
            List<Relacionamento> relacionamentoListNew = profissional.getRelacionamentoList();
            List<String> illegalOrphanMessages = null;
            for (Mensagempreconsultoria mensagempreconsultoriaListOldMensagempreconsultoria : mensagempreconsultoriaListOld) {
                if (!mensagempreconsultoriaListNew.contains(mensagempreconsultoriaListOldMensagempreconsultoria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mensagempreconsultoria " + mensagempreconsultoriaListOldMensagempreconsultoria + " since its idprofissional field is not nullable.");
                }
            }
            for (Consultoria consultoriaListOldConsultoria : consultoriaListOld) {
                if (!consultoriaListNew.contains(consultoriaListOldConsultoria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Consultoria " + consultoriaListOldConsultoria + " since its idprofissional field is not nullable.");
                }
            }
            for (Personal personalListOldPersonal : personalListOld) {
                if (!personalListNew.contains(personalListOldPersonal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Personal " + personalListOldPersonal + " since its idprofissional field is not nullable.");
                }
            }
            for (Solicitacaorelacionamento solicitacaorelacionamentoListOldSolicitacaorelacionamento : solicitacaorelacionamentoListOld) {
                if (!solicitacaorelacionamentoListNew.contains(solicitacaorelacionamentoListOldSolicitacaorelacionamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitacaorelacionamento " + solicitacaorelacionamentoListOldSolicitacaorelacionamento + " since its idprofissional field is not nullable.");
                }
            }
            for (Nutricionista nutricionistaListOldNutricionista : nutricionistaListOld) {
                if (!nutricionistaListNew.contains(nutricionistaListOldNutricionista)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Nutricionista " + nutricionistaListOldNutricionista + " since its idprofissional field is not nullable.");
                }
            }
            for (Relacionamento relacionamentoListOldRelacionamento : relacionamentoListOld) {
                if (!relacionamentoListNew.contains(relacionamentoListOldRelacionamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Relacionamento " + relacionamentoListOldRelacionamento + " since its idprofissional field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                profissional.setIdusuario(idusuarioNew);
            }
            List<Mensagempreconsultoria> attachedMensagempreconsultoriaListNew = new ArrayList<Mensagempreconsultoria>();
            for (Mensagempreconsultoria mensagempreconsultoriaListNewMensagempreconsultoriaToAttach : mensagempreconsultoriaListNew) {
                mensagempreconsultoriaListNewMensagempreconsultoriaToAttach = em.getReference(mensagempreconsultoriaListNewMensagempreconsultoriaToAttach.getClass(), mensagempreconsultoriaListNewMensagempreconsultoriaToAttach.getIdmensagempreconsultoria());
                attachedMensagempreconsultoriaListNew.add(mensagempreconsultoriaListNewMensagempreconsultoriaToAttach);
            }
            mensagempreconsultoriaListNew = attachedMensagempreconsultoriaListNew;
            profissional.setMensagempreconsultoriaList(mensagempreconsultoriaListNew);
            List<Consultoria> attachedConsultoriaListNew = new ArrayList<Consultoria>();
            for (Consultoria consultoriaListNewConsultoriaToAttach : consultoriaListNew) {
                consultoriaListNewConsultoriaToAttach = em.getReference(consultoriaListNewConsultoriaToAttach.getClass(), consultoriaListNewConsultoriaToAttach.getIdconsultoria());
                attachedConsultoriaListNew.add(consultoriaListNewConsultoriaToAttach);
            }
            consultoriaListNew = attachedConsultoriaListNew;
            profissional.setConsultoriaList(consultoriaListNew);
            List<Personal> attachedPersonalListNew = new ArrayList<Personal>();
            for (Personal personalListNewPersonalToAttach : personalListNew) {
                personalListNewPersonalToAttach = em.getReference(personalListNewPersonalToAttach.getClass(), personalListNewPersonalToAttach.getIdpersonal());
                attachedPersonalListNew.add(personalListNewPersonalToAttach);
            }
            personalListNew = attachedPersonalListNew;
            profissional.setPersonalList(personalListNew);
            List<Solicitacaorelacionamento> attachedSolicitacaorelacionamentoListNew = new ArrayList<Solicitacaorelacionamento>();
            for (Solicitacaorelacionamento solicitacaorelacionamentoListNewSolicitacaorelacionamentoToAttach : solicitacaorelacionamentoListNew) {
                solicitacaorelacionamentoListNewSolicitacaorelacionamentoToAttach = em.getReference(solicitacaorelacionamentoListNewSolicitacaorelacionamentoToAttach.getClass(), solicitacaorelacionamentoListNewSolicitacaorelacionamentoToAttach.getIdsolicitacao());
                attachedSolicitacaorelacionamentoListNew.add(solicitacaorelacionamentoListNewSolicitacaorelacionamentoToAttach);
            }
            solicitacaorelacionamentoListNew = attachedSolicitacaorelacionamentoListNew;
            profissional.setSolicitacaorelacionamentoList(solicitacaorelacionamentoListNew);
            List<Nutricionista> attachedNutricionistaListNew = new ArrayList<Nutricionista>();
            for (Nutricionista nutricionistaListNewNutricionistaToAttach : nutricionistaListNew) {
                nutricionistaListNewNutricionistaToAttach = em.getReference(nutricionistaListNewNutricionistaToAttach.getClass(), nutricionistaListNewNutricionistaToAttach.getIdnutricionista());
                attachedNutricionistaListNew.add(nutricionistaListNewNutricionistaToAttach);
            }
            nutricionistaListNew = attachedNutricionistaListNew;
            profissional.setNutricionistaList(nutricionistaListNew);
            List<Relacionamento> attachedRelacionamentoListNew = new ArrayList<Relacionamento>();
            for (Relacionamento relacionamentoListNewRelacionamentoToAttach : relacionamentoListNew) {
                relacionamentoListNewRelacionamentoToAttach = em.getReference(relacionamentoListNewRelacionamentoToAttach.getClass(), relacionamentoListNewRelacionamentoToAttach.getIdrelacionamento());
                attachedRelacionamentoListNew.add(relacionamentoListNewRelacionamentoToAttach);
            }
            relacionamentoListNew = attachedRelacionamentoListNew;
            profissional.setRelacionamentoList(relacionamentoListNew);
            profissional = em.merge(profissional);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getProfissionalList().remove(profissional);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getProfissionalList().add(profissional);
                idusuarioNew = em.merge(idusuarioNew);
            }
            for (Mensagempreconsultoria mensagempreconsultoriaListNewMensagempreconsultoria : mensagempreconsultoriaListNew) {
                if (!mensagempreconsultoriaListOld.contains(mensagempreconsultoriaListNewMensagempreconsultoria)) {
                    Profissional oldIdprofissionalOfMensagempreconsultoriaListNewMensagempreconsultoria = mensagempreconsultoriaListNewMensagempreconsultoria.getIdprofissional();
                    mensagempreconsultoriaListNewMensagempreconsultoria.setIdprofissional(profissional);
                    mensagempreconsultoriaListNewMensagempreconsultoria = em.merge(mensagempreconsultoriaListNewMensagempreconsultoria);
                    if (oldIdprofissionalOfMensagempreconsultoriaListNewMensagempreconsultoria != null && !oldIdprofissionalOfMensagempreconsultoriaListNewMensagempreconsultoria.equals(profissional)) {
                        oldIdprofissionalOfMensagempreconsultoriaListNewMensagempreconsultoria.getMensagempreconsultoriaList().remove(mensagempreconsultoriaListNewMensagempreconsultoria);
                        oldIdprofissionalOfMensagempreconsultoriaListNewMensagempreconsultoria = em.merge(oldIdprofissionalOfMensagempreconsultoriaListNewMensagempreconsultoria);
                    }
                }
            }
            for (Consultoria consultoriaListNewConsultoria : consultoriaListNew) {
                if (!consultoriaListOld.contains(consultoriaListNewConsultoria)) {
                    Profissional oldIdprofissionalOfConsultoriaListNewConsultoria = consultoriaListNewConsultoria.getIdprofissional();
                    consultoriaListNewConsultoria.setIdprofissional(profissional);
                    consultoriaListNewConsultoria = em.merge(consultoriaListNewConsultoria);
                    if (oldIdprofissionalOfConsultoriaListNewConsultoria != null && !oldIdprofissionalOfConsultoriaListNewConsultoria.equals(profissional)) {
                        oldIdprofissionalOfConsultoriaListNewConsultoria.getConsultoriaList().remove(consultoriaListNewConsultoria);
                        oldIdprofissionalOfConsultoriaListNewConsultoria = em.merge(oldIdprofissionalOfConsultoriaListNewConsultoria);
                    }
                }
            }
            for (Personal personalListNewPersonal : personalListNew) {
                if (!personalListOld.contains(personalListNewPersonal)) {
                    Profissional oldIdprofissionalOfPersonalListNewPersonal = personalListNewPersonal.getIdprofissional();
                    personalListNewPersonal.setIdprofissional(profissional);
                    personalListNewPersonal = em.merge(personalListNewPersonal);
                    if (oldIdprofissionalOfPersonalListNewPersonal != null && !oldIdprofissionalOfPersonalListNewPersonal.equals(profissional)) {
                        oldIdprofissionalOfPersonalListNewPersonal.getPersonalList().remove(personalListNewPersonal);
                        oldIdprofissionalOfPersonalListNewPersonal = em.merge(oldIdprofissionalOfPersonalListNewPersonal);
                    }
                }
            }
            for (Solicitacaorelacionamento solicitacaorelacionamentoListNewSolicitacaorelacionamento : solicitacaorelacionamentoListNew) {
                if (!solicitacaorelacionamentoListOld.contains(solicitacaorelacionamentoListNewSolicitacaorelacionamento)) {
                    Profissional oldIdprofissionalOfSolicitacaorelacionamentoListNewSolicitacaorelacionamento = solicitacaorelacionamentoListNewSolicitacaorelacionamento.getIdprofissional();
                    solicitacaorelacionamentoListNewSolicitacaorelacionamento.setIdprofissional(profissional);
                    solicitacaorelacionamentoListNewSolicitacaorelacionamento = em.merge(solicitacaorelacionamentoListNewSolicitacaorelacionamento);
                    if (oldIdprofissionalOfSolicitacaorelacionamentoListNewSolicitacaorelacionamento != null && !oldIdprofissionalOfSolicitacaorelacionamentoListNewSolicitacaorelacionamento.equals(profissional)) {
                        oldIdprofissionalOfSolicitacaorelacionamentoListNewSolicitacaorelacionamento.getSolicitacaorelacionamentoList().remove(solicitacaorelacionamentoListNewSolicitacaorelacionamento);
                        oldIdprofissionalOfSolicitacaorelacionamentoListNewSolicitacaorelacionamento = em.merge(oldIdprofissionalOfSolicitacaorelacionamentoListNewSolicitacaorelacionamento);
                    }
                }
            }
            for (Nutricionista nutricionistaListNewNutricionista : nutricionistaListNew) {
                if (!nutricionistaListOld.contains(nutricionistaListNewNutricionista)) {
                    Profissional oldIdprofissionalOfNutricionistaListNewNutricionista = nutricionistaListNewNutricionista.getIdprofissional();
                    nutricionistaListNewNutricionista.setIdprofissional(profissional);
                    nutricionistaListNewNutricionista = em.merge(nutricionistaListNewNutricionista);
                    if (oldIdprofissionalOfNutricionistaListNewNutricionista != null && !oldIdprofissionalOfNutricionistaListNewNutricionista.equals(profissional)) {
                        oldIdprofissionalOfNutricionistaListNewNutricionista.getNutricionistaList().remove(nutricionistaListNewNutricionista);
                        oldIdprofissionalOfNutricionistaListNewNutricionista = em.merge(oldIdprofissionalOfNutricionistaListNewNutricionista);
                    }
                }
            }
            for (Relacionamento relacionamentoListNewRelacionamento : relacionamentoListNew) {
                if (!relacionamentoListOld.contains(relacionamentoListNewRelacionamento)) {
                    Profissional oldIdprofissionalOfRelacionamentoListNewRelacionamento = relacionamentoListNewRelacionamento.getIdprofissional();
                    relacionamentoListNewRelacionamento.setIdprofissional(profissional);
                    relacionamentoListNewRelacionamento = em.merge(relacionamentoListNewRelacionamento);
                    if (oldIdprofissionalOfRelacionamentoListNewRelacionamento != null && !oldIdprofissionalOfRelacionamentoListNewRelacionamento.equals(profissional)) {
                        oldIdprofissionalOfRelacionamentoListNewRelacionamento.getRelacionamentoList().remove(relacionamentoListNewRelacionamento);
                        oldIdprofissionalOfRelacionamentoListNewRelacionamento = em.merge(oldIdprofissionalOfRelacionamentoListNewRelacionamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = profissional.getIdprofissional();
                if (findProfissional(id) == null) {
                    throw new NonexistentEntityException("The profissional with id " + id + " no longer exists.");
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
            Profissional profissional;
            try {
                profissional = em.getReference(Profissional.class, id);
                profissional.getIdprofissional();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The profissional with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Mensagempreconsultoria> mensagempreconsultoriaListOrphanCheck = profissional.getMensagempreconsultoriaList();
            for (Mensagempreconsultoria mensagempreconsultoriaListOrphanCheckMensagempreconsultoria : mensagempreconsultoriaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Profissional (" + profissional + ") cannot be destroyed since the Mensagempreconsultoria " + mensagempreconsultoriaListOrphanCheckMensagempreconsultoria + " in its mensagempreconsultoriaList field has a non-nullable idprofissional field.");
            }
            List<Consultoria> consultoriaListOrphanCheck = profissional.getConsultoriaList();
            for (Consultoria consultoriaListOrphanCheckConsultoria : consultoriaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Profissional (" + profissional + ") cannot be destroyed since the Consultoria " + consultoriaListOrphanCheckConsultoria + " in its consultoriaList field has a non-nullable idprofissional field.");
            }
            List<Personal> personalListOrphanCheck = profissional.getPersonalList();
            for (Personal personalListOrphanCheckPersonal : personalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Profissional (" + profissional + ") cannot be destroyed since the Personal " + personalListOrphanCheckPersonal + " in its personalList field has a non-nullable idprofissional field.");
            }
            List<Solicitacaorelacionamento> solicitacaorelacionamentoListOrphanCheck = profissional.getSolicitacaorelacionamentoList();
            for (Solicitacaorelacionamento solicitacaorelacionamentoListOrphanCheckSolicitacaorelacionamento : solicitacaorelacionamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Profissional (" + profissional + ") cannot be destroyed since the Solicitacaorelacionamento " + solicitacaorelacionamentoListOrphanCheckSolicitacaorelacionamento + " in its solicitacaorelacionamentoList field has a non-nullable idprofissional field.");
            }
            List<Nutricionista> nutricionistaListOrphanCheck = profissional.getNutricionistaList();
            for (Nutricionista nutricionistaListOrphanCheckNutricionista : nutricionistaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Profissional (" + profissional + ") cannot be destroyed since the Nutricionista " + nutricionistaListOrphanCheckNutricionista + " in its nutricionistaList field has a non-nullable idprofissional field.");
            }
            List<Relacionamento> relacionamentoListOrphanCheck = profissional.getRelacionamentoList();
            for (Relacionamento relacionamentoListOrphanCheckRelacionamento : relacionamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Profissional (" + profissional + ") cannot be destroyed since the Relacionamento " + relacionamentoListOrphanCheckRelacionamento + " in its relacionamentoList field has a non-nullable idprofissional field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario idusuario = profissional.getIdusuario();
            if (idusuario != null) {
                idusuario.getProfissionalList().remove(profissional);
                idusuario = em.merge(idusuario);
            }
            em.remove(profissional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Profissional> findProfissionalEntities() {
        return findProfissionalEntities(true, -1, -1);
    }

    public List<Profissional> findProfissionalEntities(int maxResults, int firstResult) {
        return findProfissionalEntities(false, maxResults, firstResult);
    }

    private List<Profissional> findProfissionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Profissional.class));
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

    public Profissional findProfissional(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Profissional.class, id);
        } finally {
            em.close();
        }
    }

    public int getProfissionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Profissional> rt = cq.from(Profissional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Profissional findProfissionalByUser(Usuario user){
        
        String query = "SELECT prof FROM Profissional prof WHERE prof.idusuario.idusuario = :user"; 
        
        Query q = getEntityManager().createQuery(query);
        
        q.setParameter("user", user.getIdusuario());       
        
        try{
            return (Profissional) q.getSingleResult();
        }catch(Exception ex){
            return null;
        }
    }
    
}
