package com.nttdata.bootcamp.mspersistence.consumer;

import com.nttdata.bootcamp.mspersistence.application.TransactionService;
import com.nttdata.bootcamp.mspersistence.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionConsumer {

    @Autowired
    private TransactionService transactionService;

    @KafkaListener(topics = "${kafka.topic.transaction.name}")
    public void listener(@Payload Transaction transaction) {
        log.debug("Message received : {} ", transaction);
        applyTransaction(transaction).block();
    }

    private Mono<Transaction> applyTransaction(Transaction transaction) {
        log.debug("applyBalance executed : {} ", transaction);
        return transactionService.createTransaction(Mono.just(transaction));
    }

}
