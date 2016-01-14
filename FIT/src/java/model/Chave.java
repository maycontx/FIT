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
    @NamedQuery(name = "Chave.findAll", query = "SELECT c FROM Chave c"),
    @NamedQuery(name = "Chave.findByIdchave", query = "SELECT c FROM Chave c WHERE c.idchave = :idchave"),
    @NamedQuery(name = "Chave.findByChave", query = "SELECT c FROM Chave c WHERE c.chave = :chave")})
public class Chave implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idchave;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String chave;
    @JoinColumn(name = "idpublicacao", referencedColumnName = "idpublicacao", nullable = false)
    @ManyToOne(optional = false)
    private Publicacao idpublicacao;

    public Chave() {
    }

    public Chave(Integer idchave) {
        this.idchave = idchave;
    }

    public Chave(Integer idchave, String chave) {
        this.idchave = idchave;
        this.chave = chave;
    }

    public Integer getIdchave() {
        return idchave;
    }

    public void setIdchave(Integer idchave) {
        this.idchave = idchave;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
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
        hash += (idchave != null ? idchave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chave)) {
            return false;
        }
        Chave other = (Chave) object;
        if ((this.idchave == null && other.idchave != null) || (this.idchave != null && !this.idchave.equals(other.idchave))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Chave[ idchave=" + idchave + " ]";
    }
    
}
