package com.nttdata.bootcamp.mspersistence.application;

import com.nttdata.bootcamp.mspersistence.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> createCustomer(@RequestBody Mono<Customer> customer){
        return customerService.createCustomer(customer);
    }

    @GetMapping(value = "get", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> listAll(){
        return customerService.listAll();
    }

    @GetMapping(value = "get/{id}")
    public Mono<Customer> listCustomerId(@PathVariable("id") String id) {
        return customerService.listCustomerId(id);
    }

    @DeleteMapping(value = "delete/{id}")
    public Mono<Void> deleteCustomer(@PathVariable("id") String id) {
        return customerService.deleteCustomer(id);
    }
}
