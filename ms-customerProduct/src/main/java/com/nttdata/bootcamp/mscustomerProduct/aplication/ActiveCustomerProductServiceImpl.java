package com.nttdata.bootcamp.mscustomerProduct.aplication;

import com.nttdata.bootcamp.mscustomerProduct.model.ActiveCustomerProduct;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ActiveCustomerProductServiceImpl implements ActiveCustomerProductService{

    WebClient client = WebClient.create("http://localhost:8081/activecustomerproduct/");

    @Override
    public Mono<ActiveCustomerProduct> createActiveCustomProd(Mono<ActiveCustomerProduct> activeCustomerProductMono) {
        return client.post()
                .uri("/")
                .body(activeCustomerProductMono, ActiveCustomerProduct.class)
                .retrieve()
                .bodyToMono(ActiveCustomerProduct.class);
    }

    @Override
    public Flux<ActiveCustomerProduct> listActiveCustomProdAll() {
        return client.get()
                .uri("get")
                .retrieve()
                .bodyToFlux(ActiveCustomerProduct.class);
    }

    @Override
    public Mono<ActiveCustomerProduct> listActiveCustomProd_Id(Integer id) {
        return client.get()
                .uri("get/{id}", id)
                .retrieve()
                .bodyToMono(ActiveCustomerProduct.class);
    }

    @Override
    public Mono<Void> deleteActiveCustomProd(Integer id) {
        return client.delete()
                .uri("delete/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
