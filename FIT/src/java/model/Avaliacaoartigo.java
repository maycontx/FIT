/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asdfrofl
 */
@Entity
@Table(catalog = "fit", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avaliacaoartigo.findAll", query = "SELECT a FROM Avaliacaoartigo a"),
    @NamedQuery(name = "Avaliacaoartigo.findByIdavaliacaoconsultoria", query = "SELECT a FROM Avaliacaoartigo a WHERE a.idavaliacaoconsultoria = :idavaliacaoconsultoria"),
    @NamedQuery(name = "Avaliacaoartigo.findByNota", query = "SELECT a FROM Avaliacaoartigo a WHERE a.nota = :nota")})
public class Avaliacaoartigo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idavaliacaoconsultoria;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(nullable = false, precision = 2, scale = 1)
    private BigDecimal nota;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "idconsultoriarealizada", referencedColumnName = "idconsultoriarealizada", nullable = false)
    @ManyToOne(optional = false)
    private Consultoriarealizada idconsultoriarealizada;

    public Avaliacaoartigo() {
    }

    public Avaliacaoartigo(Integer idavaliacaoconsultoria) {
        this.idavaliacaoconsultoria = idavaliacaoconsultoria;
    }

    public Avaliacaoartigo(Integer idavaliacaoconsultoria, BigDecimal nota) {
        this.idavaliacaoconsultoria = idavaliacaoconsultoria;
        this.nota = nota;
    }

    public Integer getIdavaliacaoconsultoria() {
        return idavaliacaoconsultoria;
    }

    public void setIdavaliacaoconsultoria(Integer idavaliacaoconsultoria) {
        this.idavaliacaoconsultoria = idavaliacaoconsultoria;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
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
        hash += (idavaliacaoconsultoria != null ? idavaliacaoconsultoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avaliacaoartigo)) {
            return false;
        }
        Avaliacaoartigo other = (Avaliacaoartigo) object;
        if ((this.idavaliacaoconsultoria == null && other.idavaliacaoconsultoria != null) || (this.idavaliacaoconsultoria != null && !this.idavaliacaoconsultoria.equals(other.idavaliacaoconsultoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Avaliacaoartigo[ idavaliacaoconsultoria=" + idavaliacaoconsultoria + " ]";
    }
    
}
