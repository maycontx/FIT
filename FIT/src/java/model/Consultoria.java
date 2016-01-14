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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
    @NamedQuery(name = "Consultoria.findAll", query = "SELECT c FROM Consultoria c"),
    @NamedQuery(name = "Consultoria.findByIdconsultoria", query = "SELECT c FROM Consultoria c WHERE c.idconsultoria = :idconsultoria"),
    @NamedQuery(name = "Consultoria.findByPreco", query = "SELECT c FROM Consultoria c WHERE c.preco = :preco"),
    @NamedQuery(name = "Consultoria.findByNome", query = "SELECT c FROM Consultoria c WHERE c.nome = :nome")})
public class Consultoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idconsultoria;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String nome;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String descricao;
    @JoinColumn(name = "idprofissional", referencedColumnName = "idprofissional", nullable = false)
    @ManyToOne(optional = false)
    private Profissional idprofissional;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idconsultoria")
    private List<Consultoriarealizada> consultoriarealizadaList;

    public Consultoria() {
    }

    public Consultoria(Integer idconsultoria) {
        this.idconsultoria = idconsultoria;
    }

    public Consultoria(Integer idconsultoria, BigDecimal preco, String nome, String descricao) {
        this.idconsultoria = idconsultoria;
        this.preco = preco;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getIdconsultoria() {
        return idconsultoria;
    }

    public void setIdconsultoria(Integer idconsultoria) {
        this.idconsultoria = idconsultoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Profissional getIdprofissional() {
        return idprofissional;
    }

    public void setIdprofissional(Profissional idprofissional) {
        this.idprofissional = idprofissional;
    }

    @XmlTransient
    public List<Consultoriarealizada> getConsultoriarealizadaList() {
        return consultoriarealizadaList;
    }

    public void setConsultoriarealizadaList(List<Consultoriarealizada> consultoriarealizadaList) {
        this.consultoriarealizadaList = consultoriarealizadaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconsultoria != null ? idconsultoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultoria)) {
            return false;
        }
        Consultoria other = (Consultoria) object;
        if ((this.idconsultoria == null && other.idconsultoria != null) || (this.idconsultoria != null && !this.idconsultoria.equals(other.idconsultoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Consultoria[ idconsultoria=" + idconsultoria + " ]";
    }
    
}
