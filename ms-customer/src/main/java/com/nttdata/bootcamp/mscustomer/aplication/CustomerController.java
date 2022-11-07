package com.nttdata.bootcamp.mscustomer.aplication;

import com.nttdata.bootcamp.mscustomer.model.Customer;
import com.nttdata.bootcamp.mscustomer.model.CustomerBootCoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api-customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(Mono.just(customer));
    }

    @PostMapping("customerBootCoin")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CustomerBootCoin> createCustomerBootCoin(@RequestBody CustomerBootCoin customer){
        return customerService.createCustomerBoot(Mono.just(customer));
    }

    @GetMapping(value = "getCustomer", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> listAll(){
        return customerService.listAll();
    }

    @GetMapping(value = "getCustomer/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Customer> listCustomerId(@PathVariable("id") Integer id){
        return customerService.listCustomerId(id);
    }

    @DeleteMapping(value = "delete/{id}")
    public Mono<Void> deleteCustomer(@PathVariable("id") Integer id){

        return customerService.deleteCustomer(id);
    }
}
