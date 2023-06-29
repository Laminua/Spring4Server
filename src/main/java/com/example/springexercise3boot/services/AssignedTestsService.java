package com.example.springexercise3boot.services;

import com.example.springexercise3boot.models.test.AssignedTests;
import com.example.springexercise3boot.repositories.AssignedTestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssignedTestsService {

    private final AssignedTestsRepository assignedTestsRepository;

    public List<AssignedTests> findAssignedTestsByUserId(long id) {

        return assignedTestsRepository.getAssignedTestsByUserId(id);
    }

    @Transactional
    public void update(AssignedTests updatedAssignedTests) {
        assignedTestsRepository.save(updatedAssignedTests);
    }
}
