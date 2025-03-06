package org.sam.webapp.servlet.webapp.session.services;

import org.sam.webapp.servlet.webapp.session.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> findById(Long id);
}
