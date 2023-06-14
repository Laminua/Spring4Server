package com.example.springexercise3boot.services;

import com.example.springexercise3boot.models.test.Answers;
import com.example.springexercise3boot.models.test.AssignedTests;
import com.example.springexercise3boot.models.test.Question;
import com.example.springexercise3boot.models.test.Test;
import com.example.springexercise3boot.repositories.AssignedTestsRepository;
import com.example.springexercise3boot.repositories.QuestionsRepository;
import com.example.springexercise3boot.repositories.TestsRepository;
import com.example.springexercise3boot.util.TestNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TestService {

    private final TestsRepository testsRepository;

    private final AssignedTestsRepository assignedTestsRepository;

    private final QuestionsRepository questionsRepository;

    public List<Test> findAll() {
        return testsRepository.findAll();
    }

    public Test findOne(long id) {
        Optional<Test> foundTest = testsRepository.findById(id);
        return foundTest.orElseThrow(() -> new TestNotFoundException("No test found by ID: " + id));
    }

    public List<AssignedTests> findAssignedTestsByUserId(long id) {

        return assignedTestsRepository.getAssignedTestsByUserId(id);
    }

    public List<Question> findQuestionsByTestId(long id) {

        return questionsRepository.findAllByTestId(id);
    }

    public List<Answers> findAnswersByQuestionId(long id) {

        return questionsRepository.findAllByQuestionId(id);
    }
}
