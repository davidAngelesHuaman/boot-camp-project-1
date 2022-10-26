package com.nttdata.bootcamp.mspersistence.application;

import com.nttdata.bootcamp.mspersistence.model.ActiveCustomerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("activecustomerproduct")
public class ActiveCustomerProductController {
    @Autowired
    ActiveCustomerProductService activeCustomerProductService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ActiveCustomerProduct> createActiveCustomProd(@RequestBody Mono<ActiveCustomerProduct> activeCustomerProduct){
        return activeCustomerProductService.createActiveCustomProd(activeCustomerProduct);
    }

    @GetMapping(value = "getActiveCustomerProduct", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ActiveCustomerProduct> listActiveCustomProdAll(){
        return activeCustomerProductService.listActiveCustomProdAll();
    }

    @GetMapping(value = "getActiveCustomerProduct/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ActiveCustomerProduct> listActiveCustomProd_Id(@PathVariable("id") Integer id){
        return activeCustomerProductService.listActiveCustomProd_Id(id);
    }

    @DeleteMapping(value = "deleteActiveCustomer/{id}")
    public Mono<Void> deleteActiveCustomProd(@PathVariable("id") Integer id){

        return activeCustomerProductService.deleteActiveCustomProd(id);
    }
}
