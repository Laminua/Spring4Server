package com.example.springexercise3boot.services;

import com.example.springexercise3boot.models.test.Test;
import com.example.springexercise3boot.repositories.QuestionsRepository;
import com.example.springexercise3boot.repositories.TestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TestService {

    private final TestsRepository testsRepository;

    private final QuestionsRepository questionsRepository;

    public List<Test> findAll() {
        return testsRepository.findAll();
    }
}
