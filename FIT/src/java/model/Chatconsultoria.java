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
    @NamedQuery(name = "Chatconsultoria.findAll", query = "SELECT c FROM Chatconsultoria c"),
    @NamedQuery(name = "Chatconsultoria.findByIdchatconsultoria", query = "SELECT c FROM Chatconsultoria c WHERE c.idchatconsultoria = :idchatconsultoria"),
    @NamedQuery(name = "Chatconsultoria.findByData", query = "SELECT c FROM Chatconsultoria c WHERE c.data = :data"),
    @NamedQuery(name = "Chatconsultoria.findByStatus", query = "SELECT c FROM Chatconsultoria c WHERE c.status = :status")})
public class Chatconsultoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idchatconsultoria;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String mensagem;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column(length = 8)
    private String status;
    @JoinColumn(name = "idconsultoriarealizada", referencedColumnName = "idconsultoriarealizada", nullable = false)
    @ManyToOne(optional = false)
    private Consultoriarealizada idconsultoriarealizada;
    @JoinColumn(name = "idemissor", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idemissor;
    @JoinColumn(name = "iddestinatario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario iddestinatario;

    public Chatconsultoria() {
    }

    public Chatconsultoria(Integer idchatconsultoria) {
        this.idchatconsultoria = idchatconsultoria;
    }

    public Chatconsultoria(Integer idchatconsultoria, String mensagem) {
        this.idchatconsultoria = idchatconsultoria;
        this.mensagem = mensagem;
    }

    public Integer getIdchatconsultoria() {
        return idchatconsultoria;
    }

    public void setIdchatconsultoria(Integer idchatconsultoria) {
        this.idchatconsultoria = idchatconsultoria;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Consultoriarealizada getIdconsultoriarealizada() {
        return idconsultoriarealizada;
    }

    public void setIdconsultoriarealizada(Consultoriarealizada idconsultoriarealizada) {
        this.idconsultoriarealizada = idconsultoriarealizada;
    }

    public Usuario getIdemissor() {
        return idemissor;
    }

    public void setIdemissor(Usuario idemissor) {
        this.idemissor = idemissor;
    }

    public Usuario getIddestinatario() {
        return iddestinatario;
    }

    public void setIddestinatario(Usuario iddestinatario) {
        this.iddestinatario = iddestinatario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idchatconsultoria != null ? idchatconsultoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chatconsultoria)) {
            return false;
        }
        Chatconsultoria other = (Chatconsultoria) object;
        if ((this.idchatconsultoria == null && other.idchatconsultoria != null) || (this.idchatconsultoria != null && !this.idchatconsultoria.equals(other.idchatconsultoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Chatconsultoria[ idchatconsultoria=" + idchatconsultoria + " ]";
    }
    
}
