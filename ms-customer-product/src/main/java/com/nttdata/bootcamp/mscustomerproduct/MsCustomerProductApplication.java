package com.nttdata.bootcamp.mscustomerproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsCustomerProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCustomerProductApplication.class, args);
	}

}
