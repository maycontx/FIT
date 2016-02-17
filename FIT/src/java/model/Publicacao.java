package model;

import helper.Encryption;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    @NamedQuery(name = "Publicacao.findAll", query = "SELECT p FROM Publicacao p"),
    @NamedQuery(name = "Publicacao.findByIdpublicacao", query = "SELECT p FROM Publicacao p WHERE p.idpublicacao = :idpublicacao"),
    @NamedQuery(name = "Publicacao.findByTipoPublicacao", query = "SELECT p FROM Publicacao p WHERE p.tipoPublicacao = :tipoPublicacao"),
    @NamedQuery(name = "Publicacao.findByTipoMidia", query = "SELECT p FROM Publicacao p WHERE p.tipoMidia = :tipoMidia"),
    @NamedQuery(name = "Publicacao.findByData", query = "SELECT p FROM Publicacao p WHERE p.data = :data"),
    @NamedQuery(name = "Publicacao.findByUltimaEdicao", query = "SELECT p FROM Publicacao p WHERE p.ultimaEdicao = :ultimaEdicao")})
public class Publicacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idpublicacao;
    @Basic(optional = false)
    @Column(nullable = false, length = 6)
    private String tipoPublicacao;
    @Basic(optional = false)
    @Column(nullable = false, length = 9)
    private String tipoMidia;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaEdicao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpublicacao")
    private List<Compartilhamento> compartilhamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpublicacao")
    private List<Likes> likesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpublicacao")
    private List<Chave> chaveList;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "idtexto", referencedColumnName = "idtexto")
    @ManyToOne
    private Texto idtexto;
    @JoinColumn(name = "idimagem", referencedColumnName = "idimagem")
    @ManyToOne
    private Imagem idimagem;
    @JoinColumn(name = "idvideo", referencedColumnName = "idvideo")
    @ManyToOne
    private Video idvideo;
    @JoinColumn(name = "idlink", referencedColumnName = "idlink")
    @ManyToOne
    private Link idlink;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpublicacao")
    private List<Comentariopublicacao> comentariopublicacaoList;

    public Publicacao() {
    }

    public Publicacao(Integer idpublicacao) {
        this.idpublicacao = idpublicacao;
    }

    public Publicacao(Integer idpublicacao, String tipoPublicacao, String tipoMidia) {
        this.idpublicacao = idpublicacao;
        this.tipoPublicacao = tipoPublicacao;
        this.tipoMidia = tipoMidia;
    }

    public Integer getIdpublicacao() {
        return idpublicacao;
    }

    public void setIdpublicacao(Integer idpublicacao) {
        this.idpublicacao = idpublicacao;
    }

    public String getTipoPublicacao() {
        return tipoPublicacao;
    }

    public void setTipoPublicacao(String tipoPublicacao) {
        this.tipoPublicacao = tipoPublicacao;
    }

    public String getTipoMidia() {
        return tipoMidia;
    }

    public void setTipoMidia(String tipoMidia) {
        this.tipoMidia = tipoMidia;
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

    public void setUltimaEdicao(Date ultimaEdicao) {
        this.ultimaEdicao = ultimaEdicao;
    }

    @XmlTransient
    public List<Compartilhamento> getCompartilhamentoList() {
        return compartilhamentoList;
    }

    public void setCompartilhamentoList(List<Compartilhamento> compartilhamentoList) {
        this.compartilhamentoList = compartilhamentoList;
    }

    @XmlTransient
    public List<Likes> getLikesList() {
        return likesList;
    }

    public void setLikesList(List<Likes> likesList) {
        this.likesList = likesList;
    }

    @XmlTransient
    public List<Chave> getChaveList() {
        return chaveList;
    }

    public void setChaveList(List<Chave> chaveList) {
        this.chaveList = chaveList;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Texto getIdtexto() {
        return idtexto;
    }

    public void setIdtexto(Texto idtexto) {
        this.idtexto = idtexto;
    }

    public Imagem getIdimagem() {
        return idimagem;
    }

    public void setIdimagem(Imagem idimagem) {
        this.idimagem = idimagem;
    }

    public Video getIdvideo() {
        return idvideo;
    }

    public void setIdvideo(Video idvideo) {
        this.idvideo = idvideo;
    }

    public Link getIdlink() {
        return idlink;
    }

    public void setIdlink(Link idlink) {
        this.idlink = idlink;
    }

    @XmlTransient
    public List<Comentariopublicacao> getComentariopublicacaoList() {
        return comentariopublicacaoList;
    }

    public void setComentariopublicacaoList(List<Comentariopublicacao> comentariopublicacaoList) {
        this.comentariopublicacaoList = comentariopublicacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpublicacao != null ? idpublicacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publicacao)) {
            return false;
        }
        Publicacao other = (Publicacao) object;
        if ((this.idpublicacao == null && other.idpublicacao != null) || (this.idpublicacao != null && !this.idpublicacao.equals(other.idpublicacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Publicacao[ idpublicacao=" + idpublicacao + " ]";
    }
    
    // CHECK POST LIKE
    public boolean checkLikedPost(Usuario user, List<Likes> likes){
    
        for (Likes like : likes){
            if ( like.getIdusuario().getIdusuario() == user.getIdusuario() )
                return true; 
        }
        
        return false;
        
    }
    
    // RETURN ID ENCRYPTED
    public String getEncryptedId(){
        Encryption encrypt = new Encryption(this.idpublicacao);
        return encrypt.doIdEncrypt();
    }
    
}
