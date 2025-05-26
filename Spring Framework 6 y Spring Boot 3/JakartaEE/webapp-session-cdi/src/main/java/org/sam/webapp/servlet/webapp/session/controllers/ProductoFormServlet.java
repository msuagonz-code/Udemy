package org.sam.webapp.servlet.webapp.session.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sam.webapp.servlet.webapp.session.configs.ProductoServicePrincipal;
import org.sam.webapp.servlet.webapp.session.models.Categoria;
import org.sam.webapp.servlet.webapp.session.models.Producto;
import org.sam.webapp.servlet.webapp.session.services.ProductoService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {

    @Inject
    @ProductoServicePrincipal
    private ProductoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        if(id > 0){
            Optional<Producto> productoOptional = service.findById(id);
            if(productoOptional.isPresent()){
                producto = productoOptional.get();
            }
        }

        req.setAttribute("categorias", service.getAllCategoria());
        req.setAttribute("producto", producto);
        req.setAttribute("title", req.getAttribute("title") + ": Formilario productos");

        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");

        Integer price;
        try {
            price = Integer.valueOf(req.getParameter("price"));
        } catch (NumberFormatException e) {
            price = 0;
        }

        String sku = req.getParameter("sku");
        String dateStr = req.getParameter("date");

        Long categoryId;

        try{
            categoryId = Long.valueOf(req.getParameter("category"));
        } catch (NumberFormatException e) {
            categoryId = 0L;
        }

        Map<String, String> errores = new HashMap<>();
        if(name == null || name.isBlank()){
            errores.put("name", "El nombre es requerido");
        }

        if(sku == null || sku.isBlank()){
            errores.put("sku", "El SKU es requerido!");
        }else if(sku.length() > 10){
            errores.put("sku", "El SKU debe tener max 10 caracteres!");
        }

        LocalDate fecha = null;
        if(dateStr == null || dateStr.isBlank()){
            errores.put("date", "La fecha es requerida!");
        }else{
            fecha = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        if(price.equals(0)){
            errores.put("price", "El precio es requerido");
        }

        if(categoryId.equals(0L)){
            errores.put("category", "La categoría es requerida!");
        }

        Long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = null;
        }

        Categoria categoria = new Categoria();
        categoria.setId(categoryId);

        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(name);
        producto.setPrecio(price);
        producto.setSku(sku);
        producto.setFechaRegistro(fecha);
        producto.setCategoria(categoria);

        if(errores.isEmpty()){
            service.update(producto);
            resp.sendRedirect(req.getContextPath()+"/productos");
        }else{
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", service.getAllCategoria());
            req.setAttribute("producto", producto);
            req.setAttribute("title", req.getAttribute("title") + ": Formilario productos");
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }

    }
}
