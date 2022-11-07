package com.nttdata.bootcamp.mspersistence.application;

import com.nttdata.bootcamp.mspersistence.infraestructure.TransactionRequestRepository;
import com.nttdata.bootcamp.mspersistence.model.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class TransactionRequestServiceImpl implements TransactionRequestService{

    @Autowired
    TransactionRequestRepository transactionRequestRepository;

    @Override
    public Mono<TransactionRequest> create(Mono<TransactionRequest> transactionMono) {
        log.info("Persistiendo solicitud de transacción");
        return transactionMono.flatMap(transactionRequestRepository::insert);
    }

    @Override
    public Mono<Void> delete(String id) {
        return transactionRequestRepository.deleteById(id);
    }

    @Override
    public Flux<TransactionRequest> listAll() {
        return transactionRequestRepository.findAll();
    }

    @Override
    public Mono<TransactionRequest> listId(String id) {
        return transactionRequestRepository.findById(id);
    }
}
