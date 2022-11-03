package com.nttdata.bootcamp.mstransaction.aplication;

import com.nttdata.bootcamp.mstransaction.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {
    Mono<Transaction> createTransaction(Transaction transaction);
    Mono<Void> deleteTransaction(Integer id);
    Flux<Transaction> listAll();
    Mono<Transaction> listTransactionId(Integer id);
}
