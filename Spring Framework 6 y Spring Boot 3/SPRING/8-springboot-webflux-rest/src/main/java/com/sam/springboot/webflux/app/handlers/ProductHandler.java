package com.sam.springboot.webflux.app.handlers;

import com.sam.springboot.webflux.app.models.Product;
import com.sam.springboot.webflux.app.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;

@Component
public class ProductHandler {

    private final ProductService service;
    private final Validator validator;

    public ProductHandler(ProductService service, Validator validator) {
        this.service = service;
        this.validator = validator;
    }

    public Mono<ServerResponse> list(ServerRequest request){
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Product.class);
    }

    public Mono<ServerResponse> details(ServerRequest request){
        String id = request.pathVariable("id");
        return service.findById(id).flatMap( product -> {
            return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    //.bodyValue(product)
                    .body(BodyInserters.fromValue(product))
                    .switchIfEmpty(ServerResponse.notFound().build());
        });
    }

    public Mono<ServerResponse> create(ServerRequest request){

        Mono<Product> productMono = request.bodyToMono(Product.class);

        return productMono.flatMap((product) -> {

            Errors errors = new BeanPropertyBindingResult(product, Product.class.getName());
            validator.validate(product, errors);

            if(errors.hasFieldErrors()){
                return Flux.fromIterable(errors.getFieldErrors())
                        .map(fieldError -> "El campo ".concat(fieldError.getField()).concat(" ").concat(fieldError.getDefaultMessage()))
                        .collectList()
                        .flatMap( list-> ServerResponse.badRequest().bodyValue(list));
            }

            product.setCreateAt(LocalDateTime.now());
            return service.save(product).flatMap(productDB -> ServerResponse.created(URI.create("/products/".concat(productDB.getId())))
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(productDB));
        });
    }

    public Mono<ServerResponse> update(ServerRequest request){
        Mono<Product> productMono = request.bodyToMono(Product.class);
        String id = request.pathVariable("id");
        Mono<Product> productDB = service.findById(id);

        return productDB.zipWith(productMono, (db, req) ->{
                    db.setName(req.getName());
                    db.setPrice(req.getPrice());
                    db.setCategory(req.getCategory());
                    return db;
                })
                .flatMap(product ->
                        ServerResponse.created(URI.create("/products/".concat(product.getId())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(service.save(product), Product.class)
                                .switchIfEmpty(ServerResponse.notFound().build())
                );
    }

    public Mono<ServerResponse> remove(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Product> productMono = service.findById(id);

        return productMono.flatMap(productDB -> ServerResponse.status(HttpStatus.NO_CONTENT)
                .body(service.delete(productDB), Void.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
