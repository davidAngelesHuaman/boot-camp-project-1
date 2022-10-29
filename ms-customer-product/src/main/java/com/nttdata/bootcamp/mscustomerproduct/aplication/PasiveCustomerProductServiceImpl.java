package com.nttdata.bootcamp.mscustomerproduct.aplication;

import com.nttdata.bootcamp.mscustomerproduct.model.PasiveCustomerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PasiveCustomerProductServiceImpl implements PasiveCustomerProductService{

    WebClient clientPersistence;

    @Autowired
    public PasiveCustomerProductServiceImpl(WebClient.Builder builder) {
        this.clientPersistence = builder.baseUrl("http://ms-persistence/pasivecustomerproduct/").build();
    }

    @Override
    public Mono<PasiveCustomerProduct> createPasiveCustomProd(Mono<PasiveCustomerProduct> pasiveCustomerProductMono) {
        return clientPersistence.post()
                .uri("/")
                .body(pasiveCustomerProductMono, PasiveCustomerProduct.class)
                .retrieve()
                .bodyToMono(PasiveCustomerProduct.class);
    }

    @Override
    public Flux<PasiveCustomerProduct> listPasiveCustomProdAll() {
        return clientPersistence.get()
                .uri("get")
                .retrieve()
                .bodyToFlux(PasiveCustomerProduct.class);
    }

    @Override
    public Mono<PasiveCustomerProduct> listPasiveCustomProd_Id(Integer id) {
        return clientPersistence.get()
                .uri("get/{id}", id)
                .retrieve()
                .bodyToMono(PasiveCustomerProduct.class);
    }

    @Override
    public Mono<Void> deletePasiveCustomProd(Integer id) {
        return clientPersistence.delete()
                .uri("delete/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
