package com.github.specht.pool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class MateraTalentPoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(MateraTalentPoolApplication.class, args);
	}

}
