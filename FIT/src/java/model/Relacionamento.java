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
    @NamedQuery(name = "Relacionamento.findAll", query = "SELECT r FROM Relacionamento r"),
    @NamedQuery(name = "Relacionamento.findByIdrelacionamento", query = "SELECT r FROM Relacionamento r WHERE r.idrelacionamento = :idrelacionamento"),
    @NamedQuery(name = "Relacionamento.findByInicio", query = "SELECT r FROM Relacionamento r WHERE r.inicio = :inicio")})
public class Relacionamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idrelacionamento;
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    @JoinColumn(name = "idatleta", referencedColumnName = "idatleta", nullable = false)
    @ManyToOne(optional = false)
    private Atleta idatleta;
    @JoinColumn(name = "idprofissional", referencedColumnName = "idprofissional", nullable = false)
    @ManyToOne(optional = false)
    private Profissional idprofissional;

    public Relacionamento() {
    }

    public Relacionamento(Integer idrelacionamento) {
        this.idrelacionamento = idrelacionamento;
    }

    public Integer getIdrelacionamento() {
        return idrelacionamento;
    }

    public void setIdrelacionamento(Integer idrelacionamento) {
        this.idrelacionamento = idrelacionamento;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Atleta getIdatleta() {
        return idatleta;
    }

    public void setIdatleta(Atleta idatleta) {
        this.idatleta = idatleta;
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
        hash += (idrelacionamento != null ? idrelacionamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relacionamento)) {
            return false;
        }
        Relacionamento other = (Relacionamento) object;
        if ((this.idrelacionamento == null && other.idrelacionamento != null) || (this.idrelacionamento != null && !this.idrelacionamento.equals(other.idrelacionamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Relacionamento[ idrelacionamento=" + idrelacionamento + " ]";
    }
    
}
