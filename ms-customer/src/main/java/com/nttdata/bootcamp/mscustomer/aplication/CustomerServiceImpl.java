package com.nttdata.bootcamp.mscustomer.aplication;

import com.nttdata.bootcamp.mscustomer.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class CustomerServiceImpl implements CustomerService{

    WebClient client = WebClient.create("http://localhost:8081/customer/");

    @Override
    public Mono<Customer> createCustomer(Mono<Customer> customerMono) {
        return client.post()
                .uri("/")
                .body(customerMono, Customer.class)
                .retrieve()
                .bodyToMono(Customer.class);
    }
    @Override
    public Flux<Customer> listAll() {
        return client.get()
                .uri("get")
                .retrieve()
                .bodyToFlux(Customer.class);
    }
    @Override
    public Mono<Customer> listCustomerId(Integer id) {
        return client.get()
                .uri("get/{id}", id)
                .retrieve()
                .bodyToMono(Customer.class);
    }
    @Override
    public Mono<Void> deleteCustomer(Integer id) {
        return client.delete()
                .uri("delete/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }

}
