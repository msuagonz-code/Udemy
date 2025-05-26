package org.sam.webapp.jaxws.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.sam.webapp.jaxws.models.Curso;
import org.sam.webapp.jaxws.repositories.CursoRepository;

import java.util.List;

@RequestScoped
public class CursoRepositoryImpl implements CursoRepository {
    @Inject
    private EntityManager entityManager;


    @Override
    public List<Curso> listar() {
        return entityManager.createQuery("select c from Curso c", Curso.class).getResultList();
    }

    @Override
    public Curso guardar(Curso curso) {
        if(curso != null && curso.getId() != null && curso.getId() > 0){
            entityManager.merge(curso);
        }else{
            entityManager.persist(curso);
        }
        return curso;
    }
}
