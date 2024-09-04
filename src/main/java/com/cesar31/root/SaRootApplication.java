package com.cesar31.root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SaRootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaRootApplication.class, args);
	}
}
