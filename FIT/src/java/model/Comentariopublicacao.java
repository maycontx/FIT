/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ComentariopublicacaoJpaController;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
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
    @NamedQuery(name = "Comentariopublicacao.findAll", query = "SELECT c FROM Comentariopublicacao c"),
    @NamedQuery(name = "Comentariopublicacao.findByIdcomentariopublicacao", query = "SELECT c FROM Comentariopublicacao c WHERE c.idcomentariopublicacao = :idcomentariopublicacao"),
    @NamedQuery(name = "Comentariopublicacao.findByData", query = "SELECT c FROM Comentariopublicacao c WHERE c.data = :data"),
    @NamedQuery(name = "Comentariopublicacao.findByUltimaEdicao", query = "SELECT c FROM Comentariopublicacao c WHERE c.ultimaEdicao = :ultimaEdicao"),
    @NamedQuery(name = "Comentariopublicacao.findByStatus", query = "SELECT c FROM Comentariopublicacao c WHERE c.status = :status")})
public class Comentariopublicacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idcomentariopublicacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaEdicao;
    @Basic(optional = false)
    @Column(nullable = false, length = 7)
    private String status;
    @Lob
    @Column(length = 65535)
    private String comentario;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "idpublicacao", referencedColumnName = "idpublicacao", nullable = false)
    @ManyToOne(optional = false)
    private Publicacao idpublicacao;
    @OneToMany(mappedBy = "idpai")
    private List<Comentariopublicacao> comentariopublicacaoList;
    @JoinColumn(name = "idpai", referencedColumnName = "idcomentariopublicacao")
    @ManyToOne
    private Comentariopublicacao idpai;

    public Comentariopublicacao() {
    }

    public Comentariopublicacao(Integer idcomentariopublicacao) {
        this.idcomentariopublicacao = idcomentariopublicacao;
    }

    public Comentariopublicacao(Integer idcomentariopublicacao, String status) {
        this.idcomentariopublicacao = idcomentariopublicacao;
        this.status = status;
    }

    public Integer getIdcomentariopublicacao() {
        return idcomentariopublicacao;
    }

    public void setIdcomentariopublicacao(Integer idcomentariopublicacao) {
        this.idcomentariopublicacao = idcomentariopublicacao;
    }

    public String getData() {
        
        // CAPTURA A DIFERENÇA ENTRE AS DATA EM MILISEGUNDOS E CONVERTE PARA MINUTOS
        long miliseconds = (( new Date().getTime() - data.getTime() ) / 60) / 1000;        
         
        // FORMATA A HORA
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");  
           
        if ( miliseconds < 60 )
            // RETORNAR EM SITUAÇÕES MENOR DE UMA HORA
            return miliseconds + " min";
        else if ( miliseconds >= 60 && miliseconds < 1440)
            // RETORNAR EM SITUAÇÕES MENOR DE UM DIA
            return miliseconds/60 + " h";
        else if ( miliseconds >= 1440 && miliseconds < 4320)
            // RETORNAR EM SITUAÇÕES ENTRE 1 E 3 DIAS
            return miliseconds/1440 + " dia(s)";
        else{
            // CAPTURA O ANO
            int year = data.getYear();
            // CHECA SE O ANO DA PUBLICACAO EH MENOR QUE O ATUAL
            if ( new Date().getYear() > year ){
                // FORMATO COM ANO PARA PUBLICACOES DO ANO ANTERIOR
                SimpleDateFormat date = new SimpleDateFormat("dd/MMMM/yyyy"); 
                String comp = date.format(data).replaceAll("/", " de ") + " às " + time.format(data);
                return comp;
            }else{
                // FORMATO COM ANO PARA PUBLICACOES DO MESMO ANO
                SimpleDateFormat date = new SimpleDateFormat("dd/MMMM"); 
                String comp = date.format(data).replaceAll("/", " de ") + " às " + time.format(data);
                return comp;
            }
        }
        
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getUltimaEdicao() {
        
        if ( ultimaEdicao == null )
            return "";

        // CAPTURA A DIFERENÇA ENTRE AS DATA EM MILISEGUNDOS E CONVERTE PARA MINUTOS
        long miliseconds = (( new Date().getTime() - ultimaEdicao.getTime() ) / 60) / 1000;        
         
        // FORMATA A HORA
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");  
           
        if ( miliseconds < 60 )
            // RETORNAR EM SITUAÇÕES MENOR DE UMA HORA
            return miliseconds + " min";
        else if ( miliseconds >= 60 && miliseconds < 1440)
            // RETORNAR EM SITUAÇÕES MENOR DE UM DIA
            return miliseconds/60 + " h";
        else if ( miliseconds >= 1440 && miliseconds < 4320)
            // RETORNAR EM SITUAÇÕES ENTRE 1 E 3 DIAS
            return miliseconds/1440 + " dia(s)";
        else{
            // CAPTURA O ANO
            int year = ultimaEdicao.getYear();
            // CHECA SE O ANO DA PUBLICACAO EH MENOR QUE O ATUAL
            if ( new Date().getYear() > year ){
                // FORMATO COM ANO PARA PUBLICACOES DO ANO ANTERIOR
                SimpleDateFormat date = new SimpleDateFormat("dd/MMMM/yyyy"); 
                String comp = date.format(ultimaEdicao).replaceAll("/", " de ") + " às " + time.format(ultimaEdicao);
                return comp;
            }else{
                // FORMATO COM ANO PARA PUBLICACOES DO MESMO ANO
                SimpleDateFormat date = new SimpleDateFormat("dd/MMMM"); 
                String comp = date.format(ultimaEdicao).replaceAll("/", " de ") + " às " + time.format(ultimaEdicao);
                return comp;
            }
        }
        
    }

    public void setUltimaEdicao(Date ultimaEdicao) {
        this.ultimaEdicao = ultimaEdicao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Publicacao getIdpublicacao() {
        return idpublicacao;
    }

    public void setIdpublicacao(Publicacao idpublicacao) {
        this.idpublicacao = idpublicacao;
    }

    @XmlTransient
    public List<Comentariopublicacao> getComentariopublicacaoList() {
        return comentariopublicacaoList;
    }

    public void setComentariopublicacaoList(List<Comentariopublicacao> comentariopublicacaoList) {
        this.comentariopublicacaoList = comentariopublicacaoList;
    }

    public Comentariopublicacao getIdpai() {
        return idpai;
    }

    public void setIdpai(Comentariopublicacao idpai) {
        this.idpai = idpai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomentariopublicacao != null ? idcomentariopublicacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentariopublicacao)) {
            return false;
        }
        Comentariopublicacao other = (Comentariopublicacao) object;
        if ((this.idcomentariopublicacao == null && other.idcomentariopublicacao != null) || (this.idcomentariopublicacao != null && !this.idcomentariopublicacao.equals(other.idcomentariopublicacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Comentariopublicacao[ idcomentariopublicacao=" + idcomentariopublicacao + " ]";
    }
    
    // CAPTURAR TODOS OS COMENTÁRIOS FILHOS
    public List<Comentariopublicacao> getChildren(Comentariopublicacao comment){
        //Conexão com o Banco
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FITPU");
        
        return new ComentariopublicacaoJpaController(emf).findCommentByDad(comment);
    }
    
}
