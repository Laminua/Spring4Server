package com.example.springexercise3boot.dto;

import com.example.springexercise3boot.models.test.QuestionType;
import com.example.springexercise3boot.models.test.UserAnswerInputType;
import com.example.springexercise3boot.models.test.UserAnswerMultipleType;
import com.example.springexercise3boot.models.test.UserAnswerSingleType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "questionType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserAnswerInputType.class, name = QuestionType.USER_INPUT_CONST),
        @JsonSubTypes.Type(value = UserAnswerMultipleType.class, name = QuestionType.MANY_ANSWERS_CONST),
        @JsonSubTypes.Type(value = UserAnswerSingleType.class, name = QuestionType.SINGLE_ANSWER_CONST)
})
@Getter
@Setter
@AllArgsConstructor
public abstract class UserAnswerDTO {

    private final long userId;

    private final long questionId;

    private final QuestionType questionType;
}
