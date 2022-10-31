package com.nttdata.bootcamp.mscustomer.aplication;

import com.nttdata.bootcamp.mscustomer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class CustomerServiceImpl implements CustomerService{
    ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;
    WebClient clientPersistence;

    @Autowired
    public CustomerServiceImpl(WebClient.Builder builder) {
        this.clientPersistence = builder.baseUrl("http://ms-persistence/").build();
    }

    @Override
    public Mono<Customer> createCustomer(Mono<Customer> customerMono) {
        return clientPersistence.post()
                .uri("customer/")
                .body(customerMono, Customer.class)
                .retrieve()
                .bodyToMono(Customer.class)
                .transform(it -> reactiveCircuitBreakerFactory.create("customer-service").run(it, throwable -> Mono.just(new Customer())));
    }
    @Override
    public Flux<Customer> listAll() {
        return clientPersistence.get()
                .uri("customer/get")
                .retrieve()
                .bodyToFlux(Customer.class)
                .transform(it -> reactiveCircuitBreakerFactory.create("customer-service").run(it, throwable -> Flux.just(new Customer())));
    }
    @Override
    public Mono<Customer> listCustomerId(Integer id) {
        return clientPersistence.get()
                .uri("customer/get/{id}", id)
                .retrieve()
                .bodyToMono(Customer.class)
                .transform(it -> reactiveCircuitBreakerFactory.create("customer-service").run(it, throwable -> Mono.just(new Customer())));
    }
    @Override
    public Mono<Void> deleteCustomer(Integer id) {
        return clientPersistence.delete()
                .uri("customer/delete/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .transform(it -> reactiveCircuitBreakerFactory.create("customer-service").run(it, throwable -> Mono.empty()));
    }

}
