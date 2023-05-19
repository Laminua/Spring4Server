package com.example.springexercise3boot;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring4ServerApplication {

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(Spring4ServerApplication.class, args);
    }
}
