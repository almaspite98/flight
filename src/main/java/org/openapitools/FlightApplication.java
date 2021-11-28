package org.openapitools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightApplication {
    public static void main(String[] args) {
        new SpringApplication(FlightApplication.class).run(args);
    }
}