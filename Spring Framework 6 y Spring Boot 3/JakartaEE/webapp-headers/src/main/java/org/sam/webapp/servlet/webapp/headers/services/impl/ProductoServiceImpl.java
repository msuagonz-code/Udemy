package org.sam.webapp.servlet.webapp.headers.services.impl;

import org.sam.webapp.servlet.webapp.headers.models.Producto;
import org.sam.webapp.servlet.webapp.headers.services.ProductoService;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService {

    @Override
    public List<Producto> listar() {
        return Arrays.asList(
                new Producto(1L, "notebook", "computacion", 75000),
                new Producto(2L, "mesa escritorio", "oficina", 100000),
                new Producto(3L, "teclado mecanico", "computacion", 40000)
        );
    }
}
