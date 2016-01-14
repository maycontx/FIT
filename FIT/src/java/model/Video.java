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
    @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v"),
    @NamedQuery(name = "Video.findByIdvideo", query = "SELECT v FROM Video v WHERE v.idvideo = :idvideo"),
    @NamedQuery(name = "Video.findByLink", query = "SELECT v FROM Video v WHERE v.link = :link"),
    @NamedQuery(name = "Video.findByCompartilhamentos", query = "SELECT v FROM Video v WHERE v.compartilhamentos = :compartilhamentos")})
public class Video implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idvideo;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String link;
    @Basic(optional = false)
    @Column(nullable = false)
    private int compartilhamentos;
    @OneToMany(mappedBy = "idvideo")
    private List<Publicacao> publicacaoList;

    public Video() {
    }

    public Video(Integer idvideo) {
        this.idvideo = idvideo;
    }

    public Video(Integer idvideo, String link, int compartilhamentos) {
        this.idvideo = idvideo;
        this.link = link;
        this.compartilhamentos = compartilhamentos;
    }

    public Integer getIdvideo() {
        return idvideo;
    }

    public void setIdvideo(Integer idvideo) {
        this.idvideo = idvideo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getCompartilhamentos() {
        return compartilhamentos;
    }

    public void setCompartilhamentos(int compartilhamentos) {
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
        hash += (idvideo != null ? idvideo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Video)) {
            return false;
        }
        Video other = (Video) object;
        if ((this.idvideo == null && other.idvideo != null) || (this.idvideo != null && !this.idvideo.equals(other.idvideo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Video[ idvideo=" + idvideo + " ]";
    }
    
}
