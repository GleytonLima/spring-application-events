package com.maolabs.etapas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EtapasApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtapasApplication.class, args);
    }

}
