package com.estocks.estocksauthapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "eStocks: Auth API", version = "1.0", description = "Documentation of eStocks: Auth API v1.0"))
public class EstocksAuthApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstocksAuthApiApplication.class, args);
	}

}
