package com.ibm.service;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudOpsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudOpsServiceApplication.class, args);
	}

	@Bean
	public OpenAPI customDocOpenApi(){
		return new OpenAPI()
				.info(new Info()
						.title("Document for products")
						.version("1.0.0")
						.description("Demo Bootcamp Microservicios")
						.termsOfService("https://springdoc.org/")
						.contact(new Contact()
								.name("Gabriel Castillo")
								.email("gabcast@mx1.ibm.com")
								.url("https://github.ibm.com"))
						.license(new License()
								.name("Apache 2.0")
								.url("https://springdoc.org/")));

	}

}
