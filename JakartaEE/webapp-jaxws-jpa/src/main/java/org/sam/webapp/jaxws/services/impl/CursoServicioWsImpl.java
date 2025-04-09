package org.sam.webapp.jaxws.services.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.sam.webapp.jaxws.models.Curso;
import org.sam.webapp.jaxws.repositories.CursoRepository;
import org.sam.webapp.jaxws.services.CursoServicioWs;

import java.util.Arrays;
import java.util.List;

@Stateless
@WebService(endpointInterface = "org.sam.webapp.jaxws.services.CursoServicioWs")
public class CursoServicioWsImpl implements CursoServicioWs {

    @Inject
    private CursoRepository repository;

    @Override
    @WebMethod
    public List<Curso> listar() {
        return repository.listar();
    }

    @Override
    @WebMethod
    public Curso guardar(Curso curso) {
        return repository.guardar(curso);
    }
}


