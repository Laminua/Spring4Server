package com.example.springexercise3boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Spring4ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Spring4ServerApplication.class, args);
    }
}
