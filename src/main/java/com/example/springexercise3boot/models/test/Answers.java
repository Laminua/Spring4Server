package com.example.springexercise3boot.models.test;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AnswersImplInput.class, name = "input"),
        @JsonSubTypes.Type(value = AnswersImplMultiple.class, name = "multiple"),
        @JsonSubTypes.Type(value = AnswersImplSingle.class, name = "single")
})
public interface Answers {

}
