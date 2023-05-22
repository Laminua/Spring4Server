package com.example.springexercise3boot.dto;

import com.example.springexercise3boot.models.test.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestDTO {

    long id;

    String description;

    List<Question> questionList;
}
