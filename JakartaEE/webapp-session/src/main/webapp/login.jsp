<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp" />

<h3>${title}</h3>
<form action="${pageContext.request.getContextPath()}/login" method="post">
  <div class="row mb-2 mt-2">
    <div class="col-4">
        <label class="form-label" for="username">Username</label>
        <div>
          <input type="text" name="username" id="username" class="form-control">
        </div>
    </div>
  </div>
  <div class="row mb-2 mt-2">
    <div class="col-4">
        <label class="form-label" for="password">Password</label>
        <div>
          <input type="password" name="password" id="password" class="form-control">
        </div>
    </div>
  </div>
  <div class="row mb-2 mt-2">
    <div>
        <input class="btn btn-primary" type="submit" value="Login">
    </div>
  </div>
</form>
<p><a href="${pageContext.request.getContextPath()}/index.jsp">Volver</a></p>

<jsp:include page="layout/footer.jsp" />