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
import model.Texto;
import model.Imagem;
import model.Video;
import model.Link;
import model.Compartilhamento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Likes;
import model.Chave;
import model.Comentariopublicacao;
import model.Publicacao;

/**
 *
 * @author asdfrofl
 */
public class PublicacaoJpaController implements Serializable {

    public PublicacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Publicacao publicacao) {
        if (publicacao.getCompartilhamentoList() == null) {
            publicacao.setCompartilhamentoList(new ArrayList<Compartilhamento>());
        }
        if (publicacao.getLikesList() == null) {
            publicacao.setLikesList(new ArrayList<Likes>());
        }
        if (publicacao.getChaveList() == null) {
            publicacao.setChaveList(new ArrayList<Chave>());
        }
        if (publicacao.getComentariopublicacaoList() == null) {
            publicacao.setComentariopublicacaoList(new ArrayList<Comentariopublicacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idusuario = publicacao.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                publicacao.setIdusuario(idusuario);
            }
            Texto idtexto = publicacao.getIdtexto();
            if (idtexto != null) {
                idtexto = em.getReference(idtexto.getClass(), idtexto.getIdtexto());
                publicacao.setIdtexto(idtexto);
            }
            Imagem idimagem = publicacao.getIdimagem();
            if (idimagem != null) {
                idimagem = em.getReference(idimagem.getClass(), idimagem.getIdimagem());
                publicacao.setIdimagem(idimagem);
            }
            Video idvideo = publicacao.getIdvideo();
            if (idvideo != null) {
                idvideo = em.getReference(idvideo.getClass(), idvideo.getIdvideo());
                publicacao.setIdvideo(idvideo);
            }
            Link idlink = publicacao.getIdlink();
            if (idlink != null) {
                idlink = em.getReference(idlink.getClass(), idlink.getIdlink());
                publicacao.setIdlink(idlink);
            }
            List<Compartilhamento> attachedCompartilhamentoList = new ArrayList<Compartilhamento>();
            for (Compartilhamento compartilhamentoListCompartilhamentoToAttach : publicacao.getCompartilhamentoList()) {
                compartilhamentoListCompartilhamentoToAttach = em.getReference(compartilhamentoListCompartilhamentoToAttach.getClass(), compartilhamentoListCompartilhamentoToAttach.getIdcompartilhamento());
                attachedCompartilhamentoList.add(compartilhamentoListCompartilhamentoToAttach);
            }
            publicacao.setCompartilhamentoList(attachedCompartilhamentoList);
            List<Likes> attachedLikesList = new ArrayList<Likes>();
            for (Likes likesListLikesToAttach : publicacao.getLikesList()) {
                likesListLikesToAttach = em.getReference(likesListLikesToAttach.getClass(), likesListLikesToAttach.getIdlikes());
                attachedLikesList.add(likesListLikesToAttach);
            }
            publicacao.setLikesList(attachedLikesList);
            List<Chave> attachedChaveList = new ArrayList<Chave>();
            for (Chave chaveListChaveToAttach : publicacao.getChaveList()) {
                chaveListChaveToAttach = em.getReference(chaveListChaveToAttach.getClass(), chaveListChaveToAttach.getIdchave());
                attachedChaveList.add(chaveListChaveToAttach);
            }
            publicacao.setChaveList(attachedChaveList);
            List<Comentariopublicacao> attachedComentariopublicacaoList = new ArrayList<Comentariopublicacao>();
            for (Comentariopublicacao comentariopublicacaoListComentariopublicacaoToAttach : publicacao.getComentariopublicacaoList()) {
                comentariopublicacaoListComentariopublicacaoToAttach = em.getReference(comentariopublicacaoListComentariopublicacaoToAttach.getClass(), comentariopublicacaoListComentariopublicacaoToAttach.getIdcomentariopublicacao());
                attachedComentariopublicacaoList.add(comentariopublicacaoListComentariopublicacaoToAttach);
            }
            publicacao.setComentariopublicacaoList(attachedComentariopublicacaoList);
            em.persist(publicacao);
            if (idusuario != null) {
                idusuario.getPublicacaoList().add(publicacao);
                idusuario = em.merge(idusuario);
            }
            if (idtexto != null) {
                idtexto.getPublicacaoList().add(publicacao);
                idtexto = em.merge(idtexto);
            }
            if (idimagem != null) {
                idimagem.getPublicacaoList().add(publicacao);
                idimagem = em.merge(idimagem);
            }
            if (idvideo != null) {
                idvideo.getPublicacaoList().add(publicacao);
                idvideo = em.merge(idvideo);
            }
            if (idlink != null) {
                idlink.getPublicacaoList().add(publicacao);
                idlink = em.merge(idlink);
            }
            for (Compartilhamento compartilhamentoListCompartilhamento : publicacao.getCompartilhamentoList()) {
                Publicacao oldIdpublicacaoOfCompartilhamentoListCompartilhamento = compartilhamentoListCompartilhamento.getIdpublicacao();
                compartilhamentoListCompartilhamento.setIdpublicacao(publicacao);
                compartilhamentoListCompartilhamento = em.merge(compartilhamentoListCompartilhamento);
                if (oldIdpublicacaoOfCompartilhamentoListCompartilhamento != null) {
                    oldIdpublicacaoOfCompartilhamentoListCompartilhamento.getCompartilhamentoList().remove(compartilhamentoListCompartilhamento);
                    oldIdpublicacaoOfCompartilhamentoListCompartilhamento = em.merge(oldIdpublicacaoOfCompartilhamentoListCompartilhamento);
                }
            }
            for (Likes likesListLikes : publicacao.getLikesList()) {
                Publicacao oldIdpublicacaoOfLikesListLikes = likesListLikes.getIdpublicacao();
                likesListLikes.setIdpublicacao(publicacao);
                likesListLikes = em.merge(likesListLikes);
                if (oldIdpublicacaoOfLikesListLikes != null) {
                    oldIdpublicacaoOfLikesListLikes.getLikesList().remove(likesListLikes);
                    oldIdpublicacaoOfLikesListLikes = em.merge(oldIdpublicacaoOfLikesListLikes);
                }
            }
            for (Chave chaveListChave : publicacao.getChaveList()) {
                Publicacao oldIdpublicacaoOfChaveListChave = chaveListChave.getIdpublicacao();
                chaveListChave.setIdpublicacao(publicacao);
                chaveListChave = em.merge(chaveListChave);
                if (oldIdpublicacaoOfChaveListChave != null) {
                    oldIdpublicacaoOfChaveListChave.getChaveList().remove(chaveListChave);
                    oldIdpublicacaoOfChaveListChave = em.merge(oldIdpublicacaoOfChaveListChave);
                }
            }
            for (Comentariopublicacao comentariopublicacaoListComentariopublicacao : publicacao.getComentariopublicacaoList()) {
                Publicacao oldIdpublicacaoOfComentariopublicacaoListComentariopublicacao = comentariopublicacaoListComentariopublicacao.getIdpublicacao();
                comentariopublicacaoListComentariopublicacao.setIdpublicacao(publicacao);
                comentariopublicacaoListComentariopublicacao = em.merge(comentariopublicacaoListComentariopublicacao);
                if (oldIdpublicacaoOfComentariopublicacaoListComentariopublicacao != null) {
                    oldIdpublicacaoOfComentariopublicacaoListComentariopublicacao.getComentariopublicacaoList().remove(comentariopublicacaoListComentariopublicacao);
                    oldIdpublicacaoOfComentariopublicacaoListComentariopublicacao = em.merge(oldIdpublicacaoOfComentariopublicacaoListComentariopublicacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Publicacao publicacao) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Publicacao persistentPublicacao = em.find(Publicacao.class, publicacao.getIdpublicacao());
            Usuario idusuarioOld = persistentPublicacao.getIdusuario();
            Usuario idusuarioNew = publicacao.getIdusuario();
            Texto idtextoOld = persistentPublicacao.getIdtexto();
            Texto idtextoNew = publicacao.getIdtexto();
            Imagem idimagemOld = persistentPublicacao.getIdimagem();
            Imagem idimagemNew = publicacao.getIdimagem();
            Video idvideoOld = persistentPublicacao.getIdvideo();
            Video idvideoNew = publicacao.getIdvideo();
            Link idlinkOld = persistentPublicacao.getIdlink();
            Link idlinkNew = publicacao.getIdlink();
            List<Compartilhamento> compartilhamentoListOld = persistentPublicacao.getCompartilhamentoList();
            List<Compartilhamento> compartilhamentoListNew = publicacao.getCompartilhamentoList();
            List<Likes> likesListOld = persistentPublicacao.getLikesList();
            List<Likes> likesListNew = publicacao.getLikesList();
            List<Chave> chaveListOld = persistentPublicacao.getChaveList();
            List<Chave> chaveListNew = publicacao.getChaveList();
            List<Comentariopublicacao> comentariopublicacaoListOld = persistentPublicacao.getComentariopublicacaoList();
            List<Comentariopublicacao> comentariopublicacaoListNew = publicacao.getComentariopublicacaoList();
            List<String> illegalOrphanMessages = null;
            for (Compartilhamento compartilhamentoListOldCompartilhamento : compartilhamentoListOld) {
                if (!compartilhamentoListNew.contains(compartilhamentoListOldCompartilhamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Compartilhamento " + compartilhamentoListOldCompartilhamento + " since its idpublicacao field is not nullable.");
                }
            }
            for (Likes likesListOldLikes : likesListOld) {
                if (!likesListNew.contains(likesListOldLikes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Likes " + likesListOldLikes + " since its idpublicacao field is not nullable.");
                }
            }
            for (Chave chaveListOldChave : chaveListOld) {
                if (!chaveListNew.contains(chaveListOldChave)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Chave " + chaveListOldChave + " since its idpublicacao field is not nullable.");
                }
            }
            for (Comentariopublicacao comentariopublicacaoListOldComentariopublicacao : comentariopublicacaoListOld) {
                if (!comentariopublicacaoListNew.contains(comentariopublicacaoListOldComentariopublicacao)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comentariopublicacao " + comentariopublicacaoListOldComentariopublicacao + " since its idpublicacao field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                publicacao.setIdusuario(idusuarioNew);
            }
            if (idtextoNew != null) {
                idtextoNew = em.getReference(idtextoNew.getClass(), idtextoNew.getIdtexto());
                publicacao.setIdtexto(idtextoNew);
            }
            if (idimagemNew != null) {
                idimagemNew = em.getReference(idimagemNew.getClass(), idimagemNew.getIdimagem());
                publicacao.setIdimagem(idimagemNew);
            }
            if (idvideoNew != null) {
                idvideoNew = em.getReference(idvideoNew.getClass(), idvideoNew.getIdvideo());
                publicacao.setIdvideo(idvideoNew);
            }
            if (idlinkNew != null) {
                idlinkNew = em.getReference(idlinkNew.getClass(), idlinkNew.getIdlink());
                publicacao.setIdlink(idlinkNew);
            }
            List<Compartilhamento> attachedCompartilhamentoListNew = new ArrayList<Compartilhamento>();
            for (Compartilhamento compartilhamentoListNewCompartilhamentoToAttach : compartilhamentoListNew) {
                compartilhamentoListNewCompartilhamentoToAttach = em.getReference(compartilhamentoListNewCompartilhamentoToAttach.getClass(), compartilhamentoListNewCompartilhamentoToAttach.getIdcompartilhamento());
                attachedCompartilhamentoListNew.add(compartilhamentoListNewCompartilhamentoToAttach);
            }
            compartilhamentoListNew = attachedCompartilhamentoListNew;
            publicacao.setCompartilhamentoList(compartilhamentoListNew);
            List<Likes> attachedLikesListNew = new ArrayList<Likes>();
            for (Likes likesListNewLikesToAttach : likesListNew) {
                likesListNewLikesToAttach = em.getReference(likesListNewLikesToAttach.getClass(), likesListNewLikesToAttach.getIdlikes());
                attachedLikesListNew.add(likesListNewLikesToAttach);
            }
            likesListNew = attachedLikesListNew;
            publicacao.setLikesList(likesListNew);
            List<Chave> attachedChaveListNew = new ArrayList<Chave>();
            for (Chave chaveListNewChaveToAttach : chaveListNew) {
                chaveListNewChaveToAttach = em.getReference(chaveListNewChaveToAttach.getClass(), chaveListNewChaveToAttach.getIdchave());
                attachedChaveListNew.add(chaveListNewChaveToAttach);
            }
            chaveListNew = attachedChaveListNew;
            publicacao.setChaveList(chaveListNew);
            List<Comentariopublicacao> attachedComentariopublicacaoListNew = new ArrayList<Comentariopublicacao>();
            for (Comentariopublicacao comentariopublicacaoListNewComentariopublicacaoToAttach : comentariopublicacaoListNew) {
                comentariopublicacaoListNewComentariopublicacaoToAttach = em.getReference(comentariopublicacaoListNewComentariopublicacaoToAttach.getClass(), comentariopublicacaoListNewComentariopublicacaoToAttach.getIdcomentariopublicacao());
                attachedComentariopublicacaoListNew.add(comentariopublicacaoListNewComentariopublicacaoToAttach);
            }
            comentariopublicacaoListNew = attachedComentariopublicacaoListNew;
            publicacao.setComentariopublicacaoList(comentariopublicacaoListNew);
            publicacao = em.merge(publicacao);
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getPublicacaoList().remove(publicacao);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getPublicacaoList().add(publicacao);
                idusuarioNew = em.merge(idusuarioNew);
            }
            if (idtextoOld != null && !idtextoOld.equals(idtextoNew)) {
                idtextoOld.getPublicacaoList().remove(publicacao);
                idtextoOld = em.merge(idtextoOld);
            }
            if (idtextoNew != null && !idtextoNew.equals(idtextoOld)) {
                idtextoNew.getPublicacaoList().add(publicacao);
                idtextoNew = em.merge(idtextoNew);
            }
            if (idimagemOld != null && !idimagemOld.equals(idimagemNew)) {
                idimagemOld.getPublicacaoList().remove(publicacao);
                idimagemOld = em.merge(idimagemOld);
            }
            if (idimagemNew != null && !idimagemNew.equals(idimagemOld)) {
                idimagemNew.getPublicacaoList().add(publicacao);
                idimagemNew = em.merge(idimagemNew);
            }
            if (idvideoOld != null && !idvideoOld.equals(idvideoNew)) {
                idvideoOld.getPublicacaoList().remove(publicacao);
                idvideoOld = em.merge(idvideoOld);
            }
            if (idvideoNew != null && !idvideoNew.equals(idvideoOld)) {
                idvideoNew.getPublicacaoList().add(publicacao);
                idvideoNew = em.merge(idvideoNew);
            }
            if (idlinkOld != null && !idlinkOld.equals(idlinkNew)) {
                idlinkOld.getPublicacaoList().remove(publicacao);
                idlinkOld = em.merge(idlinkOld);
            }
            if (idlinkNew != null && !idlinkNew.equals(idlinkOld)) {
                idlinkNew.getPublicacaoList().add(publicacao);
                idlinkNew = em.merge(idlinkNew);
            }
            for (Compartilhamento compartilhamentoListNewCompartilhamento : compartilhamentoListNew) {
                if (!compartilhamentoListOld.contains(compartilhamentoListNewCompartilhamento)) {
                    Publicacao oldIdpublicacaoOfCompartilhamentoListNewCompartilhamento = compartilhamentoListNewCompartilhamento.getIdpublicacao();
                    compartilhamentoListNewCompartilhamento.setIdpublicacao(publicacao);
                    compartilhamentoListNewCompartilhamento = em.merge(compartilhamentoListNewCompartilhamento);
                    if (oldIdpublicacaoOfCompartilhamentoListNewCompartilhamento != null && !oldIdpublicacaoOfCompartilhamentoListNewCompartilhamento.equals(publicacao)) {
                        oldIdpublicacaoOfCompartilhamentoListNewCompartilhamento.getCompartilhamentoList().remove(compartilhamentoListNewCompartilhamento);
                        oldIdpublicacaoOfCompartilhamentoListNewCompartilhamento = em.merge(oldIdpublicacaoOfCompartilhamentoListNewCompartilhamento);
                    }
                }
            }
            for (Likes likesListNewLikes : likesListNew) {
                if (!likesListOld.contains(likesListNewLikes)) {
                    Publicacao oldIdpublicacaoOfLikesListNewLikes = likesListNewLikes.getIdpublicacao();
                    likesListNewLikes.setIdpublicacao(publicacao);
                    likesListNewLikes = em.merge(likesListNewLikes);
                    if (oldIdpublicacaoOfLikesListNewLikes != null && !oldIdpublicacaoOfLikesListNewLikes.equals(publicacao)) {
                        oldIdpublicacaoOfLikesListNewLikes.getLikesList().remove(likesListNewLikes);
                        oldIdpublicacaoOfLikesListNewLikes = em.merge(oldIdpublicacaoOfLikesListNewLikes);
                    }
                }
            }
            for (Chave chaveListNewChave : chaveListNew) {
                if (!chaveListOld.contains(chaveListNewChave)) {
                    Publicacao oldIdpublicacaoOfChaveListNewChave = chaveListNewChave.getIdpublicacao();
                    chaveListNewChave.setIdpublicacao(publicacao);
                    chaveListNewChave = em.merge(chaveListNewChave);
                    if (oldIdpublicacaoOfChaveListNewChave != null && !oldIdpublicacaoOfChaveListNewChave.equals(publicacao)) {
                        oldIdpublicacaoOfChaveListNewChave.getChaveList().remove(chaveListNewChave);
                        oldIdpublicacaoOfChaveListNewChave = em.merge(oldIdpublicacaoOfChaveListNewChave);
                    }
                }
            }
            for (Comentariopublicacao comentariopublicacaoListNewComentariopublicacao : comentariopublicacaoListNew) {
                if (!comentariopublicacaoListOld.contains(comentariopublicacaoListNewComentariopublicacao)) {
                    Publicacao oldIdpublicacaoOfComentariopublicacaoListNewComentariopublicacao = comentariopublicacaoListNewComentariopublicacao.getIdpublicacao();
                    comentariopublicacaoListNewComentariopublicacao.setIdpublicacao(publicacao);
                    comentariopublicacaoListNewComentariopublicacao = em.merge(comentariopublicacaoListNewComentariopublicacao);
                    if (oldIdpublicacaoOfComentariopublicacaoListNewComentariopublicacao != null && !oldIdpublicacaoOfComentariopublicacaoListNewComentariopublicacao.equals(publicacao)) {
                        oldIdpublicacaoOfComentariopublicacaoListNewComentariopublicacao.getComentariopublicacaoList().remove(comentariopublicacaoListNewComentariopublicacao);
                        oldIdpublicacaoOfComentariopublicacaoListNewComentariopublicacao = em.merge(oldIdpublicacaoOfComentariopublicacaoListNewComentariopublicacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = publicacao.getIdpublicacao();
                if (findPublicacao(id) == null) {
                    throw new NonexistentEntityException("The publicacao with id " + id + " no longer exists.");
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
            Publicacao publicacao;
            try {
                publicacao = em.getReference(Publicacao.class, id);
                publicacao.getIdpublicacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The publicacao with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Compartilhamento> compartilhamentoListOrphanCheck = publicacao.getCompartilhamentoList();
            for (Compartilhamento compartilhamentoListOrphanCheckCompartilhamento : compartilhamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Publicacao (" + publicacao + ") cannot be destroyed since the Compartilhamento " + compartilhamentoListOrphanCheckCompartilhamento + " in its compartilhamentoList field has a non-nullable idpublicacao field.");
            }
            List<Likes> likesListOrphanCheck = publicacao.getLikesList();
            for (Likes likesListOrphanCheckLikes : likesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Publicacao (" + publicacao + ") cannot be destroyed since the Likes " + likesListOrphanCheckLikes + " in its likesList field has a non-nullable idpublicacao field.");
            }
            List<Chave> chaveListOrphanCheck = publicacao.getChaveList();
            for (Chave chaveListOrphanCheckChave : chaveListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Publicacao (" + publicacao + ") cannot be destroyed since the Chave " + chaveListOrphanCheckChave + " in its chaveList field has a non-nullable idpublicacao field.");
            }
            List<Comentariopublicacao> comentariopublicacaoListOrphanCheck = publicacao.getComentariopublicacaoList();
            for (Comentariopublicacao comentariopublicacaoListOrphanCheckComentariopublicacao : comentariopublicacaoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Publicacao (" + publicacao + ") cannot be destroyed since the Comentariopublicacao " + comentariopublicacaoListOrphanCheckComentariopublicacao + " in its comentariopublicacaoList field has a non-nullable idpublicacao field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario idusuario = publicacao.getIdusuario();
            if (idusuario != null) {
                idusuario.getPublicacaoList().remove(publicacao);
                idusuario = em.merge(idusuario);
            }
            Texto idtexto = publicacao.getIdtexto();
            if (idtexto != null) {
                idtexto.getPublicacaoList().remove(publicacao);
                idtexto = em.merge(idtexto);
            }
            Imagem idimagem = publicacao.getIdimagem();
            if (idimagem != null) {
                idimagem.getPublicacaoList().remove(publicacao);
                idimagem = em.merge(idimagem);
            }
            Video idvideo = publicacao.getIdvideo();
            if (idvideo != null) {
                idvideo.getPublicacaoList().remove(publicacao);
                idvideo = em.merge(idvideo);
            }
            Link idlink = publicacao.getIdlink();
            if (idlink != null) {
                idlink.getPublicacaoList().remove(publicacao);
                idlink = em.merge(idlink);
            }
            em.remove(publicacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Publicacao> findPublicacaoEntities() {
        return findPublicacaoEntities(true, -1, -1);
    }

    public List<Publicacao> findPublicacaoEntities(int maxResults, int firstResult) {
        return findPublicacaoEntities(false, maxResults, firstResult);
    }

    private List<Publicacao> findPublicacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Publicacao.class));
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

    public Publicacao findPublicacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Publicacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getPublicacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Publicacao> rt = cq.from(Publicacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Publicacao> findPublicationsUser(int id){
        
        String query = "SELECT p FROM Publicacao p WHERE "
                + "  p.idusuario.idusuario = :id order by p.data desc"; 
        
        Query q = getEntityManager().createQuery(query);
        q.setParameter("id", id);       
        
        try{
            return (List<Publicacao>) q.getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    
    public List<Publicacao> findPublicationsUserFollowed(int id){
        
        String query = "SELECT p FROM Publicacao p, Seguidor s WHERE "
                + "  s.idseguidor.idusuario = :id and s.idseguido.idusuario "
                + " = p.idusuario.idusuario order by p.data desc"; 
        
        Query q = getEntityManager().createQuery(query);
        q.setParameter("id", id);       
        
        try{
            return (List<Publicacao>) q.getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    
}
