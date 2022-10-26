package com.nttdata.bootcamp.mspersistence.application;

import com.nttdata.bootcamp.mspersistence.infraestructure.CustomerRepository;
import com.nttdata.bootcamp.mspersistence.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Mono<Customer> createCustomer(Mono<Customer> customerMono) {
        return customerMono.flatMap(customerRepository::insert);
    }

    @Override
    public Flux<Customer> listAll() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> listCustomerId(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteCustomer(Integer id) {
        return customerRepository.deleteById(id);
    }

}
