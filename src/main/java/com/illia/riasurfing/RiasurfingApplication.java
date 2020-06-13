package com.illia.riasurfing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RiasurfingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiasurfingApplication.class, args);
	}

}
