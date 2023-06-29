package com.example.springexercise3boot.models.test;

import com.example.springexercise3boot.dto.UserAnswerDTO;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeName(QuestionType.USER_INPUT_CONST)
public class UserAnswerInputType extends UserAnswerDTO {

    private String answers;

    public UserAnswerInputType(long userId, long questionId, QuestionType questionType, String answers) {
        super(userId, questionId, questionType);
        this.answers = answers;
    }
}
