package com.sam.springboot.webflux.app;

import com.sam.springboot.webflux.app.models.Category;
import com.sam.springboot.webflux.app.models.Product;
import com.sam.springboot.webflux.app.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import javax.swing.*;
import java.time.LocalDateTime;

@SpringBootApplication
public class SpringbootWebfluxRestApplication implements CommandLineRunner {

	private final ProductService service;
	private final ReactiveMongoTemplate mongoTemplate;

	public SpringbootWebfluxRestApplication(ProductService service, ReactiveMongoTemplate mongoTemplate) {
		this.service = service;
		this.mongoTemplate = mongoTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebfluxRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.mongoTemplate.dropCollection("products");
		this.mongoTemplate.dropCollection("categories");

		Category electronics = new Category("Electronicos");
		Category sports = new Category("Deportes");
		Category computing = new Category("Computacion");
		Category forniture = new Category("Muebles");

		Flux.just(electronics, sports, computing, forniture)
				.flatMap(service::saveCategory)
				.doOnNext(System.out::println)
				.thenMany(
						Flux.just(
								new Product("TV Panasonic", 35.45D, electronics),
								new Product("Procesador i9", 700.32D, computing),
								new Product("msi sword 16HX B14V", 1300.32D, computing),
								new Product("Iphone Apple", 600.32D, computing),
								new Product("Zapatillas Adidas", 23.32D, sports),
								new Product("Pesas 12kg", 15.32D, sports),
								new Product("Mesa de madera", 71.05D, forniture)
						).flatMap(product -> {
							product.setCreateAt(LocalDateTime.now());
							return service.save(product);
						})
				).subscribe(product -> System.out.println("Insert :".concat(product.getId()).concat(" ").concat(product.getName())) );

	}
}
