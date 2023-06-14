package com.example.springexercise3boot.dto;

import com.example.springexercise3boot.models.test.QuestionType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionWithAnswersDTO {
    private long id;

    private String question;

    private QuestionType questionType;

    private List<String> answers;

}
