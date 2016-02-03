/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import dao.UsuarioJpaController;
import dao.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;

/**
 *
 * @author Tiago
 */
public class Session {

    //VARIÁVEIS
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String email, senha;
    //

    // CONEXÃO COM O BANCO
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FITPU");
    //

    public Session() {
    }

    public Session(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public Session(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Session(String email, String senha, HttpServletRequest request, HttpServletResponse response) {
        this.email = email;
        this.senha = senha;
        this.request = request;
        this.response = response;
    }

    public Usuario login() {

        //BUSCANDO O USUÁRIO PELO EMAIL E SENHA
        Usuario user = new UsuarioJpaController(emf).checkEmailAndPassword(email, senha);

        //INICIA A SESSÃO SE O USER NÃO FOR NULO
        if (user != null) {
            request.getSession().setAttribute("user", user);
            //ATUALIZA DATA ULTIMO LOGIN
            updateLastLogin(user);
        }        
        return user;
    }

    public void logout() {
        // DESTROI A SESSÃO
        request.getSession().invalidate();
        
        //RECUPERANDO OS COOKIES GRAVADOS
        Cookie[] cookies = request.getCookies();
        //ENCONTRANDO O COOKIE FIT
        for (Cookie c : cookies) {
            if (c.getName().equals("fitLogin")) {
                //REMOVENDO COOKIE
                request.getSession().removeAttribute("fitLogin");
            }
        }
        
    }

    public Usuario createCookie(boolean keep) {

        //BUSCANDO O USUÁRIO PELO EMAIL E SENHA
        Usuario user = new UsuarioJpaController(emf).checkEmailAndPassword(email, senha);

        if (user != null) {
            //CRIA O COOKIE DO LOGIN
            Cookie cookieLogin = new Cookie("fitLogin", user.getEmail());            
            if (keep) {
                //DEFINE VALIDADE DE 1 ANO
                cookieLogin.setMaxAge(60 * 60 * 24 * 360);
            } else {
                //DEFINE VALIDADE ATE EXPIRAR A SESSÃO
                cookieLogin.setMaxAge(-1);
            }
            response.addCookie(cookieLogin);
        }

        return login();
    }

    public Usuario findCookie() {

        //VARIÁVEL QUE VAI RECEBER O EMAIL QUE ESTIVER NO COOKIE
        String login = null;
        //RECUPERANDO OS COOKIES GRAVADOS
        Cookie[] cookies = request.getCookies();
        //ENCONTRANDO O COOKIE FIT
        for (Cookie c : cookies) {
            if (c.getName().equals("fitLogin")) {
                login = c.getValue();
            }
        }
        if (login != null) {            
            // BUSCA USUARIO PELO EMAIL
            Usuario user = new UsuarioJpaController(emf).checkEmail(login);            
            // SETA EMAIL E SENHA QUE SERA USADO PELO MÉTODO LOGIN
            email = user.getEmail();
            senha = user.getSenha();

            return login();
        }
        return null;
    }
    
    private void updateLastLogin(Usuario user) {
        user.setUltimoLogin(new Date());
        try {
            new UsuarioJpaController(emf).edit(user);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

}
