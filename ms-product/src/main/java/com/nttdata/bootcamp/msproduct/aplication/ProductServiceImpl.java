package com.nttdata.bootcamp.msproduct.aplication;

import com.nttdata.bootcamp.msproduct.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ProductServiceImpl implements ProductService{

    WebClient client = WebClient.create("http://localhost:8081/product/");

    @Override
    public Mono<Product> createProduct(Mono<Product> productMono) {
        return client.post()
                .uri("/")
                .body(productMono, Product.class)
                .retrieve()
                .bodyToMono(Product.class);
    }

    @Override
    public Flux<Product> listAll() {
        return client.get()
                .uri("get")
                .retrieve()
                .bodyToFlux(Product.class);
    }

    @Override
    public Mono<Product> listProductId(Integer id) {
        return client.get()
                .uri("get/{id}", id)
                .retrieve()
                .bodyToMono(Product.class);
    }

    @Override
    public Mono<Void> deleteProduct(Integer id) {
       return client.delete()
                .uri("delete/{id}", id)
               .retrieve()
               .bodyToMono(Void.class);
    }
}
