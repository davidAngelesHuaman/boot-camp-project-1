package com.nttdata.bootcamp.mstransaction.aplication;

import com.nttdata.bootcamp.mstransaction.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;
    WebClient clientPersistence;

    @Autowired
    public TransactionServiceImpl(WebClient.Builder builder) {
        this.clientPersistence = builder.baseUrl("http://ms-persistence/").build();
    }

    @Override
    public Mono<Transaction> createTransaction(Mono<Transaction> transactionMono) {
        return clientPersistence.post()
                .uri("transaction/")
                .body(transactionMono, Transaction.class)
                .retrieve()
                .bodyToMono(Transaction.class)
                .transform(it -> reactiveCircuitBreakerFactory.create("transaction-service").run(it, throwable -> Mono.just(new Transaction())));
    }

    @Override
    public Flux<Transaction> listAll() {
        return clientPersistence.get()
                .uri("transaction/get")
                .retrieve()
                .bodyToFlux(Transaction.class)
                .transform(it -> reactiveCircuitBreakerFactory.create("transaction-service").run(it, throwable -> Flux.just(new Transaction())));
    }

    @Override
    public Mono<Transaction> listTransactionId(Integer id) {
        return clientPersistence.get()
                .uri("transaction/get/{id}", id)
                .retrieve()
                .bodyToMono(Transaction.class)
                .transform(it -> reactiveCircuitBreakerFactory.create("transaction-service").run(it, throwable -> Mono.just(new Transaction())));
    }

    @Override
    public Mono<Void> deleteTransaction(Integer id) {
        return clientPersistence.delete()
                .uri("transaction/delete/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .transform(it -> reactiveCircuitBreakerFactory.create("transaction-service").run(it, throwable -> Mono.empty()));
    }
}
