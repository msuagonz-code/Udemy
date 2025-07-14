package org.sam.webapp.servlet.webapp.session.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.sam.webapp.servlet.webapp.session.models.entities.Usuario;
import org.sam.webapp.servlet.webapp.session.services.UsuarioService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/usuarios/edit")
public class UsuarioEditarServlet extends HttpServlet {

    @Inject
    private UsuarioService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("action", "usuarios/edit");
        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        if(id > 0){
            Optional<Usuario> user = service.findById(id);
            if(user.isPresent()){
                HttpSession session = req.getSession();
                session.setAttribute("userId", user.get().getId());
                req.setAttribute("user", user.get());
            }
        req.setAttribute("title", req.getAttribute("title") + ": Mi perfil");
        getServletContext().getRequestDispatcher("/sing-in.jsp").forward(req, resp);
        }else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error el Id no es correcto");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        long id;
        try{
            id = Long.parseLong(session.getAttribute("userId").toString());
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Usuario user = new Usuario();
        String username = (String) req.getParameter("username");
        String password = (String) req.getParameter("password");
        String email = (String) req.getParameter("email");

        Map<String, String> errores = new HashMap<>();

        if(username == null || username.isBlank()){
            errores.put("username", "El Username es requerido!");
        }else{
            user.setUsername(username);
        }

        if(password == null || password.isBlank()){
            errores.put("password", "El Password es requerido!");
        }else{
            user.setPassword(password);
        }

        if(email == null || email.isBlank()){
            errores.put("email", "El E-mail es requerido!");
        }else{
            user.setEmail(email);
        }

        if(id > 0){
            Optional<Usuario> userOptional = service.findById(id);
                if(userOptional.isPresent()){
                    user.setId(id);
                    service.update(user);
                    Optional<Usuario> nuevoUsuario = service.findById(id);

                    session.setAttribute("userId", nuevoUsuario.get().getId());
                    req.setAttribute("user", nuevoUsuario.get());
                }
            req.setAttribute("title", req.getAttribute("title") + ": Mi perfil Editado");
            getServletContext().getRequestDispatcher("/sing-in.jsp").forward(req, resp);
        }else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error el Id no es correcto");
        }

    }
}
