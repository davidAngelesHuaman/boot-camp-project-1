package com.nttdata.bootcamp.mscustomerproduct.aplication;

import com.nttdata.bootcamp.mscustomerproduct.model.ActiveCustomerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ActiveCustomerProductServiceImpl implements ActiveCustomerProductService{

    WebClient clientPersistence;

    @Autowired
    public ActiveCustomerProductServiceImpl(WebClient.Builder builder) {
        this.clientPersistence = builder.baseUrl("http://ms-persistence/activecustomerproduct/").build();
    }

    @Override
    public Mono<ActiveCustomerProduct> createActiveCustomProd(Mono<ActiveCustomerProduct> activeCustomerProductMono) {
        return clientPersistence.post()
                .uri("/")
                .body(activeCustomerProductMono, ActiveCustomerProduct.class)
                .retrieve()
                .bodyToMono(ActiveCustomerProduct.class);
    }

    @Override
    public Flux<ActiveCustomerProduct> listActiveCustomProdAll() {
        return clientPersistence.get()
                .uri("get")
                .retrieve()
                .bodyToFlux(ActiveCustomerProduct.class);
    }

    @Override
    public Mono<ActiveCustomerProduct> listActiveCustomProd_Id(Integer id) {
        return clientPersistence.get()
                .uri("get/{id}", id)
                .retrieve()
                .bodyToMono(ActiveCustomerProduct.class);
    }

    @Override
    public Mono<Void> deleteActiveCustomProd(Integer id) {
        return clientPersistence.delete()
                .uri("delete/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }

}
