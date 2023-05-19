package com.example.springexercise3boot.models.test;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "answer_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AnswersImplInput.class, name = Answers.INPUT_TYPE),
        @JsonSubTypes.Type(value = AnswersImplMultiple.class, name = Answers.MULTIPLE_TYPE),
        @JsonSubTypes.Type(value = AnswersImplSingle.class, name = Answers.SINGLE_TYPE)
})
public interface Answers {
    String INPUT_TYPE = "input";
    String MULTIPLE_TYPE = "multiple";
    String SINGLE_TYPE = "single";
}
