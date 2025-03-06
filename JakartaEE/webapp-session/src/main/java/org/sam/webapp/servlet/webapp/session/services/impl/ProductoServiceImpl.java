package org.sam.webapp.servlet.webapp.session.services.impl;

import org.sam.webapp.servlet.webapp.session.models.Producto;
import org.sam.webapp.servlet.webapp.session.services.ProductoService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService {

    @Override
    public List<Producto> listar() {
        return Arrays.asList(
                new Producto(1L, "notebook", "computacion", 75000),
                new Producto(2L, "mesa escritorio", "oficina", 100000),
                new Producto(3L, "teclado mecanico", "computacion", 40000)
        );
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return listar().stream().filter(p -> p.getId().equals(id)).findAny();
    }
}
