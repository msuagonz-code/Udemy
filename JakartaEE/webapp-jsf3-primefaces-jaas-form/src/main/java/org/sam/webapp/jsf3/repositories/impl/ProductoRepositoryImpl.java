package org.sam.webapp.jsf3.repositories.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.sam.webapp.jsf3.entities.Producto;
import org.sam.webapp.jsf3.repositories.CrudRepository;
import org.sam.webapp.jsf3.repositories.ProductoRepository;

import java.util.List;

@RequestScoped
public class ProductoRepositoryImpl implements ProductoRepository{

    @Inject
    private EntityManager entityManager;

    @Override
    public List<Producto> listar() {
        return entityManager.createQuery("select p from Producto p left outer join fetch p.categoria", Producto.class).getResultList();
    }

    @Override
    public Producto porId(Long id) {
        //return entityManager.find(Producto.class, id);
        return entityManager.createQuery("select p from Producto p left outer join fetch p.categoria where p.id = :id", Producto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void guardar(Producto producto) {
        if(producto.getId() != null && producto.getId() > 0){
            entityManager.merge(producto);
        }else {
            entityManager.persist(producto);
        }
    }

    @Override
    public void eliminar(Long id) {
        Producto producto = porId(id);
        entityManager.remove(producto);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        return entityManager.createQuery("select p from Producto p left outer join fetch p.categoria where p.nombre like :nombre", Producto.class)
                .setParameter("nombre", "%"+nombre+"%")
                .getResultList();
    }
}
