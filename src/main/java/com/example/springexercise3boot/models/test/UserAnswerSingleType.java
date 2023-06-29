package com.example.springexercise3boot.models.test;

import com.example.springexercise3boot.dto.UserAnswerDTO;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeName(QuestionType.SINGLE_ANSWER_CONST)
public class UserAnswerSingleType extends UserAnswerDTO {

    private int answers;

    public UserAnswerSingleType(long userId, long questionId, QuestionType questionType, int answers) {
        super(userId, questionId, questionType);
        this.answers = answers;
    }
}
