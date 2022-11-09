package com.estocks.estockseurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EstocksEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstocksEurekaServerApplication.class, args);
	}

}
