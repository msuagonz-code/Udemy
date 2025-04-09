<%@page contentType="text/html" pageEncoding="UTF-8" import="java.time.format.*"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="layout/header.jsp" />

  <!--
    NO COMBINES IDIOMAS!, los nombres de las variables por convención ponlas en 1 solo idioma, no te hagas el poliglota
    esto lo he visto mucho, los miembros de las clases los declaran en un idioma, y en otro sitio, cambian el nombre a ingles o a español
    o peor, no ponen un nombre completo, ejemplo: c en vez de categoria, cd en vez de categoria_deportiva o categoriaDeportiva, no se porque
    cuesta tanto entender esto, no se porque cuesta tanto, que sigan un solo estandar
  -->
  <h3>${title}</h3>
  <form action="${pageContext.request.contextPath}/productos/form" method="post">
    <div class="row mb-2">
      <label for="name" class="col-form-label col-sm-2">Nombre</label>
      <div class="col-sm-4">
      <!--
        Esto es lo que no se debe hacer, productos tiene un miembro nombre, pero se envía un name
        el que no haya revisado el codigo del formulario, esperaría un nombre, no un name!, hijo de tu poliglota madre
        usa un solo idioma, me da igual cual sea, si quieres usar portugues peorque es tu parafilia usalo, pero solo uno!
      -->
        <input type="text" name="name" id="name" value="${producto.nombre}" class="form-control">
      </div>
      <c:if test="${errores != null && errores.containsKey('name')}">
      <div style="color:#df6882;">${errores.name}</div>
      </c:if>
    </div>
    <div class="row mb-2">
      <label for="price" class="col-form-label col-sm-2">Precio</label>
      <div class="col-sm-4">
      <!-- Recuerda: los tipos primitivos como int, no pueden ser null -->
        <input type="number" name="price" id="price" value="${producto.precio > 0 ? producto.precio : "" }" class="form-control">
      </div>
      <c:if test="${errores != null && !empty errores.price}">
      <div style="color:#df6882;">${errores.price}</div>
      </c:if>
    </div>
    <div class="row mb-2">
      <label for="sku" class="col-form-label col-sm-2">SKU</label>
      <div class="col-sm-4">
        <input type="text" name="sku" id="sku" value="${producto.sku}" class="form-control">
      </div>
      <c:if test="${errores != null && not empty errores.sku}">
      <div style="color:#df6882;">${errores.sku}</div>
      </c:if>
    </div>
    <div class="row mb-2">
      <label for="date" class="col-form-label col-sm-2">Fecha Registro</label>
      <div class="col-sm-4">
      <!--
        Un poco más de lo mismo, pero es que esto es a lo que quería llegar, producto tiene una variable fechaRegistro
        el formulario, envía un date, no solo no usas el mismo nombre sino que además ES UNA MALDITA PALABRA RESERVADA!
      -->
        <input class="form-control" type="date" name="date" id="date" value="${producto.fechaRegistro != null ? producto.fechaRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}" class="form-control">
      </div>
      <c:if test="${errores != null && !empty errores.date}">
      <div style="color:#df6882;">${errores.date}</div>
      </c:if>
    </div>
    <div class="row mb-2">
      <label for="category" class="col-form-label col-sm-2">Categoria</label>
      <div class="col-sm-4">
        <select name="category" id="category" class="form-select">
          <option value="">------ Seleccionar ------</option>
          <c:forEach items="${categorias}" var="categoria">
          <option value="${categoria.id}" ${categoria.id.equals(producto.categoria.id) ? "selected" : ""} >${categoria.name}</option>
          </c:forEach>
        </select>
      </div>
      <c:if test="${errores != null && not empty errores.category}">
      <div style="color:#df6882;">${errores.category}</div>
      </c:if>
    </div>
    <div class="row mb-2">
        <div>
            <input class="btn btn-primary" type="submit" value="${producto.id != null && producto.id > 0 ? "Editar" : "Crear" }">
        </div>
    </div>
    <input type="hidden" name="id" value="${producto.id}">
  </form>

<jsp:include page="layout/footer.jsp" />
<!-- PD: Me he desahogado un poco aquí, espero me entiendan tenía que soltarlo -->