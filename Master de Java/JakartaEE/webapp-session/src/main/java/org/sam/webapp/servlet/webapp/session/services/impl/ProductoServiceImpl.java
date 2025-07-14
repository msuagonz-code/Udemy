package org.sam.webapp.servlet.webapp.session.services.impl;

import org.sam.webapp.servlet.webapp.session.models.Categoria;
import org.sam.webapp.servlet.webapp.session.models.Producto;
import org.sam.webapp.servlet.webapp.session.services.ProductoService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService {

    @Override
    public List<Producto> getAll() {
        return Arrays.asList(
                new Producto(1L, "notebook", "computacion", 75000),
                new Producto(2L, "mesa escritorio", "oficina", 100000),
                new Producto(3L, "teclado mecanico", "computacion", 40000)
        );
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return getAll().stream().filter(p -> p.getId().equals(id)).findAny();
    }

    @Override
    public void update(Producto producto){

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Categoria> getAllCategoria() {
        return List.of();
    }

    @Override
    public Optional<Categoria> findCategoriaById(Long id) {
        return Optional.empty();
    }
}
