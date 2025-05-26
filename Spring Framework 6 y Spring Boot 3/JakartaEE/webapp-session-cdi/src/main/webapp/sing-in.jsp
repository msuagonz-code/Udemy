<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp" />

<h3>${title}</h3>
<form action="${pageContext.request.getContextPath()}/${action}" method="post">
  <div class="row mb-2 mt-2">
    <div class="col-4">
        <label class="form-label" for="username">Username</label>
        <div>
          <input type="text" name="username" id="username" value="${user.username}" class="form-control">
        </div>
    </div>
    <c:if test="${errores != null && !empty errores.username}">
    <div style="color:#df6882;">${errores.username}</div>
    </c:if>
  </div>
  <div class="row mb-2 mt-2">
    <div class="col-4">
        <label class="form-label" for="password">Password</label>
        <div>
          <input type="password" name="password" id="password" value="${user.password}" class="form-control">
        </div>
    </div>
    <c:if test="${errores != null && !empty errores.password}">
    <div style="color:#df6882;">${errores.password}</div>
    </c:if>
  </div>
  <div class="row mb-2 mt-2">
    <div class="col-4">
        <label class="form-label" for="email">E-mail</label>
        <div>
          <input type="email" name="email" id="email" value="${user.email}" class="form-control">
        </div>
    </div>
    <c:if test="${errores != null && !empty errores.email}">
    <div style="color:#df6882;">${errores.email}</div>
    </c:if>
  </div>
  <div class="row mb-2 mt-2">
    <div>
        <input class="btn btn-primary" type="submit" value="Sing In">
    </div>
  </div>
  <input type="hidden" name="id" value="${userId}">
</form>
<p><a href="${pageContext.request.getContextPath()}/index.jsp">Volver</a></p>

<jsp:include page="layout/footer.jsp" />