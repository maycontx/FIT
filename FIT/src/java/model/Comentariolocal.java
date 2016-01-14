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
    @NamedQuery(name = "Comentariolocal.findAll", query = "SELECT c FROM Comentariolocal c"),
    @NamedQuery(name = "Comentariolocal.findByIdcomentario", query = "SELECT c FROM Comentariolocal c WHERE c.idcomentario = :idcomentario"),
    @NamedQuery(name = "Comentariolocal.findByData", query = "SELECT c FROM Comentariolocal c WHERE c.data = :data"),
    @NamedQuery(name = "Comentariolocal.findByUltimaEdicao", query = "SELECT c FROM Comentariolocal c WHERE c.ultimaEdicao = :ultimaEdicao"),
    @NamedQuery(name = "Comentariolocal.findByStatus", query = "SELECT c FROM Comentariolocal c WHERE c.status = :status"),
    @NamedQuery(name = "Comentariolocal.findByAvaliacao", query = "SELECT c FROM Comentariolocal c WHERE c.avaliacao = :avaliacao")})
public class Comentariolocal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idcomentario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaEdicao;
    @Basic(optional = false)
    @Column(nullable = false, length = 7)
    private String status;
    @Lob
    @Column(length = 65535)
    private String comentario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 2, scale = 1)
    private BigDecimal avaliacao;
    @OneToMany(mappedBy = "idpai")
    private List<Comentariolocal> comentariolocalList;
    @JoinColumn(name = "idpai", referencedColumnName = "idcomentario")
    @ManyToOne
    private Comentariolocal idpai;
    @JoinColumn(name = "idlocal", referencedColumnName = "idlocal", nullable = false)
    @ManyToOne(optional = false)
    private Local idlocal;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Comentariolocal() {
    }

    public Comentariolocal(Integer idcomentario) {
        this.idcomentario = idcomentario;
    }

    public Comentariolocal(Integer idcomentario, String status) {
        this.idcomentario = idcomentario;
        this.status = status;
    }

    public Integer getIdcomentario() {
        return idcomentario;
    }

    public void setIdcomentario(Integer idcomentario) {
        this.idcomentario = idcomentario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getUltimaEdicao() {
        return ultimaEdicao;
    }

    public void setUltimaEdicao(Date ultimaEdicao) {
        this.ultimaEdicao = ultimaEdicao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public BigDecimal getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(BigDecimal avaliacao) {
        this.avaliacao = avaliacao;
    }

    @XmlTransient
    public List<Comentariolocal> getComentariolocalList() {
        return comentariolocalList;
    }

    public void setComentariolocalList(List<Comentariolocal> comentariolocalList) {
        this.comentariolocalList = comentariolocalList;
    }

    public Comentariolocal getIdpai() {
        return idpai;
    }

    public void setIdpai(Comentariolocal idpai) {
        this.idpai = idpai;
    }

    public Local getIdlocal() {
        return idlocal;
    }

    public void setIdlocal(Local idlocal) {
        this.idlocal = idlocal;
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
        hash += (idcomentario != null ? idcomentario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentariolocal)) {
            return false;
        }
        Comentariolocal other = (Comentariolocal) object;
        if ((this.idcomentario == null && other.idcomentario != null) || (this.idcomentario != null && !this.idcomentario.equals(other.idcomentario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Comentariolocal[ idcomentario=" + idcomentario + " ]";
    }
    
}
