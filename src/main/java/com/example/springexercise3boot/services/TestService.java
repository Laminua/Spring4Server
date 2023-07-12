package com.example.springexercise3boot.services;

import com.example.springexercise3boot.dto.QuestionWithAnswersDTO;
import com.example.springexercise3boot.dto.TestDescriptionDTO;
import com.example.springexercise3boot.dto.TestWithQuestionsDTO;
import com.example.springexercise3boot.models.test.Test;
import com.example.springexercise3boot.repositories.QuestionsRepository;
import com.example.springexercise3boot.repositories.TestsRepository;
import com.example.springexercise3boot.util.TestNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TestService {

    private final TestsRepository testsRepository;

    private final QuestionsRepository questionsRepository;

    private final MapperService mapper;

    public List<TestDescriptionDTO> getAllTestsAsDTOList() {

        return testsRepository.findAll().stream()
                .map(mapper::convertToTestDescriptionDTO)
                .collect(Collectors.toList());
    }

    public TestWithQuestionsDTO getTestWithQuestionsDTOByTestId(long testId) {
        Test test = findOne(testId);

        return mapper.convertToTestWithQuestionsDTO(test);
    }

    public List<QuestionWithAnswersDTO> getQuestionWithAnswersDTOListByTestId(long testId) {

        return questionsRepository.findAllByTestId(testId).stream()
                .map(a -> mapper.convertToQuestionWithAnswersDTO(
                        a, mapper.convertToAnswersDTO(a.getAnswers())))
                .collect(Collectors.toList());
    }

    public Test findOne(long testId) {
        Optional<Test> foundTest = testsRepository.findById(testId);
        return foundTest.orElseThrow(() -> new TestNotFoundException("No test found by ID: " + testId));
    }
}
