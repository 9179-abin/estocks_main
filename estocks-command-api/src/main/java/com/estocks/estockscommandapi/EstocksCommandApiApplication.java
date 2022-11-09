package com.estocks.estockscommandapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "eStocks: Command API", version = "1.0", description = "Documentation of eStocks: Command API v1.0"))
public class EstocksCommandApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstocksCommandApiApplication.class, args);
	}

}
