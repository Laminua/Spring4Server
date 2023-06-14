package com.example.springexercise3boot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnswersService {

    private final TestService testService;

    public List<String> getAnswers(long questionId) {

        return testService.findAnswersByQuestionId(questionId).stream()
                .map(Objects::toString)
                .collect(Collectors.toList());
    }
}
