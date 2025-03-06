package org.sam.webapp.servlet.webapp.session.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sam.webapp.servlet.webapp.session.models.Producto;
import org.sam.webapp.servlet.webapp.session.services.LoginService;
import org.sam.webapp.servlet.webapp.session.services.ProductoService;
import org.sam.webapp.servlet.webapp.session.services.impl.LoginServiceCookieImpl;
import org.sam.webapp.servlet.webapp.session.services.impl.LoginServiceSessionImpl;
import org.sam.webapp.servlet.webapp.session.services.impl.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductoService productoService = new ProductoServiceImpl();
        List<Producto> productos = productoService.listar();

        LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = loginService.getUsername(req);

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=<\"en\">");
            out.println("<head>");
            out.println("     <meta charset=<\"UTF-8\">");
            out.println("     <title>Listado de productos</title>");
            out.println("</head>");
            out.println("     <body>");
            out.println("         <h1>Listado de productos</h1>");
            if(usernameOptional.isPresent()) {
            out.println("         <div style='color: #53bdfa;'>Hola "+ usernameOptional.get() +" Bienvenido</div>");
            }
            out.println("         <table>");
            out.println("         <tr>");
            out.println("             <th>id</th>");
            out.println("             <th>nombre</th>");
            out.println("             <th>tipo</th>");
            if(usernameOptional.isPresent()) {
                out.println("             <th>precio</th>");
                out.println("             <th>agregar</th>");
            }
            out.println("         </tr>");
            productos.forEach(producto ->{
                out.println("         <tr>");
                out.println("         <td>"+producto.getId()+"</td>");
                out.println("         <td>"+producto.getNombre()+"</td>");
                out.println("         <td>"+producto.getTipo()+"</td>");
                if(usernameOptional.isPresent()) {
                    out.println("         <td>" + producto.getPrecio() + "</td>");
                    out.println("         <td><a href=\""+
                            req.getContextPath()
                            +"/agregar-carro?id=" +producto.getId()
                            +"\">Agregar al carro</a></td>");
                }
                out.println("         </tr>");
            });
            out.println("         </table>");
            out.println("     </body>");
            out.println("</html>");
        }
    }
}
