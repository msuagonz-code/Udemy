package org.sam.webapp.jaxrs.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.sam.webapp.jaxrs.models.Curso;
import org.sam.webapp.jaxrs.repositories.CursoRepository;

import java.util.List;

@RequestScoped
public class CursoRepositoryImpl implements CursoRepository {
    @Inject
    private EntityManager entityManager;


    @Override
    public List<Curso> listar() {
        return entityManager.createQuery("select c from Curso c left outer join fetch c.instructor", Curso.class)
                .getResultList();
    }

    @Override
    public Curso guardar(Curso curso) {
        if(curso.getId() != null && curso.getId() > 0){
            entityManager.merge(curso);
        }else{
            entityManager.persist(curso);
        }
        return curso;
    }

    @Override
    public Curso porId(Long id) {
        return entityManager.createQuery("select c from Curso c left outer join c.instructor where c.id = :id", Curso.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void Eliminar(Long id) {
        Curso curso = porId(id);
        entityManager.remove(curso);
    }

}
