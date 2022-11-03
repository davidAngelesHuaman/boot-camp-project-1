package com.nttdata.bootcamp.mscatalog.repository;

import java.util.UUID;

import com.nttdata.bootcamp.mscatalog.domain.Catalog;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor

public class CatalogRepository {

    private final ReactiveRedisOperations<String, Catalog> reactiveRedisOperations;

    public Flux<Catalog> findAll() {
        return this.reactiveRedisOperations.<String, Catalog>opsForHash().values("parameters");
    }

    public Mono<Catalog> findById(String id) {
        return this.reactiveRedisOperations.<String, Catalog>opsForHash().get("parameters", id);
    }

    public Flux<Catalog> findByParameter(String parameter) {
        return this.findAll().filter(p -> p.getParameter().equals(parameter));
    }

    public Mono<Catalog> save(Catalog parameter) {
        if (parameter.getId() == null) {
            String id = UUID.randomUUID().toString();
            parameter.setId(id);
        }
        return this.reactiveRedisOperations.<String, Catalog>opsForHash()
                .put("parameters", parameter.getId(), parameter).log().map(p -> parameter);
    }

    public Mono<Boolean> deleteAll() {
        return this.reactiveRedisOperations.<String, Catalog>opsForHash().delete("parameters");
    }

    public Mono<Catalog> delete(Catalog parameter) {
        return this.reactiveRedisOperations.<String, Catalog>opsForHash().remove("parameters", parameter.getId())
                .flatMap(p -> Mono.just(parameter));
    }


}
