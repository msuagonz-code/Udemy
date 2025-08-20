package org.sam.curso.springboot.app.springboot_crud.repositories;

import org.sam.curso.springboot.app.springboot_crud.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
