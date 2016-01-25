package controller;

import helper.Session;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        Usuario user = new Usuario();
        user.setNome("Maycon");
        user.setSobrenome("Teixeira");
        user.setEmail("tmaycon1@gmail.com");
        user.setSenha("fdsfds");
        user.setNascimento(new Date());
        user.setSexo("Masculino");
        user.setCredito(BigDecimal.ZERO);

        RequestDispatcher rd = request.getRequestDispatcher("basic-template.jsp");
        request.setAttribute("page", "cadastro");
        request.setAttribute("usuario", user);
        
        //TESTES DA CLASSE SESSION (BY TIAGO)

        //PASSANDO OS PARAMETROS
        Session s = new Session("tmaycon1@gmail.com", "123456", request);
        
        //TESTE DO RETORNO DO LOGIN
        Usuario u = s.login();
        
        //TESTE DE RECUPERAÇÃO DO USER NA SESSÃO
        Usuario usuario = (Usuario) request.getSession().getAttribute("user");
        
        //FAZENDO LOGOUT
        s.logout();
        
        //TESTE DA SESSÃO APOS LOGOUT
        Usuario usu = (Usuario) request.getSession().getAttribute("user");
        
        //

        rd.forward(request, response);

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
