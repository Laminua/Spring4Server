package com.example.springexercise3boot.models.test;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "questionType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AnswersImplInput.class, name = QuestionType.USER_INPUT_CONST),
        @JsonSubTypes.Type(value = AnswersImplMultiple.class, name = QuestionType.MANY_ANSWERS_CONST),
        @JsonSubTypes.Type(value = AnswersImplSingle.class, name = QuestionType.SINGLE_ANSWER_CONST)
})
public interface Answers {

    Map<Integer, String> getAnswers();
}
