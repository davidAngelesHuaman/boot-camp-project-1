package com.nttdata.bootcamp.msproduct.aplication;

import com.nttdata.bootcamp.msproduct.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;
    WebClient clientPersistence;

    @Autowired
    public ProductServiceImpl(WebClient.Builder builder) {
        this.clientPersistence = builder.baseUrl("http://ms-persistence/").build();
    }

    @Override
    public Mono<Product> createProduct(Mono<Product> productMono) {
        return clientPersistence.post()
                .uri("product/")
                .body(productMono, Product.class)
                .retrieve()
                .bodyToMono(Product.class)
                .transform(it -> reactiveCircuitBreakerFactory.create("product-service").run(it, throwable -> Mono.just(new Product())));
    }

    @Override
    public Flux<Product> listAll() {
        return clientPersistence.get()
                .uri("product/get")
                .retrieve()
                .bodyToFlux(Product.class)
                .transform(it -> reactiveCircuitBreakerFactory.create("product-service").run(it, throwable -> Flux.just(new Product())));
    }

    @Override
    public Mono<Product> listProductId(Integer id) {
        return clientPersistence.get()
                .uri("product/get/{id}", id)
                .retrieve()
                .bodyToMono(Product.class)
                .transform(it -> reactiveCircuitBreakerFactory.create("product-service").run(it, throwable -> Mono.just(new Product())));
    }

    @Override
    public Mono<Void> deleteProduct(Integer id) {
       return clientPersistence.delete()
                .uri("product/delete/{id}", id)
               .retrieve()
               .bodyToMono(Void.class)
               .transform(it -> reactiveCircuitBreakerFactory.create("product-service").run(it, throwable -> Mono.empty()));
    }

}
