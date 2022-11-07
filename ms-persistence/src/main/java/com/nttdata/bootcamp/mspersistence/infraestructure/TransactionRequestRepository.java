package com.nttdata.bootcamp.mspersistence.infraestructure;

import com.nttdata.bootcamp.mspersistence.model.TransactionRequest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRequestRepository extends ReactiveMongoRepository<TransactionRequest, String> {
}
