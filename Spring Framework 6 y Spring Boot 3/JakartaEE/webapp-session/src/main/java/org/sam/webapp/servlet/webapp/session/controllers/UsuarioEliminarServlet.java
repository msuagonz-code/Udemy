package org.sam.webapp.servlet.webapp.session.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sam.webapp.servlet.webapp.session.models.Producto;
import org.sam.webapp.servlet.webapp.session.models.Usuario;
import org.sam.webapp.servlet.webapp.session.services.UsuarioService;
import org.sam.webapp.servlet.webapp.session.services.impl.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/usuarios/eliminar")
public class UsuarioEliminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("connection");
        UsuarioService usuarioService = new UsuarioServiceImpl(connection);

        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        // El id 1 corresponde al admin
        if(id > 1){
            Optional<Usuario> usuarioOptional = usuarioService.findById(id);
            if(usuarioOptional.isPresent()){
                usuarioService.delete(id);
                resp.sendRedirect(req.getContextPath() + "/usuarios/lista");
            }else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el usuario en la Base de datos");
            }
        }else{
            //Id de usuario no valido
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error el Id: " + id +" no es válido");
        }
    }
}
