package com.nttdata.bootcamp.mstransaction.aplication;

import com.nttdata.bootcamp.mstransaction.model.ActiveCustomerProduct;
import com.nttdata.bootcamp.mstransaction.model.TransactionRequest;
import com.nttdata.bootcamp.mstransaction.producer.TransactionProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
public class TransactionRequestServiceImpl implements TransactionRequestService {

    WebClient clientPersistence;

    @Autowired
    private TransactionProducer transactionProducer;

    @Autowired
    public TransactionRequestServiceImpl(WebClient.Builder builder) {
        this.clientPersistence = builder.baseUrl("http://ms-persistence/").build();
    }

    @Override
    public Mono<String> sendTransaction(TransactionRequest request) {
        UUID uuid = UUID.randomUUID();
        request.setId(uuid.toString());
        //Status Pending
        request.setStatus(0);
        log.info("Enviando solicitud de transsación");

        return Mono.just(this.sendTransactionKafka(request)).map(TransactionRequest::getId);
    }

    @Override
    public void createTransactionRequest(TransactionRequest request) {
        Mono<ActiveCustomerProduct> customer = clientPersistence.get()
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
                .switchIfEmpty(Mono.error(new Exception("Cuenta de usuario destino no disponible")));

        customer.subscribe(
                data -> log.info("Persistiendo solicitud {}", request), // onNext
                err -> {
                    request.setMessage("ERROR: " + err.getMessage());
                    request.setStatus(-1);
                    clientPersistence.post()
                            .uri("transaction-request/")
                            .body(Mono.just(request), TransactionRequest.class)
                            .retrieve()
                            .bodyToMono(TransactionRequest.class)
                            .subscribe();
                },  // onError
                () -> {
                    request.setMessage("Solicitud Pendiente");
                    request.setStatus(0);
                    clientPersistence.post()
                            .uri("transaction-request/")
                            .body(Mono.just(request), TransactionRequest.class)
                            .retrieve()
                            .bodyToMono(TransactionRequest.class)
                            .subscribe();
                }
        );
    }

    private static Mono<String> fallbackMethod(Throwable error) {
        if (error instanceof RuntimeException) {
            return Mono.just("RUNTIME_EX");
        } else {
            return Mono.just("NOT_RUNTIME_EX");
        }
    }

    @Override
    public Flux<TransactionRequest> listAll() {
        return clientPersistence.get()
                .uri("transaction-request/get")
                .retrieve()
                .bodyToFlux(TransactionRequest.class);
    }

    @Override
    public Mono<TransactionRequest> listId(String id) {
        return clientPersistence.get()
                .uri("transaction-request/get/{id}", id)
                .retrieve()
                .bodyToMono(TransactionRequest.class);
    }

    private TransactionRequest sendTransactionKafka(TransactionRequest transaction) {
        log.debug("sendBalance executed {}", transaction);
        if (transaction != null) {
            transactionProducer.sendMessage(transaction);
        }
        return transaction;
    }

}
