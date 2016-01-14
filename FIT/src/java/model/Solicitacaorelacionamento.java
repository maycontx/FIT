/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
    @NamedQuery(name = "Solicitacaorelacionamento.findAll", query = "SELECT s FROM Solicitacaorelacionamento s"),
    @NamedQuery(name = "Solicitacaorelacionamento.findByIdsolicitacao", query = "SELECT s FROM Solicitacaorelacionamento s WHERE s.idsolicitacao = :idsolicitacao"),
    @NamedQuery(name = "Solicitacaorelacionamento.findByStatus", query = "SELECT s FROM Solicitacaorelacionamento s WHERE s.status = :status"),
    @NamedQuery(name = "Solicitacaorelacionamento.findByData", query = "SELECT s FROM Solicitacaorelacionamento s WHERE s.data = :data")})
public class Solicitacaorelacionamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idsolicitacao;
    @Basic(optional = false)
    @Column(nullable = false, length = 9)
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @JoinColumn(name = "idatleta", referencedColumnName = "idatleta", nullable = false)
    @ManyToOne(optional = false)
    private Atleta idatleta;
    @JoinColumn(name = "idprofissional", referencedColumnName = "idprofissional", nullable = false)
    @ManyToOne(optional = false)
    private Profissional idprofissional;

    public Solicitacaorelacionamento() {
    }

    public Solicitacaorelacionamento(Integer idsolicitacao) {
        this.idsolicitacao = idsolicitacao;
    }

    public Solicitacaorelacionamento(Integer idsolicitacao, String status) {
        this.idsolicitacao = idsolicitacao;
        this.status = status;
    }

    public Integer getIdsolicitacao() {
        return idsolicitacao;
    }

    public void setIdsolicitacao(Integer idsolicitacao) {
        this.idsolicitacao = idsolicitacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Atleta getIdatleta() {
        return idatleta;
    }

    public void setIdatleta(Atleta idatleta) {
        this.idatleta = idatleta;
    }

    public Profissional getIdprofissional() {
        return idprofissional;
    }

    public void setIdprofissional(Profissional idprofissional) {
        this.idprofissional = idprofissional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsolicitacao != null ? idsolicitacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitacaorelacionamento)) {
            return false;
        }
        Solicitacaorelacionamento other = (Solicitacaorelacionamento) object;
        if ((this.idsolicitacao == null && other.idsolicitacao != null) || (this.idsolicitacao != null && !this.idsolicitacao.equals(other.idsolicitacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Solicitacaorelacionamento[ idsolicitacao=" + idsolicitacao + " ]";
    }
    
}
