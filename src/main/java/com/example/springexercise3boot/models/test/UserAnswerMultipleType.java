package com.example.springexercise3boot.models.test;

import com.example.springexercise3boot.dto.UserAnswerDTO;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonTypeName(QuestionType.MANY_ANSWERS_CONST)
public class UserAnswerMultipleType extends UserAnswerDTO {

    private List<Integer> answers;

    public UserAnswerMultipleType(long userId, long questionId, QuestionType questionType, List<Integer> answers) {
        super(userId, questionId, questionType);
        this.answers = answers;
    }
}
