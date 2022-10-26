package com.nttdata.bootcamp.mspersistence.application;

import com.nttdata.bootcamp.mspersistence.model.ActiveCustomerProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ActiveCustomerProductService {
    Mono<ActiveCustomerProduct> createActiveCustomProd(Mono<ActiveCustomerProduct> product);
    Mono<Void> deleteActiveCustomProd(Integer id);
    Flux<ActiveCustomerProduct> listActiveCustomProdAll();
    Mono<ActiveCustomerProduct> listActiveCustomProd_Id(Integer id);
}
