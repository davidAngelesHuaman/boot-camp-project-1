package com.nttdata.bootcamp.mspersistence.application;

import com.nttdata.bootcamp.mspersistence.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Product> createProduct(Mono<Product> product);
    Mono<Void> deleteProduct(Integer id);
    Flux<Product> listAll();
    Mono<Product> listProductId(Integer id);
}
