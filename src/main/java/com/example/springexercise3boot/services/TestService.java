package com.example.springexercise3boot.services;

import com.example.springexercise3boot.models.test.Test;
import com.example.springexercise3boot.repositories.TestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestService {

    private final TestsRepository testsRepository;

    @Autowired
    public TestService(TestsRepository testsRepository) {
        this.testsRepository = testsRepository;
    }

    public List<Test> findAll() {
        return testsRepository.findAll();
    }
}
