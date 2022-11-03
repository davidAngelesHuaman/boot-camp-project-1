package com.nttdata.bootcamp.mscatalog.web;

import java.net.URI;

import javax.validation.Valid;

import com.nttdata.bootcamp.mscatalog.service.CatalogService;
import com.nttdata.bootcamp.mscatalog.web.maper.CatalogMapper;
import com.nttdata.bootcamp.mscatalog.web.model.CatalogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/catalog")


public class CatalogController {

    @Value("${spring.application.name}")
    String name;

    @Value("${server.port}")
    String port;


    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CatalogMapper catalogMapper;


    @GetMapping
    public Mono<ResponseEntity<Flux<CatalogModel>>>  getAll(){
        log.info("getAll executed");

        return Mono.just(ResponseEntity.ok()
                .body(catalogService.findAll()
                        .map(catalog -> catalogMapper.entityToModel(catalog))));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CatalogModel>> getById(@PathVariable String id){
        log.info("getById executed {}", id);
        return catalogService.findById(id)
                .map(catalog -> catalogMapper.entityToModel(catalog))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public Mono<ResponseEntity<Flux<CatalogModel>>> getByName(@PathVariable String name){
        log.info("getByName executed {}", name);
        return Mono.just(ResponseEntity.ok()
                .body(catalogService.findByName(name)
                        .map(catalog -> catalogMapper.entityToModel(catalog))));
    }

    @PostMapping
    public Mono<ResponseEntity<CatalogModel>> create(@Valid @RequestBody CatalogModel request){
        log.info("create executed {}", request);
        return catalogService.create(catalogMapper.modelToEntity(request))
                .map(catalog -> catalogMapper.entityToModel(catalog))
                .flatMap(c -> Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name, port, "Catalog", c.getId())))
                        .body(c)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<CatalogModel>> updateById(@PathVariable String id, @Valid @RequestBody CatalogModel request){
        log.info("updateById executed {}:{}", id, request);
        return catalogService.update(id, catalogMapper.modelToEntity(request))
                .map(catalog -> catalogMapper.entityToModel(catalog))
                .flatMap(c -> Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name, port, "Catalog", c.getId())))
                        .body(c)))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id){
        log.info("deleteById executed {}", id);
        return catalogService.delete(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
