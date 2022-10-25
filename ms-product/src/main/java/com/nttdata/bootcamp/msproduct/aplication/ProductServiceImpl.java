package com.nttdata.bootcamp.msproduct.aplication;

import com.nttdata.bootcamp.msproduct.infraestructure.ProductRepository;
import com.nttdata.bootcamp.msproduct.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Override
    public Mono<Product> createProduct(Mono<Product> productMono) {
        return productMono.flatMap(productRepository::insert);
    }

    @Override
    public Flux<Product> listAll() {

        //return employeeRepository.findAll();
        return productRepository.findAll().delayElements(Duration.ofSeconds(1)).log();
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
