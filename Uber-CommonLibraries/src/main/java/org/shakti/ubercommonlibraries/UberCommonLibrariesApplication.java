package org.shakti.ubercommonlibraries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UberCommonLibrariesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberCommonLibrariesApplication.class, args);
    }

}
