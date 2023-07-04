package com.example.springexercise3boot.services;

import com.example.springexercise3boot.models.test.AssignedTest;
import com.example.springexercise3boot.repositories.AssignedTestsRepository;
import com.example.springexercise3boot.util.AssignedTestsNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssignedTestService {

    private final AssignedTestsRepository assignedTestsRepository;

    public List<AssignedTest> findAssignedTestsByUserId(long id) {

        return assignedTestsRepository.getAssignedTestsByUserId(id);
    }

    @Transactional
    public void update(AssignedTest updatedAssignedTest) {

        assignedTestsRepository.save(updatedAssignedTest);
    }

    public AssignedTest getAssignedTest(long id) {

        return assignedTestsRepository.findAssignedTestById(id).orElseThrow();
    }

    public AssignedTest findAssignedTestByTestIdAndUserId(long userId, long testId) {

        return assignedTestsRepository.getAssignedTestByUserIdAndTestId(userId, testId)
                .orElseThrow(() -> new AssignedTestsNotFoundException(
                        "No assigned test with id " + testId + " for user with id " + userId + " found"));
    }
}
