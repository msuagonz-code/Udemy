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
import org.sam.webapp.servlet.webapp.session.services.impl.ProductoServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        ProductoService productoService = new ProductoServiceImpl();
        Optional<Producto> producto = productoService.findById(id);

        if(producto.isPresent()){
            ItemCarro item = new ItemCarro(1, producto.get());
            HttpSession session = req.getSession();
            Carro carro;

            // Verificamos si existe el objeto carro en la sesion
            if(session.getAttribute("carro") == null){
                carro = new Carro();
                session.setAttribute("carro", carro);
            } else{
                carro = (Carro) session.getAttribute("carro");
            }
            carro.addItem(item);
        }
        resp.sendRedirect(req.getContextPath() + "/ver-carro");
    }
}
