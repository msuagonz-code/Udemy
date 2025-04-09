package org.sam.webapp.servlet.webapp.session.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.sam.webapp.servlet.webapp.session.models.entities.Usuario;
import org.sam.webapp.servlet.webapp.session.services.LoginService;
import org.sam.webapp.servlet.webapp.session.services.UsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/usuarios/lista")
public class UsuariosServlet extends HttpServlet {

    @Inject
    private UsuarioService service;

    @Inject
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Usuario> usuarios = service.getAll();
        usuarios.removeIf(usuario -> "admin".equals(usuario.getUsername()));

        Optional<String> usernameOptional = loginService.getUsername(req);

        HttpSession session = req.getSession();
        boolean isAdmin = (boolean) session.getAttribute("isAdmin");

        if(usernameOptional.isPresent() && isAdmin){
            req.setAttribute("listaUsuarios", usuarios);
            req.setAttribute("username", usernameOptional);
            req.setAttribute("title", req.getAttribute("title") + ": Listado productos");
            getServletContext().getRequestDispatcher("/lista-usuarios.jsp").forward(req, resp);
        }else{
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Solo el admin puede ver esta página");
        }
    }
}
