package com.nttdata.bootcamp.mscatalog;

import com.nttdata.bootcamp.mscatalog.domain.Catalog;
import com.nttdata.bootcamp.mscatalog.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "Swagger Demo", version = "1.0", description = "Documentation APIs v1.0"))
public class MsCatalogApplication implements CommandLineRunner {

	@Autowired
	private CatalogRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MsCatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
		Flux.just(Catalog.builder().parameter("ProductType").value("Activos").build())
				.flatMap(p -> repository.save(p)).subscribe(p -> log.info("Insert: " + p.toString()));
		Flux.just(Catalog.builder().parameter("ProductType").value("Pasivos").build())
				.flatMap(p -> repository.save(p)).subscribe(p -> log.info("Insert: " + p.toString()));
		Flux.just(Catalog.builder().parameter("ProductSubType").value("Ahorro").build())
				.flatMap(p -> repository.save(p)).subscribe(p -> log.info("Insert: " + p.toString()));
		Flux.just(Catalog.builder().parameter("ProductSubType").value("Cuenta Corriente").build())
				.flatMap(p -> repository.save(p)).subscribe(p -> log.info("Insert: " + p.toString()));
		Flux.just(Catalog.builder().parameter("ProductSubType").value("Plazo Fijo").build())
				.flatMap(p -> repository.save(p)).subscribe(p -> log.info("Insert: " + p.toString()));
		Flux.just(Catalog.builder().parameter("ProductSubType").value("Credito Personal").build())
				.flatMap(p -> repository.save(p)).subscribe(p -> log.info("Insert: " + p.toString()));
		Flux.just(Catalog.builder().parameter("ProductSubType").value("Credito Empresarial").build())
				.flatMap(p -> repository.save(p)).subscribe(p -> log.info("Insert: " + p.toString()));
		Flux.just(Catalog.builder().parameter("ProductSubType").value("Tarjeta Credito Personal").build())
				.flatMap(p -> repository.save(p)).subscribe(p -> log.info("Insert: " + p.toString()));
		Flux.just(Catalog.builder().parameter("ProductSubType").value("Tarjeta Credito Empresarial").build())
				.flatMap(p -> repository.save(p)).subscribe(p -> log.info("Insert: " + p.toString()));
	}


	@Bean
	public ReactiveRedisTemplate<String, Catalog> reactiveJsonPostRedisTemplate(
			ReactiveRedisConnectionFactory connectionFactory) {

		RedisSerializationContext<String, Catalog> serializationContext = RedisSerializationContext
				.<String, Catalog>newSerializationContext(new StringRedisSerializer())
				.hashKey(new StringRedisSerializer())
				.hashValue(new Jackson2JsonRedisSerializer<>(Catalog.class))
				.build();


		return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
	}


}
