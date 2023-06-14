package com.example.springexercise3boot.controllers;

import com.example.springexercise3boot.dto.*;
import com.example.springexercise3boot.models.test.Question;
import com.example.springexercise3boot.models.test.Test;
import com.example.springexercise3boot.services.AnswersService;
import com.example.springexercise3boot.services.MapperService;
import com.example.springexercise3boot.services.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
@Slf4j
public class TestsApiController {

    private final TestService testService;

    private final MapperService mapper;

    private final AnswersService answersService;

    @GetMapping("tests")
    public List<TestDescriptionDTO> getAllTests() {
        log.info("API: requesting list of all tests");

        return testService.findAll().stream()
                .map(mapper::convertToTestDescriptionDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("tests/forUser/{id}")
    public List<AssignedTestsDTO> getTestsForUser(@PathVariable long id) {
        log.info("API: requesting list of tests assigned to user with id " + id);

        return testService.findAssignedTestsByUserId(id).stream()
                .map(mapper::convertToAssignedTestsDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("tests/{id}")
    public TestWithQuestionsDTO getTestById(@PathVariable long id) {
        log.info("API: requesting test with questions by test id " + id);

        Test test = testService.findOne(id);

        return mapper.convertToTestWithQuestionsDTO(test);
    }

    @GetMapping("tests/questions/{id}")
    public List<QuestionWithAnswersDTO> getQuestionsByTestId(@PathVariable long id) {
        log.info("API: requesting questions with answers for test with id: " + id);

        return testService.findQuestionsByTestId(id).stream()
                .map(a -> mapper.convertToQuestionsDTO(a, answersService.getAnswers(a.getId())))
                .collect(Collectors.toList());
    }
}
