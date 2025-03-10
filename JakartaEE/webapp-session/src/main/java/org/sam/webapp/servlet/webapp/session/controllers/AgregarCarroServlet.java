package org.sam.webapp.servlet.webapp.session.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.sam.webapp.servlet.webapp.session.models.Carro;
import org.sam.webapp.servlet.webapp.session.models.ItemCarro;
import org.sam.webapp.servlet.webapp.session.models.Producto;
import org.sam.webapp.servlet.webapp.session.services.ProductoService;
import org.sam.webapp.servlet.webapp.session.services.impl.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Connection connection = (Connection) req.getAttribute("connection");
        ProductoService productoService = new ProductoServiceJdbcImpl(connection);
        Optional<Producto> producto = productoService.findById(id);

        if(producto.isPresent()){
            ItemCarro item = new ItemCarro(1, producto.get());
            HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");
            carro.addItem(item);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
