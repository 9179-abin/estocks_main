package com.estocks.estockscloudgateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {
	
	@Autowired
    AuthenticationFilter filter;
	
	@Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
        		.route("estocks-auth-api", r -> r.path("/estocks/auth/api/**")
                		.filters(f -> f.filter(filter))
                        .uri("lb://estocks-auth-api"))
        		
        		.route("estocks-command-api", r -> r.path("/estocks/command/api/**")
        				.filters(f -> f.filter(filter))
                        .uri("lb://estocks-command-api"))

                .route("estocks-query-api", r -> r.path("/estocks/query/api/**")
                		.filters(f -> f.filter(filter))
                        .uri("lb://estocks-query-api"))
                .build();
    }

}
