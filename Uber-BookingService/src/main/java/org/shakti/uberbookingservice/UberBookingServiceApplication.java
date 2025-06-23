package org.shakti.uberbookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient

// very important to add EntityScan annotation else spring will loop these entity into your local
// project instead from the common library, we want spring to scan these model from out
// common service

// *************** V.V.V.V.V.V Important step to configure common libraries *************
@EntityScan("org.shakti.ubercommonlibraries.Models")
public class UberBookingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UberBookingServiceApplication.class, args);
    }
}
