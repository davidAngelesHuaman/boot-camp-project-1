package com.nttdata.bootcamp.mspersistence.infraestructure;

import com.nttdata.bootcamp.mspersistence.model.PasiveCustomerProduct;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasiveCustomerProductRepository extends ReactiveMongoRepository<PasiveCustomerProduct, Integer> {
}
