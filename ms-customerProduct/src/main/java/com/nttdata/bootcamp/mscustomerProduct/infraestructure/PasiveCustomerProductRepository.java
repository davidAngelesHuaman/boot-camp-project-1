package com.nttdata.bootcamp.mscustomerProduct.infraestructure;

import com.nttdata.bootcamp.mscustomerProduct.model.PasiveCustomerProduct;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasiveCustomerProductRepository extends ReactiveMongoRepository<PasiveCustomerProduct, Integer> {
}
