package org.sam.webapp.servlet.webapp.session.repositories.Impl;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.sam.webapp.servlet.webapp.session.configs.Repository;
import org.sam.webapp.servlet.webapp.session.models.entities.Categoria;
import org.sam.webapp.servlet.webapp.session.repositories.CrudRepository;
import org.sam.webapp.servlet.webapp.session.repositories.RepositoryJpa;

import java.util.List;

@Repository
@RepositoryJpa
public class CategoriaRepositoryJpaImpl implements CrudRepository<Categoria> {

    @Inject
    private EntityManager entityManager;

    @Override
    public List<Categoria> getAll() throws Exception {
        return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
    }

    @Override
    public Categoria getById(Long id) throws Exception {
        return entityManager.find(Categoria.class, id);
    }

    @Override
    public void update(Categoria categoria) throws Exception {
        if(categoria.getId() != null && categoria.getId() > 0){
            entityManager.merge(categoria);
        }else{
            entityManager.persist(categoria);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Categoria categoria = getById(id);
        entityManager.remove(categoria);
    }
}
