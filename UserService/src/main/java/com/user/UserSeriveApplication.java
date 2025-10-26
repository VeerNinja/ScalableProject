package com.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class UserSeriveApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserSeriveApplication.class, args);
	}

}
