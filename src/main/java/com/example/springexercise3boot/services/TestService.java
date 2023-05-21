package com.example.springexercise3boot.services;

import com.example.springexercise3boot.models.test.AssignedTests;
import com.example.springexercise3boot.models.test.Test;
import com.example.springexercise3boot.repositories.AssignedTestsRepository;
import com.example.springexercise3boot.repositories.QuestionsRepository;
import com.example.springexercise3boot.repositories.TestsRepository;
import com.example.springexercise3boot.repositories.UserProfilesRepository;
import com.example.springexercise3boot.util.TestNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TestService {

    private final TestsRepository testsRepository;

    private final QuestionsRepository questionsRepository;

    private final AssignedTestsRepository assignedTestsRepository;

    private final UserProfilesRepository userProfilesRepository;

    public List<Test> findAll() {
        return testsRepository.findAll();
    }

    public Test findOne(long id) {
        Optional<Test> foundTest = testsRepository.findById(id);
        return foundTest.orElseThrow(() -> new TestNotFoundException("No test found by ID: " + id));
    }

    public List<Test> findAssignedTestsByUserId(long id) {

//        List<AssignedTests> assignedTests = assignedTestsRepository.getAssignedTestsByUserId(id);

//        return assignedTests.stream()
//                .map(AssignedTests::getTest).collect(Collectors.toList());

        return assignedTestsRepository.getAssignedTestsByUserId(id);
    }
}
