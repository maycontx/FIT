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
import model.Compartilhamento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Artigo;
import model.Publicacao;
import model.Texto;

/**
 *
 * @author asdfrofl
 */
public class TextoJpaController implements Serializable {

    public TextoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Texto texto) {
        if (texto.getCompartilhamentoList() == null) {
            texto.setCompartilhamentoList(new ArrayList<Compartilhamento>());
        }
        if (texto.getArtigoList() == null) {
            texto.setArtigoList(new ArrayList<Artigo>());
        }
        if (texto.getPublicacaoList() == null) {
            texto.setPublicacaoList(new ArrayList<Publicacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Compartilhamento> attachedCompartilhamentoList = new ArrayList<Compartilhamento>();
            for (Compartilhamento compartilhamentoListCompartilhamentoToAttach : texto.getCompartilhamentoList()) {
                compartilhamentoListCompartilhamentoToAttach = em.getReference(compartilhamentoListCompartilhamentoToAttach.getClass(), compartilhamentoListCompartilhamentoToAttach.getIdcompartilhamento());
                attachedCompartilhamentoList.add(compartilhamentoListCompartilhamentoToAttach);
            }
            texto.setCompartilhamentoList(attachedCompartilhamentoList);
            List<Artigo> attachedArtigoList = new ArrayList<Artigo>();
            for (Artigo artigoListArtigoToAttach : texto.getArtigoList()) {
                artigoListArtigoToAttach = em.getReference(artigoListArtigoToAttach.getClass(), artigoListArtigoToAttach.getIdartigo());
                attachedArtigoList.add(artigoListArtigoToAttach);
            }
            texto.setArtigoList(attachedArtigoList);
            List<Publicacao> attachedPublicacaoList = new ArrayList<Publicacao>();
            for (Publicacao publicacaoListPublicacaoToAttach : texto.getPublicacaoList()) {
                publicacaoListPublicacaoToAttach = em.getReference(publicacaoListPublicacaoToAttach.getClass(), publicacaoListPublicacaoToAttach.getIdpublicacao());
                attachedPublicacaoList.add(publicacaoListPublicacaoToAttach);
            }
            texto.setPublicacaoList(attachedPublicacaoList);
            em.persist(texto);
            for (Compartilhamento compartilhamentoListCompartilhamento : texto.getCompartilhamentoList()) {
                Texto oldIdtextoOfCompartilhamentoListCompartilhamento = compartilhamentoListCompartilhamento.getIdtexto();
                compartilhamentoListCompartilhamento.setIdtexto(texto);
                compartilhamentoListCompartilhamento = em.merge(compartilhamentoListCompartilhamento);
                if (oldIdtextoOfCompartilhamentoListCompartilhamento != null) {
                    oldIdtextoOfCompartilhamentoListCompartilhamento.getCompartilhamentoList().remove(compartilhamentoListCompartilhamento);
                    oldIdtextoOfCompartilhamentoListCompartilhamento = em.merge(oldIdtextoOfCompartilhamentoListCompartilhamento);
                }
            }
            for (Artigo artigoListArtigo : texto.getArtigoList()) {
                Texto oldIdtextoOfArtigoListArtigo = artigoListArtigo.getIdtexto();
                artigoListArtigo.setIdtexto(texto);
                artigoListArtigo = em.merge(artigoListArtigo);
                if (oldIdtextoOfArtigoListArtigo != null) {
                    oldIdtextoOfArtigoListArtigo.getArtigoList().remove(artigoListArtigo);
                    oldIdtextoOfArtigoListArtigo = em.merge(oldIdtextoOfArtigoListArtigo);
                }
            }
            for (Publicacao publicacaoListPublicacao : texto.getPublicacaoList()) {
                Texto oldIdtextoOfPublicacaoListPublicacao = publicacaoListPublicacao.getIdtexto();
                publicacaoListPublicacao.setIdtexto(texto);
                publicacaoListPublicacao = em.merge(publicacaoListPublicacao);
                if (oldIdtextoOfPublicacaoListPublicacao != null) {
                    oldIdtextoOfPublicacaoListPublicacao.getPublicacaoList().remove(publicacaoListPublicacao);
                    oldIdtextoOfPublicacaoListPublicacao = em.merge(oldIdtextoOfPublicacaoListPublicacao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Texto texto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Texto persistentTexto = em.find(Texto.class, texto.getIdtexto());
            List<Compartilhamento> compartilhamentoListOld = persistentTexto.getCompartilhamentoList();
            List<Compartilhamento> compartilhamentoListNew = texto.getCompartilhamentoList();
            List<Artigo> artigoListOld = persistentTexto.getArtigoList();
            List<Artigo> artigoListNew = texto.getArtigoList();
            List<Publicacao> publicacaoListOld = persistentTexto.getPublicacaoList();
            List<Publicacao> publicacaoListNew = texto.getPublicacaoList();
            List<String> illegalOrphanMessages = null;
            for (Artigo artigoListOldArtigo : artigoListOld) {
                if (!artigoListNew.contains(artigoListOldArtigo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Artigo " + artigoListOldArtigo + " since its idtexto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Compartilhamento> attachedCompartilhamentoListNew = new ArrayList<Compartilhamento>();
            for (Compartilhamento compartilhamentoListNewCompartilhamentoToAttach : compartilhamentoListNew) {
                compartilhamentoListNewCompartilhamentoToAttach = em.getReference(compartilhamentoListNewCompartilhamentoToAttach.getClass(), compartilhamentoListNewCompartilhamentoToAttach.getIdcompartilhamento());
                attachedCompartilhamentoListNew.add(compartilhamentoListNewCompartilhamentoToAttach);
            }
            compartilhamentoListNew = attachedCompartilhamentoListNew;
            texto.setCompartilhamentoList(compartilhamentoListNew);
            List<Artigo> attachedArtigoListNew = new ArrayList<Artigo>();
            for (Artigo artigoListNewArtigoToAttach : artigoListNew) {
                artigoListNewArtigoToAttach = em.getReference(artigoListNewArtigoToAttach.getClass(), artigoListNewArtigoToAttach.getIdartigo());
                attachedArtigoListNew.add(artigoListNewArtigoToAttach);
            }
            artigoListNew = attachedArtigoListNew;
            texto.setArtigoList(artigoListNew);
            List<Publicacao> attachedPublicacaoListNew = new ArrayList<Publicacao>();
            for (Publicacao publicacaoListNewPublicacaoToAttach : publicacaoListNew) {
                publicacaoListNewPublicacaoToAttach = em.getReference(publicacaoListNewPublicacaoToAttach.getClass(), publicacaoListNewPublicacaoToAttach.getIdpublicacao());
                attachedPublicacaoListNew.add(publicacaoListNewPublicacaoToAttach);
            }
            publicacaoListNew = attachedPublicacaoListNew;
            texto.setPublicacaoList(publicacaoListNew);
            texto = em.merge(texto);
            for (Compartilhamento compartilhamentoListOldCompartilhamento : compartilhamentoListOld) {
                if (!compartilhamentoListNew.contains(compartilhamentoListOldCompartilhamento)) {
                    compartilhamentoListOldCompartilhamento.setIdtexto(null);
                    compartilhamentoListOldCompartilhamento = em.merge(compartilhamentoListOldCompartilhamento);
                }
            }
            for (Compartilhamento compartilhamentoListNewCompartilhamento : compartilhamentoListNew) {
                if (!compartilhamentoListOld.contains(compartilhamentoListNewCompartilhamento)) {
                    Texto oldIdtextoOfCompartilhamentoListNewCompartilhamento = compartilhamentoListNewCompartilhamento.getIdtexto();
                    compartilhamentoListNewCompartilhamento.setIdtexto(texto);
                    compartilhamentoListNewCompartilhamento = em.merge(compartilhamentoListNewCompartilhamento);
                    if (oldIdtextoOfCompartilhamentoListNewCompartilhamento != null && !oldIdtextoOfCompartilhamentoListNewCompartilhamento.equals(texto)) {
                        oldIdtextoOfCompartilhamentoListNewCompartilhamento.getCompartilhamentoList().remove(compartilhamentoListNewCompartilhamento);
                        oldIdtextoOfCompartilhamentoListNewCompartilhamento = em.merge(oldIdtextoOfCompartilhamentoListNewCompartilhamento);
                    }
                }
            }
            for (Artigo artigoListNewArtigo : artigoListNew) {
                if (!artigoListOld.contains(artigoListNewArtigo)) {
                    Texto oldIdtextoOfArtigoListNewArtigo = artigoListNewArtigo.getIdtexto();
                    artigoListNewArtigo.setIdtexto(texto);
                    artigoListNewArtigo = em.merge(artigoListNewArtigo);
                    if (oldIdtextoOfArtigoListNewArtigo != null && !oldIdtextoOfArtigoListNewArtigo.equals(texto)) {
                        oldIdtextoOfArtigoListNewArtigo.getArtigoList().remove(artigoListNewArtigo);
                        oldIdtextoOfArtigoListNewArtigo = em.merge(oldIdtextoOfArtigoListNewArtigo);
                    }
                }
            }
            for (Publicacao publicacaoListOldPublicacao : publicacaoListOld) {
                if (!publicacaoListNew.contains(publicacaoListOldPublicacao)) {
                    publicacaoListOldPublicacao.setIdtexto(null);
                    publicacaoListOldPublicacao = em.merge(publicacaoListOldPublicacao);
                }
            }
            for (Publicacao publicacaoListNewPublicacao : publicacaoListNew) {
                if (!publicacaoListOld.contains(publicacaoListNewPublicacao)) {
                    Texto oldIdtextoOfPublicacaoListNewPublicacao = publicacaoListNewPublicacao.getIdtexto();
                    publicacaoListNewPublicacao.setIdtexto(texto);
                    publicacaoListNewPublicacao = em.merge(publicacaoListNewPublicacao);
                    if (oldIdtextoOfPublicacaoListNewPublicacao != null && !oldIdtextoOfPublicacaoListNewPublicacao.equals(texto)) {
                        oldIdtextoOfPublicacaoListNewPublicacao.getPublicacaoList().remove(publicacaoListNewPublicacao);
                        oldIdtextoOfPublicacaoListNewPublicacao = em.merge(oldIdtextoOfPublicacaoListNewPublicacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = texto.getIdtexto();
                if (findTexto(id) == null) {
                    throw new NonexistentEntityException("The texto with id " + id + " no longer exists.");
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
            Texto texto;
            try {
                texto = em.getReference(Texto.class, id);
                texto.getIdtexto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The texto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Artigo> artigoListOrphanCheck = texto.getArtigoList();
            for (Artigo artigoListOrphanCheckArtigo : artigoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Texto (" + texto + ") cannot be destroyed since the Artigo " + artigoListOrphanCheckArtigo + " in its artigoList field has a non-nullable idtexto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Compartilhamento> compartilhamentoList = texto.getCompartilhamentoList();
            for (Compartilhamento compartilhamentoListCompartilhamento : compartilhamentoList) {
                compartilhamentoListCompartilhamento.setIdtexto(null);
                compartilhamentoListCompartilhamento = em.merge(compartilhamentoListCompartilhamento);
            }
            List<Publicacao> publicacaoList = texto.getPublicacaoList();
            for (Publicacao publicacaoListPublicacao : publicacaoList) {
                publicacaoListPublicacao.setIdtexto(null);
                publicacaoListPublicacao = em.merge(publicacaoListPublicacao);
            }
            em.remove(texto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Texto> findTextoEntities() {
        return findTextoEntities(true, -1, -1);
    }

    public List<Texto> findTextoEntities(int maxResults, int firstResult) {
        return findTextoEntities(false, maxResults, firstResult);
    }

    private List<Texto> findTextoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Texto.class));
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

    public Texto findTexto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Texto.class, id);
        } finally {
            em.close();
        }
    }

    public int getTextoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Texto> rt = cq.from(Texto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
