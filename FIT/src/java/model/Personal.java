/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asdfrofl
 */
@Entity
@Table(catalog = "fit", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p"),
    @NamedQuery(name = "Personal.findByIdpersonal", query = "SELECT p FROM Personal p WHERE p.idpersonal = :idpersonal"),
    @NamedQuery(name = "Personal.findBySeguimento", query = "SELECT p FROM Personal p WHERE p.seguimento = :seguimento")})
public class Personal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idpersonal;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String seguimento;
    @JoinColumn(name = "idprofissional", referencedColumnName = "idprofissional", nullable = false)
    @ManyToOne(optional = false)
    private Profissional idprofissional;

    public Personal() {
    }

    public Personal(Integer idpersonal) {
        this.idpersonal = idpersonal;
    }

    public Personal(Integer idpersonal, String seguimento) {
        this.idpersonal = idpersonal;
        this.seguimento = seguimento;
    }

    public Integer getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(Integer idpersonal) {
        this.idpersonal = idpersonal;
    }

    public String getSeguimento() {
        return seguimento;
    }

    public void setSeguimento(String seguimento) {
        this.seguimento = seguimento;
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
        hash += (idpersonal != null ? idpersonal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.idpersonal == null && other.idpersonal != null) || (this.idpersonal != null && !this.idpersonal.equals(other.idpersonal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Personal[ idpersonal=" + idpersonal + " ]";
    }
    
}
