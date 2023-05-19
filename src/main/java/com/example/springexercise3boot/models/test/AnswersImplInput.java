package com.example.springexercise3boot.models.test;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("input")
public class AnswersImplInput implements Answers {

    private String answer;

}
