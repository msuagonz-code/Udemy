package org.sam.webapp.servlet.webapp.session.services.impl;

import jakarta.inject.Inject;
import org.sam.webapp.servlet.webapp.session.configs.ProductoServicePrincipal;
import org.sam.webapp.servlet.webapp.session.configs.Service;
import org.sam.webapp.servlet.webapp.session.models.Categoria;
import org.sam.webapp.servlet.webapp.session.models.Producto;
import org.sam.webapp.servlet.webapp.session.repositories.CrudRepository;
import org.sam.webapp.servlet.webapp.session.services.ProductoService;
import org.sam.webapp.servlet.webapp.session.services.ServiceJDBCException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@ProductoServicePrincipal
public class ProductoServiceJdbcImpl implements ProductoService {

    @Inject
    private CrudRepository<Producto> productoRepository;

    @Inject
    private CrudRepository<Categoria> categoriaRepository;

    @Override
    public List<Producto> getAll() {
        try {
            return productoRepository.getAll();
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Producto> findById(Long id) {
        try {
            return Optional.ofNullable(productoRepository.getById(id));
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void update(Producto producto) {
        try {
            productoRepository.update(producto);
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id){
        try {
            productoRepository.delete(id);
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Categoria> getAllCategoria(){
        try {
            return categoriaRepository.getAll();
        } catch (SQLException e) {
            throw new ServiceJDBCException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Categoria> findCategoriaById(Long id){
        try {
            return Optional.ofNullable(categoriaRepository.getById(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
