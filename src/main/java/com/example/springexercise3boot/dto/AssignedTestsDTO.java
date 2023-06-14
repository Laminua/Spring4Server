package com.example.springexercise3boot.dto;

import com.example.springexercise3boot.models.test.Test;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignedTestsDTO {
    private long id;

    private Test test;

    private boolean finished;

    private int attempts;
}
