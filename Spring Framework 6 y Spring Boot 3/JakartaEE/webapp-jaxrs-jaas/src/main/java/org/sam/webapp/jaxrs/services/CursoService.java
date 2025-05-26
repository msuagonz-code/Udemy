package org.sam.webapp.jaxrs.services;

import jakarta.ejb.Local;
import org.sam.webapp.jaxrs.models.Curso;

import java.util.List;
import java.util.Optional;

@Local
public interface CursoService {
    List<Curso> listar();
    Curso guardar(Curso curso);
    Optional<Curso> porId(Long id);
    void eliminar(Long id);
}
