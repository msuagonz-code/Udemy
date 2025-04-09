<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de usuarios</title>
</head>
<body>
    <h3>Lista de usuarios</h3>
    <ul>
        <c:forEach items="${usuarios}" var="u">
        <li>${u.username}</li>
        </c:forEach>
    </ul>
</body>
</html>