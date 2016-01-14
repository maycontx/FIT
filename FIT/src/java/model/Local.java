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
    @NamedQuery(name = "Local.findAll", query = "SELECT l FROM Local l"),
    @NamedQuery(name = "Local.findByIdlocal", query = "SELECT l FROM Local l WHERE l.idlocal = :idlocal"),
    @NamedQuery(name = "Local.findByNome", query = "SELECT l FROM Local l WHERE l.nome = :nome"),
    @NamedQuery(name = "Local.findByDatacriacao", query = "SELECT l FROM Local l WHERE l.datacriacao = :datacriacao")})
public class Local implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idlocal;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String nome;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String endereco;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String descricao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datacriacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idlocal")
    private List<Comentariolocal> comentariolocalList;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Local() {
    }

    public Local(Integer idlocal) {
        this.idlocal = idlocal;
    }

    public Local(Integer idlocal, String nome, String endereco, String descricao) {
        this.idlocal = idlocal;
        this.nome = nome;
        this.endereco = endereco;
        this.descricao = descricao;
    }

    public Integer getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(Integer idlocal) {
        this.idlocal = idlocal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(Date datacriacao) {
        this.datacriacao = datacriacao;
    }

    @XmlTransient
    public List<Comentariolocal> getComentariolocalList() {
        return comentariolocalList;
    }

    public void setComentariolocalList(List<Comentariolocal> comentariolocalList) {
        this.comentariolocalList = comentariolocalList;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlocal != null ? idlocal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Local)) {
            return false;
        }
        Local other = (Local) object;
        if ((this.idlocal == null && other.idlocal != null) || (this.idlocal != null && !this.idlocal.equals(other.idlocal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Local[ idlocal=" + idlocal + " ]";
    }
    
}
