<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%
 Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
 %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Formulario de usuarios</title>
    <link href="<%=request.getContextPath()%>/style/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid px-5">
<h3>Formulario de usuarios</h3>

<%
if(errores != null && errores.size() > 0){
%>
<ul class="alert alert-danger col-6 px-5">
    <%for(String error: errores.values()){%>
    <li><%=error%></li>
    <%}%>
</ul>
<%}%>
<div class = "row">
    <div class = "col-4 offset-0">
        <form action="/webapp-form/registro" method="post">
            <div class="mb-3">
                <label for="username">Username</label>
                    <div>
                        <input type="text" name="username" id="username" class="form-control" value="${param.username}">
                    </div>
                <%
                    if(errores != null && errores.containsKey("username")){
                        out.println("<div class='mt-1 alert alert-danger' style='color: red;'>"+ errores.get("username") +"</div>");
                    }
                %>
            </div>
            <div class="mb-3">
                <label for="password">Password</label>
                    <div>
                        <input type="password" name="password" id="password" class="form-control">
                    </div>
                <%
                    if(errores != null && errores.containsKey("password")){
                        out.println("<div class='mt-1 alert alert-danger' style='color: red;'>"+errores.get("password")+"</div>");
                    }
                %>
            </div>
            <div class="mb-3">
                <label for="email">Email</label>
                    <div>
                        <input type="text" name="email" id="email" class="form-control" value="${param.email}">
                    </div>
                <%
                    if(errores != null && errores.containsKey("email")){
                        out.println("<div class='mt-1 alert alert-danger' style='color: red;'>"+errores.get("email")+"</div>");
                    }
                %>
            </div>
            <div class="mb-3">
                <label for="pais">País</label>
                <div>
                    <select name="pais" id="pais" class="form-select">
                        <option value="">-- Seleccionar --</option>
                        <option value="ES" ${param.pais.equals("ES") ? "selected": ""}>España</option>
                        <option value="MX" ${param.pai.equals("MX") ? "selected": ""}>Mexico</option>
                        <option value="Cl" ${param.pais.equals("Cl") ? "selected": ""}>Chile</option>
                        <option value="AR" ${param.pais.equals("AR") ? "selected": ""}>Argentina</option>
                        <option value="PE" ${param.pais.equals("PE") ? "selected": ""}>Perú</option>
                        <option value="CO" ${param.pais.equals("CO") ? "selected": ""}>Colombia</option>
                        <option value="VE" ${param.pais.equals("VE") ? "selected": ""}>Venezuela</option>
                    </select>
                </div>
                <%
                    if(errores != null && errores.containsKey("pais")){
                        out.println("<div class='mt-1 alert alert-danger' style='color: red;'>"+errores.get("pais")+"</div>");
                    }
                %>
            </div>
            <div class="mb-3">
                <label for="lenguajes">Lenguajes de programación</label>
                <div>
                    <select name="lenguajes" id="lenguajes" multiple class="form-select">
                        <option value="java" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("java")).get() ? "selected": ""}>Java SE</option>
                        <option value="jakartaee" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("jakartaee")).get() ? "selected": ""}>Jakarta EE9</option>
                        <option value="spring" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("spring")).get() ? "selected": ""}>Spring Boot</option>
                        <option value="js" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("js")).get() ? "selected": ""}>JavaScript</option>
                        <option value="angular" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("angular")).get() ? "selected": ""}>Angular</option>
                        <option value="react" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("react")).get() ? "selected": ""}>React</option>
                    </select>
                </div>
                <%
                    if(errores != null && errores.containsKey("lenguajes")){
                        out.println("<div class='mt-1 alert alert-danger' style='color: red;'>"+errores.get("lenguajes")+"</div>");
                    }
                %>
            </div>
            <div class="mb-3">
                <div class="row">
                    <label>Roles</label>
                    <div class="form-check col-sm-3">
                        <input class="form-check-input" type="checkbox" name="roles" value="ROLE_ADMIN"
                        ${paramValues.roles.stream().anyMatch(v -> v.equals("ROLE_ADMIN")).get() ? "checked": ""}>
                        <label class="form-check-label">Administrador</label>
                    </div>
                    <div class="form-check col-sm-2">
                        <input class="form-check-input" type="checkbox" name="roles" value="ROLE_USER"
                        ${paramValues.roles.stream().anyMatch(v -> v.equals("ROLE_USER")).get() ? "checked": ""}>
                        <label class="form-check-label">Usuario</label>
                    </div>
                    <div class="form-check col-sm-2">
                        <input class="form-check-input" type="checkbox" name="roles" value="ROLE_MODERATOR"
                        ${paramValues.roles.stream().anyMatch(v -> v.equals("ROLE_MODERATOR")).get() ? "checked": ""}>
                        <label class="form-check-label">Moderador</label>
                    </div>
                    <%
                        if(errores != null && errores.containsKey("roles")){
                            out.println("<div class='mt-1 alert alert-danger' style='color: red;'>"+errores.get("roles")+"</div>");
                        }
                    %>
                </div>
            </div>
            <div class="mb-3">
                <div class="row">
                    <label>Idiomas</label>
                    <div class="form-check col-sm-2">
                        <input class="form-check-input" type="radio" name="idioma" value="es" ${param.idioma.equals("es") ? "checked": ""}>
                        <label class="form-check-label">Español</label>
                    </div>
                    <div class="form-check col-sm-2">
                        <input class="form-check-input" type="radio" name="idioma" value="en" ${param.idioma.equals("en") ? "checked": ""}>
                        <label class="form-check-label">Inglés</label>
                    </div>
                    <div class="form-check col-sm-2">
                        <input class="form-check-input" type="radio" name="idioma" value="fr" ${param.idioma.equals("fr") ? "checked": ""}>
                        <label class="form-check-label">frances</label>
                    </div>
                    <%
                        if(errores != null && errores.containsKey("idioma")){
                            out.println("<div class='mt-1 alert alert-danger' style='color: red;'>"+errores.get("idioma")+"</div>");
                        }
                    %>
                </div>
            </div>
            <div class="mb-3">
                <label class="form-check-label" for="habilitar">Habilitar</label>
                <div>
                    <input class="form-check-input" type="checkbox" name="habilitar" id="habilitar" checked>
                </div>
            </div>
            <div class="mb-3">
                <input type="submit" value="Enviar" class="btn btn-primary w-100">
            </div>
            <input type="hidden" name="secreto" value="12345">
        </form>
    </div>
</div>
</div>
</body>
</html>