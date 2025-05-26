<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="layout/header.jsp" />

<h3>${title}</h3>
<table class="table table-hover table-striped">
    <tr>
        <th>Id</th>
        <th>username</th>
        <th>email</th>
        <th>eliminar</th>
    </tr>
    <c:forEach items="${listaUsuarios}" var="usuario">
        <tr>
            <td>${usuario.id}</td>
            <td>${usuario.username}</td>
            <td>${usuario.email}</td>
            <td><a class="btn btn-sm btn-danger" onclick="return confirm('¿Está seguro que desea este usuario eliminar?');"
                       href="${pageContext.request.contextPath}/usuarios/eliminar?id=${usuario.id}">Eliminar</a></td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="layout/footer.jsp" />