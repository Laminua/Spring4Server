package com.example.springexercise3boot.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AssignedTestsNotFoundException extends RuntimeException {

    public AssignedTestsNotFoundException(String message) {
        super(message);
    }
}
