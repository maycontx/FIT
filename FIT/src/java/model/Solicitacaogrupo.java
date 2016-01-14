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
    @NamedQuery(name = "Solicitacaogrupo.findAll", query = "SELECT s FROM Solicitacaogrupo s"),
    @NamedQuery(name = "Solicitacaogrupo.findByIdsolicitacao", query = "SELECT s FROM Solicitacaogrupo s WHERE s.idsolicitacao = :idsolicitacao"),
    @NamedQuery(name = "Solicitacaogrupo.findByStatus", query = "SELECT s FROM Solicitacaogrupo s WHERE s.status = :status"),
    @NamedQuery(name = "Solicitacaogrupo.findByOrigem", query = "SELECT s FROM Solicitacaogrupo s WHERE s.origem = :origem"),
    @NamedQuery(name = "Solicitacaogrupo.findByData", query = "SELECT s FROM Solicitacaogrupo s WHERE s.data = :data")})
public class Solicitacaogrupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idsolicitacao;
    @Basic(optional = false)
    @Column(nullable = false, length = 9)
    private String status;
    @Basic(optional = false)
    @Column(nullable = false, length = 8)
    private String origem;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @JoinColumn(name = "idgrupo", referencedColumnName = "idgrupo", nullable = false)
    @ManyToOne(optional = false)
    private Grupo idgrupo;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Solicitacaogrupo() {
    }

    public Solicitacaogrupo(Integer idsolicitacao) {
        this.idsolicitacao = idsolicitacao;
    }

    public Solicitacaogrupo(Integer idsolicitacao, String status, String origem) {
        this.idsolicitacao = idsolicitacao;
        this.status = status;
        this.origem = origem;
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

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Grupo getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Grupo idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
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
        if (!(object instanceof Solicitacaogrupo)) {
            return false;
        }
        Solicitacaogrupo other = (Solicitacaogrupo) object;
        if ((this.idsolicitacao == null && other.idsolicitacao != null) || (this.idsolicitacao != null && !this.idsolicitacao.equals(other.idsolicitacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Solicitacaogrupo[ idsolicitacao=" + idsolicitacao + " ]";
    }
    
}
