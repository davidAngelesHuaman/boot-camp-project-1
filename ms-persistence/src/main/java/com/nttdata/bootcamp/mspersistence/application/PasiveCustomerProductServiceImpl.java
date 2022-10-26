package com.nttdata.bootcamp.mspersistence.application;

import com.nttdata.bootcamp.mspersistence.infraestructure.PasiveCustomerProductRepository;
import com.nttdata.bootcamp.mspersistence.model.PasiveCustomerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class PasiveCustomerProductServiceImpl implements PasiveCustomerProductService{

    @Autowired
    PasiveCustomerProductRepository pasiveCustomerProductRepository;
    @Override
    public Mono<PasiveCustomerProduct> createPasiveCustomProd(Mono<PasiveCustomerProduct> pasiveCustomerProductMono) {
        return pasiveCustomerProductMono.flatMap(pasiveCustomerProductRepository::insert);
    }

    @Override
    public Flux<PasiveCustomerProduct> listPasiveCustomProdAll() {
        return pasiveCustomerProductRepository.findAll();
    }
    @Override
    public Mono<PasiveCustomerProduct> listPasiveCustomProd_Id(Integer id) {
        return pasiveCustomerProductRepository.findById(id);
    }

    @Override
    public Mono<Void> deletePasiveCustomProd(Integer id) {
        return pasiveCustomerProductRepository.deleteById(id);
    }

}
