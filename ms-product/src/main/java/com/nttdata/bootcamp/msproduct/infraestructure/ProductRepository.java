package com.nttdata.bootcamp.msproduct.infraestructure;

import com.nttdata.bootcamp.msproduct.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, Integer> {
}
