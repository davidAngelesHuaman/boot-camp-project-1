package com.nttdata.bootcamp.mspersistence.infraestructure;

import com.nttdata.bootcamp.mspersistence.model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, Integer> {
}
