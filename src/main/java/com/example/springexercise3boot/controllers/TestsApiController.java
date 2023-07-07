package com.example.springexercise3boot.controllers;

import com.example.springexercise3boot.dto.*;
import com.example.springexercise3boot.models.test.Test;
import com.example.springexercise3boot.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
@Slf4j
public class TestsApiController {

    private final TestService testService;

    private final CheckTestService checkTestService;

    private final MapperService mapper;

    private final AnswersService answersService;

    private final AssignedTestService assignedTestService;

    private final TimeService timeService;

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

        return assignedTestService.findAssignedTestsByUserId(id).stream()
                .map(mapper::convertToAssignedTestsDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("tests/{id}")
    public TestWithQuestionsDTO getTestById(@PathVariable long id) {
        log.info("API: requesting test with questions by test id " + id);

        Test test = testService.findOne(id);

        return mapper.convertToTestWithQuestionsDTO(test);
    }

    @GetMapping("tests/questions/{testId},{userId}")
    public List<QuestionWithAnswersDTO> getQuestionsByTestId(@PathVariable long testId, @PathVariable long userId) {
        log.info("API: requesting questions with answers for test with id: " + testId);

        timeService.startTest(testId, userId);

        return testService.findQuestionsByTestId(testId).stream()
                .map(a -> mapper.convertToQuestionWithAnswersDTO(a, mapper.convertToAnswersDTO(answersService.getAnswers(a.getId()))))
                .collect(Collectors.toList());
    }

    @PostMapping("tests/questions/{testId}")
    public ResponseEntity<String> postUserResponseOnTest(@RequestBody UserAnswerDTO request,
                                                         @PathVariable long testId) {
        log.info("API: recieved user response on test with id " + testId);

        String checked = checkTestService.checkTest(testId, request);

        timeService.endTest();

        return new ResponseEntity<>(checked, HttpStatus.OK);
    }

    @GetMapping("tests/checkTestStatus/{testId},{userId}")
    public boolean checkTestStatus(@PathVariable long testId, @PathVariable long userId) {
        log.info("API: checking test status");

        return timeService.checkTestStatus(testId, userId);
    }
}
