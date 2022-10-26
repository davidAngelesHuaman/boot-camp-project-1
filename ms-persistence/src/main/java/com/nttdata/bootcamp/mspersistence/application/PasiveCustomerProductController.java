package com.nttdata.bootcamp.mspersistence.application;

import com.nttdata.bootcamp.mspersistence.model.PasiveCustomerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("pasivecustomerproduct")
public class PasiveCustomerProductController {
    @Autowired
    PasiveCustomerProductService pasiveCustomerProductService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PasiveCustomerProduct> createPasiveCustomProd(@RequestBody Mono<PasiveCustomerProduct> pasiveCustomerProduct){
        return pasiveCustomerProductService.createPasiveCustomProd(pasiveCustomerProduct);
    }

    @GetMapping(value = "getPasiveCustomerProduct", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PasiveCustomerProduct> listAll(){
        return pasiveCustomerProductService.listPasiveCustomProdAll();
    }

    @GetMapping(value = "getPasiveCustomerProduct/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<PasiveCustomerProduct> listPasiveCustomProd_Id(@PathVariable("id") Integer id){
        return pasiveCustomerProductService.listPasiveCustomProd_Id(id);
    }

    @DeleteMapping(value = "delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable("id") Integer id){
        return pasiveCustomerProductService.deletePasiveCustomProd(id);
    }
}
