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
    @NamedQuery(name = "Denuncia.findAll", query = "SELECT d FROM Denuncia d"),
    @NamedQuery(name = "Denuncia.findByIddenuncia", query = "SELECT d FROM Denuncia d WHERE d.iddenuncia = :iddenuncia"),
    @NamedQuery(name = "Denuncia.findByAssunto", query = "SELECT d FROM Denuncia d WHERE d.assunto = :assunto"),
    @NamedQuery(name = "Denuncia.findByIdorigem", query = "SELECT d FROM Denuncia d WHERE d.idorigem = :idorigem"),
    @NamedQuery(name = "Denuncia.findByData", query = "SELECT d FROM Denuncia d WHERE d.data = :data"),
    @NamedQuery(name = "Denuncia.findByStatus", query = "SELECT d FROM Denuncia d WHERE d.status = :status")})
public class Denuncia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer iddenuncia;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String assunto;
    @Basic(optional = false)
    @Column(nullable = false)
    private int idorigem;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 16777215)
    private String denuncia;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column(length = 10)
    private String status;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Denuncia() {
    }

    public Denuncia(Integer iddenuncia) {
        this.iddenuncia = iddenuncia;
    }

    public Denuncia(Integer iddenuncia, String assunto, int idorigem, String denuncia) {
        this.iddenuncia = iddenuncia;
        this.assunto = assunto;
        this.idorigem = idorigem;
        this.denuncia = denuncia;
    }

    public Integer getIddenuncia() {
        return iddenuncia;
    }

    public void setIddenuncia(Integer iddenuncia) {
        this.iddenuncia = iddenuncia;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public int getIdorigem() {
        return idorigem;
    }

    public void setIdorigem(int idorigem) {
        this.idorigem = idorigem;
    }

    public String getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(String denuncia) {
        this.denuncia = denuncia;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddenuncia != null ? iddenuncia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Denuncia)) {
            return false;
        }
        Denuncia other = (Denuncia) object;
        if ((this.iddenuncia == null && other.iddenuncia != null) || (this.iddenuncia != null && !this.iddenuncia.equals(other.iddenuncia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Denuncia[ iddenuncia=" + iddenuncia + " ]";
    }
    
}
