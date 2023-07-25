package com.example.springexercise3boot.models.test;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName(QuestionType.SINGLE_ANSWER_CONST)
public class AnswersImplSingle implements Answers {

    private Map<Integer, String> answers;

    private int rightAnswerKey;
}
