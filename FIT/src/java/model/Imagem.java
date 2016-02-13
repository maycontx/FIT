/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Imagem.findAll", query = "SELECT i FROM Imagem i"),
    @NamedQuery(name = "Imagem.findByIdimagem", query = "SELECT i FROM Imagem i WHERE i.idimagem = :idimagem"),
    @NamedQuery(name = "Imagem.findByIdorigem", query = "SELECT i FROM Imagem i WHERE i.idorigem = :idorigem"),
    @NamedQuery(name = "Imagem.findByLink", query = "SELECT i FROM Imagem i WHERE i.link = :link"),
    @NamedQuery(name = "Imagem.findByTipo", query = "SELECT i FROM Imagem i WHERE i.tipo = :tipo"),
    @NamedQuery(name = "Imagem.findByTamanho", query = "SELECT i FROM Imagem i WHERE i.tamanho = :tamanho"),
    @NamedQuery(name = "Imagem.findByOrdem", query = "SELECT i FROM Imagem i WHERE i.ordem = :ordem"),
    @NamedQuery(name = "Imagem.findByData", query = "SELECT i FROM Imagem i WHERE i.data = :data")})
public class Imagem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idimagem;
    @Basic(optional = false)
    @Column(nullable = false)
    private int idorigem;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String link;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String tipo;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String tamanho;
    @Basic(optional = false)
    @Column(nullable = false, length = 11)
    private String ordem;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @OneToMany(mappedBy = "idimagem")
    private List<Publicacao> publicacaoList;
    @OneToMany(mappedBy = "perfil")
    private List<Usuario> usuarioList;
    public Imagem() {
    }

    public Imagem(Integer idimagem) {
        this.idimagem = idimagem;
    }

    public Imagem(Integer idimagem, int idorigem, String link, String tipo, String tamanho, String ordem) {
        this.idimagem = idimagem;
        this.idorigem = idorigem;
        this.link = link;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.ordem = ordem;
    }

    public Integer getIdimagem() {
        return idimagem;
    }

    public void setIdimagem(Integer idimagem) {
        this.idimagem = idimagem;
    }

    public int getIdorigem() {
        return idorigem;
    }

    public void setIdorigem(int idorigem) {
        this.idorigem = idorigem;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
        hash += (idimagem != null ? idimagem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagem)) {
            return false;
        }
        Imagem other = (Imagem) object;
        if ((this.idimagem == null && other.idimagem != null) || (this.idimagem != null && !this.idimagem.equals(other.idimagem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Imagem[ idimagem=" + idimagem + " ]";
    }
    
}
