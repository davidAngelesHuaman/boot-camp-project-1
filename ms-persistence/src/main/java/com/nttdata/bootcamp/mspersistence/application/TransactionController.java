package com.nttdata.bootcamp.mspersistence.application;

import com.nttdata.bootcamp.mspersistence.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Transaction> createTransaction(@RequestBody Mono<Transaction> transaction){
        return transactionService.createTransaction(transaction);
    }

    @GetMapping(value = "get", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Transaction> listAll(){
        return transactionService.listAll();
    }

    @GetMapping(value = "get/{id}")
    public Mono<Transaction> listProductId(@PathVariable("id") Integer id){
        return transactionService.listTransactionId(id);
    }

    @DeleteMapping(value = "delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable("id") Integer id){
        return transactionService.deleteTransaction(id);
    }
}
