package com.nttdata.bootcamp.mscustomerProduct.infraestructure;

import com.nttdata.bootcamp.mscustomerProduct.model.ActiveCustomerProduct;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveCustomerProductRepository extends ReactiveMongoRepository<ActiveCustomerProduct, Integer> {
}
