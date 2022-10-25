package com.nttdata.bootcamp.mspersistence.infraestructure;

import com.nttdata.bootcamp.mspersistence.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, Integer> {
}
