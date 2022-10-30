package com.nttdata.bootcamp.mspersistence.application;

import com.nttdata.bootcamp.mspersistence.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> createCustomer(@RequestBody Mono<Product> product){
        return productService.createProduct(product);
    }

    @GetMapping(value = "get", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> listAll(){
        return productService.listAll();
    }

    @GetMapping(value = "get/{id}")
    public Mono<Product> listProductId(@PathVariable("id") Integer id){
        return productService.listProductId(id);
    }

    @DeleteMapping(value = "delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable("id") Integer id){
        return productService.deleteProduct(id);
    }
}
