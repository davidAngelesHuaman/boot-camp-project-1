package com.nttdata.bootcamp.mscustomerproduct.aplication;

import com.nttdata.bootcamp.mscustomerproduct.model.ActiveCustomerProduct;
import com.nttdata.bootcamp.mscustomerproduct.model.PasiveCustomerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ActiveCustomerProductServiceImpl implements ActiveCustomerProductService{
    @Autowired
    ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;
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
                .bodyToMono(ActiveCustomerProduct.class)
                .transform(it -> reactiveCircuitBreakerFactory.create("active-service").run(it, throwable -> Mono.just(new ActiveCustomerProduct())));
    }

    @Override
    public Flux<ActiveCustomerProduct> listActiveCustomProdAll() {
        return clientPersistence.get()
                .uri("get")
                .retrieve()
                .bodyToFlux(ActiveCustomerProduct.class)
                .transform(it -> reactiveCircuitBreakerFactory.create("active-service").run(it, throwable -> Flux.just(new ActiveCustomerProduct())));
    }

    @Override
    public Mono<ActiveCustomerProduct> listActiveCustomProd_Id(String id) {
        return clientPersistence.get()
                .uri("get/{id}", id)
                .retrieve()
                .bodyToMono(ActiveCustomerProduct.class)
                .transform(it -> reactiveCircuitBreakerFactory.create("active-service").run(it, throwable -> Mono.just(new ActiveCustomerProduct())));
    }

    @Override
    public Mono<Void> deleteActiveCustomProd(String id) {
        return clientPersistence.delete()
                .uri("delete/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .transform(it -> reactiveCircuitBreakerFactory.create("active-service").run(it, throwable -> Mono.empty()));
    }

}
