package org.sam.webapp.servlet.webapp.session.services.impl;

import jakarta.inject.Inject;
import org.sam.webapp.servlet.webapp.session.configs.ProductoServicePrincipal;
import org.sam.webapp.servlet.webapp.session.configs.Service;
import org.sam.webapp.servlet.webapp.session.interceptors.TransactionalJPA;
import org.sam.webapp.servlet.webapp.session.models.entities.Categoria;
import org.sam.webapp.servlet.webapp.session.models.entities.Producto;
import org.sam.webapp.servlet.webapp.session.repositories.CrudRepository;
import org.sam.webapp.servlet.webapp.session.repositories.RepositoryJpa;
import org.sam.webapp.servlet.webapp.session.services.ProductoService;
import org.sam.webapp.servlet.webapp.session.services.ServiceJDBCException;

import java.util.List;
import java.util.Optional;

@Service
@ProductoServicePrincipal
@TransactionalJPA
public class ProductoServiceImpl implements ProductoService {

    @Inject
    @RepositoryJpa
    private CrudRepository<Producto> productoRepository;

    @Inject
    @RepositoryJpa
    private CrudRepository<Categoria> categoriaRepository;

    @Override
    public List<Producto> getAll() {
        try {
            return productoRepository.getAll();
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Producto> findById(Long id) {
        try {
            return Optional.ofNullable(productoRepository.getById(id));
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void update(Producto producto) {
        try {
            productoRepository.update(producto);
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id){
        try {
            productoRepository.delete(id);
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Categoria> getAllCategoria(){
        try {
            return categoriaRepository.getAll();
        } catch (Exception e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Categoria> findCategoriaById(Long id){
        try {
            return Optional.ofNullable(categoriaRepository.getById(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
