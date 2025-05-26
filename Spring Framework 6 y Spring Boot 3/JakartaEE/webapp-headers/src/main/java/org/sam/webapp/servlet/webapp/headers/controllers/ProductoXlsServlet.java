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
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/productos.xls", "/productos.html", "/productos"})
public class ProductoXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductoService productoService = new ProductoServiceImpl();
        List<Producto> productos = productoService.listar();

        resp.setContentType("text/html;charset=UTF-8");
        String servletPath = req.getServletPath();
        boolean isXLS = servletPath.endsWith(".xls");

        if (isXLS) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=productos.xls");
        }

        try (PrintWriter out = resp.getWriter()) {
            if(!isXLS) {
                out.println("<!DOCTYPE html>");
                out.println("<html lang=<\"en\">");
                out.println("<head>");
                out.println("     <meta charset=<\"UTF-8\">");
                out.println("     <title>Listado de productos</title>");
                out.println("</head>");
                out.println("     <body>");
                out.println("         <h1>Listado de productos</h1>");
                out.println("         <p><a href='" + req.getContextPath() + "/productos.xls" + "'>Exportar a xls</a></p>");
                out.println("         <p><a href='" + req.getContextPath() + "/productos.json" + "'>Mostrar json</a></p>");
            }
            out.println("         <table>");
            out.println("         <tr>");
            out.println("             <th>id</th>");
            out.println("             <th>nombre</th>");
            out.println("             <th>tipo</th>");
            out.println("             <th>precio</th>");
            out.println("         </tr>");
            productos.forEach(producto ->{
                out.println("         <tr>");
                out.println("         <td>"+producto.getId()+"</td>");
                out.println("         <td>"+producto.getNombre()+"</td>");
                out.println("         <td>"+producto.getTipo()+"</td>");
                out.println("         <td>"+producto.getPrecio()+"</td>");
                out.println("         </tr>");
            });

            if(!isXLS) {
                out.println("         </table>");
                out.println("     </body>");
                out.println("</html>");
            }
        }
    }
}
