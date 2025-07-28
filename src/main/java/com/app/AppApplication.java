package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the TalonOneApp Spring Boot application.
 * This class initializes the Spring application context and starts the embedded web server.
 * The @SpringBootApplication annotation enables auto-configuration, component scanning,
 * and other Spring Boot features.
 */
@SpringBootApplication
public class AppApplication {

    /**
     * The main method that serves as the application's entry point.
     * It delegates to Spring Boot's SpringApplication class to launch the application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}