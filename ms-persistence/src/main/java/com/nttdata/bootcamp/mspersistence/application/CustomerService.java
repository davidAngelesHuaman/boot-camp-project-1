package com.nttdata.bootcamp.mspersistence.application;

import com.nttdata.bootcamp.mspersistence.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<Customer> createCustomer(Mono<Customer> product);
    Mono<Void> deleteCustomer(Integer id);
    Flux<Customer> listAll();
    Mono<Customer> listCustomerId(Integer id);
}
