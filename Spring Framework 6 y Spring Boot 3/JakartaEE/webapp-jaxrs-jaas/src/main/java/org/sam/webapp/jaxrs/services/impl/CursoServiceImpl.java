package org.sam.webapp.jaxrs.services.impl;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.sam.webapp.jaxrs.models.Curso;
import org.sam.webapp.jaxrs.repositories.CursoRepository;
import org.sam.webapp.jaxrs.services.CursoService;

import java.util.List;
import java.util.Optional;

@Stateless
@DeclareRoles({"USER", "ADMIN"})
public class CursoServiceImpl implements CursoService {

    @Inject
    private CursoRepository repository;

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public List<Curso> listar() {
        return repository.listar();
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public Curso guardar(Curso curso) {
        return repository.guardar(curso);
    }

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public Optional<Curso> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void eliminar(Long id) {
        repository.Eliminar(id);
    }
}


