/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
    @NamedQuery(name = "Texto.findAll", query = "SELECT t FROM Texto t"),
    @NamedQuery(name = "Texto.findByIdtexto", query = "SELECT t FROM Texto t WHERE t.idtexto = :idtexto")})
public class Texto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idtexto;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 2147483647)
    private String texto;
    @OneToMany(mappedBy = "idtexto")
    private List<Compartilhamento> compartilhamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtexto")
    private List<Artigo> artigoList;
    @OneToMany(mappedBy = "idtexto")
    private List<Publicacao> publicacaoList;

    public Texto() {
    }

    public Texto(Integer idtexto) {
        this.idtexto = idtexto;
    }

    public Texto(Integer idtexto, String texto) {
        this.idtexto = idtexto;
        this.texto = texto;
    }

    public Integer getIdtexto() {
        return idtexto;
    }

    public void setIdtexto(Integer idtexto) {
        this.idtexto = idtexto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @XmlTransient
    public List<Compartilhamento> getCompartilhamentoList() {
        return compartilhamentoList;
    }

    public void setCompartilhamentoList(List<Compartilhamento> compartilhamentoList) {
        this.compartilhamentoList = compartilhamentoList;
    }

    @XmlTransient
    public List<Artigo> getArtigoList() {
        return artigoList;
    }

    public void setArtigoList(List<Artigo> artigoList) {
        this.artigoList = artigoList;
    }

    @XmlTransient
    public List<Publicacao> getPublicacaoList() {
        return publicacaoList;
    }

    public void setPublicacaoList(List<Publicacao> publicacaoList) {
        this.publicacaoList = publicacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtexto != null ? idtexto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Texto)) {
            return false;
        }
        Texto other = (Texto) object;
        if ((this.idtexto == null && other.idtexto != null) || (this.idtexto != null && !this.idtexto.equals(other.idtexto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Texto[ idtexto=" + idtexto + " ]";
    }
    
}
