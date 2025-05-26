package org.sam.springboot.backend.services;

import org.sam.springboot.backend.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Optional<Product> deleteById(Long id);
}
