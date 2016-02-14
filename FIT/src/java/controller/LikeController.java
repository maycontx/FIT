package controller;

import dao.LikesJpaController;
import dao.PublicacaoJpaController;
import dao.exceptions.NonexistentEntityException;
import helper.Injection;
import java.io.IOException;
import java.util.List;
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
import model.Likes;
import model.Publicacao;
import model.Usuario;

@WebServlet(name = "LikeController", urlPatterns = {"/LikeController"})
public class LikeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        //Conexão com o Banco
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FITPU");

        Publicacao post = new PublicacaoJpaController(emf).findPublicacao(Integer.parseInt(request.getParameter("post")));
        Usuario user = (Usuario) request.getSession().getAttribute("user");

        // CHECA A EXISTENCIA DE UM LIKE COM ESTE USUARIO E POST
        Likes ext = new LikesJpaController(emf).findLikesByUserAndPost(post, user);

        String status = null;
        if (ext == null) {
            // SE NAO EXISTIR INSERE O NOVO LIKE
            Likes like = new Likes();
            like.setIdpublicacao(post);
            like.setIdusuario(user);

            new LikesJpaController(emf).create(like);           
            status = "add";
        } else {
            // SE HOUVER REMOVE O LIKE EXISTENTE
            try {
                new LikesJpaController(emf).destroy(ext.getIdlikes());
                status = "remove";
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(LikeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        String postLikes = String.valueOf(new PublicacaoJpaController(emf).findPublicacao(post.getIdpublicacao()).getLikesList().size());
        
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<result><status>"+status+"</status><postLikes>"+postLikes+"</postLikes></result>");
       
        

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
