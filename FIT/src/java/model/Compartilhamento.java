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
    @NamedQuery(name = "Compartilhamento.findAll", query = "SELECT c FROM Compartilhamento c"),
    @NamedQuery(name = "Compartilhamento.findByIdcompartilhamento", query = "SELECT c FROM Compartilhamento c WHERE c.idcompartilhamento = :idcompartilhamento"),
    @NamedQuery(name = "Compartilhamento.findByData", query = "SELECT c FROM Compartilhamento c WHERE c.data = :data")})
public class Compartilhamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idcompartilhamento;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "idpublicacao", referencedColumnName = "idpublicacao", nullable = false)
    @ManyToOne(optional = false)
    private Publicacao idpublicacao;
    @JoinColumn(name = "idtexto", referencedColumnName = "idtexto")
    @ManyToOne
    private Texto idtexto;

    public Compartilhamento() {
    }

    public Compartilhamento(Integer idcompartilhamento) {
        this.idcompartilhamento = idcompartilhamento;
    }

    public Integer getIdcompartilhamento() {
        return idcompartilhamento;
    }

    public void setIdcompartilhamento(Integer idcompartilhamento) {
        this.idcompartilhamento = idcompartilhamento;
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

    public Texto getIdtexto() {
        return idtexto;
    }

    public void setIdtexto(Texto idtexto) {
        this.idtexto = idtexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompartilhamento != null ? idcompartilhamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compartilhamento)) {
            return false;
        }
        Compartilhamento other = (Compartilhamento) object;
        if ((this.idcompartilhamento == null && other.idcompartilhamento != null) || (this.idcompartilhamento != null && !this.idcompartilhamento.equals(other.idcompartilhamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Compartilhamento[ idcompartilhamento=" + idcompartilhamento + " ]";
    }
    
}
