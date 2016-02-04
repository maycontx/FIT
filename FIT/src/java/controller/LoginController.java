package controller;

import dao.MensagemcomumJpaController;
import dao.SeguidorJpaController;
import helper.Injection;
import helper.Session;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //Conexão com o Banco
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FITPU");

        
        String email = request.getParameter("log-email");
        String pass = request.getParameter("log-pass");        
        
        // KEEP EH TRUE SE MANTER CONECTADO ESTIVER SELECIONADO
        boolean keep = request.getParameter("log-keep") != null;
        //CRIA O COOKIE E LOGA
        Usuario user = new Session(email, pass, request, response).createCookie(keep);        
        // INSTANCIANDO INJECTION
        Injection injection = new Injection(request, emf);
        if ( user != null ){            
            //TRUE = CADASTRO CONCLUIDO / FALSE = CONCLUSÃO DE CADASTRO PENDENTE
            if ( user.checkingCompletionRegister() ) {                
                RequestDispatcher rd = request.getRequestDispatcher("main-template.jsp");
                // INJETANDO DADOS DO TEMPLATE PRINCIPAL
                injection.mainTemplate(user);
                // INJETANDO DADOS DA TIMELINE
                injection.timeline(user); 
                // STATUS A PARTIR DESTE CONTROLLER
                request.setAttribute("status", "login");
                // SEGUINDO
                rd.forward(request, response);                
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("basic-template.jsp");
                // INJETANDO DADOS DO TEMPLATE BÁSICO
                injection.basicTemplate(user);                
                // INJETANDO DADOS DA PÁGINA DE CADASTRO
                injection.register(user);
                // STATUS A PARTIR DESTE CONTROLLER
                request.setAttribute("status", "login");
                // SEGUINDO
                rd.forward(request, response);
            }
        }else{
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
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
