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
    @NamedQuery(name = "Usuariogrupo.findAll", query = "SELECT u FROM Usuariogrupo u"),
    @NamedQuery(name = "Usuariogrupo.findByIdusuariogrupo", query = "SELECT u FROM Usuariogrupo u WHERE u.idusuariogrupo = :idusuariogrupo"),
    @NamedQuery(name = "Usuariogrupo.findByPermissao", query = "SELECT u FROM Usuariogrupo u WHERE u.permissao = :permissao")})
public class Usuariogrupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idusuariogrupo;
    @Basic(optional = false)
    @Column(nullable = false, length = 9)
    private String permissao;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "idgrupo", referencedColumnName = "idgrupo", nullable = false)
    @ManyToOne(optional = false)
    private Grupo idgrupo;

    public Usuariogrupo() {
    }

    public Usuariogrupo(Integer idusuariogrupo) {
        this.idusuariogrupo = idusuariogrupo;
    }

    public Usuariogrupo(Integer idusuariogrupo, String permissao) {
        this.idusuariogrupo = idusuariogrupo;
        this.permissao = permissao;
    }

    public Integer getIdusuariogrupo() {
        return idusuariogrupo;
    }

    public void setIdusuariogrupo(Integer idusuariogrupo) {
        this.idusuariogrupo = idusuariogrupo;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Grupo getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Grupo idgrupo) {
        this.idgrupo = idgrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuariogrupo != null ? idusuariogrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuariogrupo)) {
            return false;
        }
        Usuariogrupo other = (Usuariogrupo) object;
        if ((this.idusuariogrupo == null && other.idusuariogrupo != null) || (this.idusuariogrupo != null && !this.idusuariogrupo.equals(other.idusuariogrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Usuariogrupo[ idusuariogrupo=" + idusuariogrupo + " ]";
    }
    
}
