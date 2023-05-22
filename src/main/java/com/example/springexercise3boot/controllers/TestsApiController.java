package com.example.springexercise3boot.controllers;

import com.example.springexercise3boot.dto.TestDTO;
import com.example.springexercise3boot.models.test.Test;
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

    @GetMapping("tests")
    public List<TestDTO> getAllTests() {
        log.info("API: requesting list of all tests");

        return testService.findAll().stream()
                .map(this::convertToTestDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("tests/{id}")
    public List<TestDTO> getTestsForUser(@PathVariable long id) {
        log.info("API: requesting list of tests assigned to user with id " + id);

        return testService.findAssignedTestsByUserId(id).stream()
                .map(this::convertToTestDTO)
                .collect(Collectors.toList());
    }

    private TestDTO convertToTestDTO(Test test) {
        TestDTO testDTO = new TestDTO();
        testDTO.setId(test.getId());
        testDTO.setDescription(test.getDescription());

        return testDTO;
    }
}
