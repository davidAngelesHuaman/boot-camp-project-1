package com.nttdata.bootcamp.mscustomerProduct.aplication;

import com.nttdata.bootcamp.mscustomerProduct.infraestructure.ActiveCustomerProductRepository;
import com.nttdata.bootcamp.mscustomerProduct.model.ActiveCustomerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ActiveCustomerProductServiceImpl implements ActiveCustomerProductService{
    @Autowired
    ActiveCustomerProductRepository activeCustomerProductRepository;
    @Override
    public Mono<ActiveCustomerProduct> createActiveCustomProd(Mono<ActiveCustomerProduct> activeCustomerProductMono) {
        return activeCustomerProductMono.flatMap(activeCustomerProductRepository::insert);
    }

    @Override
    public Flux<ActiveCustomerProduct> listActiveCustomProdAll() {

        //return employeeRepository.findAll();
        return activeCustomerProductRepository.findAll().delayElements(Duration.ofSeconds(1)).log();
    }
    @Override
    public Mono<ActiveCustomerProduct> listActiveCustomProd_Id(Integer id) {

        return activeCustomerProductRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteActiveCustomProd(Integer id) {
        return activeCustomerProductRepository.deleteById(id);
    }
}
