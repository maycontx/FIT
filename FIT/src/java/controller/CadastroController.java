package controller;

import dao.AtletaJpaController;
import dao.CorpoJpaController;
import dao.NutricionistaJpaController;
import dao.PersonalJpaController;
import dao.ProfissionalJpaController;
import dao.UsuarioJpaController;
import dao.exceptions.NonexistentEntityException;
import helper.Injection;
import helper.Session;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Atleta;
import model.Corpo;
import model.Nutricionista;
import model.Personal;
import model.Profissional;
import model.Usuario;

/**
 *
 * @author asdfrofl
 */
@WebServlet(name = "CadastroController", urlPatterns = {"/concluir-cadastro"})
public class CadastroController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //Conexão com o Banco
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FITPU");

        // RECEBENDO O VALOR DO TIPO DE CONTA
        String acctype = request.getParameter("acctype-ajax");
        
        // RECUPERANDO O USUÁRIO DA SESSÃO        
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        
        // SETA O TIPO DE USUARIO
        user.setTipo(acctype);
        
        // ALTERA NO BANCO
        try {            
            new UsuarioJpaController(emf).edit(user);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if ( acctype.equals("Atleta") ){            
           
                // INSTANCIANDO UM ATLETA
                Atleta ath = new Atleta();
                
                // INSERINDO O USUARIO NO ATLETA
                ath.setIdusuario(user);
                
                // INSTANCIANDO UM CORPO
                Corpo body = new Corpo();
                
                // INSERE UM CORPO NO BANCO DE DADOS, RECUPERA SEU ID E ALTERA NO OBJECTO INSTANCIADO
                body.setIdcorpo( (int) new CorpoJpaController(emf).create(body) );
                
                // ADICIONA O CORPO NO ATLETA
                ath.setIdcorpo(body);
                
                // INSERE O ATLETA NO BANCO DE DADOS
                new AtletaJpaController(emf).create(ath);
                
                // CHAMA O REDIRECIONAMENTO
                sendToProfile(request, response, emf, user);           
        
        }else if( acctype.equals("Personal") ){
            
            // INSTANCIANDO PROFISSIONAL
            Profissional prof = new Profissional();
            
            // INSERINDO O USUARIO NO PROFISSIONAL
            prof.setIdusuario(user);
            // INSERE UM PROFISSIONAL NO BANCO DE DADOS, RECUPERA SEU ID E ALTERA NO OBJECTO INSTANCIADO
            prof.setIdprofissional((int) new ProfissionalJpaController(emf).create(prof));
            
            // INSTANCIANDO PERSONAL
            Personal pers = new Personal();
            
            // INSERINDO O PROFISSIONAL NO PERSONAL
            pers.setIdprofissional(prof);
            
            // INSERE O PERSONAL NO BANCO
            new PersonalJpaController(emf).create(pers);
            
            // CHAMA O REDIRECIONAMENTO
            sendToProfile(request, response, emf, user);
        
        }else if( acctype.equals("Nutricionista") ){
        
            // INSTANCIANDO PROFISSIONAL
            Profissional prof = new Profissional();
            
            // INSERINDO O USUARIO NO PROFISSIONAL
            prof.setIdusuario(user);
            // INSERE UM PROFISSIONAL NO BANCO DE DADOS, RECUPERA SEU ID E ALTERA NO OBJECTO INSTANCIADO
            prof.setIdprofissional((int) new ProfissionalJpaController(emf).create(prof));
            
            // INSTANCIANDO O NUTRICIONISTA
            Nutricionista nutri = new Nutricionista();
            
            // INSERINDO O PROFISSIONAL NO NUTRICIONISTA
            nutri.setIdprofissional(prof);
            
            // INSERE O NUTRICIONISTA NO BANCO
            new NutricionistaJpaController(emf).create(nutri);
            
            // CHAMA O REDIRECIONAMENTO
            sendToProfile(request, response, emf, user);
            
        }else{
        
        }
        
    }
    
    // REDIRECIONAMENTO
    public void sendToProfile(HttpServletRequest request, HttpServletResponse response, EntityManagerFactory emf, Usuario user){
        // INSTANCIANDO INJECTION
        Injection injection = new Injection(request, emf);
        try {
            // REDIRECIONANDO
            RequestDispatcher rd = request.getRequestDispatcher("main-template.jsp");
            // INJETANDO DADOS DO TEMPLATE PRINCIPAL
            injection.mainTemplate(user);
            // INJETANDO OS DADOS DO PERFIL
            injection.profile(user);
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
