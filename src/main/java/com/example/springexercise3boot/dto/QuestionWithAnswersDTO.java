package com.example.springexercise3boot.dto;

import com.example.springexercise3boot.models.test.QuestionType;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class QuestionWithAnswersDTO {
    private long id;

    private String question;

    private QuestionType questionType;

    private Map<Integer, String> answers;

}
