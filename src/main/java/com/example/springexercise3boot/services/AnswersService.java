package com.example.springexercise3boot.services;

import com.example.springexercise3boot.models.test.Answers;
import com.example.springexercise3boot.repositories.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnswersService {

    private final QuestionsRepository questionsRepository;

    public Answers getAnswers(long questionId) {

        return questionsRepository.findAllByQuestionId(questionId);
    }
}
