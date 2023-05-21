package com.example.springexercise3boot.controllers;

import com.example.springexercise3boot.models.test.AssignedTests;
import com.example.springexercise3boot.models.test.Test;
import com.example.springexercise3boot.repositories.AssignedTestsRepository;
import com.example.springexercise3boot.services.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
@Slf4j
public class TestsApiController {

    private final TestService testService;
    private final AssignedTestsRepository repository;

    @GetMapping("tests")
    public List<Test> getTests() {
        log.info("API: requesting list of tests");

        return testService.findAll();
    }

    @GetMapping("teste")
    public List<AssignedTests> getTeste(@RequestParam Integer userId) {

        return repository.getAssignedTestsByUserIdWithFetch(userId);
    }
}
