/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
    @NamedQuery(name = "Link.findAll", query = "SELECT l FROM Link l"),
    @NamedQuery(name = "Link.findByIdlink", query = "SELECT l FROM Link l WHERE l.idlink = :idlink"),
    @NamedQuery(name = "Link.findByLink", query = "SELECT l FROM Link l WHERE l.link = :link"),
    @NamedQuery(name = "Link.findByCompartilhamentos", query = "SELECT l FROM Link l WHERE l.compartilhamentos = :compartilhamentos")})
public class Link implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idlink;
    @Column(length = 255)
    private String link;
    private Integer compartilhamentos;
    @OneToMany(mappedBy = "idlink")
    private List<Publicacao> publicacaoList;

    public Link() {
    }

    public Link(Integer idlink) {
        this.idlink = idlink;
    }

    public Integer getIdlink() {
        return idlink;
    }

    public void setIdlink(Integer idlink) {
        this.idlink = idlink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getCompartilhamentos() {
        return compartilhamentos;
    }

    public void setCompartilhamentos(Integer compartilhamentos) {
        this.compartilhamentos = compartilhamentos;
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
        hash += (idlink != null ? idlink.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Link)) {
            return false;
        }
        Link other = (Link) object;
        if ((this.idlink == null && other.idlink != null) || (this.idlink != null && !this.idlink.equals(other.idlink))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Link[ idlink=" + idlink + " ]";
    }
    
}
