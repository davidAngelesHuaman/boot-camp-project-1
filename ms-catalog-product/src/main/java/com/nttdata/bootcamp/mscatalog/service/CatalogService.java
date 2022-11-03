package com.nttdata.bootcamp.mscatalog.service;

import com.nttdata.bootcamp.mscatalog.domain.Catalog;
import com.nttdata.bootcamp.mscatalog.repository.CatalogRepository;
import com.nttdata.bootcamp.mscatalog.web.maper.CatalogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional

public class CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private CatalogMapper catalogMapper;


    public Flux<Catalog> findAll(){
        log.debug("findAll executed");
        return catalogRepository.findAll();
    }


    public Mono<Catalog> findById(String catalogId){
        log.debug("findById executed {}", catalogId);
        return catalogRepository.findById(catalogId);
    }

    public Flux<Catalog> findByName(String parameter){
        log.debug("findByName executed {}", parameter);
        return catalogRepository.findByParameter(parameter);
    }


    public Mono<Catalog> create(Catalog parameter){
        log.debug("create executed {}", parameter);
        return catalogRepository.save(parameter);
    }


    public Mono<Catalog> update(String parameterId,  Catalog parameter){
        log.debug("update executed {}:{}", parameterId, parameter);
        return catalogRepository.findById(parameterId)
                .flatMap(dbparameter -> {
                    catalogMapper.update(dbparameter, parameter);
                    return catalogRepository.save(dbparameter);
                });
    }


    public Mono<Catalog> delete(String parameterId){
        log.debug("delete executed {}", parameterId);
        return catalogRepository.findById(parameterId)
                .flatMap(existingparameter -> catalogRepository.delete(existingparameter)
                        .then(Mono.just(existingparameter)));
    }

}
