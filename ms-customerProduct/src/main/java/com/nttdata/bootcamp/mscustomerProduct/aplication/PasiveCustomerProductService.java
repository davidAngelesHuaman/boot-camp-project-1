package com.nttdata.bootcamp.mscustomerProduct.aplication;

import com.nttdata.bootcamp.mscustomerProduct.model.PasiveCustomerProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PasiveCustomerProductService {
    Mono<PasiveCustomerProduct> createPasiveCustomProd(Mono<PasiveCustomerProduct> pasiveCustomerProductMono);
    Mono<Void> deletePasiveCustomProd(Integer id);
    Flux<PasiveCustomerProduct> listPasiveCustomProdAll();
    Mono<PasiveCustomerProduct> listPasiveCustomProd_Id(Integer id);
}
