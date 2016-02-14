<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="posts">
    <c:forEach items="${posts}" var="post">
        <div class="post" data-info="${post.idpublicacao}">
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
                    <div class="post-video embed-responsive embed-responsive-4by3">
                        <iframe class="embed-responsive-item" src="${post.idvideo.link}"></iframe>
                    </div>                
                </c:if>
            </div>
            <div class="post-data">                
                <div>
                    <span data-id="like-count" class="glyphicon glyphicon-heart"></span>
                    <span>${post.getLikesList().size()}</span>
                </div>
                <div>
                    <span class="glyphicon glyphicon-bullhorn"></span>
                    ${post.getComentariopublicacaoList().size()}
                </div>
                <div>
                    <span class="glyphicon glyphicon-send"></span>
                    ${post.getCompartilhamentoList().size()}
                </div>
            </div>
            <div class="post-btn">                
                <c:if test="${post.checkLikedPost(user, post.getLikesList()) == true}">
                    <div data-id="like" class="liked">
                        <span class="glyphicon glyphicon-heart"></span>
                        Like                       
                    </div>
                </c:if>
                <c:if test="${post.checkLikedPost(user, post.getLikesList()) == false}">
                    <div data-id="like">
                        <span class="glyphicon glyphicon-heart"></span>
                        Like                       
                    </div>
                </c:if>                
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
</div>