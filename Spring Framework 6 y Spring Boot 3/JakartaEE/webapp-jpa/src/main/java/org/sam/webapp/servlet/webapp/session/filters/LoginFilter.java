package org.sam.webapp.servlet.webapp.session.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.sam.webapp.servlet.webapp.session.services.LoginService;
import org.sam.webapp.servlet.webapp.session.services.impl.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/carro/*", "/productos/form", "/productos/eliminar", "/usuarios/lista", "/usuarios/edit", "/usuarios/eliminar"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) servletRequest);

        if (username.isPresent()) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "No estás autorizado para entrar a esta pagina");
        }
    }
}
