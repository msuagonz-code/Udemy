package org.sam.webapp.ejb.controllers;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sam.webapp.ejb.models.Producto;
import org.sam.webapp.ejb.service.ServiceEjb;
import org.sam.webapp.ejb.service.ServiceEjbLocal;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet("/index")
public class EjemploServlet extends HttpServlet {

    //@EJB no funciona si se usa CDI @RequestScoped
    //@Inject // esto inyecta según el contexto del CDI @RequestScoped y @Stateful
    //private ServiceEjbLocal service;

    //@EJB es mejor usarlo con @Stateless
    //@Inject
    //private ServiceEjbLocal service2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServiceEjbLocal service = null;
        ServiceEjbLocal service2 = null;
        try {
            InitialContext ctx = new InitialContext();
            service = (ServiceEjbLocal) ctx.lookup("java:global/webapp-ejb/ServiceEjb!org.sam.webapp.ejb.service.ServiceEjbLocal");
            service2 = (ServiceEjbLocal) ctx.lookup("java:global/webapp-ejb/ServiceEjb!org.sam.webapp.ejb.service.ServiceEjbLocal");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Service si es igual al service2: " + service.equals(service2));

        Producto producto = service.crear(new Producto("Uvas"));
        System.out.println("Nuevo producto:" + producto);

        req.setAttribute("saludo", service.saludar("Manuel"));
        req.setAttribute("saludo2", service2.saludar("Stefany"));
        req.setAttribute("listado", service.listar());
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
