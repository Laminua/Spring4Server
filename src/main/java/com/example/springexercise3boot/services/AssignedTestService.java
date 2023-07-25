package com.example.springexercise3boot.services;

import com.example.springexercise3boot.dto.AssignedTestDTO;
import com.example.springexercise3boot.models.test.AssignedTest;
import com.example.springexercise3boot.repositories.AssignedTestsRepository;
import com.example.springexercise3boot.util.AssignedTestsNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssignedTestService {

    private final AssignedTestsRepository assignedTestsRepository;

    private final MapperService mapper;

    public List<AssignedTestDTO> getAssignedTestDTOListByUserId(long userId) {

        return assignedTestsRepository.getAssignedTestsByUserId(userId).stream()
                .map(mapper::convertToAssignedTestsDTO)
                .collect(Collectors.toList());
    }

    public AssignedTest findAssignedTestByTestIdAndUserId(long userId, long testId) {

        return assignedTestsRepository.getAssignedTestByUserIdAndTestId(userId, testId)
                .orElseThrow(() -> new AssignedTestsNotFoundException(
                        "No assigned test with id " + testId + " for user with id " + userId + " found"));
    }
}
