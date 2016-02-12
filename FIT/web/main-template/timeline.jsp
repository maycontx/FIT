<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${posts}" var="post">
    <div class="post">
        <div class="post-header">
            <div class="header-img">
                <img src="#">
            </div>
            <div class="header-info">
                <div class="header-author">
                    <a href="#">${post.idusuario.nome}</a> publicou
                </div>
                <div class="header-date">
                    <c:set var="paymentValueCode" value='${helper.Helper.dateValidation()}'/>
                    ${paymentValueCode}
                </div>
            </div>            
        </div>
        <div class="post-content">
            ${post.idtexto.texto}
        </div>
    </div>
</c:forEach> 