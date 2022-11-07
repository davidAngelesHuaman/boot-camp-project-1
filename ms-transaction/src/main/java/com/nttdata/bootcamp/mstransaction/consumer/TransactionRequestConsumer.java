package com.nttdata.bootcamp.mstransaction.consumer;

import com.nttdata.bootcamp.mstransaction.aplication.TransactionRequestService;
import com.nttdata.bootcamp.mstransaction.model.TransactionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionRequestConsumer {

    @Autowired
    private TransactionRequestService transactionService;

    @KafkaListener(topics = "${kafka.topic.transaction-request.name}")
    public void listener(@Payload TransactionRequest request) {
        log.info("Message received : {} ", request);
        transactionService.createTransactionRequest(request);

    }



}
