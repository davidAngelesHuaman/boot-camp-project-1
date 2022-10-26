package com.nttdata.bootcamp.mscustomerProduct.aplication;

import com.nttdata.bootcamp.mscustomerProduct.model.PasiveCustomerProduct;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PasiveCustomerProductServiceImpl implements PasiveCustomerProductService{

    WebClient client = WebClient.create("http://localhost:8081/pasivecustomerproduct/");

    @Override
    public Mono<PasiveCustomerProduct> createPasiveCustomProd(Mono<PasiveCustomerProduct> pasiveCustomerProductMono) {
        return client.post()
                .uri("/")
                .body(pasiveCustomerProductMono, PasiveCustomerProduct.class)
                .retrieve()
                .bodyToMono(PasiveCustomerProduct.class);
    }

    @Override
    public Flux<PasiveCustomerProduct> listPasiveCustomProdAll() {
        return client.get()
                .uri("get")
                .retrieve()
                .bodyToFlux(PasiveCustomerProduct.class);
    }

    @Override
    public Mono<PasiveCustomerProduct> listPasiveCustomProd_Id(Integer id) {
        return client.get()
                .uri("get/{id}", id)
                .retrieve()
                .bodyToMono(PasiveCustomerProduct.class);
    }

    @Override
    public Mono<Void> deletePasiveCustomProd(Integer id) {
        return client.delete()
                .uri("delete/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
