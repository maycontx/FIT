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
import model.Atleta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Solicitacaoevento;
import model.Chatconsultoria;
import model.Comentariolocal;
import model.Grupo;
import model.Compartilhamento;
import model.Blog;
import model.Local;
import model.Profissional;
import model.Mensagempreconsultoria;
import model.Avaliacaoartigo;
import model.Usuariogrupo;
import model.Evento;
import model.Likes;
import model.Seguidor;
import model.Solicitacaogrupo;
import model.Publicacao;
import model.Denuncia;
import model.Usuarioevento;
import model.Consultoriarealizada;
import model.Pagamento;
import model.Comentariopublicacao;
import model.Usuario;

/**
 *
 * @author asdfrofl
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getAtletaList() == null) {
            usuario.setAtletaList(new ArrayList<Atleta>());
        }
        if (usuario.getSolicitacaoeventoList() == null) {
            usuario.setSolicitacaoeventoList(new ArrayList<Solicitacaoevento>());
        }
        if (usuario.getChatconsultoriaList() == null) {
            usuario.setChatconsultoriaList(new ArrayList<Chatconsultoria>());
        }
        if (usuario.getChatconsultoriaList1() == null) {
            usuario.setChatconsultoriaList1(new ArrayList<Chatconsultoria>());
        }
        if (usuario.getComentariolocalList() == null) {
            usuario.setComentariolocalList(new ArrayList<Comentariolocal>());
        }
        if (usuario.getGrupoList() == null) {
            usuario.setGrupoList(new ArrayList<Grupo>());
        }
        if (usuario.getCompartilhamentoList() == null) {
            usuario.setCompartilhamentoList(new ArrayList<Compartilhamento>());
        }
        if (usuario.getBlogList() == null) {
            usuario.setBlogList(new ArrayList<Blog>());
        }
        if (usuario.getLocalList() == null) {
            usuario.setLocalList(new ArrayList<Local>());
        }
        if (usuario.getProfissionalList() == null) {
            usuario.setProfissionalList(new ArrayList<Profissional>());
        }
        if (usuario.getMensagempreconsultoriaList() == null) {
            usuario.setMensagempreconsultoriaList(new ArrayList<Mensagempreconsultoria>());
        }
        if (usuario.getAvaliacaoartigoList() == null) {
            usuario.setAvaliacaoartigoList(new ArrayList<Avaliacaoartigo>());
        }
        if (usuario.getUsuariogrupoList() == null) {
            usuario.setUsuariogrupoList(new ArrayList<Usuariogrupo>());
        }
        if (usuario.getEventoList() == null) {
            usuario.setEventoList(new ArrayList<Evento>());
        }
        if (usuario.getLikesList() == null) {
            usuario.setLikesList(new ArrayList<Likes>());
        }
        if (usuario.getSeguidorList() == null) {
            usuario.setSeguidorList(new ArrayList<Seguidor>());
        }
        if (usuario.getSeguidorList1() == null) {
            usuario.setSeguidorList1(new ArrayList<Seguidor>());
        }
        if (usuario.getSolicitacaogrupoList() == null) {
            usuario.setSolicitacaogrupoList(new ArrayList<Solicitacaogrupo>());
        }
        if (usuario.getPublicacaoList() == null) {
            usuario.setPublicacaoList(new ArrayList<Publicacao>());
        }
        if (usuario.getDenunciaList() == null) {
            usuario.setDenunciaList(new ArrayList<Denuncia>());
        }
        if (usuario.getUsuarioeventoList() == null) {
            usuario.setUsuarioeventoList(new ArrayList<Usuarioevento>());
        }
        if (usuario.getConsultoriarealizadaList() == null) {
            usuario.setConsultoriarealizadaList(new ArrayList<Consultoriarealizada>());
        }
        if (usuario.getPagamentoList() == null) {
            usuario.setPagamentoList(new ArrayList<Pagamento>());
        }
        if (usuario.getComentariopublicacaoList() == null) {
            usuario.setComentariopublicacaoList(new ArrayList<Comentariopublicacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Atleta> attachedAtletaList = new ArrayList<Atleta>();
            for (Atleta atletaListAtletaToAttach : usuario.getAtletaList()) {
                atletaListAtletaToAttach = em.getReference(atletaListAtletaToAttach.getClass(), atletaListAtletaToAttach.getIdatleta());
                attachedAtletaList.add(atletaListAtletaToAttach);
            }
            usuario.setAtletaList(attachedAtletaList);
            List<Solicitacaoevento> attachedSolicitacaoeventoList = new ArrayList<Solicitacaoevento>();
            for (Solicitacaoevento solicitacaoeventoListSolicitacaoeventoToAttach : usuario.getSolicitacaoeventoList()) {
                solicitacaoeventoListSolicitacaoeventoToAttach = em.getReference(solicitacaoeventoListSolicitacaoeventoToAttach.getClass(), solicitacaoeventoListSolicitacaoeventoToAttach.getIdsolicitacao());
                attachedSolicitacaoeventoList.add(solicitacaoeventoListSolicitacaoeventoToAttach);
            }
            usuario.setSolicitacaoeventoList(attachedSolicitacaoeventoList);
            List<Chatconsultoria> attachedChatconsultoriaList = new ArrayList<Chatconsultoria>();
            for (Chatconsultoria chatconsultoriaListChatconsultoriaToAttach : usuario.getChatconsultoriaList()) {
                chatconsultoriaListChatconsultoriaToAttach = em.getReference(chatconsultoriaListChatconsultoriaToAttach.getClass(), chatconsultoriaListChatconsultoriaToAttach.getIdchatconsultoria());
                attachedChatconsultoriaList.add(chatconsultoriaListChatconsultoriaToAttach);
            }
            usuario.setChatconsultoriaList(attachedChatconsultoriaList);
            List<Chatconsultoria> attachedChatconsultoriaList1 = new ArrayList<Chatconsultoria>();
            for (Chatconsultoria chatconsultoriaList1ChatconsultoriaToAttach : usuario.getChatconsultoriaList1()) {
                chatconsultoriaList1ChatconsultoriaToAttach = em.getReference(chatconsultoriaList1ChatconsultoriaToAttach.getClass(), chatconsultoriaList1ChatconsultoriaToAttach.getIdchatconsultoria());
                attachedChatconsultoriaList1.add(chatconsultoriaList1ChatconsultoriaToAttach);
            }
            usuario.setChatconsultoriaList1(attachedChatconsultoriaList1);
            List<Comentariolocal> attachedComentariolocalList = new ArrayList<Comentariolocal>();
            for (Comentariolocal comentariolocalListComentariolocalToAttach : usuario.getComentariolocalList()) {
                comentariolocalListComentariolocalToAttach = em.getReference(comentariolocalListComentariolocalToAttach.getClass(), comentariolocalListComentariolocalToAttach.getIdcomentario());
                attachedComentariolocalList.add(comentariolocalListComentariolocalToAttach);
            }
            usuario.setComentariolocalList(attachedComentariolocalList);
            List<Grupo> attachedGrupoList = new ArrayList<Grupo>();
            for (Grupo grupoListGrupoToAttach : usuario.getGrupoList()) {
                grupoListGrupoToAttach = em.getReference(grupoListGrupoToAttach.getClass(), grupoListGrupoToAttach.getIdgrupo());
                attachedGrupoList.add(grupoListGrupoToAttach);
            }
            usuario.setGrupoList(attachedGrupoList);
            List<Compartilhamento> attachedCompartilhamentoList = new ArrayList<Compartilhamento>();
            for (Compartilhamento compartilhamentoListCompartilhamentoToAttach : usuario.getCompartilhamentoList()) {
                compartilhamentoListCompartilhamentoToAttach = em.getReference(compartilhamentoListCompartilhamentoToAttach.getClass(), compartilhamentoListCompartilhamentoToAttach.getIdcompartilhamento());
                attachedCompartilhamentoList.add(compartilhamentoListCompartilhamentoToAttach);
            }
            usuario.setCompartilhamentoList(attachedCompartilhamentoList);
            List<Blog> attachedBlogList = new ArrayList<Blog>();
            for (Blog blogListBlogToAttach : usuario.getBlogList()) {
                blogListBlogToAttach = em.getReference(blogListBlogToAttach.getClass(), blogListBlogToAttach.getIdblog());
                attachedBlogList.add(blogListBlogToAttach);
            }
            usuario.setBlogList(attachedBlogList);
            List<Local> attachedLocalList = new ArrayList<Local>();
            for (Local localListLocalToAttach : usuario.getLocalList()) {
                localListLocalToAttach = em.getReference(localListLocalToAttach.getClass(), localListLocalToAttach.getIdlocal());
                attachedLocalList.add(localListLocalToAttach);
            }
            usuario.setLocalList(attachedLocalList);
            List<Profissional> attachedProfissionalList = new ArrayList<Profissional>();
            for (Profissional profissionalListProfissionalToAttach : usuario.getProfissionalList()) {
                profissionalListProfissionalToAttach = em.getReference(profissionalListProfissionalToAttach.getClass(), profissionalListProfissionalToAttach.getIdprofissional());
                attachedProfissionalList.add(profissionalListProfissionalToAttach);
            }
            usuario.setProfissionalList(attachedProfissionalList);
            List<Mensagempreconsultoria> attachedMensagempreconsultoriaList = new ArrayList<Mensagempreconsultoria>();
            for (Mensagempreconsultoria mensagempreconsultoriaListMensagempreconsultoriaToAttach : usuario.getMensagempreconsultoriaList()) {
                mensagempreconsultoriaListMensagempreconsultoriaToAttach = em.getReference(mensagempreconsultoriaListMensagempreconsultoriaToAttach.getClass(), mensagempreconsultoriaListMensagempreconsultoriaToAttach.getIdmensagempreconsultoria());
                attachedMensagempreconsultoriaList.add(mensagempreconsultoriaListMensagempreconsultoriaToAttach);
            }
            usuario.setMensagempreconsultoriaList(attachedMensagempreconsultoriaList);
            List<Avaliacaoartigo> attachedAvaliacaoartigoList = new ArrayList<Avaliacaoartigo>();
            for (Avaliacaoartigo avaliacaoartigoListAvaliacaoartigoToAttach : usuario.getAvaliacaoartigoList()) {
                avaliacaoartigoListAvaliacaoartigoToAttach = em.getReference(avaliacaoartigoListAvaliacaoartigoToAttach.getClass(), avaliacaoartigoListAvaliacaoartigoToAttach.getIdavaliacaoconsultoria());
                attachedAvaliacaoartigoList.add(avaliacaoartigoListAvaliacaoartigoToAttach);
            }
            usuario.setAvaliacaoartigoList(attachedAvaliacaoartigoList);
            List<Usuariogrupo> attachedUsuariogrupoList = new ArrayList<Usuariogrupo>();
            for (Usuariogrupo usuariogrupoListUsuariogrupoToAttach : usuario.getUsuariogrupoList()) {
                usuariogrupoListUsuariogrupoToAttach = em.getReference(usuariogrupoListUsuariogrupoToAttach.getClass(), usuariogrupoListUsuariogrupoToAttach.getIdusuariogrupo());
                attachedUsuariogrupoList.add(usuariogrupoListUsuariogrupoToAttach);
            }
            usuario.setUsuariogrupoList(attachedUsuariogrupoList);
            List<Evento> attachedEventoList = new ArrayList<Evento>();
            for (Evento eventoListEventoToAttach : usuario.getEventoList()) {
                eventoListEventoToAttach = em.getReference(eventoListEventoToAttach.getClass(), eventoListEventoToAttach.getIdevento());
                attachedEventoList.add(eventoListEventoToAttach);
            }
            usuario.setEventoList(attachedEventoList);
            List<Likes> attachedLikesList = new ArrayList<Likes>();
            for (Likes likesListLikesToAttach : usuario.getLikesList()) {
                likesListLikesToAttach = em.getReference(likesListLikesToAttach.getClass(), likesListLikesToAttach.getIdlikes());
                attachedLikesList.add(likesListLikesToAttach);
            }
            usuario.setLikesList(attachedLikesList);
            List<Seguidor> attachedSeguidorList = new ArrayList<Seguidor>();
            for (Seguidor seguidorListSeguidorToAttach : usuario.getSeguidorList()) {
                seguidorListSeguidorToAttach = em.getReference(seguidorListSeguidorToAttach.getClass(), seguidorListSeguidorToAttach.getIdtabela());
                attachedSeguidorList.add(seguidorListSeguidorToAttach);
            }
            usuario.setSeguidorList(attachedSeguidorList);
            List<Seguidor> attachedSeguidorList1 = new ArrayList<Seguidor>();
            for (Seguidor seguidorList1SeguidorToAttach : usuario.getSeguidorList1()) {
                seguidorList1SeguidorToAttach = em.getReference(seguidorList1SeguidorToAttach.getClass(), seguidorList1SeguidorToAttach.getIdtabela());
                attachedSeguidorList1.add(seguidorList1SeguidorToAttach);
            }
            usuario.setSeguidorList1(attachedSeguidorList1);
            List<Solicitacaogrupo> attachedSolicitacaogrupoList = new ArrayList<Solicitacaogrupo>();
            for (Solicitacaogrupo solicitacaogrupoListSolicitacaogrupoToAttach : usuario.getSolicitacaogrupoList()) {
                solicitacaogrupoListSolicitacaogrupoToAttach = em.getReference(solicitacaogrupoListSolicitacaogrupoToAttach.getClass(), solicitacaogrupoListSolicitacaogrupoToAttach.getIdsolicitacao());
                attachedSolicitacaogrupoList.add(solicitacaogrupoListSolicitacaogrupoToAttach);
            }
            usuario.setSolicitacaogrupoList(attachedSolicitacaogrupoList);
            List<Publicacao> attachedPublicacaoList = new ArrayList<Publicacao>();
            for (Publicacao publicacaoListPublicacaoToAttach : usuario.getPublicacaoList()) {
                publicacaoListPublicacaoToAttach = em.getReference(publicacaoListPublicacaoToAttach.getClass(), publicacaoListPublicacaoToAttach.getIdpublicacao());
                attachedPublicacaoList.add(publicacaoListPublicacaoToAttach);
            }
            usuario.setPublicacaoList(attachedPublicacaoList);
            List<Denuncia> attachedDenunciaList = new ArrayList<Denuncia>();
            for (Denuncia denunciaListDenunciaToAttach : usuario.getDenunciaList()) {
                denunciaListDenunciaToAttach = em.getReference(denunciaListDenunciaToAttach.getClass(), denunciaListDenunciaToAttach.getIddenuncia());
                attachedDenunciaList.add(denunciaListDenunciaToAttach);
            }
            usuario.setDenunciaList(attachedDenunciaList);
            List<Usuarioevento> attachedUsuarioeventoList = new ArrayList<Usuarioevento>();
            for (Usuarioevento usuarioeventoListUsuarioeventoToAttach : usuario.getUsuarioeventoList()) {
                usuarioeventoListUsuarioeventoToAttach = em.getReference(usuarioeventoListUsuarioeventoToAttach.getClass(), usuarioeventoListUsuarioeventoToAttach.getIdusuarioevento());
                attachedUsuarioeventoList.add(usuarioeventoListUsuarioeventoToAttach);
            }
            usuario.setUsuarioeventoList(attachedUsuarioeventoList);
            List<Consultoriarealizada> attachedConsultoriarealizadaList = new ArrayList<Consultoriarealizada>();
            for (Consultoriarealizada consultoriarealizadaListConsultoriarealizadaToAttach : usuario.getConsultoriarealizadaList()) {
                consultoriarealizadaListConsultoriarealizadaToAttach = em.getReference(consultoriarealizadaListConsultoriarealizadaToAttach.getClass(), consultoriarealizadaListConsultoriarealizadaToAttach.getIdconsultoriarealizada());
                attachedConsultoriarealizadaList.add(consultoriarealizadaListConsultoriarealizadaToAttach);
            }
            usuario.setConsultoriarealizadaList(attachedConsultoriarealizadaList);
            List<Pagamento> attachedPagamentoList = new ArrayList<Pagamento>();
            for (Pagamento pagamentoListPagamentoToAttach : usuario.getPagamentoList()) {
                pagamentoListPagamentoToAttach = em.getReference(pagamentoListPagamentoToAttach.getClass(), pagamentoListPagamentoToAttach.getIdpagamento());
                attachedPagamentoList.add(pagamentoListPagamentoToAttach);
            }
            usuario.setPagamentoList(attachedPagamentoList);
            List<Comentariopublicacao> attachedComentariopublicacaoList = new ArrayList<Comentariopublicacao>();
            for (Comentariopublicacao comentariopublicacaoListComentariopublicacaoToAttach : usuario.getComentariopublicacaoList()) {
                comentariopublicacaoListComentariopublicacaoToAttach = em.getReference(comentariopublicacaoListComentariopublicacaoToAttach.getClass(), comentariopublicacaoListComentariopublicacaoToAttach.getIdcomentariopublicacao());
                attachedComentariopublicacaoList.add(comentariopublicacaoListComentariopublicacaoToAttach);
            }
            usuario.setComentariopublicacaoList(attachedComentariopublicacaoList);
            em.persist(usuario);
            for (Atleta atletaListAtleta : usuario.getAtletaList()) {
                Usuario oldIdusuarioOfAtletaListAtleta = atletaListAtleta.getIdusuario();
                atletaListAtleta.setIdusuario(usuario);
                atletaListAtleta = em.merge(atletaListAtleta);
                if (oldIdusuarioOfAtletaListAtleta != null) {
                    oldIdusuarioOfAtletaListAtleta.getAtletaList().remove(atletaListAtleta);
                    oldIdusuarioOfAtletaListAtleta = em.merge(oldIdusuarioOfAtletaListAtleta);
                }
            }
            for (Solicitacaoevento solicitacaoeventoListSolicitacaoevento : usuario.getSolicitacaoeventoList()) {
                Usuario oldIdusuarioOfSolicitacaoeventoListSolicitacaoevento = solicitacaoeventoListSolicitacaoevento.getIdusuario();
                solicitacaoeventoListSolicitacaoevento.setIdusuario(usuario);
                solicitacaoeventoListSolicitacaoevento = em.merge(solicitacaoeventoListSolicitacaoevento);
                if (oldIdusuarioOfSolicitacaoeventoListSolicitacaoevento != null) {
                    oldIdusuarioOfSolicitacaoeventoListSolicitacaoevento.getSolicitacaoeventoList().remove(solicitacaoeventoListSolicitacaoevento);
                    oldIdusuarioOfSolicitacaoeventoListSolicitacaoevento = em.merge(oldIdusuarioOfSolicitacaoeventoListSolicitacaoevento);
                }
            }
            for (Chatconsultoria chatconsultoriaListChatconsultoria : usuario.getChatconsultoriaList()) {
                Usuario oldIdemissorOfChatconsultoriaListChatconsultoria = chatconsultoriaListChatconsultoria.getIdemissor();
                chatconsultoriaListChatconsultoria.setIdemissor(usuario);
                chatconsultoriaListChatconsultoria = em.merge(chatconsultoriaListChatconsultoria);
                if (oldIdemissorOfChatconsultoriaListChatconsultoria != null) {
                    oldIdemissorOfChatconsultoriaListChatconsultoria.getChatconsultoriaList().remove(chatconsultoriaListChatconsultoria);
                    oldIdemissorOfChatconsultoriaListChatconsultoria = em.merge(oldIdemissorOfChatconsultoriaListChatconsultoria);
                }
            }
            for (Chatconsultoria chatconsultoriaList1Chatconsultoria : usuario.getChatconsultoriaList1()) {
                Usuario oldIddestinatarioOfChatconsultoriaList1Chatconsultoria = chatconsultoriaList1Chatconsultoria.getIddestinatario();
                chatconsultoriaList1Chatconsultoria.setIddestinatario(usuario);
                chatconsultoriaList1Chatconsultoria = em.merge(chatconsultoriaList1Chatconsultoria);
                if (oldIddestinatarioOfChatconsultoriaList1Chatconsultoria != null) {
                    oldIddestinatarioOfChatconsultoriaList1Chatconsultoria.getChatconsultoriaList1().remove(chatconsultoriaList1Chatconsultoria);
                    oldIddestinatarioOfChatconsultoriaList1Chatconsultoria = em.merge(oldIddestinatarioOfChatconsultoriaList1Chatconsultoria);
                }
            }
            for (Comentariolocal comentariolocalListComentariolocal : usuario.getComentariolocalList()) {
                Usuario oldIdusuarioOfComentariolocalListComentariolocal = comentariolocalListComentariolocal.getIdusuario();
                comentariolocalListComentariolocal.setIdusuario(usuario);
                comentariolocalListComentariolocal = em.merge(comentariolocalListComentariolocal);
                if (oldIdusuarioOfComentariolocalListComentariolocal != null) {
                    oldIdusuarioOfComentariolocalListComentariolocal.getComentariolocalList().remove(comentariolocalListComentariolocal);
                    oldIdusuarioOfComentariolocalListComentariolocal = em.merge(oldIdusuarioOfComentariolocalListComentariolocal);
                }
            }
            for (Grupo grupoListGrupo : usuario.getGrupoList()) {
                Usuario oldIdusuarioOfGrupoListGrupo = grupoListGrupo.getIdusuario();
                grupoListGrupo.setIdusuario(usuario);
                grupoListGrupo = em.merge(grupoListGrupo);
                if (oldIdusuarioOfGrupoListGrupo != null) {
                    oldIdusuarioOfGrupoListGrupo.getGrupoList().remove(grupoListGrupo);
                    oldIdusuarioOfGrupoListGrupo = em.merge(oldIdusuarioOfGrupoListGrupo);
                }
            }
            for (Compartilhamento compartilhamentoListCompartilhamento : usuario.getCompartilhamentoList()) {
                Usuario oldIdusuarioOfCompartilhamentoListCompartilhamento = compartilhamentoListCompartilhamento.getIdusuario();
                compartilhamentoListCompartilhamento.setIdusuario(usuario);
                compartilhamentoListCompartilhamento = em.merge(compartilhamentoListCompartilhamento);
                if (oldIdusuarioOfCompartilhamentoListCompartilhamento != null) {
                    oldIdusuarioOfCompartilhamentoListCompartilhamento.getCompartilhamentoList().remove(compartilhamentoListCompartilhamento);
                    oldIdusuarioOfCompartilhamentoListCompartilhamento = em.merge(oldIdusuarioOfCompartilhamentoListCompartilhamento);
                }
            }
            for (Blog blogListBlog : usuario.getBlogList()) {
                Usuario oldAutorOfBlogListBlog = blogListBlog.getAutor();
                blogListBlog.setAutor(usuario);
                blogListBlog = em.merge(blogListBlog);
                if (oldAutorOfBlogListBlog != null) {
                    oldAutorOfBlogListBlog.getBlogList().remove(blogListBlog);
                    oldAutorOfBlogListBlog = em.merge(oldAutorOfBlogListBlog);
                }
            }
            for (Local localListLocal : usuario.getLocalList()) {
                Usuario oldIdusuarioOfLocalListLocal = localListLocal.getIdusuario();
                localListLocal.setIdusuario(usuario);
                localListLocal = em.merge(localListLocal);
                if (oldIdusuarioOfLocalListLocal != null) {
                    oldIdusuarioOfLocalListLocal.getLocalList().remove(localListLocal);
                    oldIdusuarioOfLocalListLocal = em.merge(oldIdusuarioOfLocalListLocal);
                }
            }
            for (Profissional profissionalListProfissional : usuario.getProfissionalList()) {
                Usuario oldIdusuarioOfProfissionalListProfissional = profissionalListProfissional.getIdusuario();
                profissionalListProfissional.setIdusuario(usuario);
                profissionalListProfissional = em.merge(profissionalListProfissional);
                if (oldIdusuarioOfProfissionalListProfissional != null) {
                    oldIdusuarioOfProfissionalListProfissional.getProfissionalList().remove(profissionalListProfissional);
                    oldIdusuarioOfProfissionalListProfissional = em.merge(oldIdusuarioOfProfissionalListProfissional);
                }
            }
            for (Mensagempreconsultoria mensagempreconsultoriaListMensagempreconsultoria : usuario.getMensagempreconsultoriaList()) {
                Usuario oldIdusuarioOfMensagempreconsultoriaListMensagempreconsultoria = mensagempreconsultoriaListMensagempreconsultoria.getIdusuario();
                mensagempreconsultoriaListMensagempreconsultoria.setIdusuario(usuario);
                mensagempreconsultoriaListMensagempreconsultoria = em.merge(mensagempreconsultoriaListMensagempreconsultoria);
                if (oldIdusuarioOfMensagempreconsultoriaListMensagempreconsultoria != null) {
                    oldIdusuarioOfMensagempreconsultoriaListMensagempreconsultoria.getMensagempreconsultoriaList().remove(mensagempreconsultoriaListMensagempreconsultoria);
                    oldIdusuarioOfMensagempreconsultoriaListMensagempreconsultoria = em.merge(oldIdusuarioOfMensagempreconsultoriaListMensagempreconsultoria);
                }
            }
            for (Avaliacaoartigo avaliacaoartigoListAvaliacaoartigo : usuario.getAvaliacaoartigoList()) {
                Usuario oldIdusuarioOfAvaliacaoartigoListAvaliacaoartigo = avaliacaoartigoListAvaliacaoartigo.getIdusuario();
                avaliacaoartigoListAvaliacaoartigo.setIdusuario(usuario);
                avaliacaoartigoListAvaliacaoartigo = em.merge(avaliacaoartigoListAvaliacaoartigo);
                if (oldIdusuarioOfAvaliacaoartigoListAvaliacaoartigo != null) {
                    oldIdusuarioOfAvaliacaoartigoListAvaliacaoartigo.getAvaliacaoartigoList().remove(avaliacaoartigoListAvaliacaoartigo);
                    oldIdusuarioOfAvaliacaoartigoListAvaliacaoartigo = em.merge(oldIdusuarioOfAvaliacaoartigoListAvaliacaoartigo);
                }
            }
            for (Usuariogrupo usuariogrupoListUsuariogrupo : usuario.getUsuariogrupoList()) {
                Usuario oldIdusuarioOfUsuariogrupoListUsuariogrupo = usuariogrupoListUsuariogrupo.getIdusuario();
                usuariogrupoListUsuariogrupo.setIdusuario(usuario);
                usuariogrupoListUsuariogrupo = em.merge(usuariogrupoListUsuariogrupo);
                if (oldIdusuarioOfUsuariogrupoListUsuariogrupo != null) {
                    oldIdusuarioOfUsuariogrupoListUsuariogrupo.getUsuariogrupoList().remove(usuariogrupoListUsuariogrupo);
                    oldIdusuarioOfUsuariogrupoListUsuariogrupo = em.merge(oldIdusuarioOfUsuariogrupoListUsuariogrupo);
                }
            }
            for (Evento eventoListEvento : usuario.getEventoList()) {
                Usuario oldIdusuarioOfEventoListEvento = eventoListEvento.getIdusuario();
                eventoListEvento.setIdusuario(usuario);
                eventoListEvento = em.merge(eventoListEvento);
                if (oldIdusuarioOfEventoListEvento != null) {
                    oldIdusuarioOfEventoListEvento.getEventoList().remove(eventoListEvento);
                    oldIdusuarioOfEventoListEvento = em.merge(oldIdusuarioOfEventoListEvento);
                }
            }
            for (Likes likesListLikes : usuario.getLikesList()) {
                Usuario oldIdusuarioOfLikesListLikes = likesListLikes.getIdusuario();
                likesListLikes.setIdusuario(usuario);
                likesListLikes = em.merge(likesListLikes);
                if (oldIdusuarioOfLikesListLikes != null) {
                    oldIdusuarioOfLikesListLikes.getLikesList().remove(likesListLikes);
                    oldIdusuarioOfLikesListLikes = em.merge(oldIdusuarioOfLikesListLikes);
                }
            }
            for (Seguidor seguidorListSeguidor : usuario.getSeguidorList()) {
                Usuario oldIdseguidorOfSeguidorListSeguidor = seguidorListSeguidor.getIdseguidor();
                seguidorListSeguidor.setIdseguidor(usuario);
                seguidorListSeguidor = em.merge(seguidorListSeguidor);
                if (oldIdseguidorOfSeguidorListSeguidor != null) {
                    oldIdseguidorOfSeguidorListSeguidor.getSeguidorList().remove(seguidorListSeguidor);
                    oldIdseguidorOfSeguidorListSeguidor = em.merge(oldIdseguidorOfSeguidorListSeguidor);
                }
            }
            for (Seguidor seguidorList1Seguidor : usuario.getSeguidorList1()) {
                Usuario oldIdseguidoOfSeguidorList1Seguidor = seguidorList1Seguidor.getIdseguido();
                seguidorList1Seguidor.setIdseguido(usuario);
                seguidorList1Seguidor = em.merge(seguidorList1Seguidor);
                if (oldIdseguidoOfSeguidorList1Seguidor != null) {
                    oldIdseguidoOfSeguidorList1Seguidor.getSeguidorList1().remove(seguidorList1Seguidor);
                    oldIdseguidoOfSeguidorList1Seguidor = em.merge(oldIdseguidoOfSeguidorList1Seguidor);
                }
            }
            for (Solicitacaogrupo solicitacaogrupoListSolicitacaogrupo : usuario.getSolicitacaogrupoList()) {
                Usuario oldIdusuarioOfSolicitacaogrupoListSolicitacaogrupo = solicitacaogrupoListSolicitacaogrupo.getIdusuario();
                solicitacaogrupoListSolicitacaogrupo.setIdusuario(usuario);
                solicitacaogrupoListSolicitacaogrupo = em.merge(solicitacaogrupoListSolicitacaogrupo);
                if (oldIdusuarioOfSolicitacaogrupoListSolicitacaogrupo != null) {
                    oldIdusuarioOfSolicitacaogrupoListSolicitacaogrupo.getSolicitacaogrupoList().remove(solicitacaogrupoListSolicitacaogrupo);
                    oldIdusuarioOfSolicitacaogrupoListSolicitacaogrupo = em.merge(oldIdusuarioOfSolicitacaogrupoListSolicitacaogrupo);
                }
            }
            for (Publicacao publicacaoListPublicacao : usuario.getPublicacaoList()) {
                Usuario oldIdusuarioOfPublicacaoListPublicacao = publicacaoListPublicacao.getIdusuario();
                publicacaoListPublicacao.setIdusuario(usuario);
                publicacaoListPublicacao = em.merge(publicacaoListPublicacao);
                if (oldIdusuarioOfPublicacaoListPublicacao != null) {
                    oldIdusuarioOfPublicacaoListPublicacao.getPublicacaoList().remove(publicacaoListPublicacao);
                    oldIdusuarioOfPublicacaoListPublicacao = em.merge(oldIdusuarioOfPublicacaoListPublicacao);
                }
            }
            for (Denuncia denunciaListDenuncia : usuario.getDenunciaList()) {
                Usuario oldIdusuarioOfDenunciaListDenuncia = denunciaListDenuncia.getIdusuario();
                denunciaListDenuncia.setIdusuario(usuario);
                denunciaListDenuncia = em.merge(denunciaListDenuncia);
                if (oldIdusuarioOfDenunciaListDenuncia != null) {
                    oldIdusuarioOfDenunciaListDenuncia.getDenunciaList().remove(denunciaListDenuncia);
                    oldIdusuarioOfDenunciaListDenuncia = em.merge(oldIdusuarioOfDenunciaListDenuncia);
                }
            }
            for (Usuarioevento usuarioeventoListUsuarioevento : usuario.getUsuarioeventoList()) {
                Usuario oldIdusuarioOfUsuarioeventoListUsuarioevento = usuarioeventoListUsuarioevento.getIdusuario();
                usuarioeventoListUsuarioevento.setIdusuario(usuario);
                usuarioeventoListUsuarioevento = em.merge(usuarioeventoListUsuarioevento);
                if (oldIdusuarioOfUsuarioeventoListUsuarioevento != null) {
                    oldIdusuarioOfUsuarioeventoListUsuarioevento.getUsuarioeventoList().remove(usuarioeventoListUsuarioevento);
                    oldIdusuarioOfUsuarioeventoListUsuarioevento = em.merge(oldIdusuarioOfUsuarioeventoListUsuarioevento);
                }
            }
            for (Consultoriarealizada consultoriarealizadaListConsultoriarealizada : usuario.getConsultoriarealizadaList()) {
                Usuario oldIdusuarioOfConsultoriarealizadaListConsultoriarealizada = consultoriarealizadaListConsultoriarealizada.getIdusuario();
                consultoriarealizadaListConsultoriarealizada.setIdusuario(usuario);
                consultoriarealizadaListConsultoriarealizada = em.merge(consultoriarealizadaListConsultoriarealizada);
                if (oldIdusuarioOfConsultoriarealizadaListConsultoriarealizada != null) {
                    oldIdusuarioOfConsultoriarealizadaListConsultoriarealizada.getConsultoriarealizadaList().remove(consultoriarealizadaListConsultoriarealizada);
                    oldIdusuarioOfConsultoriarealizadaListConsultoriarealizada = em.merge(oldIdusuarioOfConsultoriarealizadaListConsultoriarealizada);
                }
            }
            for (Pagamento pagamentoListPagamento : usuario.getPagamentoList()) {
                Usuario oldIdusuarioOfPagamentoListPagamento = pagamentoListPagamento.getIdusuario();
                pagamentoListPagamento.setIdusuario(usuario);
                pagamentoListPagamento = em.merge(pagamentoListPagamento);
                if (oldIdusuarioOfPagamentoListPagamento != null) {
                    oldIdusuarioOfPagamentoListPagamento.getPagamentoList().remove(pagamentoListPagamento);
                    oldIdusuarioOfPagamentoListPagamento = em.merge(oldIdusuarioOfPagamentoListPagamento);
                }
            }
            for (Comentariopublicacao comentariopublicacaoListComentariopublicacao : usuario.getComentariopublicacaoList()) {
                Usuario oldIdusuarioOfComentariopublicacaoListComentariopublicacao = comentariopublicacaoListComentariopublicacao.getIdusuario();
                comentariopublicacaoListComentariopublicacao.setIdusuario(usuario);
                comentariopublicacaoListComentariopublicacao = em.merge(comentariopublicacaoListComentariopublicacao);
                if (oldIdusuarioOfComentariopublicacaoListComentariopublicacao != null) {
                    oldIdusuarioOfComentariopublicacaoListComentariopublicacao.getComentariopublicacaoList().remove(comentariopublicacaoListComentariopublicacao);
                    oldIdusuarioOfComentariopublicacaoListComentariopublicacao = em.merge(oldIdusuarioOfComentariopublicacaoListComentariopublicacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdusuario());
            List<Atleta> atletaListOld = persistentUsuario.getAtletaList();
            List<Atleta> atletaListNew = usuario.getAtletaList();
            List<Solicitacaoevento> solicitacaoeventoListOld = persistentUsuario.getSolicitacaoeventoList();
            List<Solicitacaoevento> solicitacaoeventoListNew = usuario.getSolicitacaoeventoList();
            List<Chatconsultoria> chatconsultoriaListOld = persistentUsuario.getChatconsultoriaList();
            List<Chatconsultoria> chatconsultoriaListNew = usuario.getChatconsultoriaList();
            List<Chatconsultoria> chatconsultoriaList1Old = persistentUsuario.getChatconsultoriaList1();
            List<Chatconsultoria> chatconsultoriaList1New = usuario.getChatconsultoriaList1();
            List<Comentariolocal> comentariolocalListOld = persistentUsuario.getComentariolocalList();
            List<Comentariolocal> comentariolocalListNew = usuario.getComentariolocalList();
            List<Grupo> grupoListOld = persistentUsuario.getGrupoList();
            List<Grupo> grupoListNew = usuario.getGrupoList();
            List<Compartilhamento> compartilhamentoListOld = persistentUsuario.getCompartilhamentoList();
            List<Compartilhamento> compartilhamentoListNew = usuario.getCompartilhamentoList();
            List<Blog> blogListOld = persistentUsuario.getBlogList();
            List<Blog> blogListNew = usuario.getBlogList();
            List<Local> localListOld = persistentUsuario.getLocalList();
            List<Local> localListNew = usuario.getLocalList();
            List<Profissional> profissionalListOld = persistentUsuario.getProfissionalList();
            List<Profissional> profissionalListNew = usuario.getProfissionalList();
            List<Mensagempreconsultoria> mensagempreconsultoriaListOld = persistentUsuario.getMensagempreconsultoriaList();
            List<Mensagempreconsultoria> mensagempreconsultoriaListNew = usuario.getMensagempreconsultoriaList();
            List<Avaliacaoartigo> avaliacaoartigoListOld = persistentUsuario.getAvaliacaoartigoList();
            List<Avaliacaoartigo> avaliacaoartigoListNew = usuario.getAvaliacaoartigoList();
            List<Usuariogrupo> usuariogrupoListOld = persistentUsuario.getUsuariogrupoList();
            List<Usuariogrupo> usuariogrupoListNew = usuario.getUsuariogrupoList();
            List<Evento> eventoListOld = persistentUsuario.getEventoList();
            List<Evento> eventoListNew = usuario.getEventoList();
            List<Likes> likesListOld = persistentUsuario.getLikesList();
            List<Likes> likesListNew = usuario.getLikesList();
            List<Seguidor> seguidorListOld = persistentUsuario.getSeguidorList();
            List<Seguidor> seguidorListNew = usuario.getSeguidorList();
            List<Seguidor> seguidorList1Old = persistentUsuario.getSeguidorList1();
            List<Seguidor> seguidorList1New = usuario.getSeguidorList1();
            List<Solicitacaogrupo> solicitacaogrupoListOld = persistentUsuario.getSolicitacaogrupoList();
            List<Solicitacaogrupo> solicitacaogrupoListNew = usuario.getSolicitacaogrupoList();
            List<Publicacao> publicacaoListOld = persistentUsuario.getPublicacaoList();
            List<Publicacao> publicacaoListNew = usuario.getPublicacaoList();
            List<Denuncia> denunciaListOld = persistentUsuario.getDenunciaList();
            List<Denuncia> denunciaListNew = usuario.getDenunciaList();
            List<Usuarioevento> usuarioeventoListOld = persistentUsuario.getUsuarioeventoList();
            List<Usuarioevento> usuarioeventoListNew = usuario.getUsuarioeventoList();
            List<Consultoriarealizada> consultoriarealizadaListOld = persistentUsuario.getConsultoriarealizadaList();
            List<Consultoriarealizada> consultoriarealizadaListNew = usuario.getConsultoriarealizadaList();
            List<Pagamento> pagamentoListOld = persistentUsuario.getPagamentoList();
            List<Pagamento> pagamentoListNew = usuario.getPagamentoList();
            List<Comentariopublicacao> comentariopublicacaoListOld = persistentUsuario.getComentariopublicacaoList();
            List<Comentariopublicacao> comentariopublicacaoListNew = usuario.getComentariopublicacaoList();
            List<String> illegalOrphanMessages = null;
            for (Atleta atletaListOldAtleta : atletaListOld) {
                if (!atletaListNew.contains(atletaListOldAtleta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Atleta " + atletaListOldAtleta + " since its idusuario field is not nullable.");
                }
            }
            for (Solicitacaoevento solicitacaoeventoListOldSolicitacaoevento : solicitacaoeventoListOld) {
                if (!solicitacaoeventoListNew.contains(solicitacaoeventoListOldSolicitacaoevento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitacaoevento " + solicitacaoeventoListOldSolicitacaoevento + " since its idusuario field is not nullable.");
                }
            }
            for (Chatconsultoria chatconsultoriaListOldChatconsultoria : chatconsultoriaListOld) {
                if (!chatconsultoriaListNew.contains(chatconsultoriaListOldChatconsultoria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Chatconsultoria " + chatconsultoriaListOldChatconsultoria + " since its idemissor field is not nullable.");
                }
            }
            for (Chatconsultoria chatconsultoriaList1OldChatconsultoria : chatconsultoriaList1Old) {
                if (!chatconsultoriaList1New.contains(chatconsultoriaList1OldChatconsultoria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Chatconsultoria " + chatconsultoriaList1OldChatconsultoria + " since its iddestinatario field is not nullable.");
                }
            }
            for (Comentariolocal comentariolocalListOldComentariolocal : comentariolocalListOld) {
                if (!comentariolocalListNew.contains(comentariolocalListOldComentariolocal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comentariolocal " + comentariolocalListOldComentariolocal + " since its idusuario field is not nullable.");
                }
            }
            for (Grupo grupoListOldGrupo : grupoListOld) {
                if (!grupoListNew.contains(grupoListOldGrupo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Grupo " + grupoListOldGrupo + " since its idusuario field is not nullable.");
                }
            }
            for (Compartilhamento compartilhamentoListOldCompartilhamento : compartilhamentoListOld) {
                if (!compartilhamentoListNew.contains(compartilhamentoListOldCompartilhamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Compartilhamento " + compartilhamentoListOldCompartilhamento + " since its idusuario field is not nullable.");
                }
            }
            for (Blog blogListOldBlog : blogListOld) {
                if (!blogListNew.contains(blogListOldBlog)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Blog " + blogListOldBlog + " since its autor field is not nullable.");
                }
            }
            for (Local localListOldLocal : localListOld) {
                if (!localListNew.contains(localListOldLocal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Local " + localListOldLocal + " since its idusuario field is not nullable.");
                }
            }
            for (Profissional profissionalListOldProfissional : profissionalListOld) {
                if (!profissionalListNew.contains(profissionalListOldProfissional)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Profissional " + profissionalListOldProfissional + " since its idusuario field is not nullable.");
                }
            }
            for (Mensagempreconsultoria mensagempreconsultoriaListOldMensagempreconsultoria : mensagempreconsultoriaListOld) {
                if (!mensagempreconsultoriaListNew.contains(mensagempreconsultoriaListOldMensagempreconsultoria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mensagempreconsultoria " + mensagempreconsultoriaListOldMensagempreconsultoria + " since its idusuario field is not nullable.");
                }
            }
            for (Avaliacaoartigo avaliacaoartigoListOldAvaliacaoartigo : avaliacaoartigoListOld) {
                if (!avaliacaoartigoListNew.contains(avaliacaoartigoListOldAvaliacaoartigo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Avaliacaoartigo " + avaliacaoartigoListOldAvaliacaoartigo + " since its idusuario field is not nullable.");
                }
            }
            for (Usuariogrupo usuariogrupoListOldUsuariogrupo : usuariogrupoListOld) {
                if (!usuariogrupoListNew.contains(usuariogrupoListOldUsuariogrupo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuariogrupo " + usuariogrupoListOldUsuariogrupo + " since its idusuario field is not nullable.");
                }
            }
            for (Evento eventoListOldEvento : eventoListOld) {
                if (!eventoListNew.contains(eventoListOldEvento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Evento " + eventoListOldEvento + " since its idusuario field is not nullable.");
                }
            }
            for (Likes likesListOldLikes : likesListOld) {
                if (!likesListNew.contains(likesListOldLikes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Likes " + likesListOldLikes + " since its idusuario field is not nullable.");
                }
            }
            for (Seguidor seguidorListOldSeguidor : seguidorListOld) {
                if (!seguidorListNew.contains(seguidorListOldSeguidor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Seguidor " + seguidorListOldSeguidor + " since its idseguidor field is not nullable.");
                }
            }
            for (Seguidor seguidorList1OldSeguidor : seguidorList1Old) {
                if (!seguidorList1New.contains(seguidorList1OldSeguidor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Seguidor " + seguidorList1OldSeguidor + " since its idseguido field is not nullable.");
                }
            }
            for (Solicitacaogrupo solicitacaogrupoListOldSolicitacaogrupo : solicitacaogrupoListOld) {
                if (!solicitacaogrupoListNew.contains(solicitacaogrupoListOldSolicitacaogrupo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitacaogrupo " + solicitacaogrupoListOldSolicitacaogrupo + " since its idusuario field is not nullable.");
                }
            }
            for (Publicacao publicacaoListOldPublicacao : publicacaoListOld) {
                if (!publicacaoListNew.contains(publicacaoListOldPublicacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Publicacao " + publicacaoListOldPublicacao + " since its idusuario field is not nullable.");
                }
            }
            for (Denuncia denunciaListOldDenuncia : denunciaListOld) {
                if (!denunciaListNew.contains(denunciaListOldDenuncia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Denuncia " + denunciaListOldDenuncia + " since its idusuario field is not nullable.");
                }
            }
            for (Usuarioevento usuarioeventoListOldUsuarioevento : usuarioeventoListOld) {
                if (!usuarioeventoListNew.contains(usuarioeventoListOldUsuarioevento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuarioevento " + usuarioeventoListOldUsuarioevento + " since its idusuario field is not nullable.");
                }
            }
            for (Consultoriarealizada consultoriarealizadaListOldConsultoriarealizada : consultoriarealizadaListOld) {
                if (!consultoriarealizadaListNew.contains(consultoriarealizadaListOldConsultoriarealizada)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Consultoriarealizada " + consultoriarealizadaListOldConsultoriarealizada + " since its idusuario field is not nullable.");
                }
            }
            for (Pagamento pagamentoListOldPagamento : pagamentoListOld) {
                if (!pagamentoListNew.contains(pagamentoListOldPagamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pagamento " + pagamentoListOldPagamento + " since its idusuario field is not nullable.");
                }
            }
            for (Comentariopublicacao comentariopublicacaoListOldComentariopublicacao : comentariopublicacaoListOld) {
                if (!comentariopublicacaoListNew.contains(comentariopublicacaoListOldComentariopublicacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comentariopublicacao " + comentariopublicacaoListOldComentariopublicacao + " since its idusuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Atleta> attachedAtletaListNew = new ArrayList<Atleta>();
            for (Atleta atletaListNewAtletaToAttach : atletaListNew) {
                atletaListNewAtletaToAttach = em.getReference(atletaListNewAtletaToAttach.getClass(), atletaListNewAtletaToAttach.getIdatleta());
                attachedAtletaListNew.add(atletaListNewAtletaToAttach);
            }
            atletaListNew = attachedAtletaListNew;
            usuario.setAtletaList(atletaListNew);
            List<Solicitacaoevento> attachedSolicitacaoeventoListNew = new ArrayList<Solicitacaoevento>();
            for (Solicitacaoevento solicitacaoeventoListNewSolicitacaoeventoToAttach : solicitacaoeventoListNew) {
                solicitacaoeventoListNewSolicitacaoeventoToAttach = em.getReference(solicitacaoeventoListNewSolicitacaoeventoToAttach.getClass(), solicitacaoeventoListNewSolicitacaoeventoToAttach.getIdsolicitacao());
                attachedSolicitacaoeventoListNew.add(solicitacaoeventoListNewSolicitacaoeventoToAttach);
            }
            solicitacaoeventoListNew = attachedSolicitacaoeventoListNew;
            usuario.setSolicitacaoeventoList(solicitacaoeventoListNew);
            List<Chatconsultoria> attachedChatconsultoriaListNew = new ArrayList<Chatconsultoria>();
            for (Chatconsultoria chatconsultoriaListNewChatconsultoriaToAttach : chatconsultoriaListNew) {
                chatconsultoriaListNewChatconsultoriaToAttach = em.getReference(chatconsultoriaListNewChatconsultoriaToAttach.getClass(), chatconsultoriaListNewChatconsultoriaToAttach.getIdchatconsultoria());
                attachedChatconsultoriaListNew.add(chatconsultoriaListNewChatconsultoriaToAttach);
            }
            chatconsultoriaListNew = attachedChatconsultoriaListNew;
            usuario.setChatconsultoriaList(chatconsultoriaListNew);
            List<Chatconsultoria> attachedChatconsultoriaList1New = new ArrayList<Chatconsultoria>();
            for (Chatconsultoria chatconsultoriaList1NewChatconsultoriaToAttach : chatconsultoriaList1New) {
                chatconsultoriaList1NewChatconsultoriaToAttach = em.getReference(chatconsultoriaList1NewChatconsultoriaToAttach.getClass(), chatconsultoriaList1NewChatconsultoriaToAttach.getIdchatconsultoria());
                attachedChatconsultoriaList1New.add(chatconsultoriaList1NewChatconsultoriaToAttach);
            }
            chatconsultoriaList1New = attachedChatconsultoriaList1New;
            usuario.setChatconsultoriaList1(chatconsultoriaList1New);
            List<Comentariolocal> attachedComentariolocalListNew = new ArrayList<Comentariolocal>();
            for (Comentariolocal comentariolocalListNewComentariolocalToAttach : comentariolocalListNew) {
                comentariolocalListNewComentariolocalToAttach = em.getReference(comentariolocalListNewComentariolocalToAttach.getClass(), comentariolocalListNewComentariolocalToAttach.getIdcomentario());
                attachedComentariolocalListNew.add(comentariolocalListNewComentariolocalToAttach);
            }
            comentariolocalListNew = attachedComentariolocalListNew;
            usuario.setComentariolocalList(comentariolocalListNew);
            List<Grupo> attachedGrupoListNew = new ArrayList<Grupo>();
            for (Grupo grupoListNewGrupoToAttach : grupoListNew) {
                grupoListNewGrupoToAttach = em.getReference(grupoListNewGrupoToAttach.getClass(), grupoListNewGrupoToAttach.getIdgrupo());
                attachedGrupoListNew.add(grupoListNewGrupoToAttach);
            }
            grupoListNew = attachedGrupoListNew;
            usuario.setGrupoList(grupoListNew);
            List<Compartilhamento> attachedCompartilhamentoListNew = new ArrayList<Compartilhamento>();
            for (Compartilhamento compartilhamentoListNewCompartilhamentoToAttach : compartilhamentoListNew) {
                compartilhamentoListNewCompartilhamentoToAttach = em.getReference(compartilhamentoListNewCompartilhamentoToAttach.getClass(), compartilhamentoListNewCompartilhamentoToAttach.getIdcompartilhamento());
                attachedCompartilhamentoListNew.add(compartilhamentoListNewCompartilhamentoToAttach);
            }
            compartilhamentoListNew = attachedCompartilhamentoListNew;
            usuario.setCompartilhamentoList(compartilhamentoListNew);
            List<Blog> attachedBlogListNew = new ArrayList<Blog>();
            for (Blog blogListNewBlogToAttach : blogListNew) {
                blogListNewBlogToAttach = em.getReference(blogListNewBlogToAttach.getClass(), blogListNewBlogToAttach.getIdblog());
                attachedBlogListNew.add(blogListNewBlogToAttach);
            }
            blogListNew = attachedBlogListNew;
            usuario.setBlogList(blogListNew);
            List<Local> attachedLocalListNew = new ArrayList<Local>();
            for (Local localListNewLocalToAttach : localListNew) {
                localListNewLocalToAttach = em.getReference(localListNewLocalToAttach.getClass(), localListNewLocalToAttach.getIdlocal());
                attachedLocalListNew.add(localListNewLocalToAttach);
            }
            localListNew = attachedLocalListNew;
            usuario.setLocalList(localListNew);
            List<Profissional> attachedProfissionalListNew = new ArrayList<Profissional>();
            for (Profissional profissionalListNewProfissionalToAttach : profissionalListNew) {
                profissionalListNewProfissionalToAttach = em.getReference(profissionalListNewProfissionalToAttach.getClass(), profissionalListNewProfissionalToAttach.getIdprofissional());
                attachedProfissionalListNew.add(profissionalListNewProfissionalToAttach);
            }
            profissionalListNew = attachedProfissionalListNew;
            usuario.setProfissionalList(profissionalListNew);
            List<Mensagempreconsultoria> attachedMensagempreconsultoriaListNew = new ArrayList<Mensagempreconsultoria>();
            for (Mensagempreconsultoria mensagempreconsultoriaListNewMensagempreconsultoriaToAttach : mensagempreconsultoriaListNew) {
                mensagempreconsultoriaListNewMensagempreconsultoriaToAttach = em.getReference(mensagempreconsultoriaListNewMensagempreconsultoriaToAttach.getClass(), mensagempreconsultoriaListNewMensagempreconsultoriaToAttach.getIdmensagempreconsultoria());
                attachedMensagempreconsultoriaListNew.add(mensagempreconsultoriaListNewMensagempreconsultoriaToAttach);
            }
            mensagempreconsultoriaListNew = attachedMensagempreconsultoriaListNew;
            usuario.setMensagempreconsultoriaList(mensagempreconsultoriaListNew);
            List<Avaliacaoartigo> attachedAvaliacaoartigoListNew = new ArrayList<Avaliacaoartigo>();
            for (Avaliacaoartigo avaliacaoartigoListNewAvaliacaoartigoToAttach : avaliacaoartigoListNew) {
                avaliacaoartigoListNewAvaliacaoartigoToAttach = em.getReference(avaliacaoartigoListNewAvaliacaoartigoToAttach.getClass(), avaliacaoartigoListNewAvaliacaoartigoToAttach.getIdavaliacaoconsultoria());
                attachedAvaliacaoartigoListNew.add(avaliacaoartigoListNewAvaliacaoartigoToAttach);
            }
            avaliacaoartigoListNew = attachedAvaliacaoartigoListNew;
            usuario.setAvaliacaoartigoList(avaliacaoartigoListNew);
            List<Usuariogrupo> attachedUsuariogrupoListNew = new ArrayList<Usuariogrupo>();
            for (Usuariogrupo usuariogrupoListNewUsuariogrupoToAttach : usuariogrupoListNew) {
                usuariogrupoListNewUsuariogrupoToAttach = em.getReference(usuariogrupoListNewUsuariogrupoToAttach.getClass(), usuariogrupoListNewUsuariogrupoToAttach.getIdusuariogrupo());
                attachedUsuariogrupoListNew.add(usuariogrupoListNewUsuariogrupoToAttach);
            }
            usuariogrupoListNew = attachedUsuariogrupoListNew;
            usuario.setUsuariogrupoList(usuariogrupoListNew);
            List<Evento> attachedEventoListNew = new ArrayList<Evento>();
            for (Evento eventoListNewEventoToAttach : eventoListNew) {
                eventoListNewEventoToAttach = em.getReference(eventoListNewEventoToAttach.getClass(), eventoListNewEventoToAttach.getIdevento());
                attachedEventoListNew.add(eventoListNewEventoToAttach);
            }
            eventoListNew = attachedEventoListNew;
            usuario.setEventoList(eventoListNew);
            List<Likes> attachedLikesListNew = new ArrayList<Likes>();
            for (Likes likesListNewLikesToAttach : likesListNew) {
                likesListNewLikesToAttach = em.getReference(likesListNewLikesToAttach.getClass(), likesListNewLikesToAttach.getIdlikes());
                attachedLikesListNew.add(likesListNewLikesToAttach);
            }
            likesListNew = attachedLikesListNew;
            usuario.setLikesList(likesListNew);
            List<Seguidor> attachedSeguidorListNew = new ArrayList<Seguidor>();
            for (Seguidor seguidorListNewSeguidorToAttach : seguidorListNew) {
                seguidorListNewSeguidorToAttach = em.getReference(seguidorListNewSeguidorToAttach.getClass(), seguidorListNewSeguidorToAttach.getIdtabela());
                attachedSeguidorListNew.add(seguidorListNewSeguidorToAttach);
            }
            seguidorListNew = attachedSeguidorListNew;
            usuario.setSeguidorList(seguidorListNew);
            List<Seguidor> attachedSeguidorList1New = new ArrayList<Seguidor>();
            for (Seguidor seguidorList1NewSeguidorToAttach : seguidorList1New) {
                seguidorList1NewSeguidorToAttach = em.getReference(seguidorList1NewSeguidorToAttach.getClass(), seguidorList1NewSeguidorToAttach.getIdtabela());
                attachedSeguidorList1New.add(seguidorList1NewSeguidorToAttach);
            }
            seguidorList1New = attachedSeguidorList1New;
            usuario.setSeguidorList1(seguidorList1New);
            List<Solicitacaogrupo> attachedSolicitacaogrupoListNew = new ArrayList<Solicitacaogrupo>();
            for (Solicitacaogrupo solicitacaogrupoListNewSolicitacaogrupoToAttach : solicitacaogrupoListNew) {
                solicitacaogrupoListNewSolicitacaogrupoToAttach = em.getReference(solicitacaogrupoListNewSolicitacaogrupoToAttach.getClass(), solicitacaogrupoListNewSolicitacaogrupoToAttach.getIdsolicitacao());
                attachedSolicitacaogrupoListNew.add(solicitacaogrupoListNewSolicitacaogrupoToAttach);
            }
            solicitacaogrupoListNew = attachedSolicitacaogrupoListNew;
            usuario.setSolicitacaogrupoList(solicitacaogrupoListNew);
            List<Publicacao> attachedPublicacaoListNew = new ArrayList<Publicacao>();
            for (Publicacao publicacaoListNewPublicacaoToAttach : publicacaoListNew) {
                publicacaoListNewPublicacaoToAttach = em.getReference(publicacaoListNewPublicacaoToAttach.getClass(), publicacaoListNewPublicacaoToAttach.getIdpublicacao());
                attachedPublicacaoListNew.add(publicacaoListNewPublicacaoToAttach);
            }
            publicacaoListNew = attachedPublicacaoListNew;
            usuario.setPublicacaoList(publicacaoListNew);
            List<Denuncia> attachedDenunciaListNew = new ArrayList<Denuncia>();
            for (Denuncia denunciaListNewDenunciaToAttach : denunciaListNew) {
                denunciaListNewDenunciaToAttach = em.getReference(denunciaListNewDenunciaToAttach.getClass(), denunciaListNewDenunciaToAttach.getIddenuncia());
                attachedDenunciaListNew.add(denunciaListNewDenunciaToAttach);
            }
            denunciaListNew = attachedDenunciaListNew;
            usuario.setDenunciaList(denunciaListNew);
            List<Usuarioevento> attachedUsuarioeventoListNew = new ArrayList<Usuarioevento>();
            for (Usuarioevento usuarioeventoListNewUsuarioeventoToAttach : usuarioeventoListNew) {
                usuarioeventoListNewUsuarioeventoToAttach = em.getReference(usuarioeventoListNewUsuarioeventoToAttach.getClass(), usuarioeventoListNewUsuarioeventoToAttach.getIdusuarioevento());
                attachedUsuarioeventoListNew.add(usuarioeventoListNewUsuarioeventoToAttach);
            }
            usuarioeventoListNew = attachedUsuarioeventoListNew;
            usuario.setUsuarioeventoList(usuarioeventoListNew);
            List<Consultoriarealizada> attachedConsultoriarealizadaListNew = new ArrayList<Consultoriarealizada>();
            for (Consultoriarealizada consultoriarealizadaListNewConsultoriarealizadaToAttach : consultoriarealizadaListNew) {
                consultoriarealizadaListNewConsultoriarealizadaToAttach = em.getReference(consultoriarealizadaListNewConsultoriarealizadaToAttach.getClass(), consultoriarealizadaListNewConsultoriarealizadaToAttach.getIdconsultoriarealizada());
                attachedConsultoriarealizadaListNew.add(consultoriarealizadaListNewConsultoriarealizadaToAttach);
            }
            consultoriarealizadaListNew = attachedConsultoriarealizadaListNew;
            usuario.setConsultoriarealizadaList(consultoriarealizadaListNew);
            List<Pagamento> attachedPagamentoListNew = new ArrayList<Pagamento>();
            for (Pagamento pagamentoListNewPagamentoToAttach : pagamentoListNew) {
                pagamentoListNewPagamentoToAttach = em.getReference(pagamentoListNewPagamentoToAttach.getClass(), pagamentoListNewPagamentoToAttach.getIdpagamento());
                attachedPagamentoListNew.add(pagamentoListNewPagamentoToAttach);
            }
            pagamentoListNew = attachedPagamentoListNew;
            usuario.setPagamentoList(pagamentoListNew);
            List<Comentariopublicacao> attachedComentariopublicacaoListNew = new ArrayList<Comentariopublicacao>();
            for (Comentariopublicacao comentariopublicacaoListNewComentariopublicacaoToAttach : comentariopublicacaoListNew) {
                comentariopublicacaoListNewComentariopublicacaoToAttach = em.getReference(comentariopublicacaoListNewComentariopublicacaoToAttach.getClass(), comentariopublicacaoListNewComentariopublicacaoToAttach.getIdcomentariopublicacao());
                attachedComentariopublicacaoListNew.add(comentariopublicacaoListNewComentariopublicacaoToAttach);
            }
            comentariopublicacaoListNew = attachedComentariopublicacaoListNew;
            usuario.setComentariopublicacaoList(comentariopublicacaoListNew);
            usuario = em.merge(usuario);
            for (Atleta atletaListNewAtleta : atletaListNew) {
                if (!atletaListOld.contains(atletaListNewAtleta)) {
                    Usuario oldIdusuarioOfAtletaListNewAtleta = atletaListNewAtleta.getIdusuario();
                    atletaListNewAtleta.setIdusuario(usuario);
                    atletaListNewAtleta = em.merge(atletaListNewAtleta);
                    if (oldIdusuarioOfAtletaListNewAtleta != null && !oldIdusuarioOfAtletaListNewAtleta.equals(usuario)) {
                        oldIdusuarioOfAtletaListNewAtleta.getAtletaList().remove(atletaListNewAtleta);
                        oldIdusuarioOfAtletaListNewAtleta = em.merge(oldIdusuarioOfAtletaListNewAtleta);
                    }
                }
            }
            for (Solicitacaoevento solicitacaoeventoListNewSolicitacaoevento : solicitacaoeventoListNew) {
                if (!solicitacaoeventoListOld.contains(solicitacaoeventoListNewSolicitacaoevento)) {
                    Usuario oldIdusuarioOfSolicitacaoeventoListNewSolicitacaoevento = solicitacaoeventoListNewSolicitacaoevento.getIdusuario();
                    solicitacaoeventoListNewSolicitacaoevento.setIdusuario(usuario);
                    solicitacaoeventoListNewSolicitacaoevento = em.merge(solicitacaoeventoListNewSolicitacaoevento);
                    if (oldIdusuarioOfSolicitacaoeventoListNewSolicitacaoevento != null && !oldIdusuarioOfSolicitacaoeventoListNewSolicitacaoevento.equals(usuario)) {
                        oldIdusuarioOfSolicitacaoeventoListNewSolicitacaoevento.getSolicitacaoeventoList().remove(solicitacaoeventoListNewSolicitacaoevento);
                        oldIdusuarioOfSolicitacaoeventoListNewSolicitacaoevento = em.merge(oldIdusuarioOfSolicitacaoeventoListNewSolicitacaoevento);
                    }
                }
            }
            for (Chatconsultoria chatconsultoriaListNewChatconsultoria : chatconsultoriaListNew) {
                if (!chatconsultoriaListOld.contains(chatconsultoriaListNewChatconsultoria)) {
                    Usuario oldIdemissorOfChatconsultoriaListNewChatconsultoria = chatconsultoriaListNewChatconsultoria.getIdemissor();
                    chatconsultoriaListNewChatconsultoria.setIdemissor(usuario);
                    chatconsultoriaListNewChatconsultoria = em.merge(chatconsultoriaListNewChatconsultoria);
                    if (oldIdemissorOfChatconsultoriaListNewChatconsultoria != null && !oldIdemissorOfChatconsultoriaListNewChatconsultoria.equals(usuario)) {
                        oldIdemissorOfChatconsultoriaListNewChatconsultoria.getChatconsultoriaList().remove(chatconsultoriaListNewChatconsultoria);
                        oldIdemissorOfChatconsultoriaListNewChatconsultoria = em.merge(oldIdemissorOfChatconsultoriaListNewChatconsultoria);
                    }
                }
            }
            for (Chatconsultoria chatconsultoriaList1NewChatconsultoria : chatconsultoriaList1New) {
                if (!chatconsultoriaList1Old.contains(chatconsultoriaList1NewChatconsultoria)) {
                    Usuario oldIddestinatarioOfChatconsultoriaList1NewChatconsultoria = chatconsultoriaList1NewChatconsultoria.getIddestinatario();
                    chatconsultoriaList1NewChatconsultoria.setIddestinatario(usuario);
                    chatconsultoriaList1NewChatconsultoria = em.merge(chatconsultoriaList1NewChatconsultoria);
                    if (oldIddestinatarioOfChatconsultoriaList1NewChatconsultoria != null && !oldIddestinatarioOfChatconsultoriaList1NewChatconsultoria.equals(usuario)) {
                        oldIddestinatarioOfChatconsultoriaList1NewChatconsultoria.getChatconsultoriaList1().remove(chatconsultoriaList1NewChatconsultoria);
                        oldIddestinatarioOfChatconsultoriaList1NewChatconsultoria = em.merge(oldIddestinatarioOfChatconsultoriaList1NewChatconsultoria);
                    }
                }
            }
            for (Comentariolocal comentariolocalListNewComentariolocal : comentariolocalListNew) {
                if (!comentariolocalListOld.contains(comentariolocalListNewComentariolocal)) {
                    Usuario oldIdusuarioOfComentariolocalListNewComentariolocal = comentariolocalListNewComentariolocal.getIdusuario();
                    comentariolocalListNewComentariolocal.setIdusuario(usuario);
                    comentariolocalListNewComentariolocal = em.merge(comentariolocalListNewComentariolocal);
                    if (oldIdusuarioOfComentariolocalListNewComentariolocal != null && !oldIdusuarioOfComentariolocalListNewComentariolocal.equals(usuario)) {
                        oldIdusuarioOfComentariolocalListNewComentariolocal.getComentariolocalList().remove(comentariolocalListNewComentariolocal);
                        oldIdusuarioOfComentariolocalListNewComentariolocal = em.merge(oldIdusuarioOfComentariolocalListNewComentariolocal);
                    }
                }
            }
            for (Grupo grupoListNewGrupo : grupoListNew) {
                if (!grupoListOld.contains(grupoListNewGrupo)) {
                    Usuario oldIdusuarioOfGrupoListNewGrupo = grupoListNewGrupo.getIdusuario();
                    grupoListNewGrupo.setIdusuario(usuario);
                    grupoListNewGrupo = em.merge(grupoListNewGrupo);
                    if (oldIdusuarioOfGrupoListNewGrupo != null && !oldIdusuarioOfGrupoListNewGrupo.equals(usuario)) {
                        oldIdusuarioOfGrupoListNewGrupo.getGrupoList().remove(grupoListNewGrupo);
                        oldIdusuarioOfGrupoListNewGrupo = em.merge(oldIdusuarioOfGrupoListNewGrupo);
                    }
                }
            }
            for (Compartilhamento compartilhamentoListNewCompartilhamento : compartilhamentoListNew) {
                if (!compartilhamentoListOld.contains(compartilhamentoListNewCompartilhamento)) {
                    Usuario oldIdusuarioOfCompartilhamentoListNewCompartilhamento = compartilhamentoListNewCompartilhamento.getIdusuario();
                    compartilhamentoListNewCompartilhamento.setIdusuario(usuario);
                    compartilhamentoListNewCompartilhamento = em.merge(compartilhamentoListNewCompartilhamento);
                    if (oldIdusuarioOfCompartilhamentoListNewCompartilhamento != null && !oldIdusuarioOfCompartilhamentoListNewCompartilhamento.equals(usuario)) {
                        oldIdusuarioOfCompartilhamentoListNewCompartilhamento.getCompartilhamentoList().remove(compartilhamentoListNewCompartilhamento);
                        oldIdusuarioOfCompartilhamentoListNewCompartilhamento = em.merge(oldIdusuarioOfCompartilhamentoListNewCompartilhamento);
                    }
                }
            }
            for (Blog blogListNewBlog : blogListNew) {
                if (!blogListOld.contains(blogListNewBlog)) {
                    Usuario oldAutorOfBlogListNewBlog = blogListNewBlog.getAutor();
                    blogListNewBlog.setAutor(usuario);
                    blogListNewBlog = em.merge(blogListNewBlog);
                    if (oldAutorOfBlogListNewBlog != null && !oldAutorOfBlogListNewBlog.equals(usuario)) {
                        oldAutorOfBlogListNewBlog.getBlogList().remove(blogListNewBlog);
                        oldAutorOfBlogListNewBlog = em.merge(oldAutorOfBlogListNewBlog);
                    }
                }
            }
            for (Local localListNewLocal : localListNew) {
                if (!localListOld.contains(localListNewLocal)) {
                    Usuario oldIdusuarioOfLocalListNewLocal = localListNewLocal.getIdusuario();
                    localListNewLocal.setIdusuario(usuario);
                    localListNewLocal = em.merge(localListNewLocal);
                    if (oldIdusuarioOfLocalListNewLocal != null && !oldIdusuarioOfLocalListNewLocal.equals(usuario)) {
                        oldIdusuarioOfLocalListNewLocal.getLocalList().remove(localListNewLocal);
                        oldIdusuarioOfLocalListNewLocal = em.merge(oldIdusuarioOfLocalListNewLocal);
                    }
                }
            }
            for (Profissional profissionalListNewProfissional : profissionalListNew) {
                if (!profissionalListOld.contains(profissionalListNewProfissional)) {
                    Usuario oldIdusuarioOfProfissionalListNewProfissional = profissionalListNewProfissional.getIdusuario();
                    profissionalListNewProfissional.setIdusuario(usuario);
                    profissionalListNewProfissional = em.merge(profissionalListNewProfissional);
                    if (oldIdusuarioOfProfissionalListNewProfissional != null && !oldIdusuarioOfProfissionalListNewProfissional.equals(usuario)) {
                        oldIdusuarioOfProfissionalListNewProfissional.getProfissionalList().remove(profissionalListNewProfissional);
                        oldIdusuarioOfProfissionalListNewProfissional = em.merge(oldIdusuarioOfProfissionalListNewProfissional);
                    }
                }
            }
            for (Mensagempreconsultoria mensagempreconsultoriaListNewMensagempreconsultoria : mensagempreconsultoriaListNew) {
                if (!mensagempreconsultoriaListOld.contains(mensagempreconsultoriaListNewMensagempreconsultoria)) {
                    Usuario oldIdusuarioOfMensagempreconsultoriaListNewMensagempreconsultoria = mensagempreconsultoriaListNewMensagempreconsultoria.getIdusuario();
                    mensagempreconsultoriaListNewMensagempreconsultoria.setIdusuario(usuario);
                    mensagempreconsultoriaListNewMensagempreconsultoria = em.merge(mensagempreconsultoriaListNewMensagempreconsultoria);
                    if (oldIdusuarioOfMensagempreconsultoriaListNewMensagempreconsultoria != null && !oldIdusuarioOfMensagempreconsultoriaListNewMensagempreconsultoria.equals(usuario)) {
                        oldIdusuarioOfMensagempreconsultoriaListNewMensagempreconsultoria.getMensagempreconsultoriaList().remove(mensagempreconsultoriaListNewMensagempreconsultoria);
                        oldIdusuarioOfMensagempreconsultoriaListNewMensagempreconsultoria = em.merge(oldIdusuarioOfMensagempreconsultoriaListNewMensagempreconsultoria);
                    }
                }
            }
            for (Avaliacaoartigo avaliacaoartigoListNewAvaliacaoartigo : avaliacaoartigoListNew) {
                if (!avaliacaoartigoListOld.contains(avaliacaoartigoListNewAvaliacaoartigo)) {
                    Usuario oldIdusuarioOfAvaliacaoartigoListNewAvaliacaoartigo = avaliacaoartigoListNewAvaliacaoartigo.getIdusuario();
                    avaliacaoartigoListNewAvaliacaoartigo.setIdusuario(usuario);
                    avaliacaoartigoListNewAvaliacaoartigo = em.merge(avaliacaoartigoListNewAvaliacaoartigo);
                    if (oldIdusuarioOfAvaliacaoartigoListNewAvaliacaoartigo != null && !oldIdusuarioOfAvaliacaoartigoListNewAvaliacaoartigo.equals(usuario)) {
                        oldIdusuarioOfAvaliacaoartigoListNewAvaliacaoartigo.getAvaliacaoartigoList().remove(avaliacaoartigoListNewAvaliacaoartigo);
                        oldIdusuarioOfAvaliacaoartigoListNewAvaliacaoartigo = em.merge(oldIdusuarioOfAvaliacaoartigoListNewAvaliacaoartigo);
                    }
                }
            }
            for (Usuariogrupo usuariogrupoListNewUsuariogrupo : usuariogrupoListNew) {
                if (!usuariogrupoListOld.contains(usuariogrupoListNewUsuariogrupo)) {
                    Usuario oldIdusuarioOfUsuariogrupoListNewUsuariogrupo = usuariogrupoListNewUsuariogrupo.getIdusuario();
                    usuariogrupoListNewUsuariogrupo.setIdusuario(usuario);
                    usuariogrupoListNewUsuariogrupo = em.merge(usuariogrupoListNewUsuariogrupo);
                    if (oldIdusuarioOfUsuariogrupoListNewUsuariogrupo != null && !oldIdusuarioOfUsuariogrupoListNewUsuariogrupo.equals(usuario)) {
                        oldIdusuarioOfUsuariogrupoListNewUsuariogrupo.getUsuariogrupoList().remove(usuariogrupoListNewUsuariogrupo);
                        oldIdusuarioOfUsuariogrupoListNewUsuariogrupo = em.merge(oldIdusuarioOfUsuariogrupoListNewUsuariogrupo);
                    }
                }
            }
            for (Evento eventoListNewEvento : eventoListNew) {
                if (!eventoListOld.contains(eventoListNewEvento)) {
                    Usuario oldIdusuarioOfEventoListNewEvento = eventoListNewEvento.getIdusuario();
                    eventoListNewEvento.setIdusuario(usuario);
                    eventoListNewEvento = em.merge(eventoListNewEvento);
                    if (oldIdusuarioOfEventoListNewEvento != null && !oldIdusuarioOfEventoListNewEvento.equals(usuario)) {
                        oldIdusuarioOfEventoListNewEvento.getEventoList().remove(eventoListNewEvento);
                        oldIdusuarioOfEventoListNewEvento = em.merge(oldIdusuarioOfEventoListNewEvento);
                    }
                }
            }
            for (Likes likesListNewLikes : likesListNew) {
                if (!likesListOld.contains(likesListNewLikes)) {
                    Usuario oldIdusuarioOfLikesListNewLikes = likesListNewLikes.getIdusuario();
                    likesListNewLikes.setIdusuario(usuario);
                    likesListNewLikes = em.merge(likesListNewLikes);
                    if (oldIdusuarioOfLikesListNewLikes != null && !oldIdusuarioOfLikesListNewLikes.equals(usuario)) {
                        oldIdusuarioOfLikesListNewLikes.getLikesList().remove(likesListNewLikes);
                        oldIdusuarioOfLikesListNewLikes = em.merge(oldIdusuarioOfLikesListNewLikes);
                    }
                }
            }
            for (Seguidor seguidorListNewSeguidor : seguidorListNew) {
                if (!seguidorListOld.contains(seguidorListNewSeguidor)) {
                    Usuario oldIdseguidorOfSeguidorListNewSeguidor = seguidorListNewSeguidor.getIdseguidor();
                    seguidorListNewSeguidor.setIdseguidor(usuario);
                    seguidorListNewSeguidor = em.merge(seguidorListNewSeguidor);
                    if (oldIdseguidorOfSeguidorListNewSeguidor != null && !oldIdseguidorOfSeguidorListNewSeguidor.equals(usuario)) {
                        oldIdseguidorOfSeguidorListNewSeguidor.getSeguidorList().remove(seguidorListNewSeguidor);
                        oldIdseguidorOfSeguidorListNewSeguidor = em.merge(oldIdseguidorOfSeguidorListNewSeguidor);
                    }
                }
            }
            for (Seguidor seguidorList1NewSeguidor : seguidorList1New) {
                if (!seguidorList1Old.contains(seguidorList1NewSeguidor)) {
                    Usuario oldIdseguidoOfSeguidorList1NewSeguidor = seguidorList1NewSeguidor.getIdseguido();
                    seguidorList1NewSeguidor.setIdseguido(usuario);
                    seguidorList1NewSeguidor = em.merge(seguidorList1NewSeguidor);
                    if (oldIdseguidoOfSeguidorList1NewSeguidor != null && !oldIdseguidoOfSeguidorList1NewSeguidor.equals(usuario)) {
                        oldIdseguidoOfSeguidorList1NewSeguidor.getSeguidorList1().remove(seguidorList1NewSeguidor);
                        oldIdseguidoOfSeguidorList1NewSeguidor = em.merge(oldIdseguidoOfSeguidorList1NewSeguidor);
                    }
                }
            }
            for (Solicitacaogrupo solicitacaogrupoListNewSolicitacaogrupo : solicitacaogrupoListNew) {
                if (!solicitacaogrupoListOld.contains(solicitacaogrupoListNewSolicitacaogrupo)) {
                    Usuario oldIdusuarioOfSolicitacaogrupoListNewSolicitacaogrupo = solicitacaogrupoListNewSolicitacaogrupo.getIdusuario();
                    solicitacaogrupoListNewSolicitacaogrupo.setIdusuario(usuario);
                    solicitacaogrupoListNewSolicitacaogrupo = em.merge(solicitacaogrupoListNewSolicitacaogrupo);
                    if (oldIdusuarioOfSolicitacaogrupoListNewSolicitacaogrupo != null && !oldIdusuarioOfSolicitacaogrupoListNewSolicitacaogrupo.equals(usuario)) {
                        oldIdusuarioOfSolicitacaogrupoListNewSolicitacaogrupo.getSolicitacaogrupoList().remove(solicitacaogrupoListNewSolicitacaogrupo);
                        oldIdusuarioOfSolicitacaogrupoListNewSolicitacaogrupo = em.merge(oldIdusuarioOfSolicitacaogrupoListNewSolicitacaogrupo);
                    }
                }
            }
            for (Publicacao publicacaoListNewPublicacao : publicacaoListNew) {
                if (!publicacaoListOld.contains(publicacaoListNewPublicacao)) {
                    Usuario oldIdusuarioOfPublicacaoListNewPublicacao = publicacaoListNewPublicacao.getIdusuario();
                    publicacaoListNewPublicacao.setIdusuario(usuario);
                    publicacaoListNewPublicacao = em.merge(publicacaoListNewPublicacao);
                    if (oldIdusuarioOfPublicacaoListNewPublicacao != null && !oldIdusuarioOfPublicacaoListNewPublicacao.equals(usuario)) {
                        oldIdusuarioOfPublicacaoListNewPublicacao.getPublicacaoList().remove(publicacaoListNewPublicacao);
                        oldIdusuarioOfPublicacaoListNewPublicacao = em.merge(oldIdusuarioOfPublicacaoListNewPublicacao);
                    }
                }
            }
            for (Denuncia denunciaListNewDenuncia : denunciaListNew) {
                if (!denunciaListOld.contains(denunciaListNewDenuncia)) {
                    Usuario oldIdusuarioOfDenunciaListNewDenuncia = denunciaListNewDenuncia.getIdusuario();
                    denunciaListNewDenuncia.setIdusuario(usuario);
                    denunciaListNewDenuncia = em.merge(denunciaListNewDenuncia);
                    if (oldIdusuarioOfDenunciaListNewDenuncia != null && !oldIdusuarioOfDenunciaListNewDenuncia.equals(usuario)) {
                        oldIdusuarioOfDenunciaListNewDenuncia.getDenunciaList().remove(denunciaListNewDenuncia);
                        oldIdusuarioOfDenunciaListNewDenuncia = em.merge(oldIdusuarioOfDenunciaListNewDenuncia);
                    }
                }
            }
            for (Usuarioevento usuarioeventoListNewUsuarioevento : usuarioeventoListNew) {
                if (!usuarioeventoListOld.contains(usuarioeventoListNewUsuarioevento)) {
                    Usuario oldIdusuarioOfUsuarioeventoListNewUsuarioevento = usuarioeventoListNewUsuarioevento.getIdusuario();
                    usuarioeventoListNewUsuarioevento.setIdusuario(usuario);
                    usuarioeventoListNewUsuarioevento = em.merge(usuarioeventoListNewUsuarioevento);
                    if (oldIdusuarioOfUsuarioeventoListNewUsuarioevento != null && !oldIdusuarioOfUsuarioeventoListNewUsuarioevento.equals(usuario)) {
                        oldIdusuarioOfUsuarioeventoListNewUsuarioevento.getUsuarioeventoList().remove(usuarioeventoListNewUsuarioevento);
                        oldIdusuarioOfUsuarioeventoListNewUsuarioevento = em.merge(oldIdusuarioOfUsuarioeventoListNewUsuarioevento);
                    }
                }
            }
            for (Consultoriarealizada consultoriarealizadaListNewConsultoriarealizada : consultoriarealizadaListNew) {
                if (!consultoriarealizadaListOld.contains(consultoriarealizadaListNewConsultoriarealizada)) {
                    Usuario oldIdusuarioOfConsultoriarealizadaListNewConsultoriarealizada = consultoriarealizadaListNewConsultoriarealizada.getIdusuario();
                    consultoriarealizadaListNewConsultoriarealizada.setIdusuario(usuario);
                    consultoriarealizadaListNewConsultoriarealizada = em.merge(consultoriarealizadaListNewConsultoriarealizada);
                    if (oldIdusuarioOfConsultoriarealizadaListNewConsultoriarealizada != null && !oldIdusuarioOfConsultoriarealizadaListNewConsultoriarealizada.equals(usuario)) {
                        oldIdusuarioOfConsultoriarealizadaListNewConsultoriarealizada.getConsultoriarealizadaList().remove(consultoriarealizadaListNewConsultoriarealizada);
                        oldIdusuarioOfConsultoriarealizadaListNewConsultoriarealizada = em.merge(oldIdusuarioOfConsultoriarealizadaListNewConsultoriarealizada);
                    }
                }
            }
            for (Pagamento pagamentoListNewPagamento : pagamentoListNew) {
                if (!pagamentoListOld.contains(pagamentoListNewPagamento)) {
                    Usuario oldIdusuarioOfPagamentoListNewPagamento = pagamentoListNewPagamento.getIdusuario();
                    pagamentoListNewPagamento.setIdusuario(usuario);
                    pagamentoListNewPagamento = em.merge(pagamentoListNewPagamento);
                    if (oldIdusuarioOfPagamentoListNewPagamento != null && !oldIdusuarioOfPagamentoListNewPagamento.equals(usuario)) {
                        oldIdusuarioOfPagamentoListNewPagamento.getPagamentoList().remove(pagamentoListNewPagamento);
                        oldIdusuarioOfPagamentoListNewPagamento = em.merge(oldIdusuarioOfPagamentoListNewPagamento);
                    }
                }
            }
            for (Comentariopublicacao comentariopublicacaoListNewComentariopublicacao : comentariopublicacaoListNew) {
                if (!comentariopublicacaoListOld.contains(comentariopublicacaoListNewComentariopublicacao)) {
                    Usuario oldIdusuarioOfComentariopublicacaoListNewComentariopublicacao = comentariopublicacaoListNewComentariopublicacao.getIdusuario();
                    comentariopublicacaoListNewComentariopublicacao.setIdusuario(usuario);
                    comentariopublicacaoListNewComentariopublicacao = em.merge(comentariopublicacaoListNewComentariopublicacao);
                    if (oldIdusuarioOfComentariopublicacaoListNewComentariopublicacao != null && !oldIdusuarioOfComentariopublicacaoListNewComentariopublicacao.equals(usuario)) {
                        oldIdusuarioOfComentariopublicacaoListNewComentariopublicacao.getComentariopublicacaoList().remove(comentariopublicacaoListNewComentariopublicacao);
                        oldIdusuarioOfComentariopublicacaoListNewComentariopublicacao = em.merge(oldIdusuarioOfComentariopublicacaoListNewComentariopublicacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdusuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdusuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Atleta> atletaListOrphanCheck = usuario.getAtletaList();
            for (Atleta atletaListOrphanCheckAtleta : atletaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Atleta " + atletaListOrphanCheckAtleta + " in its atletaList field has a non-nullable idusuario field.");
            }
            List<Solicitacaoevento> solicitacaoeventoListOrphanCheck = usuario.getSolicitacaoeventoList();
            for (Solicitacaoevento solicitacaoeventoListOrphanCheckSolicitacaoevento : solicitacaoeventoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Solicitacaoevento " + solicitacaoeventoListOrphanCheckSolicitacaoevento + " in its solicitacaoeventoList field has a non-nullable idusuario field.");
            }
            List<Chatconsultoria> chatconsultoriaListOrphanCheck = usuario.getChatconsultoriaList();
            for (Chatconsultoria chatconsultoriaListOrphanCheckChatconsultoria : chatconsultoriaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Chatconsultoria " + chatconsultoriaListOrphanCheckChatconsultoria + " in its chatconsultoriaList field has a non-nullable idemissor field.");
            }
            List<Chatconsultoria> chatconsultoriaList1OrphanCheck = usuario.getChatconsultoriaList1();
            for (Chatconsultoria chatconsultoriaList1OrphanCheckChatconsultoria : chatconsultoriaList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Chatconsultoria " + chatconsultoriaList1OrphanCheckChatconsultoria + " in its chatconsultoriaList1 field has a non-nullable iddestinatario field.");
            }
            List<Comentariolocal> comentariolocalListOrphanCheck = usuario.getComentariolocalList();
            for (Comentariolocal comentariolocalListOrphanCheckComentariolocal : comentariolocalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Comentariolocal " + comentariolocalListOrphanCheckComentariolocal + " in its comentariolocalList field has a non-nullable idusuario field.");
            }
            List<Grupo> grupoListOrphanCheck = usuario.getGrupoList();
            for (Grupo grupoListOrphanCheckGrupo : grupoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Grupo " + grupoListOrphanCheckGrupo + " in its grupoList field has a non-nullable idusuario field.");
            }
            List<Compartilhamento> compartilhamentoListOrphanCheck = usuario.getCompartilhamentoList();
            for (Compartilhamento compartilhamentoListOrphanCheckCompartilhamento : compartilhamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Compartilhamento " + compartilhamentoListOrphanCheckCompartilhamento + " in its compartilhamentoList field has a non-nullable idusuario field.");
            }
            List<Blog> blogListOrphanCheck = usuario.getBlogList();
            for (Blog blogListOrphanCheckBlog : blogListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Blog " + blogListOrphanCheckBlog + " in its blogList field has a non-nullable autor field.");
            }
            List<Local> localListOrphanCheck = usuario.getLocalList();
            for (Local localListOrphanCheckLocal : localListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Local " + localListOrphanCheckLocal + " in its localList field has a non-nullable idusuario field.");
            }
            List<Profissional> profissionalListOrphanCheck = usuario.getProfissionalList();
            for (Profissional profissionalListOrphanCheckProfissional : profissionalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Profissional " + profissionalListOrphanCheckProfissional + " in its profissionalList field has a non-nullable idusuario field.");
            }
            List<Mensagempreconsultoria> mensagempreconsultoriaListOrphanCheck = usuario.getMensagempreconsultoriaList();
            for (Mensagempreconsultoria mensagempreconsultoriaListOrphanCheckMensagempreconsultoria : mensagempreconsultoriaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Mensagempreconsultoria " + mensagempreconsultoriaListOrphanCheckMensagempreconsultoria + " in its mensagempreconsultoriaList field has a non-nullable idusuario field.");
            }
            List<Avaliacaoartigo> avaliacaoartigoListOrphanCheck = usuario.getAvaliacaoartigoList();
            for (Avaliacaoartigo avaliacaoartigoListOrphanCheckAvaliacaoartigo : avaliacaoartigoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Avaliacaoartigo " + avaliacaoartigoListOrphanCheckAvaliacaoartigo + " in its avaliacaoartigoList field has a non-nullable idusuario field.");
            }
            List<Usuariogrupo> usuariogrupoListOrphanCheck = usuario.getUsuariogrupoList();
            for (Usuariogrupo usuariogrupoListOrphanCheckUsuariogrupo : usuariogrupoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Usuariogrupo " + usuariogrupoListOrphanCheckUsuariogrupo + " in its usuariogrupoList field has a non-nullable idusuario field.");
            }
            List<Evento> eventoListOrphanCheck = usuario.getEventoList();
            for (Evento eventoListOrphanCheckEvento : eventoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Evento " + eventoListOrphanCheckEvento + " in its eventoList field has a non-nullable idusuario field.");
            }
            List<Likes> likesListOrphanCheck = usuario.getLikesList();
            for (Likes likesListOrphanCheckLikes : likesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Likes " + likesListOrphanCheckLikes + " in its likesList field has a non-nullable idusuario field.");
            }
            List<Seguidor> seguidorListOrphanCheck = usuario.getSeguidorList();
            for (Seguidor seguidorListOrphanCheckSeguidor : seguidorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Seguidor " + seguidorListOrphanCheckSeguidor + " in its seguidorList field has a non-nullable idseguidor field.");
            }
            List<Seguidor> seguidorList1OrphanCheck = usuario.getSeguidorList1();
            for (Seguidor seguidorList1OrphanCheckSeguidor : seguidorList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Seguidor " + seguidorList1OrphanCheckSeguidor + " in its seguidorList1 field has a non-nullable idseguido field.");
            }
            List<Solicitacaogrupo> solicitacaogrupoListOrphanCheck = usuario.getSolicitacaogrupoList();
            for (Solicitacaogrupo solicitacaogrupoListOrphanCheckSolicitacaogrupo : solicitacaogrupoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Solicitacaogrupo " + solicitacaogrupoListOrphanCheckSolicitacaogrupo + " in its solicitacaogrupoList field has a non-nullable idusuario field.");
            }
            List<Publicacao> publicacaoListOrphanCheck = usuario.getPublicacaoList();
            for (Publicacao publicacaoListOrphanCheckPublicacao : publicacaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Publicacao " + publicacaoListOrphanCheckPublicacao + " in its publicacaoList field has a non-nullable idusuario field.");
            }
            List<Denuncia> denunciaListOrphanCheck = usuario.getDenunciaList();
            for (Denuncia denunciaListOrphanCheckDenuncia : denunciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Denuncia " + denunciaListOrphanCheckDenuncia + " in its denunciaList field has a non-nullable idusuario field.");
            }
            List<Usuarioevento> usuarioeventoListOrphanCheck = usuario.getUsuarioeventoList();
            for (Usuarioevento usuarioeventoListOrphanCheckUsuarioevento : usuarioeventoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Usuarioevento " + usuarioeventoListOrphanCheckUsuarioevento + " in its usuarioeventoList field has a non-nullable idusuario field.");
            }
            List<Consultoriarealizada> consultoriarealizadaListOrphanCheck = usuario.getConsultoriarealizadaList();
            for (Consultoriarealizada consultoriarealizadaListOrphanCheckConsultoriarealizada : consultoriarealizadaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Consultoriarealizada " + consultoriarealizadaListOrphanCheckConsultoriarealizada + " in its consultoriarealizadaList field has a non-nullable idusuario field.");
            }
            List<Pagamento> pagamentoListOrphanCheck = usuario.getPagamentoList();
            for (Pagamento pagamentoListOrphanCheckPagamento : pagamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Pagamento " + pagamentoListOrphanCheckPagamento + " in its pagamentoList field has a non-nullable idusuario field.");
            }
            List<Comentariopublicacao> comentariopublicacaoListOrphanCheck = usuario.getComentariopublicacaoList();
            for (Comentariopublicacao comentariopublicacaoListOrphanCheckComentariopublicacao : comentariopublicacaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Comentariopublicacao " + comentariopublicacaoListOrphanCheckComentariopublicacao + " in its comentariopublicacaoList field has a non-nullable idusuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
