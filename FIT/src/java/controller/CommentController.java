package controller;

import dao.ComentariopublicacaoJpaController;
import dao.PublicacaoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Comentariopublicacao;
import model.Publicacao;
import model.Usuario;

@WebServlet(name = "CommentController", urlPatterns = {"/CommentController"})
public class CommentController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    
        //Conexão com o Banco
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FITPU");
        
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        
        String reply = request.getParameter("reply");
        Integer post = Integer.parseInt(request.getParameter("post"));
        String comment = request.getParameter("comment");
        
        Comentariopublicacao commentObj = new Comentariopublicacao();
        commentObj.setComentario(comment);
        commentObj.setIdpublicacao(new PublicacaoJpaController(emf).findPublicacao(post));
        if ( !reply.equals("0") ){
            Comentariopublicacao dad = new ComentariopublicacaoJpaController(emf).findComentariopublicacao(Integer.parseInt(reply));
            commentObj.setIdpai(dad);
        }else{
            Comentariopublicacao dad = new ComentariopublicacaoJpaController(emf).findComentariopublicacao(1);
            commentObj.setIdpai(dad);
        }
        commentObj.setIdusuario(user);
        commentObj.setStatus("Ativo");
        
        int id = new ComentariopublicacaoJpaController(emf).create(commentObj);
        
        String name = commentObj.getIdusuario().getNome() + " " + commentObj.getIdusuario().getSobrenome();
        //String image = commentObj.getIdusuario().getPerfil().getLink();
        
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<result>"
                + "<name>"+name+"</name>"
                + "<id>"+id+"</id>"
                + "<comment>"+commentObj.getComentario()+"</comment>"
                + "<reply>"+reply+"</reply>"
        + "</result>");
    
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
