<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${posts}" var="post">
    Autor: ${post.idusuario.nome}<br />
    Texto: ${post.idtexto.texto} <br /><br /><br /><br />
</c:forEach> 