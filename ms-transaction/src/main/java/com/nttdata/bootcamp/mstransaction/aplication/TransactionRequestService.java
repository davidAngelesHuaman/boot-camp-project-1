package com.nttdata.bootcamp.mstransaction.aplication;

import com.nttdata.bootcamp.mstransaction.model.TransactionRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionRequestService {

    Mono<String> sendTransaction(TransactionRequest request);

    void createTransactionRequest(TransactionRequest request);

    Flux<TransactionRequest> listAll();

    Mono<TransactionRequest> listId(String id);

}
