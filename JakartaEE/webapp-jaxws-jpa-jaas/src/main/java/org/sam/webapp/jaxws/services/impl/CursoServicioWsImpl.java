package org.sam.webapp.jaxws.services.impl;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.sam.webapp.jaxws.models.Curso;
import org.sam.webapp.jaxws.repositories.CursoRepository;
import org.sam.webapp.jaxws.services.CursoServicioWs;

import java.util.Arrays;
import java.util.List;

@DeclareRoles({"USER", "ADMIN"})
@Stateless
@WebService(endpointInterface = "org.sam.webapp.jaxws.services.CursoServicioWs")
public class CursoServicioWsImpl implements CursoServicioWs {

    @Inject
    private CursoRepository repository;

    @Override
    @WebMethod
    @RolesAllowed({"ADMIN", "USER"})
    public List<Curso> listar() {
        return repository.listar();
    }

    @Override
    @WebMethod
    @RolesAllowed({"ADMIN"})
    public Curso guardar(Curso curso) {
        return repository.guardar(curso);
    }
}


