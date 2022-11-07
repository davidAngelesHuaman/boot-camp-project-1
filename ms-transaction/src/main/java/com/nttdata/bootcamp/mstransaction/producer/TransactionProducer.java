package com.nttdata.bootcamp.mstransaction.producer;

import com.nttdata.bootcamp.mstransaction.model.Transaction;
import com.nttdata.bootcamp.mstransaction.model.TransactionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionProducer {

    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    private final KafkaTemplate<String, TransactionRequest> kafkaTemplateTransactionRequest;

    @Value(value = "${kafka.topic.transaction.name}")
    private String topic;

    @Value(value = "${kafka.topic.transaction-request.name}")
    private String topicRequest;

    public void sendMessage(Transaction transaction) {
        ListenableFuture<SendResult<String, Transaction>> future = kafkaTemplate.send(this.topic, transaction);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Transaction>>() {
            @Override
            public void onSuccess(SendResult<String, Transaction> result) {
                log.info("Message {} has been sent ", transaction);
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Something went wrong with the balanceModel {} ", transaction);
            }
        });
    }

    public void sendMessage(TransactionRequest transactionRequest) {
        ListenableFuture<SendResult<String, TransactionRequest>> future =
                kafkaTemplateTransactionRequest.send(this.topicRequest, transactionRequest);

        future.addCallback(new ListenableFutureCallback<SendResult<String, TransactionRequest>>() {
            @Override
            public void onSuccess(SendResult<String, TransactionRequest> result) {
                log.info("Message {} has been sent ", transactionRequest);
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Something went wrong with the balanceModel {} ", transactionRequest);
            }
        });
    }

}
