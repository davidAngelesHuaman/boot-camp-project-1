package com.nttdata.bootcamp.msproduct.aplication;

import com.nttdata.bootcamp.msproduct.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("product")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> createCustomer(@RequestBody Product product){
        return productService.createProduct(Mono.just(product));
    }

    @GetMapping(value = "getProduct", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> listAll(){
        return productService.listAll();
    }

    @GetMapping(value = "getProduct/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Product> listProductId(@PathVariable("id") Integer id){
        return productService.listProductId(id);
    }

    @DeleteMapping(value = "delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable("id") Integer id){

        return productService.deleteProduct(id);
    }
}
