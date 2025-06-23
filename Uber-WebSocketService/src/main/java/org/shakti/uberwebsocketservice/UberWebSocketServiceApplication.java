package org.shakti.uberwebsocketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UberWebSocketServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UberWebSocketServiceApplication.class, args);
    }

}
