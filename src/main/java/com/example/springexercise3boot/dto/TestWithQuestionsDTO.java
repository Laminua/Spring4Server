package com.example.springexercise3boot.dto;

import com.example.springexercise3boot.models.test.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestWithQuestionsDTO {

    private long id;

    private String description;

    private List<Question> questions;

    private int max_attempts;
}
