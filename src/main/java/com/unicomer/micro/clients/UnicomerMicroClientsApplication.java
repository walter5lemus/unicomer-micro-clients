package com.unicomer.micro.clients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class UnicomerMicroClientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnicomerMicroClientsApplication.class, args);
	}

}
