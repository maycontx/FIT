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
    @NamedQuery(name = "Atleta.findAll", query = "SELECT a FROM Atleta a"),
    @NamedQuery(name = "Atleta.findByIdatleta", query = "SELECT a FROM Atleta a WHERE a.idatleta = :idatleta"),
    @NamedQuery(name = "Atleta.findByLocaltreino", query = "SELECT a FROM Atleta a WHERE a.localtreino = :localtreino"),
    @NamedQuery(name = "Atleta.findByAltura", query = "SELECT a FROM Atleta a WHERE a.altura = :altura"),
    @NamedQuery(name = "Atleta.findByPeso", query = "SELECT a FROM Atleta a WHERE a.peso = :peso"),
    @NamedQuery(name = "Atleta.findByBf", query = "SELECT a FROM Atleta a WHERE a.bf = :bf"),
    @NamedQuery(name = "Atleta.findByEspecialidade", query = "SELECT a FROM Atleta a WHERE a.especialidade = :especialidade"),
    @NamedQuery(name = "Atleta.findByBiotipo", query = "SELECT a FROM Atleta a WHERE a.biotipo = :biotipo"),
    @NamedQuery(name = "Atleta.findByAutenticacao", query = "SELECT a FROM Atleta a WHERE a.autenticacao = :autenticacao")})
public class Atleta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idatleta;
    @Column(length = 255)
    private String localtreino;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 3, scale = 1)
    private BigDecimal altura;
    @Column(precision = 4, scale = 1)
    private BigDecimal peso;
    private Integer bf;
    @Column(length = 255)
    private String especialidade;
    @Column(length = 255)
    private String biotipo;
    @Lob
    @Column(length = 65535)
    private String caracteristicas;
    @Column(length = 13)
    private String autenticacao;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "idcorpo", referencedColumnName = "idcorpo", nullable = false)
    @ManyToOne(optional = false)
    private Corpo idcorpo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAtletaEmissor")
    private List<Mensagemcomum> mensagemcomumList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAtletaDestinatario")
    private List<Mensagemcomum> mensagemcomumList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idatleta")
    private List<Solicitacaorelacionamento> solicitacaorelacionamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idatleta")
    private List<Relacionamento> relacionamentoList;

    public Atleta() {
    }

    public Atleta(Integer idatleta) {
        this.idatleta = idatleta;
    }

    public Integer getIdatleta() {
        return idatleta;
    }

    public void setIdatleta(Integer idatleta) {
        this.idatleta = idatleta;
    }

    public String getLocaltreino() {
        return localtreino;
    }

    public void setLocaltreino(String localtreino) {
        this.localtreino = localtreino;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Integer getBf() {
        return bf;
    }

    public void setBf(Integer bf) {
        this.bf = bf;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getBiotipo() {
        return biotipo;
    }

    public void setBiotipo(String biotipo) {
        this.biotipo = biotipo;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getAutenticacao() {
        return autenticacao;
    }

    public void setAutenticacao(String autenticacao) {
        this.autenticacao = autenticacao;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Corpo getIdcorpo() {
        return idcorpo;
    }

    public void setIdcorpo(Corpo idcorpo) {
        this.idcorpo = idcorpo;
    }

    @XmlTransient
    public List<Mensagemcomum> getMensagemcomumList() {
        return mensagemcomumList;
    }

    public void setMensagemcomumList(List<Mensagemcomum> mensagemcomumList) {
        this.mensagemcomumList = mensagemcomumList;
    }

    @XmlTransient
    public List<Mensagemcomum> getMensagemcomumList1() {
        return mensagemcomumList1;
    }

    public void setMensagemcomumList1(List<Mensagemcomum> mensagemcomumList1) {
        this.mensagemcomumList1 = mensagemcomumList1;
    }

    @XmlTransient
    public List<Solicitacaorelacionamento> getSolicitacaorelacionamentoList() {
        return solicitacaorelacionamentoList;
    }

    public void setSolicitacaorelacionamentoList(List<Solicitacaorelacionamento> solicitacaorelacionamentoList) {
        this.solicitacaorelacionamentoList = solicitacaorelacionamentoList;
    }

    @XmlTransient
    public List<Relacionamento> getRelacionamentoList() {
        return relacionamentoList;
    }

    public void setRelacionamentoList(List<Relacionamento> relacionamentoList) {
        this.relacionamentoList = relacionamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idatleta != null ? idatleta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atleta)) {
            return false;
        }
        Atleta other = (Atleta) object;
        if ((this.idatleta == null && other.idatleta != null) || (this.idatleta != null && !this.idatleta.equals(other.idatleta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Atleta[ idatleta=" + idatleta + " ]";
    }
    
}
