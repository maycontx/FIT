/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsuarioJpaController;
import helper.Session;
import helper.validation;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;

/**
 *
 * @author Tiago
 */
@WebServlet(name = "indexController", urlPatterns = {"/cadastro"})
public class indexController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");        
        
        //Conexão com o Banco
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FITPU");
        
        //Passando os valores para classe de validação
        validation validate = new validation();
        validate.setName(request.getParameter("reg-name"));
        validate.setAftername(request.getParameter("reg-aftername"));
        validate.setEmail(request.getParameter("reg-email"));
        validate.setPassword(request.getParameter("reg-pass"));
        validate.setRepassword(request.getParameter("reg-repass"));
        validate.setBirthdate(request.getParameter("reg-day") + "/"
                + request.getParameter("reg-mon") + "/" + request.getParameter("reg-year"));
        validate.setSex(request.getParameter("reg-sex"));
        //

        //Verifica o resultado da validação
        if (validate.validationRegister()) {
            //manda mensagem de erro para a página
            
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            String message = "Um dos campos apresenta erro. Por favor, insira seus dados corretamente.";
            request.setAttribute("message", message);
            rd.forward(request, response);
            //
        } else if (new UsuarioJpaController(emf).checkEmail(validate.getEmail()) != null) {
            //manda mensagem de erro para a página
            
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            String message = "Já existe um cadastro com este e-mail.";
            request.setAttribute("message", message);
            rd.forward(request, response);
            //
        }else{
            //Preenche o usuário e grava
            Usuario user = new Usuario();
            user.setNome(validate.getName());
            user.setSobrenome(validate.getAftername());
            user.setEmail(validate.getEmail());
            user.setSenha(validate.getPassword());
            user.setNascimento(validate.convertDate(validate.getBirthdate()));
            if (validate.getSex().equals("M"))
                user.setSexo("Masculino");
            else
                user.setSexo("Feminino");
            user.setCredito(BigDecimal.ZERO);

            // ADICIONANDO O USUARIO NO BANCO DE DADOS
            new UsuarioJpaController(emf).create(user);
            
            // LOGANDO O USUARIO
            new Session(user.getEmail(), user.getSenha(), request, response).createCookie(false);
            
            // REDIRECIONANDO
            RequestDispatcher rd = request.getRequestDispatcher("basic-template.jsp");
            request.setAttribute("page", "cadastro");
            request.setAttribute("usuario", request.getSession().getAttribute("user"));
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
