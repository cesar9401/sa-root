package com.cesar31.root;

import org.springframework.boot.SpringApplication;

public class TestSaRootApplication {

	public static void main(String[] args) {
		SpringApplication.from(SaRootApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
