/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p"),
    @NamedQuery(name = "Pagamento.findByIdpagamento", query = "SELECT p FROM Pagamento p WHERE p.idpagamento = :idpagamento"),
    @NamedQuery(name = "Pagamento.findBySituacao", query = "SELECT p FROM Pagamento p WHERE p.situacao = :situacao"),
    @NamedQuery(name = "Pagamento.findByTipopagamento", query = "SELECT p FROM Pagamento p WHERE p.tipopagamento = :tipopagamento"),
    @NamedQuery(name = "Pagamento.findByValor", query = "SELECT p FROM Pagamento p WHERE p.valor = :valor"),
    @NamedQuery(name = "Pagamento.findByData", query = "SELECT p FROM Pagamento p WHERE p.data = :data"),
    @NamedQuery(name = "Pagamento.findByUltimaAtualizacao", query = "SELECT p FROM Pagamento p WHERE p.ultimaAtualizacao = :ultimaAtualizacao")})
public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idpagamento;
    @Column(length = 9)
    private String situacao;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String tipopagamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaAtualizacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpagamento")
    private List<Consultoriarealizada> consultoriarealizadaList;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "idconsultoriarealizada", referencedColumnName = "idconsultoriarealizada", nullable = false)
    @ManyToOne(optional = false)
    private Consultoriarealizada idconsultoriarealizada;

    public Pagamento() {
    }

    public Pagamento(Integer idpagamento) {
        this.idpagamento = idpagamento;
    }

    public Pagamento(Integer idpagamento, String tipopagamento, BigDecimal valor) {
        this.idpagamento = idpagamento;
        this.tipopagamento = tipopagamento;
        this.valor = valor;
    }

    public Integer getIdpagamento() {
        return idpagamento;
    }

    public void setIdpagamento(Integer idpagamento) {
        this.idpagamento = idpagamento;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getTipopagamento() {
        return tipopagamento;
    }

    public void setTipopagamento(String tipopagamento) {
        this.tipopagamento = tipopagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(Date ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    @XmlTransient
    public List<Consultoriarealizada> getConsultoriarealizadaList() {
        return consultoriarealizadaList;
    }

    public void setConsultoriarealizadaList(List<Consultoriarealizada> consultoriarealizadaList) {
        this.consultoriarealizadaList = consultoriarealizadaList;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Consultoriarealizada getIdconsultoriarealizada() {
        return idconsultoriarealizada;
    }

    public void setIdconsultoriarealizada(Consultoriarealizada idconsultoriarealizada) {
        this.idconsultoriarealizada = idconsultoriarealizada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpagamento != null ? idpagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.idpagamento == null && other.idpagamento != null) || (this.idpagamento != null && !this.idpagamento.equals(other.idpagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pagamento[ idpagamento=" + idpagamento + " ]";
    }
    
}
