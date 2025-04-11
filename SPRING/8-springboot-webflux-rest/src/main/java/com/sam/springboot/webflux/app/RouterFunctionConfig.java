package com.sam.springboot.webflux.app;

import com.sam.springboot.webflux.app.handlers.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterFunctionConfig {

    @Bean
    RouterFunction<ServerResponse> routes(ProductHandler handler){
        return route(GET("/products").or(GET("/api/v2/products")), handler::list)
                .andRoute(GET("/products/{id}"), handler::details)
                .andRoute(POST("/products"), handler::create)
                .andRoute(PUT("/products/{id}"), handler::update)
                .andRoute(DELETE("/products/{id}"), handler::remove);
    }
}
