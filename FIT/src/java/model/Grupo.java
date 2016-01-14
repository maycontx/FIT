/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findByIdgrupo", query = "SELECT g FROM Grupo g WHERE g.idgrupo = :idgrupo"),
    @NamedQuery(name = "Grupo.findByNome", query = "SELECT g FROM Grupo g WHERE g.nome = :nome"),
    @NamedQuery(name = "Grupo.findByDatacriacao", query = "SELECT g FROM Grupo g WHERE g.datacriacao = :datacriacao"),
    @NamedQuery(name = "Grupo.findByStatus", query = "SELECT g FROM Grupo g WHERE g.status = :status")})
public class Grupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idgrupo;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String nome;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datacriacao;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String descricao;
    @Basic(optional = false)
    @Column(nullable = false, length = 8)
    private String status;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgrupo")
    private List<Usuariogrupo> usuariogrupoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgrupo")
    private List<Solicitacaogrupo> solicitacaogrupoList;

    public Grupo() {
    }

    public Grupo(Integer idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Grupo(Integer idgrupo, String nome, Date datacriacao, String descricao, String status) {
        this.idgrupo = idgrupo;
        this.nome = nome;
        this.datacriacao = datacriacao;
        this.descricao = descricao;
        this.status = status;
    }

    public Integer getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Integer idgrupo) {
        this.idgrupo = idgrupo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(Date datacriacao) {
        this.datacriacao = datacriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @XmlTransient
    public List<Usuariogrupo> getUsuariogrupoList() {
        return usuariogrupoList;
    }

    public void setUsuariogrupoList(List<Usuariogrupo> usuariogrupoList) {
        this.usuariogrupoList = usuariogrupoList;
    }

    @XmlTransient
    public List<Solicitacaogrupo> getSolicitacaogrupoList() {
        return solicitacaogrupoList;
    }

    public void setSolicitacaogrupoList(List<Solicitacaogrupo> solicitacaogrupoList) {
        this.solicitacaogrupoList = solicitacaogrupoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgrupo != null ? idgrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.idgrupo == null && other.idgrupo != null) || (this.idgrupo != null && !this.idgrupo.equals(other.idgrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Grupo[ idgrupo=" + idgrupo + " ]";
    }
    
}
