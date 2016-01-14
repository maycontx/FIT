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
    @NamedQuery(name = "Usuarioevento.findAll", query = "SELECT u FROM Usuarioevento u"),
    @NamedQuery(name = "Usuarioevento.findByIdusuarioevento", query = "SELECT u FROM Usuarioevento u WHERE u.idusuarioevento = :idusuarioevento"),
    @NamedQuery(name = "Usuarioevento.findByPermissao", query = "SELECT u FROM Usuarioevento u WHERE u.permissao = :permissao"),
    @NamedQuery(name = "Usuarioevento.findByStatus", query = "SELECT u FROM Usuarioevento u WHERE u.status = :status")})
public class Usuarioevento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idusuarioevento;
    @Basic(optional = false)
    @Column(nullable = false, length = 9)
    private String permissao;
    @Basic(optional = false)
    @Column(nullable = false, length = 15)
    private String status;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "idevento", referencedColumnName = "idevento", nullable = false)
    @ManyToOne(optional = false)
    private Evento idevento;

    public Usuarioevento() {
    }

    public Usuarioevento(Integer idusuarioevento) {
        this.idusuarioevento = idusuarioevento;
    }

    public Usuarioevento(Integer idusuarioevento, String permissao, String status) {
        this.idusuarioevento = idusuarioevento;
        this.permissao = permissao;
        this.status = status;
    }

    public Integer getIdusuarioevento() {
        return idusuarioevento;
    }

    public void setIdusuarioevento(Integer idusuarioevento) {
        this.idusuarioevento = idusuarioevento;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Evento getIdevento() {
        return idevento;
    }

    public void setIdevento(Evento idevento) {
        this.idevento = idevento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuarioevento != null ? idusuarioevento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarioevento)) {
            return false;
        }
        Usuarioevento other = (Usuarioevento) object;
        if ((this.idusuarioevento == null && other.idusuarioevento != null) || (this.idusuarioevento != null && !this.idusuarioevento.equals(other.idusuarioevento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Usuarioevento[ idusuarioevento=" + idusuarioevento + " ]";
    }
    
}
