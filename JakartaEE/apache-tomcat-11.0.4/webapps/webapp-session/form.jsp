<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, org.sam.webapp.servlet.webapp.session.models.*"%>
<%
List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario Producto</title>
</head>
<body>
  <h1>Formulario Producto</h1>
  <form action="<%=request.getContextPath()%>/productos/form" method="post">
    <div>
      <label for="name">Nombre</label>
      <div>
        <input type="text" name="name" id="name">
      </div>
    </div>
    <div>
      <label for="price">Precio</label>
      <div>
        <input type="number" name="price" id="price">
      </div>
    </div>
    <div>
      <label for="sku">SKU</label>
      <div>
        <input type="text" name="sku" id="sku">
      </div>
    </div>
    <div>
      <label for="date">Fecha Registro</label>
      <div>
        <input type="date" name="date" id="date">
      </div>
    </div>
    <div>
      <label for="categoria">Categoria</label>
      <div>
        <select name="categoria" id="categoria">
          <option value="">------ Seleccionar ------</option>
          <% for(Categoria categoria: categorias){ %>
          <option value="<%=categoria.getId()%>"><%=categoria.getName()%></option>
          <% } %>
        </select>
      </div>
    </div>
    <div>
      <input type="submit" value="Crear">
    </div>
  </form>
</body>
</html>