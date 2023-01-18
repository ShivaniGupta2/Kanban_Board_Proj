package com.example.KanbanMongoService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class KanbanMongoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanbanMongoServiceApplication.class, args);
	}

}
