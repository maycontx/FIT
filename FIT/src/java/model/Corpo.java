/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asdfrofl
 */
@Entity
@Table(catalog = "fit", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Corpo.findAll", query = "SELECT c FROM Corpo c"),
    @NamedQuery(name = "Corpo.findByIdcorpo", query = "SELECT c FROM Corpo c WHERE c.idcorpo = :idcorpo"),
    @NamedQuery(name = "Corpo.findByBracoesquerdo", query = "SELECT c FROM Corpo c WHERE c.bracoesquerdo = :bracoesquerdo"),
    @NamedQuery(name = "Corpo.findByBracodireito", query = "SELECT c FROM Corpo c WHERE c.bracodireito = :bracodireito"),
    @NamedQuery(name = "Corpo.findByPeitoral", query = "SELECT c FROM Corpo c WHERE c.peitoral = :peitoral"),
    @NamedQuery(name = "Corpo.findByPernaesquerda", query = "SELECT c FROM Corpo c WHERE c.pernaesquerda = :pernaesquerda"),
    @NamedQuery(name = "Corpo.findByPernadireita", query = "SELECT c FROM Corpo c WHERE c.pernadireita = :pernadireita"),
    @NamedQuery(name = "Corpo.findByPantesquerda", query = "SELECT c FROM Corpo c WHERE c.pantesquerda = :pantesquerda"),
    @NamedQuery(name = "Corpo.findByPantdireita", query = "SELECT c FROM Corpo c WHERE c.pantdireita = :pantdireita"),
    @NamedQuery(name = "Corpo.findByCintura", query = "SELECT c FROM Corpo c WHERE c.cintura = :cintura")})
public class Corpo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idcorpo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 4, scale = 1)
    private BigDecimal bracoesquerdo;
    @Column(precision = 4, scale = 1)
    private BigDecimal bracodireito;
    @Column(precision = 4, scale = 1)
    private BigDecimal peitoral;
    @Column(precision = 4, scale = 1)
    private BigDecimal pernaesquerda;
    @Column(precision = 4, scale = 1)
    private BigDecimal pernadireita;
    @Column(precision = 4, scale = 1)
    private BigDecimal pantesquerda;
    @Column(precision = 4, scale = 1)
    private BigDecimal pantdireita;
    @Column(precision = 4, scale = 1)
    private BigDecimal cintura;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcorpo")
    private List<Atleta> atletaList;

    public Corpo() {
    }

    public Corpo(Integer idcorpo) {
        this.idcorpo = idcorpo;
    }

    public Integer getIdcorpo() {
        return idcorpo;
    }

    public void setIdcorpo(Integer idcorpo) {
        this.idcorpo = idcorpo;
    }

    public BigDecimal getBracoesquerdo() {
        return bracoesquerdo;
    }

    public void setBracoesquerdo(BigDecimal bracoesquerdo) {
        this.bracoesquerdo = bracoesquerdo;
    }

    public BigDecimal getBracodireito() {
        return bracodireito;
    }

    public void setBracodireito(BigDecimal bracodireito) {
        this.bracodireito = bracodireito;
    }

    public BigDecimal getPeitoral() {
        return peitoral;
    }

    public void setPeitoral(BigDecimal peitoral) {
        this.peitoral = peitoral;
    }

    public BigDecimal getPernaesquerda() {
        return pernaesquerda;
    }

    public void setPernaesquerda(BigDecimal pernaesquerda) {
        this.pernaesquerda = pernaesquerda;
    }

    public BigDecimal getPernadireita() {
        return pernadireita;
    }

    public void setPernadireita(BigDecimal pernadireita) {
        this.pernadireita = pernadireita;
    }

    public BigDecimal getPantesquerda() {
        return pantesquerda;
    }

    public void setPantesquerda(BigDecimal pantesquerda) {
        this.pantesquerda = pantesquerda;
    }

    public BigDecimal getPantdireita() {
        return pantdireita;
    }

    public void setPantdireita(BigDecimal pantdireita) {
        this.pantdireita = pantdireita;
    }

    public BigDecimal getCintura() {
        return cintura;
    }

    public void setCintura(BigDecimal cintura) {
        this.cintura = cintura;
    }

    @XmlTransient
    public List<Atleta> getAtletaList() {
        return atletaList;
    }

    public void setAtletaList(List<Atleta> atletaList) {
        this.atletaList = atletaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcorpo != null ? idcorpo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Corpo)) {
            return false;
        }
        Corpo other = (Corpo) object;
        if ((this.idcorpo == null && other.idcorpo != null) || (this.idcorpo != null && !this.idcorpo.equals(other.idcorpo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Corpo[ idcorpo=" + idcorpo + " ]";
    }
    
}
