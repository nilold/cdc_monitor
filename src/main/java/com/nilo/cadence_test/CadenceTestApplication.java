package com.nilo.cadence_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.nilo.cadence_test.repository")
@SpringBootApplication
public class CadenceTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CadenceTestApplication.class, args);
    }

}
