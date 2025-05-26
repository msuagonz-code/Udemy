package org.sam.webapp.servlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sam.webapp.servlet.webapp.headers.models.Producto;
import org.sam.webapp.servlet.webapp.headers.services.ProductoService;
import org.sam.webapp.servlet.webapp.headers.services.impl.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/buscar-producto")
public class BuscarProductoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImpl();
        String nombre = req.getParameter("producto");

        List<Producto> productos = service.listar();

        Optional<Producto> encontrado = productos.stream().filter(producto -> {
            boolean blank = nombre.isBlank();
            if(nombre == null || blank){
             return false;
         }
            return producto.getNombre().contains(nombre);
        }).findFirst();

        if (encontrado.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html lang=<\"en\">");
                out.println("<head>");
                out.println("     <meta charset=<\"UTF-8\">");
                out.println("     <title>Producto Encontrado</title>");
                out.println("</head>");
                out.println("     <body>");
                out.println("         <h1> Producto encontrado </h1>");
                out.println("         <h3> Nombre: "+ encontrado.get().getNombre() +"</h3>");
                out.println("         <h3> Precio: "+ encontrado.get().getPrecio() +"</h3>");
                out.println("     </body>");
                out.println("</html>");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Lo sentimos no se encontró el producto: "+ nombre);
        }

    }
}
