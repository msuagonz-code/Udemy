package org.sam.springboot.di.app.springboot_di;

import org.sam.springboot.di.app.springboot_di.repositories.ProductRepository;
import org.sam.springboot.di.app.springboot_di.repositories.ProductRepositoryJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("classpath:json/product.json")
    private Resource resource;

    @Bean("productJson")
    ProductRepository productRepositoryJson(){
        //return new ProductRepositoryJson();
        return new ProductRepositoryJson(resource);
    }

}
