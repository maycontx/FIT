/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asdfrofl
 */
@Entity
@Table(catalog = "fit", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
    @NamedQuery(name = "Usuario.findBySobrenome", query = "SELECT u FROM Usuario u WHERE u.sobrenome = :sobrenome"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha"),
    @NamedQuery(name = "Usuario.findByNascimento", query = "SELECT u FROM Usuario u WHERE u.nascimento = :nascimento"),
    @NamedQuery(name = "Usuario.findBySexo", query = "SELECT u FROM Usuario u WHERE u.sexo = :sexo"),
    @NamedQuery(name = "Usuario.findByCidade", query = "SELECT u FROM Usuario u WHERE u.cidade = :cidade"),
    @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado"),
    @NamedQuery(name = "Usuario.findByUltimoLogin", query = "SELECT u FROM Usuario u WHERE u.ultimoLogin = :ultimoLogin"),
    @NamedQuery(name = "Usuario.findByCredito", query = "SELECT u FROM Usuario u WHERE u.credito = :credito")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idusuario;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String nome;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String sobrenome;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String email;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String senha;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String sexo;
    @Column(length = 255)
    private String cidade;
    @Column(length = 255)
    private String estado;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoLogin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal credito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Atleta> atletaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Solicitacaoevento> solicitacaoeventoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idemissor")
    private List<Chatconsultoria> chatconsultoriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddestinatario")
    private List<Chatconsultoria> chatconsultoriaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Comentariolocal> comentariolocalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Grupo> grupoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Compartilhamento> compartilhamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "autor")
    private List<Blog> blogList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Local> localList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Profissional> profissionalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Mensagempreconsultoria> mensagempreconsultoriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Avaliacaoartigo> avaliacaoartigoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Usuariogrupo> usuariogrupoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Evento> eventoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Likes> likesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idseguidor")
    private List<Seguidor> seguidorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idseguido")
    private List<Seguidor> seguidorList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Solicitacaogrupo> solicitacaogrupoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Publicacao> publicacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Denuncia> denunciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Usuarioevento> usuarioeventoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Consultoriarealizada> consultoriarealizadaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Pagamento> pagamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Comentariopublicacao> comentariopublicacaoList;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(Integer idusuario, String nome, String sobrenome, String email, String senha, Date nascimento, String sexo, BigDecimal credito) {
        this.idusuario = idusuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.credito = credito;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Date ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
    }

    @XmlTransient
    public List<Atleta> getAtletaList() {
        return atletaList;
    }

    public void setAtletaList(List<Atleta> atletaList) {
        this.atletaList = atletaList;
    }

    @XmlTransient
    public List<Solicitacaoevento> getSolicitacaoeventoList() {
        return solicitacaoeventoList;
    }

    public void setSolicitacaoeventoList(List<Solicitacaoevento> solicitacaoeventoList) {
        this.solicitacaoeventoList = solicitacaoeventoList;
    }

    @XmlTransient
    public List<Chatconsultoria> getChatconsultoriaList() {
        return chatconsultoriaList;
    }

    public void setChatconsultoriaList(List<Chatconsultoria> chatconsultoriaList) {
        this.chatconsultoriaList = chatconsultoriaList;
    }

    @XmlTransient
    public List<Chatconsultoria> getChatconsultoriaList1() {
        return chatconsultoriaList1;
    }

    public void setChatconsultoriaList1(List<Chatconsultoria> chatconsultoriaList1) {
        this.chatconsultoriaList1 = chatconsultoriaList1;
    }

    @XmlTransient
    public List<Comentariolocal> getComentariolocalList() {
        return comentariolocalList;
    }

    public void setComentariolocalList(List<Comentariolocal> comentariolocalList) {
        this.comentariolocalList = comentariolocalList;
    }

    @XmlTransient
    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    @XmlTransient
    public List<Compartilhamento> getCompartilhamentoList() {
        return compartilhamentoList;
    }

    public void setCompartilhamentoList(List<Compartilhamento> compartilhamentoList) {
        this.compartilhamentoList = compartilhamentoList;
    }

    @XmlTransient
    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }

    @XmlTransient
    public List<Local> getLocalList() {
        return localList;
    }

    public void setLocalList(List<Local> localList) {
        this.localList = localList;
    }

    @XmlTransient
    public List<Profissional> getProfissionalList() {
        return profissionalList;
    }

    public void setProfissionalList(List<Profissional> profissionalList) {
        this.profissionalList = profissionalList;
    }

    @XmlTransient
    public List<Mensagempreconsultoria> getMensagempreconsultoriaList() {
        return mensagempreconsultoriaList;
    }

    public void setMensagempreconsultoriaList(List<Mensagempreconsultoria> mensagempreconsultoriaList) {
        this.mensagempreconsultoriaList = mensagempreconsultoriaList;
    }

    @XmlTransient
    public List<Avaliacaoartigo> getAvaliacaoartigoList() {
        return avaliacaoartigoList;
    }

    public void setAvaliacaoartigoList(List<Avaliacaoartigo> avaliacaoartigoList) {
        this.avaliacaoartigoList = avaliacaoartigoList;
    }

    @XmlTransient
    public List<Usuariogrupo> getUsuariogrupoList() {
        return usuariogrupoList;
    }

    public void setUsuariogrupoList(List<Usuariogrupo> usuariogrupoList) {
        this.usuariogrupoList = usuariogrupoList;
    }

    @XmlTransient
    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    @XmlTransient
    public List<Likes> getLikesList() {
        return likesList;
    }

    public void setLikesList(List<Likes> likesList) {
        this.likesList = likesList;
    }

    @XmlTransient
    public List<Seguidor> getSeguidorList() {
        return seguidorList;
    }

    public void setSeguidorList(List<Seguidor> seguidorList) {
        this.seguidorList = seguidorList;
    }

    @XmlTransient
    public List<Seguidor> getSeguidorList1() {
        return seguidorList1;
    }

    public void setSeguidorList1(List<Seguidor> seguidorList1) {
        this.seguidorList1 = seguidorList1;
    }

    @XmlTransient
    public List<Solicitacaogrupo> getSolicitacaogrupoList() {
        return solicitacaogrupoList;
    }

    public void setSolicitacaogrupoList(List<Solicitacaogrupo> solicitacaogrupoList) {
        this.solicitacaogrupoList = solicitacaogrupoList;
    }

    @XmlTransient
    public List<Publicacao> getPublicacaoList() {
        return publicacaoList;
    }

    public void setPublicacaoList(List<Publicacao> publicacaoList) {
        this.publicacaoList = publicacaoList;
    }

    @XmlTransient
    public List<Denuncia> getDenunciaList() {
        return denunciaList;
    }

    public void setDenunciaList(List<Denuncia> denunciaList) {
        this.denunciaList = denunciaList;
    }

    @XmlTransient
    public List<Usuarioevento> getUsuarioeventoList() {
        return usuarioeventoList;
    }

    public void setUsuarioeventoList(List<Usuarioevento> usuarioeventoList) {
        this.usuarioeventoList = usuarioeventoList;
    }

    @XmlTransient
    public List<Consultoriarealizada> getConsultoriarealizadaList() {
        return consultoriarealizadaList;
    }

    public void setConsultoriarealizadaList(List<Consultoriarealizada> consultoriarealizadaList) {
        this.consultoriarealizadaList = consultoriarealizadaList;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList() {
        return pagamentoList;
    }

    public void setPagamentoList(List<Pagamento> pagamentoList) {
        this.pagamentoList = pagamentoList;
    }

    @XmlTransient
    public List<Comentariopublicacao> getComentariopublicacaoList() {
        return comentariopublicacaoList;
    }

    public void setComentariopublicacaoList(List<Comentariopublicacao> comentariopublicacaoList) {
        this.comentariopublicacaoList = comentariopublicacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Usuario[ idusuario=" + idusuario + " ]";
    }
    
}
