package com.nttdata.bootcamp.mspersistence.application;

import com.nttdata.bootcamp.mspersistence.model.Transaction;
import com.nttdata.bootcamp.mspersistence.model.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("transaction-request")
public class TransactionRequestController {

    @Autowired
    TransactionRequestService transactionRequestService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TransactionRequest> createTransaction(@RequestBody Mono<TransactionRequest> transaction){
        return transactionRequestService.create(transaction);
    }

    @GetMapping(value = "get", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<TransactionRequest> listAll(){
        return transactionRequestService.listAll();
    }

    @GetMapping(value = "get/{id}")
    public Mono<TransactionRequest> listProductId(@PathVariable("id") String id){
        return transactionRequestService.listId(id);
    }

    @DeleteMapping(value = "delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable("id") String id){
        return transactionRequestService.delete(id);
    }
}
