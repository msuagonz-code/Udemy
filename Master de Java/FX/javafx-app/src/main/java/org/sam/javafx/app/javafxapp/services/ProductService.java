package org.sam.javafx.app.javafxapp.services;

import org.sam.javafx.app.javafxapp.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product save(Product product);
    Product update(Product product);
    Product delete(Product product);

}
