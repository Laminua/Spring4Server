package com.example.springexercise3boot.models.test;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName(QuestionType.MANY_ANSWERS_CONST)
public class AnswersImplMultiple implements Answers {

    private Map<Integer, String> answers;

    private List<Integer> rightAnswersKeys;
}
