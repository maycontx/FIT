<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="posts">
    <c:forEach items="${posts}" var="post">
        <div class="post" data-info="${post.idpublicacao}" comment="false">
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
                <div data-id="comment">
                    <span class="glyphicon glyphicon-bullhorn"></span>
                    Comentar
                </div>
                <div>
                    <span class="glyphicon glyphicon-send"></span>
                    Compartilhar
                </div>
            </div>
        </div>
        <div class="post-comment" data-info="post-${post.idpublicacao}">
            <c:forEach items="${post.getComentariopublicacaoList()}" var="comment">
                <c:if test="${comment.idpai.idcomentariopublicacao == 1}">
                    <div class="comment-box" data-info="comment-${comment.idcomentariopublicacao}" comment="false">
                        <div class="comment-image">
                            <img src="${comment.idusuario.perfil.link}" alt="">
                        </div>
                        <div class="comment-header">
                            <div><a href="#">${comment.idusuario.nome} ${comment.idusuario.sobrenome}</a> comentou</div>
                            <span>
                                <!-- DATA AQUI -->
                            </span>
                        </div>
                        <div class="comment-body">
                            ${comment.comentario}
                        </div>
                        <div class="comment-footer">
                            <c:if test="${comment.idusuario == user}"><div><span class="glyphicon glyphicon-pencil"></span> Editar</div></c:if>
                                <div data-id="reply"><span class="glyphicon glyphicon-bullhorn"></span> Responder</div>
                            <c:if test="${comment.getChildren(comment).size() > 0}"><div data-id="oa"><span class="glyphicon glyphicon-chevron-down"></span> Ver respostas (${comment.getChildren(comment).size()})</div></c:if>
                        </div>
                        <!-- INICIO RESPOSTAS -->
                        <div class="reply-box" data-info="reply-${comment.idcomentariopublicacao}">
                            <c:forEach items="${comment.getChildren(comment)}" var="children">
                                <c:if test="${children.idpai.idcomentariopublicacao != 1}">
                                    <div class="reply comment-box" data-info="comment-${children.idcomentariopublicacao}">
                                        <div class="comment-image">
                                            <img src="${comment.idusuario.perfil.link}" alt="">
                                        </div>
                                        <div class="comment-header">
                                            <div><a href="#">${children.idusuario.nome} ${children.idusuario.sobrenome}</a> respondeu</div>
                                            <span>
                                                <!-- DATA AQUI --> 
                                            </span>
                                        </div>
                                        <div class="comment-body">
                                            ${children.comentario}
                                        </div>
                                        <div class="comment-footer">
                                            <c:if test="${children.idusuario == user}"><div><span class="glyphicon glyphicon-pencil"></span> Editar</div></c:if>                                                
                                        </div>                                            
                                    </div>
                                </c:if>
                            </c:forEach> 
                        </div>
                        <!-- FIM RESPOSTAS -->
                    </div>
                </c:if>
            </c:forEach>
            <textarea addr="0" post="${post.idpublicacao}" name="comment-text" class="form-control" placeholder="Comentar..." rows="1"></textarea>

        </div>
    </c:forEach>
</div>