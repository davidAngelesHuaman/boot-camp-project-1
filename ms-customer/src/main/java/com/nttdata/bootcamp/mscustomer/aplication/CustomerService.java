package com.nttdata.bootcamp.mscustomer.aplication;

import com.nttdata.bootcamp.mscustomer.model.Customer;
import com.nttdata.bootcamp.mscustomer.model.CustomerBootCoin;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<Customer> createCustomer(Mono<Customer> customer);
    Mono<CustomerBootCoin> createCustomerBoot(Mono<CustomerBootCoin> customer);
    Mono<Void> deleteCustomer(Integer id);
    Flux<Customer> listAll();
    Mono<Customer> listCustomerId(Integer id);
}
