/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import dao.UsuarioJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import model.Usuario;

/**
 *
 * @author Tiago
 */
public class Session{

    //VARIÁVEIS
    private HttpServletRequest request;
    private String email, senha;
    //
    
    public Session() {
    }

    public Session(String email, String senha, HttpServletRequest request) {
        this.email = email;
        this.senha = senha;
        this.request = request;
    }

    public Usuario login() {

        // CONEXÃO COM O BANCO
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FITPU");

        //BUSCANDO O USUÁRIO PELO EMAIL E SENHA
        Usuario user = new UsuarioJpaController(emf).checkEmailAndPassword(email, senha);

        //INICIA A SESSÃO SE O USER NÃO FOR NULO
        if (user != null) {
            request.getSession().setAttribute("user", user);
        }
        return user;
    }

    public void logout() {
        // DESTROI A SESSÃO
        request.getSession().invalidate();
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
