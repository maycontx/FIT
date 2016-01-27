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
    @NamedQuery(name = "Profissional.findAll", query = "SELECT p FROM Profissional p"),
    @NamedQuery(name = "Profissional.findByIdprofissional", query = "SELECT p FROM Profissional p WHERE p.idprofissional = :idprofissional"),
    @NamedQuery(name = "Profissional.findByRanking", query = "SELECT p FROM Profissional p WHERE p.ranking = :ranking"),
    @NamedQuery(name = "Profissional.findByReputacao", query = "SELECT p FROM Profissional p WHERE p.reputacao = :reputacao"),
    @NamedQuery(name = "Profissional.findByDatadeposito", query = "SELECT p FROM Profissional p WHERE p.datadeposito = :datadeposito"),
    @NamedQuery(name = "Profissional.findByLocaltrabalho", query = "SELECT p FROM Profissional p WHERE p.localtrabalho = :localtrabalho"),
    @NamedQuery(name = "Profissional.findByLocaltrabalho2", query = "SELECT p FROM Profissional p WHERE p.localtrabalho2 = :localtrabalho2"),
    @NamedQuery(name = "Profissional.findByDocumento", query = "SELECT p FROM Profissional p WHERE p.documento = :documento")})
public class Profissional implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idprofissional;
    @Basic(optional = false)
    @Lob
    @Column(length = 65535)
    private String descricao;
    private Integer ranking;
    private Integer reputacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datadeposito;
    private Integer localtrabalho;
    @Column(length = 255)
    private String localtrabalho2;
    @Column(length = 255)
    private String documento;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprofissional")
    private List<Mensagempreconsultoria> mensagempreconsultoriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprofissional")
    private List<Consultoria> consultoriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprofissional")
    private List<Personal> personalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprofissional")
    private List<Solicitacaorelacionamento> solicitacaorelacionamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprofissional")
    private List<Nutricionista> nutricionistaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprofissional")
    private List<Relacionamento> relacionamentoList;

    public Profissional() {
    }

    public Profissional(Integer idprofissional) {
        this.idprofissional = idprofissional;
    }

    public Profissional(Integer idprofissional, String descricao) {
        this.idprofissional = idprofissional;
        this.descricao = descricao;
    }

    public Integer getIdprofissional() {
        return idprofissional;
    }

    public void setIdprofissional(Integer idprofissional) {
        this.idprofissional = idprofissional;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getReputacao() {
        return reputacao;
    }

    public void setReputacao(Integer reputacao) {
        this.reputacao = reputacao;
    }

    public Date getDatadeposito() {
        return datadeposito;
    }

    public void setDatadeposito(Date datadeposito) {
        this.datadeposito = datadeposito;
    }

    public Integer getLocaltrabalho() {
        return localtrabalho;
    }

    public void setLocaltrabalho(Integer localtrabalho) {
        this.localtrabalho = localtrabalho;
    }

    public String getLocaltrabalho2() {
        return localtrabalho2;
    }

    public void setLocaltrabalho2(String localtrabalho2) {
        this.localtrabalho2 = localtrabalho2;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @XmlTransient
    public List<Mensagempreconsultoria> getMensagempreconsultoriaList() {
        return mensagempreconsultoriaList;
    }

    public void setMensagempreconsultoriaList(List<Mensagempreconsultoria> mensagempreconsultoriaList) {
        this.mensagempreconsultoriaList = mensagempreconsultoriaList;
    }

    @XmlTransient
    public List<Consultoria> getConsultoriaList() {
        return consultoriaList;
    }

    public void setConsultoriaList(List<Consultoria> consultoriaList) {
        this.consultoriaList = consultoriaList;
    }

    @XmlTransient
    public List<Personal> getPersonalList() {
        return personalList;
    }

    public void setPersonalList(List<Personal> personalList) {
        this.personalList = personalList;
    }

    @XmlTransient
    public List<Solicitacaorelacionamento> getSolicitacaorelacionamentoList() {
        return solicitacaorelacionamentoList;
    }

    public void setSolicitacaorelacionamentoList(List<Solicitacaorelacionamento> solicitacaorelacionamentoList) {
        this.solicitacaorelacionamentoList = solicitacaorelacionamentoList;
    }

    @XmlTransient
    public List<Nutricionista> getNutricionistaList() {
        return nutricionistaList;
    }

    public void setNutricionistaList(List<Nutricionista> nutricionistaList) {
        this.nutricionistaList = nutricionistaList;
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
        hash += (idprofissional != null ? idprofissional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profissional)) {
            return false;
        }
        Profissional other = (Profissional) object;
        if ((this.idprofissional == null && other.idprofissional != null) || (this.idprofissional != null && !this.idprofissional.equals(other.idprofissional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Profissional[ idprofissional=" + idprofissional + " ]";
    }
    
}
