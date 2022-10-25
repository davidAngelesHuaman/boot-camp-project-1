package com.nttdata.bootcamp.mscustomerProduct.aplication;

import com.nttdata.bootcamp.mscustomerProduct.infraestructure.PasiveCustomerProductRepository;
import com.nttdata.bootcamp.mscustomerProduct.model.PasiveCustomerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class PasiveCustomerProductServiceImpl implements PasiveCustomerProductService{
    @Autowired
    PasiveCustomerProductRepository pasiveCustomerProductRepository;
    @Override
    public Mono<PasiveCustomerProduct> createPasiveCustomProd(Mono<PasiveCustomerProduct> pasiveCustomerProductMono) {
        return pasiveCustomerProductMono.flatMap(pasiveCustomerProductRepository::insert);
    }

    @Override
    public Flux<PasiveCustomerProduct> listPasiveCustomProdAll() {
        return pasiveCustomerProductRepository.findAll().delayElements(Duration.ofSeconds(1)).log();
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
