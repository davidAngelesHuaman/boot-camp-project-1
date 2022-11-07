package com.nttdata.bootcamp.mspersistence.application;

import com.nttdata.bootcamp.mspersistence.model.TransactionRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionRequestService {

    Mono<TransactionRequest> create(Mono<TransactionRequest> transactionMono);

    Mono<Void> delete(String id);

    Flux<TransactionRequest> listAll();

    Mono<TransactionRequest> listId(String id);

}
