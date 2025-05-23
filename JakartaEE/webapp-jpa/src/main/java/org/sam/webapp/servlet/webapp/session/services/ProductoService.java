package org.sam.webapp.servlet.webapp.session.services;

import org.sam.webapp.servlet.webapp.session.models.entities.Categoria;
import org.sam.webapp.servlet.webapp.session.models.entities.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> getAll();

    Optional<Producto> findById(Long id);

    void update(Producto producto);

    void delete(Long id);

    List<Categoria> getAllCategoria();

    Optional<Categoria> findCategoriaById(Long id);
}
