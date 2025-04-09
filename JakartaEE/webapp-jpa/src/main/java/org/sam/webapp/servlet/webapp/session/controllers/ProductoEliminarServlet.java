package org.sam.webapp.servlet.webapp.session.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sam.webapp.servlet.webapp.session.configs.ProductoServicePrincipal;
import org.sam.webapp.servlet.webapp.session.models.entities.Producto;
import org.sam.webapp.servlet.webapp.session.services.ProductoService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {

    @Inject
    @ProductoServicePrincipal
    private ProductoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
