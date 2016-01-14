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
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findByIdevento", query = "SELECT e FROM Evento e WHERE e.idevento = :idevento"),
    @NamedQuery(name = "Evento.findByNome", query = "SELECT e FROM Evento e WHERE e.nome = :nome"),
    @NamedQuery(name = "Evento.findByDatainicial", query = "SELECT e FROM Evento e WHERE e.datainicial = :datainicial"),
    @NamedQuery(name = "Evento.findByDatafinal", query = "SELECT e FROM Evento e WHERE e.datafinal = :datafinal")})
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idevento;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String nome;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datainicial;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datafinal;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idevento")
    private List<Solicitacaoevento> solicitacaoeventoList;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idevento")
    private List<Usuarioevento> usuarioeventoList;

    public Evento() {
    }

    public Evento(Integer idevento) {
        this.idevento = idevento;
    }

    public Evento(Integer idevento, String nome, Date datainicial, Date datafinal, String descricao) {
        this.idevento = idevento;
        this.nome = nome;
        this.datainicial = datainicial;
        this.datafinal = datafinal;
        this.descricao = descricao;
    }

    public Integer getIdevento() {
        return idevento;
    }

    public void setIdevento(Integer idevento) {
        this.idevento = idevento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDatainicial() {
        return datainicial;
    }

    public void setDatainicial(Date datainicial) {
        this.datainicial = datainicial;
    }

    public Date getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(Date datafinal) {
        this.datafinal = datafinal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<Solicitacaoevento> getSolicitacaoeventoList() {
        return solicitacaoeventoList;
    }

    public void setSolicitacaoeventoList(List<Solicitacaoevento> solicitacaoeventoList) {
        this.solicitacaoeventoList = solicitacaoeventoList;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @XmlTransient
    public List<Usuarioevento> getUsuarioeventoList() {
        return usuarioeventoList;
    }

    public void setUsuarioeventoList(List<Usuarioevento> usuarioeventoList) {
        this.usuarioeventoList = usuarioeventoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevento != null ? idevento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.idevento == null && other.idevento != null) || (this.idevento != null && !this.idevento.equals(other.idevento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Evento[ idevento=" + idevento + " ]";
    }
    
}
