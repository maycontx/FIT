package controller;

import dao.AtletaJpaController;
import dao.CorpoJpaController;
import helper.Session;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
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
        
        }else if( acctype.equals("Personal") ){
        
        }else if( acctype.equals("Nutricionista") ){
        
        }else{
        
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
