/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asdfrofl
 */
@Entity
@Table(catalog = "fit", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Likes.findAll", query = "SELECT l FROM Likes l"),
    @NamedQuery(name = "Likes.findByIdlikes", query = "SELECT l FROM Likes l WHERE l.idlikes = :idlikes"),
    @NamedQuery(name = "Likes.findByData", query = "SELECT l FROM Likes l WHERE l.data = :data")})
public class Likes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idlikes;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "idpublicacao", referencedColumnName = "idpublicacao", nullable = false)
    @ManyToOne(optional = false)
    private Publicacao idpublicacao;

    public Likes() {
    }

    public Likes(Integer idlikes) {
        this.idlikes = idlikes;
    }

    public Integer getIdlikes() {
        return idlikes;
    }

    public void setIdlikes(Integer idlikes) {
        this.idlikes = idlikes;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Publicacao getIdpublicacao() {
        return idpublicacao;
    }

    public void setIdpublicacao(Publicacao idpublicacao) {
        this.idpublicacao = idpublicacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlikes != null ? idlikes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Likes)) {
            return false;
        }
        Likes other = (Likes) object;
        if ((this.idlikes == null && other.idlikes != null) || (this.idlikes != null && !this.idlikes.equals(other.idlikes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Likes[ idlikes=" + idlikes + " ]";
    }
    
}
