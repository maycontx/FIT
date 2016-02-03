<%@page import="model.Usuario"%>
<%@page import="helper.Session"%>
<%
    // RECEBE STATUS DO PROCESSO
    String status = (String) request.getAttribute("status");
    // CHECA DE HÁ ALGUM COOKIE
    Usuario user = new Session(request, response).findCookie();
    // CHECA SE HÁ ALGUM STATUS E SE É DIFERENTE DE LOGIN
    if ( status != null && !status.equals("login") ){
        // SE NÃO HOUVER USUARIO, REDIRECIONA PRA INDEX
        if (user == null){
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");       
            rd.forward(request, response);
        }     
    } 
    
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" />   
    <body>
        <div class="global-bt">
            <jsp:include page="basic-template/header.jsp" /> 
            <article>           
                <%
                    // RECEBENDO O PARAMETRO POR GET
                    String pageRequest = request.getParameter("page");
                    try {
                        // SE A PAGINA NÃO FOR NULO
                        if (pageRequest != null) {
                            // COLOCAR O O LINK DA PAGINA DENTRO DA VARIAVEL
                            pageRequest = "basic-template/" + pageRequest + ".jsp";
                %>
                <jsp:include page="<%= pageRequest%>" />
                <%
                } else {
                    // RECEBENDO O PARAMETRO POR POST
                    pageRequest = (String) request.getAttribute("page");
                    // SE A PAGINA NÃO FOR NULO
                    if (pageRequest != null) {
                        // COLOCAR O O LINK DA PAGINA DENTRO DA VARIAVEL
                        pageRequest = "basic-template/" + pageRequest + ".jsp";
                %>
                <jsp:include page="<%= pageRequest%>" />
                <%
                    // CASO NÃO HAJA PARÂMETRO
                } else {
                %>
                <jsp:include page="basic-template/404.jsp" />
                <%
                        }
                    }
                } catch (Exception ex) {
                %>
                <jsp:include page="basic-template/404.jsp" />
                <%
                    }

                %>           
            </article>
        </div>
    </body>
</html>
