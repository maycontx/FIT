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
    @NamedQuery(name = "Consultoriarealizada.findAll", query = "SELECT c FROM Consultoriarealizada c"),
    @NamedQuery(name = "Consultoriarealizada.findByIdconsultoriarealizada", query = "SELECT c FROM Consultoriarealizada c WHERE c.idconsultoriarealizada = :idconsultoriarealizada"),
    @NamedQuery(name = "Consultoriarealizada.findByStatus", query = "SELECT c FROM Consultoriarealizada c WHERE c.status = :status"),
    @NamedQuery(name = "Consultoriarealizada.findByDatainicio", query = "SELECT c FROM Consultoriarealizada c WHERE c.datainicio = :datainicio"),
    @NamedQuery(name = "Consultoriarealizada.findByDataencerramento", query = "SELECT c FROM Consultoriarealizada c WHERE c.dataencerramento = :dataencerramento")})
public class Consultoriarealizada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idconsultoriarealizada;
    @Column(length = 12)
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datainicio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataencerramento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idconsultoriarealizada")
    private List<Chatconsultoria> chatconsultoriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idconsultoriarealizada")
    private List<Avaliacaoartigo> avaliacaoartigoList;
    @JoinColumn(name = "idconsultoria", referencedColumnName = "idconsultoria", nullable = false)
    @ManyToOne(optional = false)
    private Consultoria idconsultoria;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "idpagamento", referencedColumnName = "idpagamento", nullable = false)
    @ManyToOne(optional = false)
    private Pagamento idpagamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idconsultoriarealizada")
    private List<Pagamento> pagamentoList;

    public Consultoriarealizada() {
    }

    public Consultoriarealizada(Integer idconsultoriarealizada) {
        this.idconsultoriarealizada = idconsultoriarealizada;
    }

    public Integer getIdconsultoriarealizada() {
        return idconsultoriarealizada;
    }

    public void setIdconsultoriarealizada(Integer idconsultoriarealizada) {
        this.idconsultoriarealizada = idconsultoriarealizada;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDataencerramento() {
        return dataencerramento;
    }

    public void setDataencerramento(Date dataencerramento) {
        this.dataencerramento = dataencerramento;
    }

    @XmlTransient
    public List<Chatconsultoria> getChatconsultoriaList() {
        return chatconsultoriaList;
    }

    public void setChatconsultoriaList(List<Chatconsultoria> chatconsultoriaList) {
        this.chatconsultoriaList = chatconsultoriaList;
    }

    @XmlTransient
    public List<Avaliacaoartigo> getAvaliacaoartigoList() {
        return avaliacaoartigoList;
    }

    public void setAvaliacaoartigoList(List<Avaliacaoartigo> avaliacaoartigoList) {
        this.avaliacaoartigoList = avaliacaoartigoList;
    }

    public Consultoria getIdconsultoria() {
        return idconsultoria;
    }

    public void setIdconsultoria(Consultoria idconsultoria) {
        this.idconsultoria = idconsultoria;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Pagamento getIdpagamento() {
        return idpagamento;
    }

    public void setIdpagamento(Pagamento idpagamento) {
        this.idpagamento = idpagamento;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList() {
        return pagamentoList;
    }

    public void setPagamentoList(List<Pagamento> pagamentoList) {
        this.pagamentoList = pagamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconsultoriarealizada != null ? idconsultoriarealizada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultoriarealizada)) {
            return false;
        }
        Consultoriarealizada other = (Consultoriarealizada) object;
        if ((this.idconsultoriarealizada == null && other.idconsultoriarealizada != null) || (this.idconsultoriarealizada != null && !this.idconsultoriarealizada.equals(other.idconsultoriarealizada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Consultoriarealizada[ idconsultoriarealizada=" + idconsultoriarealizada + " ]";
    }
    
}
