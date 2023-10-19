package com.bankingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class BankingAppApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/test");
		SpringApplication.run(BankingAppApplication.class, args);
	}

}
