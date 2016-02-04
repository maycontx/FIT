package helper;

import dao.AtletaJpaController;
import dao.MensagemcomumJpaController;
import dao.NutricionistaJpaController;
import dao.PersonalJpaController;
import dao.ProfissionalJpaController;
import dao.PublicacaoJpaController;
import dao.SeguidorJpaController;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import model.Atleta;
import model.Profissional;
import model.Publicacao;
import model.Usuario;

public class Injection {
    
    private final HttpServletRequest request;
    private EntityManagerFactory emf;   

    public Injection(HttpServletRequest request, EntityManagerFactory emf) {
        this.request = request;
        this.emf = emf;
    }

    public Injection(HttpServletRequest request) {
        this.request = request;
    }
    
    // TEMPLATE BÁSICO
    public void basicTemplate(Usuario user){
        // ENVIA O USUARIO EM SUA FORMA CURTA
        request.setAttribute("user", user);
    }

    // PAGINA DE COMPLETAR CADASTRO
    public void register(Usuario user){
        request.setAttribute("page", "register");
    }

    // TEMPLATE PRINCIPAL
    public void mainTemplate(Usuario user){
        
        // ENVIA O OBJETO DO USUARIO COMPLETO JA TIPADO (ATLETA, PERSONAL OU NUTRI) COM NOME DE typed
        setUserTyped(user);
        // ENVIA O USUARIO EM SUA FORMA CURTA
        request.setAttribute("user", user);
        // ENVIA O NÚMERO DE SEGUIDORES DESTE USUÁRIO
        request.setAttribute("followers", new SeguidorJpaController(emf).findFollowers(user.getIdusuario()));
                
    }

    public void timeline(Usuario user) {
        // INJETANDO A PÁGINA
        request.setAttribute("page", "timeline");
        // INJETANDO PUBLICAÇÕES
        List<Publicacao> posts = new PublicacaoJpaController(emf).findPublicationsUserFollowed(user.getIdusuario());
        request.setAttribute("posts", posts);
        
    }
    
    public void profile(Usuario user) {
        // INJETANDO A PÁGINA
        request.setAttribute("page", "profile");  
    }
    
    public void setUserTyped(Usuario user){       
        if ( user.getTipo().equals("Atleta") ){
            Atleta ath = new AtletaJpaController(emf).findAthByUser(user);
            // MANDA ATRIBUTO TIPADO COM OBJETO COMPLETO
            request.setAttribute("typed", ath);
            // ENVIA MENSAGENS NÃO LIDAS
            request.setAttribute("newMessages", new MensagemcomumJpaController(emf).findNewMessage(ath.getIdatleta()));
        
        }else if ( user.getTipo().equals("Personal") ){
            // ENCONTRA OBJETO DO PROFISSIONAL
            Profissional prof = new ProfissionalJpaController(emf).findProfissionalByUser(user);
            // MANDA ATRIBUTO TIPADO COM OBJETO COMPLETO
            request.setAttribute("typed", new PersonalJpaController(emf).findPersonalByProfissional(prof));
        }else if ( user.getTipo().equals("Nutricionista") ){ 
            // ENCONTRA OBJETO DO PROFISSIONAL
            Profissional prof = new ProfissionalJpaController(emf).findProfissionalByUser(user);
            // MANDA ATRIBUTO TIPADO COM OBJETO COMPLETO
            request.setAttribute("typed", new NutricionistaJpaController(emf).findNutriByProfissional(prof));
        }
    }
    
}
