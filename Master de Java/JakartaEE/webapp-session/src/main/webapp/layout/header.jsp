<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <title>${title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
    <body>
    <div class="container">
        <nav class="navbar navbar-expand-lg bg-body-tertiary mb-10">
          <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp"><img src="https://skillicons.dev/icons?i=arch&theme=light" /></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                  <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/index.jsp">Home</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="${pageContext.request.contextPath}/productos">Productos</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="${pageContext.request.contextPath}/carro/ver">Ver carro (${carro.items.size()})</a>
                </li>
              </ul>
              <!-- Nuevo elemento alineado a la derecha -->
              <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    ${not empty sessionScope.username ? sessionScope.username : "Cuenta"}
                  </a>
                  <ul class="dropdown-menu">
                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/${not empty sessionScope.username ? "logout" : "login"}">${not empty sessionScope.username ? "Logout" : "Login"}</a>
                    </li>
                    <c:if test="${not empty username}">
                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/usuarios/edit?id=${userId}">Mi perfil</a>
                    </li>
                    </c:if>
                    <c:if test="${empty username}">
                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/sing-in">Sing In</a>
                    </li>
                    </c:if>
                    <c:if test="${isAdmin}">
                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/usuarios/lista">Lista de Usuarios</a>
                    </li>
                    </c:if>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </nav>