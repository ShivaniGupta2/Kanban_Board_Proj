package com.example.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@SpringBootApplication
@EnableEurekaClient
@CrossOrigin(origins = "https://localhost:4200")
public class ApiGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator getRoutes(RouteLocatorBuilder builder)
	{
		return builder.routes().route(p->p.path("/kanban/v3/**").uri("http://localhost:8080"))
				.route(p->p.path("/kanban/v2/**").uri("http://localhost:8082")).
				route(p->p.path("/kanban/v1/**").uri("http://localhost:8085")).build();
	}
}
