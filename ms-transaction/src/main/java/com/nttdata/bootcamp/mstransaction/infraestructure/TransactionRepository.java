package com.nttdata.bootcamp.mstransaction.infraestructure;

import com.nttdata.bootcamp.mstransaction.model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, Integer> {
}
