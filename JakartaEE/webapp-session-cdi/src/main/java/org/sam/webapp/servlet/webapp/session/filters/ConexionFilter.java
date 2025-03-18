package org.sam.webapp.servlet.webapp.session.filters;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.sam.webapp.servlet.webapp.session.configs.OracleConn;
import org.sam.webapp.servlet.webapp.session.services.ServiceJDBCException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter("/*")
public class ConexionFilter implements Filter {

    @Inject
    private Logger log;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            try {
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (ServiceJDBCException e) {
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                log.info(e.getMessage());
            }
    }
}
