package org.sam.webapp.servlet.webapp.session.repositories.Impl;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.sam.webapp.servlet.webapp.session.configs.Repository;
import org.sam.webapp.servlet.webapp.session.models.entities.Producto;
import org.sam.webapp.servlet.webapp.session.repositories.CrudRepository;
import org.sam.webapp.servlet.webapp.session.repositories.RepositoryJpa;

import java.util.List;

@Repository
@RepositoryJpa
public class ProductoRepositoryJpaImpl implements CrudRepository<Producto> {

    @Inject
    private EntityManager entityManager;

    @Override
    public List<Producto> getAll() throws Exception {
        return entityManager.createQuery("from Producto", Producto.class).getResultList();
    }

    @Override
    public Producto getById(Long id) throws Exception {
        return entityManager.find(Producto.class, id);
    }

    @Override
    public void update(Producto producto) throws Exception {
        if(producto.getId() != null && producto.getId() > 0){
            entityManager.merge(producto);
        }else{
            entityManager.persist(producto);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Producto producto = getById(id);
        entityManager.remove(producto);
    }
}
