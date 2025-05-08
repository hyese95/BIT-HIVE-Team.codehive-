package com.example.codehive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources(@PropertySource("classpath:env.properties"))
@SpringBootApplication
public class CodehiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodehiveApplication.class, args);
	}

}
