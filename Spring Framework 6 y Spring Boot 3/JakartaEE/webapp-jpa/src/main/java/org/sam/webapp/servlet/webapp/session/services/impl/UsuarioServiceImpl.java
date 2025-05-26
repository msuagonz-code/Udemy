package org.sam.webapp.servlet.webapp.session.services.impl;

import jakarta.inject.Inject;
import org.sam.webapp.servlet.webapp.session.configs.Service;
import org.sam.webapp.servlet.webapp.session.interceptors.TransactionalJPA;
import org.sam.webapp.servlet.webapp.session.models.entities.Usuario;
import org.sam.webapp.servlet.webapp.session.repositories.RepositoryJpa;
import org.sam.webapp.servlet.webapp.session.repositories.UsuarioRepository;
import org.sam.webapp.servlet.webapp.session.services.ServiceJDBCException;
import org.sam.webapp.servlet.webapp.session.services.UsuarioService;

import java.util.List;
import java.util.Optional;

@Service
@TransactionalJPA
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Inject
    public UsuarioServiceImpl(@RepositoryJpa UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        try {
            return Optional.ofNullable(this.usuarioRepository.getByUsername(username));
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(this.usuarioRepository.getByUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Usuario> getAll() {
        try {
            return usuarioRepository.getAll();
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        try {
            return Optional.ofNullable(usuarioRepository.getById(id));
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void update(Usuario usuario) {
        try {
            usuarioRepository.update(usuario);
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            usuarioRepository.delete(id);
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

}
