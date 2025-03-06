package org.sam.webapp.servlet.webapp.session.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.sam.webapp.servlet.webapp.session.models.Carro;

@WebListener
public class ApplicationListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        servletContext.log("Inicializando la aplicación!");
        servletContext.setAttribute("mensaje", "Valor para el context");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Destruyendo la aplicación!");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Inicializando el Request!");
        sre.getServletRequest().setAttribute("mensaje", "Valor para el request");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Destruyendo la Request!");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Inicializando la Sesion HTTP");
        Carro carro = new Carro();
        HttpSession session = se.getSession();
        session.setAttribute("carro", carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Destruyendo la Sesion HTTP");
    }
}
