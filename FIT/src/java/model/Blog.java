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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Blog.findAll", query = "SELECT b FROM Blog b"),
    @NamedQuery(name = "Blog.findByIdblog", query = "SELECT b FROM Blog b WHERE b.idblog = :idblog"),
    @NamedQuery(name = "Blog.findByNome", query = "SELECT b FROM Blog b WHERE b.nome = :nome")})
public class Blog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idblog;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idblog")
    private List<Artigo> artigoList;
    @JoinColumn(name = "autor", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario autor;

    public Blog() {
    }

    public Blog(Integer idblog) {
        this.idblog = idblog;
    }

    public Blog(Integer idblog, String nome) {
        this.idblog = idblog;
        this.nome = nome;
    }

    public Integer getIdblog() {
        return idblog;
    }

    public void setIdblog(Integer idblog) {
        this.idblog = idblog;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Artigo> getArtigoList() {
        return artigoList;
    }

    public void setArtigoList(List<Artigo> artigoList) {
        this.artigoList = artigoList;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idblog != null ? idblog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blog)) {
            return false;
        }
        Blog other = (Blog) object;
        if ((this.idblog == null && other.idblog != null) || (this.idblog != null && !this.idblog.equals(other.idblog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Blog[ idblog=" + idblog + " ]";
    }
    
}
