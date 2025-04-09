package org.sam.webapp.servlet.webapp.session.services;

import org.sam.webapp.servlet.webapp.session.models.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> login(String username, String password);

    List<Usuario> getAll();

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByUsername(String username);

    void update(Usuario producto);

    void delete(Long id);

}
