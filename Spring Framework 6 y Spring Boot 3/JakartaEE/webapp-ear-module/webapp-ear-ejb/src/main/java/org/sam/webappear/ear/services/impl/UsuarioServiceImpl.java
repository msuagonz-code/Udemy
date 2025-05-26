package org.sam.webappear.ear.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.sam.webappear.ear.entities.Usuario;
import org.sam.webappear.ear.repositories.UsuarioRepository;
import org.sam.webappear.ear.services.UsuarioService;

import java.util.List;

@Stateless
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    private UsuarioRepository repository;

    @Override
    public List<Usuario> listar() {
        return repository.listar();
    }
}
