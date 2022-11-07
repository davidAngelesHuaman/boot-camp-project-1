package com.nttdata.bootcamp.mstransaction.aplication;

import com.nttdata.bootcamp.mstransaction.model.ActiveCustomerProduct;
import com.nttdata.bootcamp.mstransaction.model.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api-transaction-request")
public class TransactionRequestController {

    @Autowired
    private TransactionRequestService transactionRequestService;

    @PostMapping("request")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<String> createTransactionRequest(@RequestBody TransactionRequest request) {
        return transactionRequestService.createTransaction(request);
    }

    @GetMapping(value = "get", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<TransactionRequest> listAll() {
        return transactionRequestService.listAll();
    }


}
