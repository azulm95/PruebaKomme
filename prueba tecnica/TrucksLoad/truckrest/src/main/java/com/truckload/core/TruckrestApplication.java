package com.truckload.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@ComponentScan({"com.truckload.core.*"})
@SpringBootApplication
public class TruckrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TruckrestApplication.class, args);
	}

}
