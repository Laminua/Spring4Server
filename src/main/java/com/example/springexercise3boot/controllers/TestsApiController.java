package com.example.springexercise3boot.controllers;

import com.example.springexercise3boot.dto.TestDescriptionDTO;
import com.example.springexercise3boot.models.test.Test;
import com.example.springexercise3boot.services.TestService;
import com.example.springexercise3boot.services.MapperService;
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

    @GetMapping("tests")
    public List<TestDescriptionDTO> getAllTests() {
        log.info("API: requesting list of all tests");

        return testService.findAll().stream()
                .map(mapper::convertToTestDescriptionDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("tests/{id}")
    public List<TestDescriptionDTO> getTestsForUser(@PathVariable long id) {
        log.info("API: requesting list of tests assigned to user with id " + id);

        return testService.findAssignedTestsByUserId(id).stream()
                .map(mapper::convertToTestDescriptionDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("tests/single/{id}")
    public TestDescriptionDTO getTestById(@PathVariable long id) {

        Test test = testService.findOne(id);

        return mapper.convertToTestDescriptionDTO(test);
    }


}
