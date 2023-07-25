package com.example.springexercise3boot.controllers;

import com.example.springexercise3boot.dto.*;
import com.example.springexercise3boot.services.AssignedTestService;
import com.example.springexercise3boot.services.CheckTestService;
import com.example.springexercise3boot.services.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
@Slf4j
public class TestsApiController {

    private final TestService testService;

    private final CheckTestService checkTestService;

    private final AssignedTestService assignedTestService;

    @GetMapping("tests")
    public List<TestDescriptionDTO> getAllTests() {
        log.debug("API: requesting list of all tests");

        return testService.getAllTestsAsDTOList();
    }

    @GetMapping("tests/forUser/{userId}")
    public List<AssignedTestDTO> getTestsForUser(@PathVariable long userId) {
        log.debug("API: requesting list of tests assigned to user with id {}", userId);

        return assignedTestService.getAssignedTestDTOListByUserId(userId);
    }

    @GetMapping("tests/{testId}")
    public TestWithQuestionsDTO getTestById(@PathVariable long testId) {
        log.debug("API: requesting test with questions for test with id {}", testId);

        return testService.getTestWithQuestionsDTOByTestId(testId);
    }

    @GetMapping("tests/questions/{testId}")
    public List<QuestionWithAnswersDTO> getQuestionsByTestId(@PathVariable long testId) {
        log.debug("API: requesting questions with answers for test with id {}", testId);

        return testService.getQuestionWithAnswersDTOListByTestId(testId);
    }

    @PostMapping("tests/questions/{testId}")
    public ResponseEntity<String> postUserResponseOnTest(@RequestBody UserAnswerDTO request,
                                                         @PathVariable long testId) {
        log.debug("API: received user response on test with id {}", testId);

        String checked = checkTestService.checkTest(testId, request);

        return new ResponseEntity<>(checked, HttpStatus.OK);
    }
}
