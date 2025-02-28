package com.example.ogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.ogs")
public class OgsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OgsApplication.class, args);
	}

}
