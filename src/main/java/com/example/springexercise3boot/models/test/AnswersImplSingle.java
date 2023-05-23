package com.example.springexercise3boot.models.test;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

import static com.example.springexercise3boot.models.test.Answers.SINGLE_TYPE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName(SINGLE_TYPE)
public class AnswersImplSingle implements Answers {

    private Map<Integer, String> answers;

    private int rightAnswerKey;

}
