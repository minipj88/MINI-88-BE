package com.ujutechnology.api8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Api8Application {

    public static void main(String[] args) {
        SpringApplication.run(Api8Application.class, args);
    }

}
