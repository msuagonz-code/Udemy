package org.sam.repositories;

import org.sam.models.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    void delete(Long id);
}
