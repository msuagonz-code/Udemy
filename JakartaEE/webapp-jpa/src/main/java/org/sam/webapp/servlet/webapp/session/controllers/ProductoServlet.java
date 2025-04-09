package org.sam.webapp.servlet.webapp.session.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sam.webapp.servlet.webapp.session.configs.ProductoServicePrincipal;
import org.sam.webapp.servlet.webapp.session.models.entities.Producto;
import org.sam.webapp.servlet.webapp.session.services.LoginService;
import org.sam.webapp.servlet.webapp.session.services.ProductoService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    @Inject
    @ProductoServicePrincipal
    private ProductoService productoService;

    @Inject
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Producto> productos = productoService.getAll();

        Optional<String> usernameOptional = loginService.getUsername(req);

        req.setAttribute("productos", productos);
        req.setAttribute("username", usernameOptional);
        req.setAttribute("title", req.getAttribute("title") + ": Listado productos");

        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
