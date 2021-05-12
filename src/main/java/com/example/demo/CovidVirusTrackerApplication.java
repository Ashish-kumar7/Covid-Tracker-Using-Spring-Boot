package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// We use the @SpringBootApplication annotation in our Application or Main class to enable a host of features, e.g. Java-based Spring 
// configuration, component scanning, and in particular for enabling Spring Boot's auto-configuration feature.
@SpringBootApplication
@EnableScheduling
public class CovidVirusTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidVirusTrackerApplication.class, args);
		System.out.println("hello!!");
	}

}
