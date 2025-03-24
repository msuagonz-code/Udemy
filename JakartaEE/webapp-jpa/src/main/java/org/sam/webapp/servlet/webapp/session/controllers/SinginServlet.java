package org.sam.webapp.servlet.webapp.session.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.sam.webapp.servlet.webapp.session.models.Usuario;
import org.sam.webapp.servlet.webapp.session.services.UsuarioService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/sing-in")
public class SinginServlet extends HttpServlet {

    @Inject
    private UsuarioService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", req.getAttribute("title") + ": Sing In");
        req.setAttribute("action", "sing-in");
        getServletContext().getRequestDispatcher("/sing-in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("action", "usuarios/sing-in");

        HttpSession session = req.getSession();

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

        if(errores.isEmpty()){
            Optional<Usuario> usuarioDB = service.findByUsername(username);
            Optional<Usuario> login = Optional.empty();

            if(usuarioDB.isEmpty()) {
                service.update(user);
                usuarioDB = service.findByUsername(username);
                login = service.login(usuarioDB.get().getUsername(), password);
            }else{
                errores.put("username", "El nombre de usuario ya existe");
                req.setAttribute("errores", errores);
                req.setAttribute("user", user);
                req.setAttribute("title", req.getAttribute("title") + ": Sing In");
                getServletContext().getRequestDispatcher("/sing-in.jsp").forward(req, resp);
            }

            if(login.isPresent()){
                session.setAttribute("userId", usuarioDB.get().getId());
                session.setAttribute("username", username);
                resp.sendRedirect(req.getContextPath()+"/productos");
            }else{
                req.setAttribute("errores", errores);
                req.setAttribute("user", user);
                req.setAttribute("title", req.getAttribute("title") + ": Sing In");
                getServletContext().getRequestDispatcher("/sing-in.jsp").forward(req, resp);
            }

        }else{
            req.setAttribute("errores", errores);
            req.setAttribute("user", user);
            req.setAttribute("title", req.getAttribute("title") + ": Sing In");
            getServletContext().getRequestDispatcher("/sing-in.jsp").forward(req, resp);
        }
    }
}
