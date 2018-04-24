package com.zwp.slcp.frontapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FrontApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontApiApplication.class, args);
	}
}
