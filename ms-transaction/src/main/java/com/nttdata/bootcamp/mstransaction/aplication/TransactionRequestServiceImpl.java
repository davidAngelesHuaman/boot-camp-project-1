package com.nttdata.bootcamp.mstransaction.aplication;

import com.nttdata.bootcamp.mstransaction.model.ActiveCustomerProduct;
import com.nttdata.bootcamp.mstransaction.model.Transaction;
import com.nttdata.bootcamp.mstransaction.model.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class TransactionRequestServiceImpl implements TransactionRequestService {

    WebClient clientPersistence;

    @Autowired
    public TransactionRequestServiceImpl(WebClient.Builder builder) {
        this.clientPersistence = builder.baseUrl("http://ms-persistence/").build();
    }

    @Override
    public Mono<String> createTransaction(TransactionRequest request) {
        log.info("Reciviendo solicitud de transsación");
        //Status Pending
        request.setStatus(0);

        //Validated Product Customer
        return clientPersistence.get()
                .uri("activecustomerproduct/get/{id}", request.getOriginCustomer())
                .retrieve()
                .bodyToMono(ActiveCustomerProduct.class)
                .switchIfEmpty(Mono.error(new Exception("Cuenta de usuario origen no disponible")))
                .filter(p -> p.getAmountCredit().compareTo(request.getAmountTransaction()) > -1)
                .switchIfEmpty(Mono.error(new Exception("Cuenta de usuario origen no cuenta con saldo disponible")))
                .flatMap(p -> {
                    return clientPersistence.get()
                            .uri("activecustomerproduct/get/{id}", request.getDestinationCustomer())
                            .retrieve()
                            .bodyToMono(ActiveCustomerProduct.class);
                })
                .switchIfEmpty(Mono.error(new Exception("Cuenta de usuario destino no disponible")))
                .flatMap(p -> {
                    return clientPersistence.post()
                            .uri("transaction-request/")
                            .body(Mono.just(request), TransactionRequest.class)
                            .retrieve()
                            .bodyToMono(TransactionRequest.class);
                })
                .map(TransactionRequest::getId);
    }

    @Override
    public Flux<TransactionRequest> listAll() {
        return clientPersistence.get()
                .uri("transaction-request/get")
                .retrieve()
                .bodyToFlux(TransactionRequest.class);
    }

}
