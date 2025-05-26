package org.sam.webappear.ear.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.sam.webappear.ear.entities.Usuario;
import org.sam.webappear.ear.repositories.UsuarioRepository;

import java.util.List;

@RequestScoped
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Inject
    private EntityManager entityManager;

    @Override
    public List<Usuario> listar() {
        return entityManager.createQuery("select u from Usuario u", Usuario.class).getResultList();
    }
}
