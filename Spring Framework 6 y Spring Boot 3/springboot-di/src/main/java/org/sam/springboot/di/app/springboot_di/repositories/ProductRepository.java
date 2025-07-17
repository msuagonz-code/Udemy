package org.sam.springboot.di.app.springboot_di.repositories;

import java.util.List;

import org.sam.springboot.di.app.springboot_di.models.Product;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(Long id);
}
