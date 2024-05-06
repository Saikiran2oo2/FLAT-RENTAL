package com.capstone.landlordmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LandlordmoduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandlordmoduleApplication.class, args);
	}

}
