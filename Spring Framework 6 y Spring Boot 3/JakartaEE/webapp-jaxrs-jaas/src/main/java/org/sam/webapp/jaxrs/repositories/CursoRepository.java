package org.sam.webapp.jaxrs.repositories;

import org.sam.webapp.jaxrs.models.Curso;

import java.util.List;

public interface CursoRepository {
    List<Curso> listar();
    Curso guardar(Curso curso);
    Curso porId(Long id);
    void Eliminar(Long id);
}
