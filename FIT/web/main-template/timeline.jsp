<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${posts}" var="post">
    <div class="post">
        <div class="post-header">
            <div class="header-img">
                <img src="${post.idusuario.perfil.link}" alt="">
            </div>
            <div class="header-info">
                <div class="header-author">
                    <a href="#">${post.idusuario.nome}</a> publicou 
                    <c:if test="${post.idimagem != null}"> uma foto </c:if>
                    <c:if test="${post.idvideo != null}"> um video </c:if>
                    <c:if test="${post.idlink != null}"> um link </c:if>
                </div>
                <div class="header-date">
                    ${post.data}
                </div>
            </div>            
        </div>
        <div class="post-content">
            ${post.idtexto.texto}
            <c:if test="${post.idimagem != null}">
                <div class="post-image">
                    <img src="${post.idimagem.link}" alt="">
                </div>
            </c:if>
            <c:if test="${post.idvideo != null}">
                <div class="post-image embed-responsive embed-responsive-4by3">
                    <iframe class="embed-responsive-item" src="${post.idvideo.link}"></iframe>
                </div>                
            </c:if>
        </div>
        <div class="post-data">
            <div>
                <span class="glyphicon glyphicon-heart"></span>
                10
            </div>
            <div>
                <span class="glyphicon glyphicon-bullhorn"></span>
                10
            </div>
            <div>
                <span class="glyphicon glyphicon-send"></span>
                10
            </div>
        </div>
        <div class="post-btn">
            <div>
                <span class="glyphicon glyphicon-heart"></span>
                Like
            </div>
            <div>
                <span class="glyphicon glyphicon-bullhorn"></span>
                Comentar
            </div>
            <div>
                <span class="glyphicon glyphicon-send"></span>
                Compartilhar
            </div>
        </div>
    </div>
</c:forEach> 