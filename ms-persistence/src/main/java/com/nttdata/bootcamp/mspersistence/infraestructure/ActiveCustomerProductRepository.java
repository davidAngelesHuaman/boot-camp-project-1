package com.nttdata.bootcamp.mspersistence.infraestructure;

import com.nttdata.bootcamp.mspersistence.model.ActiveCustomerProduct;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveCustomerProductRepository extends ReactiveMongoRepository<ActiveCustomerProduct, Integer> {
}
