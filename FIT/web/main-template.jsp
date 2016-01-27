<%@page import="helper.Session"%>
<%@page import="model.Usuario"%>
<%
    Usuario user = new Session(request, response).findCookie();
    if (user == null){
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");       
        rd.forward(request, response);
    }    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" />
    <div class="global-mt">
            <jsp:include page="main-template/header.jsp" /> 
            <article>           
                <%
                    // RECEBENDO O PARAMETRO POR GET
                    String pageRequest = request.getParameter("page");
                    try {
                        // SE A PAGINA NÃO FOR NULO
                        if (pageRequest != null) {
                            // COLOCAR O O LINK DA PAGINA DENTRO DA VARIAVEL
                            pageRequest = "main-template/" + pageRequest + ".jsp";
                %>
                <jsp:include page="<%= pageRequest%>" />
                <%
                } else {
                    // RECEBENDO O PARAMETRO POR POST
                    pageRequest = (String) request.getAttribute("page");
                    // SE A PAGINA NÃO FOR NULO
                    if (pageRequest != null) {
                        // COLOCAR O O LINK DA PAGINA DENTRO DA VARIAVEL
                        pageRequest = "main-template/" + pageRequest + ".jsp";
                %>
                <jsp:include page="<%= pageRequest%>" />
                <%
                    // CASO NÃO HAJA PARÂMETRO
                } else {
                %>
                <jsp:include page="main-template/404.jsp" />
                <%
                        }
                    }
                } catch (Exception ex) {
                %>
                <jsp:include page="main-template/404.jsp" />
                <%
                    }

                %>           
            </article>
        </div>
</html>
