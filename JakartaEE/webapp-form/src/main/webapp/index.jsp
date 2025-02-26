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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
<h3>Formulario de usuarios</h3>

<%
if(errores != null && errores.size() > 0){
%>
<ul>
    <%for(String error: errores.values()){%>
    <li><%=error%></li>
    <%}%>
</ul>
<%}%>
<div class = "row">
    <div class = "col-4 offset-0">
        <form action="/webapp-form/registro" method="post">
            <div class="row mb-3">
                <label for="username">Username</label>
                    <div><input type="text" name="username" id="username" class="form-control"></div>
                    <%
                        if(errores != null && errores.containsKey("username")){
                            out.println("<small style='color: red;'>"+ errores.get("username") +"</small>");
                        }
                    %>
            </div>
            <div class="mb-3">
                <label for="password">Password</label>
                    <div><input type="password" name="password" id="password" class="form-control"></div>
                    <%
                        if(errores != null && errores.containsKey("password")){
                            out.println("<small style='color: red;'>"+errores.get("password")+"</small>");
                        }
                    %>
            </div>
            <div class="mb-3">
                <label for="email">Email</label>
                    <div><input type="text" name="email" id="email" class="form-control"></div>
                    <%
                        if(errores != null && errores.containsKey("email")){
                            out.println("<small style='color: red;'>"+errores.get("email")+"</small>");
                        }
                    %>
            </div>
            <div class="mb-3">
                <label for="pais">País</label>
                <div>
                    <select name="pais" id="pais" class="form-select">
                        <option value="">-- Seleccionar --</option>
                        <option value="ES">España</option>
                        <option value="MX">Mexico</option>
                        <option value="Cl" selected>Chile</option>
                        <option value="AR">Argentina</option>
                        <option value="PE">Perú</option>
                        <option value="CO">Colombia</option>
                        <option value="VE">Venezuela</option>
                    </select>
                </div>
                <%
                    if(errores != null && errores.containsKey("pais")){
                        out.println("<small style='color: red;'>"+errores.get("pais")+"</small>");
                    }
                %>
            </div>
            <div class="mb-3">
                <label for="lenguajes">Lenguajes de programación</label>
                <div>
                    <select name="lenguajes" id="lenguajes" multiple class="form-select">
                        <option value="java" selected>Java SE</option>
                        <option value="jakartaee">Jakarta EE9</option>
                        <option value="spring" selected>Spring Boot</option>
                        <option value="js">JavaScript</option>
                        <option value="angular">Angular</option>
                        <option value="react">React</option>
                    </select>
                </div>
                <%
                    if(errores != null && errores.containsKey("lenguajes")){
                        out.println("<small style='color: red;'>"+errores.get("lenguajes")+"</small>");
                    }
                %>
            </div>
            <div class="mb-3">
                <label>Roles</label>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="roles" value="ROLE_ADMIN">
                    <label class="form-check-label">Administrador</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="roles" value="ROLE_USER" checked>
                    <label class="form-check-label">Usuario</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="roles" value="ROLE_MODERATOR">
                    <label class="form-check-label">Moderador</label>
                </div>
                <%
                    if(errores != null && errores.containsKey("roles")){
                        out.println("<small style='color: red;'>"+errores.get("roles")+"</small>");
                    }
                %>
            </div>
            <div class="mb-3">
                <label>Idiomas</label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="idioma" value="es">
                    <label class="form-check-label">Español</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="idioma" value="en">
                    <label class="form-check-label">Inglés</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="idioma" value="fr">
                    <label class="form-check-label">frances</label>
                </div>
                <%
                    if(errores != null && errores.containsKey("idioma")){
                        out.println("<small style='color: red;'>"+errores.get("idioma")+"</small>");
                    }
                %>
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