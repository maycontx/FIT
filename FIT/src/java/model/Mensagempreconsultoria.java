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
    @NamedQuery(name = "Mensagempreconsultoria.findAll", query = "SELECT m FROM Mensagempreconsultoria m"),
    @NamedQuery(name = "Mensagempreconsultoria.findByIdmensagempreconsultoria", query = "SELECT m FROM Mensagempreconsultoria m WHERE m.idmensagempreconsultoria = :idmensagempreconsultoria"),
    @NamedQuery(name = "Mensagempreconsultoria.findByAssunto", query = "SELECT m FROM Mensagempreconsultoria m WHERE m.assunto = :assunto"),
    @NamedQuery(name = "Mensagempreconsultoria.findByData", query = "SELECT m FROM Mensagempreconsultoria m WHERE m.data = :data"),
    @NamedQuery(name = "Mensagempreconsultoria.findByStatus", query = "SELECT m FROM Mensagempreconsultoria m WHERE m.status = :status")})
public class Mensagempreconsultoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idmensagempreconsultoria;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String assunto;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String mensagem;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column(length = 8)
    private String status;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "idprofissional", referencedColumnName = "idprofissional", nullable = false)
    @ManyToOne(optional = false)
    private Profissional idprofissional;

    public Mensagempreconsultoria() {
    }

    public Mensagempreconsultoria(Integer idmensagempreconsultoria) {
        this.idmensagempreconsultoria = idmensagempreconsultoria;
    }

    public Mensagempreconsultoria(Integer idmensagempreconsultoria, String assunto, String mensagem) {
        this.idmensagempreconsultoria = idmensagempreconsultoria;
        this.assunto = assunto;
        this.mensagem = mensagem;
    }

    public Integer getIdmensagempreconsultoria() {
        return idmensagempreconsultoria;
    }

    public void setIdmensagempreconsultoria(Integer idmensagempreconsultoria) {
        this.idmensagempreconsultoria = idmensagempreconsultoria;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
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

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Profissional getIdprofissional() {
        return idprofissional;
    }

    public void setIdprofissional(Profissional idprofissional) {
        this.idprofissional = idprofissional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmensagempreconsultoria != null ? idmensagempreconsultoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensagempreconsultoria)) {
            return false;
        }
        Mensagempreconsultoria other = (Mensagempreconsultoria) object;
        if ((this.idmensagempreconsultoria == null && other.idmensagempreconsultoria != null) || (this.idmensagempreconsultoria != null && !this.idmensagempreconsultoria.equals(other.idmensagempreconsultoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Mensagempreconsultoria[ idmensagempreconsultoria=" + idmensagempreconsultoria + " ]";
    }
    
}
