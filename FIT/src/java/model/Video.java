/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v"),
    @NamedQuery(name = "Video.findByIdvideo", query = "SELECT v FROM Video v WHERE v.idvideo = :idvideo"),
    @NamedQuery(name = "Video.findByLink", query = "SELECT v FROM Video v WHERE v.link = :link"),
    @NamedQuery(name = "Video.findByCompartilhamentos", query = "SELECT v FROM Video v WHERE v.compartilhamentos = :compartilhamentos")})
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idvideo;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String link;
    @Basic(optional = false)
    @Column(nullable = false)
    private int compartilhamentos;
    @Column(length = 255)
    private String nome;
    @Lob
    @Column(length = 65535)
    private String descricao;
    @OneToMany(mappedBy = "idvideo")
    private List<Publicacao> publicacaoList;

    public Video() {
    }

    public Video(Integer idvideo) {
        this.idvideo = idvideo;
    }

    public Video(Integer idvideo, String link, int compartilhamentos) {
        this.idvideo = idvideo;
        this.link = link;
        this.compartilhamentos = compartilhamentos;
    }

    public Video findInformationsVideo(String link) {

        try {
            //CRIA URL ATRAVES DO LINK
            URL url = new URL(link);
            //CRIA CONEXÃO HTTP
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //CRIA VARIAVEL DE LEITURA DAS LINHAS
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            //VARIÁVEIS PROVISÓRIAS
            String linha = null;
            String nomeP = null;
            String descricaoP = null;

            while ((linha = in.readLine()) != null) {
                //RECUPERA LINHA DO NOME
                if (linha.contains("<span id=\"eow-title\"")) {
                    nomeP = linha.substring(61);
                }
                //RECUPERA LINHA DA DESCRIÇÃO
                if (linha.contains("<div id=\"watch-description-text\"")) {
                    descricaoP = linha.substring(431);
                }
            }
            in.close();
            urlConnection.disconnect();

            Video v = new Video();
            v.setDescricao(formatDescriptionVideo(descricaoP));
            v.setNome(formatNameVideo(nomeP));
            v.setLink(link);
            return v;

        } catch (MalformedURLException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String formatNameVideo(String name) {
        //ELIMINA CARACTERES DO NOME
        String format1 = name.replace("\"", "");
        String format2 = format1.replace(">", "");

        return format2;
    }

    public String formatDescriptionVideo(String description) {
        //ELIMINA TAG FINAL DA DESCRÇÃO
        String format1 = description.replace("</p></div>  <div id=\"watch-description-extras\" >", "");

        return format1;
    }

    public Integer getIdvideo() {
        return idvideo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setIdvideo(Integer idvideo) {
        this.idvideo = idvideo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getCompartilhamentos() {
        return compartilhamentos;
    }

    public void setCompartilhamentos(int compartilhamentos) {
        this.compartilhamentos = compartilhamentos;
    }

    @XmlTransient
    public List<Publicacao> getPublicacaoList() {
        return publicacaoList;
    }

    public void setPublicacaoList(List<Publicacao> publicacaoList) {
        this.publicacaoList = publicacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvideo != null ? idvideo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Video)) {
            return false;
        }
        Video other = (Video) object;
        if ((this.idvideo == null && other.idvideo != null) || (this.idvideo != null && !this.idvideo.equals(other.idvideo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Video[ idvideo=" + idvideo + " ]";
    }

}
