package com.example.springexercise3boot.models.test;

import com.example.springexercise3boot.dto.UserAnswerDTO;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonTypeName(QuestionType.MANY_ANSWERS_CONST)
public class UserAnswerMultipleType extends UserAnswerDTO {

    long userId;

    long questionId;

    QuestionType questionType;

    List<Integer> answers;
}
