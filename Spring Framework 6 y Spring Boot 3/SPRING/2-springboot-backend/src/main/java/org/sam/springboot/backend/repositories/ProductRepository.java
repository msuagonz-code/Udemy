package org.sam.springboot.backend.repositories;

import org.sam.springboot.backend.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
