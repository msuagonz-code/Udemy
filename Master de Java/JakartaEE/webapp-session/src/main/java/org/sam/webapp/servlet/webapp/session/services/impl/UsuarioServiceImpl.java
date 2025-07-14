package org.sam.webapp.servlet.webapp.session.services.impl;

import org.sam.webapp.servlet.webapp.session.models.Usuario;
import org.sam.webapp.servlet.webapp.session.repositories.Impl.UsuarioRepositoryImpl;
import org.sam.webapp.servlet.webapp.session.repositories.UsuarioRepository;
import org.sam.webapp.servlet.webapp.session.services.ServiceJDBCException;
import org.sam.webapp.servlet.webapp.session.services.UsuarioService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(Connection connection) {
        this.usuarioRepository = new UsuarioRepositoryImpl(connection);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        try {
            return Optional.ofNullable(this.usuarioRepository.getByUsername(username));
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(this.usuarioRepository.getByUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Usuario> getAll() {
        try {
            return usuarioRepository.getAll();
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        try {
            return Optional.ofNullable(usuarioRepository.getById(id));
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void update(Usuario usuario) {
        try {
            usuarioRepository.update(usuario);
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            usuarioRepository.delete(id);
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

}
