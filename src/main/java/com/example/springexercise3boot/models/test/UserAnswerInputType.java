package com.example.springexercise3boot.models.test;

import com.example.springexercise3boot.dto.UserAnswerDTO;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonTypeName(QuestionType.USER_INPUT_CONST)
public class UserAnswerInputType extends UserAnswerDTO {

    long userId;

    long questionId;

    QuestionType questionType;

    String answers;
}
