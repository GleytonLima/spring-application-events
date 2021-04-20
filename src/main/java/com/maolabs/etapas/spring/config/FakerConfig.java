package com.maolabs.etapas.spring.config;

import com.github.javafaker.ChuckNorris;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class FakerConfig {
    @Bean
    public ChuckNorris chuckNorris() {
        return (new Faker()).chuckNorris();
    }
}
