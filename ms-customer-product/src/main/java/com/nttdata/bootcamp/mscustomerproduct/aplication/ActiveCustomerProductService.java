package com.nttdata.bootcamp.mscustomerproduct.aplication;

import com.nttdata.bootcamp.mscustomerproduct.model.ActiveCustomerProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ActiveCustomerProductService {
    Mono<ActiveCustomerProduct> createActiveCustomProd(Mono<ActiveCustomerProduct> product);
    Mono<Void> deleteActiveCustomProd(String id);
    Flux<ActiveCustomerProduct> listActiveCustomProdAll();
    Mono<ActiveCustomerProduct> listActiveCustomProd_Id(String id);
}
