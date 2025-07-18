package org.sam.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.sam.springboot.di.app.springboot_di.models.Product;
import org.sam.springboot.di.app.springboot_di.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    /*
     * De esta manera @Autowired no es necesario
     */
    private ProductRepository repository;
    private Environment environment;

    public ProductServiceImpl(@Qualifier("productList") ProductRepository repository , Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }

    @Override
    public List<Product> findAll(){

        return repository.findAll().stream().map(p -> {
            Double priceTax = p.getPrice() * environment.getProperty("config.price.tax", Double.class);

            /* Inmutable */
            Product newProduct = (Product) p.clone();
            newProduct.setPrice(priceTax.longValue());
            return newProduct;

        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id){
        return repository.findById(id);
    }


}
