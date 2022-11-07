package com.nttdata.bootcamp.mscustomerproduct.aplication;

import com.nttdata.bootcamp.mscustomerproduct.model.ActiveCustomerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api-activecustomerproduct")
public class ActiveCustomerProductController {

    @Autowired
    ActiveCustomerProductService activeCustomerProductService;

    @PostMapping("activecustomerproduct")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ActiveCustomerProduct> createActiveCustomProd(@RequestBody ActiveCustomerProduct activeCustomerProduct){
        return activeCustomerProductService.createActiveCustomProd(Mono.just(activeCustomerProduct));
    }

    @GetMapping(value = "get", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ActiveCustomerProduct> listActiveCustomProdAll(){
        return activeCustomerProductService.listActiveCustomProdAll();
    }

    @GetMapping(value = "get/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ActiveCustomerProduct> listActiveCustomProd_Id(@PathVariable("id") String id){
        return activeCustomerProductService.listActiveCustomProd_Id(id);
    }

    @DeleteMapping(value = "delete/{id}")
    public Mono<Void> deleteActiveCustomProd(@PathVariable("id") String id){
        return activeCustomerProductService.deleteActiveCustomProd(id);
    }
}
