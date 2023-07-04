package com.example.springexercise3boot.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class NoAttemptsLeftException extends RuntimeException {

    public NoAttemptsLeftException(String message) {
        super(message);
    }
}
