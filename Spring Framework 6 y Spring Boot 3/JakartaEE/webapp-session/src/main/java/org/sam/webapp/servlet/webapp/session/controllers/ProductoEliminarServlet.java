package org.sam.webapp.servlet.webapp.session.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sam.webapp.servlet.webapp.session.models.Producto;
import org.sam.webapp.servlet.webapp.session.services.ProductoService;
import org.sam.webapp.servlet.webapp.session.services.impl.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        ProductoService service = new ProductoServiceJdbcImpl(connection);

        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        if(id > 0){
            Optional<Producto> producto = service.findById(id);
            if(producto.isPresent()){
                service.delete(id);
                resp.sendRedirect(req.getContextPath() + "/productos");
            }else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el producto en la Base de datos");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error el Id es requerido");
        }
    }
}
