<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hola mundo EJB</title>
</head>
<body>
    <h2>Hola mundo EJB</h2>
    <h3>${saludo}</h3>
    <h3>${saludo2}</h3>
    <ul>
    <c:forEach items="${listado}" var="prod">
        <li>${prod.nombre}</li>
    </c:forEach>
    </ul>
</body>
</html>