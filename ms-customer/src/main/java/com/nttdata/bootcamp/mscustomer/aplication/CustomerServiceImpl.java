package com.nttdata.bootcamp.mscustomer.aplication;

import com.nttdata.bootcamp.mscustomer.infraestructure.CustomerRepository;
import com.nttdata.bootcamp.mscustomer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

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

        //return employeeRepository.findAll();
        return customerRepository.findAll().delayElements(Duration.ofSeconds(1)).log();
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
