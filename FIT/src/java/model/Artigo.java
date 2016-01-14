/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asdfrofl
 */
@Entity
@Table(catalog = "fit", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artigo.findAll", query = "SELECT a FROM Artigo a"),
    @NamedQuery(name = "Artigo.findByIdartigo", query = "SELECT a FROM Artigo a WHERE a.idartigo = :idartigo"),
    @NamedQuery(name = "Artigo.findByTitulo", query = "SELECT a FROM Artigo a WHERE a.titulo = :titulo"),
    @NamedQuery(name = "Artigo.findByData", query = "SELECT a FROM Artigo a WHERE a.data = :data"),
    @NamedQuery(name = "Artigo.findByDataEdicao", query = "SELECT a FROM Artigo a WHERE a.dataEdicao = :dataEdicao"),
    @NamedQuery(name = "Artigo.findByNota", query = "SELECT a FROM Artigo a WHERE a.nota = :nota"),
    @NamedQuery(name = "Artigo.findByVisualizacao", query = "SELECT a FROM Artigo a WHERE a.visualizacao = :visualizacao")})
public class Artigo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idartigo;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String titulo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEdicao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 2, scale = 1)
    private BigDecimal nota;
    private Integer visualizacao;
    @JoinColumn(name = "idblog", referencedColumnName = "idblog", nullable = false)
    @ManyToOne(optional = false)
    private Blog idblog;
    @JoinColumn(name = "idtexto", referencedColumnName = "idtexto", nullable = false)
    @ManyToOne(optional = false)
    private Texto idtexto;

    public Artigo() {
    }

    public Artigo(Integer idartigo) {
        this.idartigo = idartigo;
    }

    public Artigo(Integer idartigo, String titulo) {
        this.idartigo = idartigo;
        this.titulo = titulo;
    }

    public Integer getIdartigo() {
        return idartigo;
    }

    public void setIdartigo(Integer idartigo) {
        this.idartigo = idartigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDataEdicao() {
        return dataEdicao;
    }

    public void setDataEdicao(Date dataEdicao) {
        this.dataEdicao = dataEdicao;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public Integer getVisualizacao() {
        return visualizacao;
    }

    public void setVisualizacao(Integer visualizacao) {
        this.visualizacao = visualizacao;
    }

    public Blog getIdblog() {
        return idblog;
    }

    public void setIdblog(Blog idblog) {
        this.idblog = idblog;
    }

    public Texto getIdtexto() {
        return idtexto;
    }

    public void setIdtexto(Texto idtexto) {
        this.idtexto = idtexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idartigo != null ? idartigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artigo)) {
            return false;
        }
        Artigo other = (Artigo) object;
        if ((this.idartigo == null && other.idartigo != null) || (this.idartigo != null && !this.idartigo.equals(other.idartigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Artigo[ idartigo=" + idartigo + " ]";
    }
    
}
