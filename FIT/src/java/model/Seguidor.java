/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
    @NamedQuery(name = "Seguidor.findAll", query = "SELECT s FROM Seguidor s"),
    @NamedQuery(name = "Seguidor.findByIdtabela", query = "SELECT s FROM Seguidor s WHERE s.idtabela = :idtabela"),
    @NamedQuery(name = "Seguidor.findByStatus", query = "SELECT s FROM Seguidor s WHERE s.status = :status")})
public class Seguidor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idtabela;
    @Basic(optional = false)
    @Column(nullable = false, length = 9)
    private String status;
    @JoinColumn(name = "idseguidor", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idseguidor;
    @JoinColumn(name = "idseguido", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idseguido;

    public Seguidor() {
    }

    public Seguidor(Integer idtabela) {
        this.idtabela = idtabela;
    }

    public Seguidor(Integer idtabela, String status) {
        this.idtabela = idtabela;
        this.status = status;
    }

    public Integer getIdtabela() {
        return idtabela;
    }

    public void setIdtabela(Integer idtabela) {
        this.idtabela = idtabela;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getIdseguidor() {
        return idseguidor;
    }

    public void setIdseguidor(Usuario idseguidor) {
        this.idseguidor = idseguidor;
    }

    public Usuario getIdseguido() {
        return idseguido;
    }

    public void setIdseguido(Usuario idseguido) {
        this.idseguido = idseguido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtabela != null ? idtabela.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seguidor)) {
            return false;
        }
        Seguidor other = (Seguidor) object;
        if ((this.idtabela == null && other.idtabela != null) || (this.idtabela != null && !this.idtabela.equals(other.idtabela))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Seguidor[ idtabela=" + idtabela + " ]";
    }
    
}
