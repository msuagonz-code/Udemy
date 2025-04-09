<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="layout/header.jsp" />

  <h3>${title}</h3>
  <c:choose>
      <c:when test="${carro.items.isEmpty()}">
      <div class="alert alert-warning">Lo sentimos no hay productos en el carro de compras!</div>
      </c:when>
      <c:otherwise>
        <form name="formcarro" action="${pageContext.request.contextPath}/carro/actualizar" method="post">
              <table class="table table-hover table-striped">
              <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Tipo</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Total</th>
                <th>borrar</th>
              </tr>
              <c:forEach items="${carro.items}" var="item">
              <tr>
                <td>${item.producto.id}</td>
                <td>${item.producto.nombre}</td>
                <td>${item.producto.categoria.name}</td>
                <td>${item.producto.precio}</td>
                <td><input type="text" size="4" name="cant_${item.producto.id}" value="${item.cantidad}" /></td>
                <td>${item.importe}</td>
                <td><input type="checkbox" value="${item.producto.id}" name="deleteProductos" /></td>
              </tr>
              </c:forEach>
              <tr>
              <td colspan="6" style="text-align:right">Total:</td>
              <td style="text-align:right">${carro.total}</td>
              </tr>
              </table>
          <a class="btn btn-primary" href="javascript:document.formcarro.submit();">Actualizar</a>
        </form>
      </c:otherwise>
  </c:choose>
  <div class="my-2">
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/index.jsp">Vovler</a>
    <a class="btn btn-success" href="${pageContext.request.contextPath}/productos">Seguir comprando</a>
  </div>

<jsp:include page="layout/footer.jsp" />