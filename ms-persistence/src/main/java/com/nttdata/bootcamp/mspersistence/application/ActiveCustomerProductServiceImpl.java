package com.nttdata.bootcamp.mspersistence.application;

import com.nttdata.bootcamp.mspersistence.infraestructure.ActiveCustomerProductRepository;
import com.nttdata.bootcamp.mspersistence.model.ActiveCustomerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ActiveCustomerProductServiceImpl implements ActiveCustomerProductService{

    @Autowired
    ActiveCustomerProductRepository activeCustomerProductRepository;

    @Override
    public Mono<ActiveCustomerProduct> createActiveCustomProd(Mono<ActiveCustomerProduct> activeCustomerProductMono) {
        return activeCustomerProductMono.flatMap(activeCustomerProductRepository::insert);
    }

    @Override
    public Flux<ActiveCustomerProduct> listActiveCustomProdAll() {
        return activeCustomerProductRepository.findAll();
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
