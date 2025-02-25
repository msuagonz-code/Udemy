package org.sam.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/parametros/url-get")
public class ParametrosGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String saludo = req.getParameter("saludo");
        out.println("<!DOCTYPE html>");
        out.println("<html lang=<\"en\">");
        out.println("<head>");
        out.println("     <meta charset=<\"UTF-8\">");
        out.println("     <title>Hola mundo url-get</title>");
        out.println("</head>");
        out.println("     <body>");
        out.println("         <h1>Hola "+ saludo +"</h1>");
        out.println("     </body>");
        out.println("</html>");
        out.close();
    }
}
