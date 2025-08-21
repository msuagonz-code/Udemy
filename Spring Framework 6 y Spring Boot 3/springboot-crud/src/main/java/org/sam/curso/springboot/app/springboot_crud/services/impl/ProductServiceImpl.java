package org.sam.curso.springboot.app.springboot_crud.services.impl;

import java.util.List;
import java.util.Optional;

import org.sam.curso.springboot.app.springboot_crud.entities.Product;
import org.sam.curso.springboot.app.springboot_crud.repositories.ProductRepository;
import org.sam.curso.springboot.app.springboot_crud.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productOptional = repository.findById(id);
        if(productOptional.isPresent()){
            Product productDB = productOptional.orElseThrow();
            productDB.setSku(product.getSku());
            productDB.setName(product.getName());
            productDB.setPrice(product.getPrice());
            productDB.setDescription(product.getDescription());
            return Optional.of(repository.save(productDB));
        }
        return productOptional;
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        productOptional.ifPresent(productDB -> {
            repository.delete(productDB);
        });
        return productOptional;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsBySku(String sku) {
        return repository.existsBySku(sku);
    }

}
