package com.synergisticit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class AirlineReservationApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AirlineReservationApplication.class, args);
	}

	public SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(SpringApplication.class);
	}
}
