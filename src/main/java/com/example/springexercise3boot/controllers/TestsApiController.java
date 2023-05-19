package com.example.springexercise3boot.controllers;

import com.example.springexercise3boot.models.test.Test;
import com.example.springexercise3boot.services.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Slf4j
public class TestsApiController {

    TestService testService;

    @Autowired
    public TestsApiController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("tests")
    public List<Test> getTests() {
        log.info("API: requesting list of tests");

        return testService.findAll();
    }
}
