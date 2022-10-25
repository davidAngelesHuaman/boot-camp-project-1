package com.nttdata.bootcamp.mspersistence.application;

import com.nttdata.bootcamp.mspersistence.infraestructure.ProductRepository;
import com.nttdata.bootcamp.mspersistence.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Mono<Product> createProduct(Mono<Product> productMono) {
        return productMono.flatMap(productRepository::insert);
    }

    @Override
    public Flux<Product> listAll() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> listProductId(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteProduct(Integer id) {
        return productRepository.deleteById(id);
    }

}
