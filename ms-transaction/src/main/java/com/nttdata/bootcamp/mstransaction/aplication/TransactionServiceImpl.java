package com.nttdata.bootcamp.mstransaction.aplication;

import com.nttdata.bootcamp.mstransaction.model.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {

    WebClient client = WebClient.create("http://localhost:8081/transaction/");

    @Override
    public Mono<Transaction> createTransaction(Mono<Transaction> transactionMono) {
        return client.post()
                .uri("/")
                .body(transactionMono, Transaction.class)
                .retrieve()
                .bodyToMono(Transaction.class);
    }

    @Override
    public Flux<Transaction> listAll() {
        return client.get()
                .uri("get")
                .retrieve()
                .bodyToFlux(Transaction.class);
    }

    @Override
    public Mono<Transaction> listTransactionId(Integer id) {
        return client.get()
                .uri("get/{id}", id)
                .retrieve()
                .bodyToMono(Transaction.class);
    }

    @Override
    public Mono<Void> deleteTransaction(Integer id) {
        return client.delete()
                .uri("delete/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
