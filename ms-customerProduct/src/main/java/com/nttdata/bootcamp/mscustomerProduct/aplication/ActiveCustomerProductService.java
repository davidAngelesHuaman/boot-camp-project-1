package com.nttdata.bootcamp.mscustomerProduct.aplication;

import com.nttdata.bootcamp.mscustomerProduct.model.ActiveCustomerProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ActiveCustomerProductService {
    Mono<ActiveCustomerProduct> createActiveCustomProd(Mono<ActiveCustomerProduct> product);
    Mono<Void> deleteActiveCustomProd(Integer id);
    Flux<ActiveCustomerProduct> listActiveCustomProdAll();
    Mono<ActiveCustomerProduct> listActiveCustomProd_Id(Integer id);
}
