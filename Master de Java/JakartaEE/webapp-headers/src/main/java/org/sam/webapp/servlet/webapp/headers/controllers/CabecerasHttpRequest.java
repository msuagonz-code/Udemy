package org.sam.webapp.servlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabecerasHttpRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        String metodoHttp = req.getMethod();
        String requestUri = req.getRequestURI();
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ipCliente = req.getRemoteAddr();
        String ip = req.getLocalAddr();
        String localPort = String.valueOf(req.getLocalPort());
        String scheme = req.getScheme();
        String host = req.getHeader("host");

        //String urlCompleta = scheme+"://"+host+contextPath+servletPath;
        String urlCompleta = scheme+"://"+host+requestUri;

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=<\"en\">");
            out.println("<head>");
            out.println("     <meta charset=<\"UTF-8\">");
            out.println("     <title>Cabeceras HTTP Request</title>");
            out.println("</head>");
            out.println("     <body>");
            out.println("         <h1>Cabeceras HTTP Request</h1>");
            out.println("         <ul>");
            out.println("             <li>Method HTTP: "+metodoHttp+"</li>");
            out.println("             <li>Request URI: "+requestUri+"</li>");
            out.println("             <li>Request URL: "+requestUrl+"</li>");
            out.println("             <li>Context Path: "+contextPath+"</li>");
            out.println("             <li>Servlet Path: "+servletPath+"</li>");
            out.println("             <li>IP Address: "+ip+"</li>");
            out.println("             <li>IP Client Address: "+ipCliente+"</li>");
            out.println("             <li>Local Port: "+localPort+"</li>");
            out.println("             <li>Scheme: "+scheme+"</li>");
            out.println("             <li>Host: "+host+"</li>");
            out.println("             <li>urlCompleta: "+urlCompleta+"</li>");

            out.println("             <br/>");

            Enumeration<String> headerNames = req.getHeaderNames();
            while(headerNames.hasMoreElements()){
                String header = headerNames.nextElement();
                out.println("             <li>"+header+": "+ req.getHeader(header) +"</li>");
            }

            out.println("         </ul>");
            out.println("     </body>");
            out.println("</html>");
        }
    }
}