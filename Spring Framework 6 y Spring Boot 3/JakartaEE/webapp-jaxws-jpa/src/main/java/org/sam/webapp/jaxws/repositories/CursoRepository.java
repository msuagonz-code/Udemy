package org.sam.webapp.jaxws.repositories;

import org.sam.webapp.jaxws.models.Curso;

import java.util.List;

public interface CursoRepository {
    List<Curso> listar();
    Curso guardar(Curso curso);

}
