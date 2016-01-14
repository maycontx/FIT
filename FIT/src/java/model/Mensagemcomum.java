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
import javax.persistence.Lob;
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
    @NamedQuery(name = "Mensagemcomum.findAll", query = "SELECT m FROM Mensagemcomum m"),
    @NamedQuery(name = "Mensagemcomum.findByIdmensagemComum", query = "SELECT m FROM Mensagemcomum m WHERE m.idmensagemComum = :idmensagemComum"),
    @NamedQuery(name = "Mensagemcomum.findByStatus", query = "SELECT m FROM Mensagemcomum m WHERE m.status = :status"),
    @NamedQuery(name = "Mensagemcomum.findByData", query = "SELECT m FROM Mensagemcomum m WHERE m.data = :data")})
public class Mensagemcomum implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idmensagemComum;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String mensagem;
    @Column(length = 8)
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @JoinColumn(name = "idAtletaEmissor", referencedColumnName = "idatleta", nullable = false)
    @ManyToOne(optional = false)
    private Atleta idAtletaEmissor;
    @JoinColumn(name = "idAtletaDestinatario", referencedColumnName = "idatleta", nullable = false)
    @ManyToOne(optional = false)
    private Atleta idAtletaDestinatario;

    public Mensagemcomum() {
    }

    public Mensagemcomum(Integer idmensagemComum) {
        this.idmensagemComum = idmensagemComum;
    }

    public Mensagemcomum(Integer idmensagemComum, String mensagem) {
        this.idmensagemComum = idmensagemComum;
        this.mensagem = mensagem;
    }

    public Integer getIdmensagemComum() {
        return idmensagemComum;
    }

    public void setIdmensagemComum(Integer idmensagemComum) {
        this.idmensagemComum = idmensagemComum;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Atleta getIdAtletaEmissor() {
        return idAtletaEmissor;
    }

    public void setIdAtletaEmissor(Atleta idAtletaEmissor) {
        this.idAtletaEmissor = idAtletaEmissor;
    }

    public Atleta getIdAtletaDestinatario() {
        return idAtletaDestinatario;
    }

    public void setIdAtletaDestinatario(Atleta idAtletaDestinatario) {
        this.idAtletaDestinatario = idAtletaDestinatario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmensagemComum != null ? idmensagemComum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensagemcomum)) {
            return false;
        }
        Mensagemcomum other = (Mensagemcomum) object;
        if ((this.idmensagemComum == null && other.idmensagemComum != null) || (this.idmensagemComum != null && !this.idmensagemComum.equals(other.idmensagemComum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Mensagemcomum[ idmensagemComum=" + idmensagemComum + " ]";
    }
    
}
