package com.example.springexercise3boot.models.test;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.example.springexercise3boot.models.test.Answers.INPUT_TYPE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName(INPUT_TYPE)
public class AnswersImplInput implements Answers {

    private String answer;

}
