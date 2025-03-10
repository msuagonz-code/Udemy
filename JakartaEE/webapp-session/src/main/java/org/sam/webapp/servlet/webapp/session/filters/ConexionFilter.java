package org.sam.webapp.servlet.webapp.session.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.sam.webapp.servlet.webapp.session.services.ServiceJDBCException;
import org.sam.webapp.servlet.webapp.session.utils.BasicDataSourceProvider;
import org.sam.webapp.servlet.webapp.session.utils.ConexionDB;
import org.sam.webapp.servlet.webapp.session.utils.DataSourceProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        DataSourceProvider provider = new BasicDataSourceProvider();
        ConexionDB pool = new ConexionDB(provider);

        try(Connection connection = pool.getConnection()) {
            if(connection.getAutoCommit()){
                connection.setAutoCommit(false);
            }

            try {
                servletRequest.setAttribute("connection", connection);
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (ServiceJDBCException e) {
                connection.rollback();
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
