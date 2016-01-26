<%@page import="model.Usuario"%>
<%@page import="helper.Session"%>
<%
    Usuario user = new Session(request, response).findCookie();

    if (user != null) {
        //CARREGA A PAGINA NORMALMENTE
    } else {
        //REDIRECIONA PARA PAGINA DE LOGIN
    }
    //OBS: SE DER CERTO ESSE CÓDIGO TEMOS QUE COPIAR PRA INDEX TAMBÉM
    // PORQUE ELA NÃO USA O TEMPLATE
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
