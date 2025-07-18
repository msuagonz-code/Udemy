package org.sam.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.sam.springboot.di.app.springboot_di.models.Product;
import org.sam.springboot.di.app.springboot_di.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    /*
     * De esta manera @Autowired no es necesario
     */
    private ProductRepository repository;

    @Value("${config.price.tax}")
    private Double tax;

    public ProductServiceImpl(@Qualifier("productJson") ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll(){

        return repository.findAll().stream().map(p -> {
            Double priceTax = p.getPrice() * tax;

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
