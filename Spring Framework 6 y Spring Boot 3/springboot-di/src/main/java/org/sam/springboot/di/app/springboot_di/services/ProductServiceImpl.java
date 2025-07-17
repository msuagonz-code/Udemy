package org.sam.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.sam.springboot.di.app.springboot_di.models.Product;
import org.sam.springboot.di.app.springboot_di.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    /*
     * De esta manera @Autowired no es necesario
     */
    private ProductRepository repository;

    public ProductServiceImpl(@Qualifier("productList") ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll(){

        return repository.findAll().stream().map(p -> {
            Double priceTax = p.getPrice() * 1.25d;

            //Product newProduct = new Product(p.getId(), p.getName(), priceTax.longValue());

            /* Inmutable */
            //Product newProduct = (Product) p.clone();
            //newProduct.setPrice(priceTax.longValue());
            //return newProduct;

            /* Mutable */
            p.setPrice(priceTax.longValue());
            return p;

        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id){
        return repository.findById(id);
    }


}
