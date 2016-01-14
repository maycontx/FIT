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
    @NamedQuery(name = "Nutricionista.findAll", query = "SELECT n FROM Nutricionista n"),
    @NamedQuery(name = "Nutricionista.findByIdnutricionista", query = "SELECT n FROM Nutricionista n WHERE n.idnutricionista = :idnutricionista")})
public class Nutricionista implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idnutricionista;
    @JoinColumn(name = "idprofissional", referencedColumnName = "idprofissional", nullable = false)
    @ManyToOne(optional = false)
    private Profissional idprofissional;

    public Nutricionista() {
    }

    public Nutricionista(Integer idnutricionista) {
        this.idnutricionista = idnutricionista;
    }

    public Integer getIdnutricionista() {
        return idnutricionista;
    }

    public void setIdnutricionista(Integer idnutricionista) {
        this.idnutricionista = idnutricionista;
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
        hash += (idnutricionista != null ? idnutricionista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nutricionista)) {
            return false;
        }
        Nutricionista other = (Nutricionista) object;
        if ((this.idnutricionista == null && other.idnutricionista != null) || (this.idnutricionista != null && !this.idnutricionista.equals(other.idnutricionista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Nutricionista[ idnutricionista=" + idnutricionista + " ]";
    }
    
}
