package com.example.springexercise3boot.services;

import com.example.springexercise3boot.dto.UserAnswerDTO;
import com.example.springexercise3boot.models.test.*;
import com.example.springexercise3boot.repositories.AssignedTestsRepository;
import com.example.springexercise3boot.repositories.QuestionsRepository;
import com.example.springexercise3boot.repositories.TestsRepository;
import com.example.springexercise3boot.util.TestNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TestService {

    private final TestsRepository testsRepository;

    private final AssignedTestsRepository assignedTestsRepository;

    private final QuestionsRepository questionsRepository;

    public List<Test> findAll() {
        return testsRepository.findAll();
    }

    public Test findOne(long id) {
        Optional<Test> foundTest = testsRepository.findById(id);
        return foundTest.orElseThrow(() -> new TestNotFoundException("No test found by ID: " + id));
    }

    public List<AssignedTests> findAssignedTestsByUserId(long id) {

        return assignedTestsRepository.getAssignedTestsByUserId(id);
    }

    public List<Question> findQuestionsByTestId(long id) {

        return questionsRepository.findAllByTestId(id);
    }

    public String checkTest(String id, UserAnswerDTO[] response) {

        long testId = Long.parseLong(id);

        Test test = testsRepository.findById(testId).orElseThrow();

        List<Question> questions = test.getQuestions();

        for (UserAnswerDTO userAnswerDTO : response) {
            if (userAnswerDTO.getClass().equals(UserAnswerInputType.class)) {

                UserAnswerInputType userAnswerInputType = (UserAnswerInputType) userAnswerDTO;

                AnswersImplInput rightAnswer = (AnswersImplInput) questions.stream()
                        .filter(q -> userAnswerInputType.getQuestionId() == q.getId())
                        .findFirst().orElse(null).getAnswers();

                checkUserInputType(rightAnswer, userAnswerInputType.getAnswers());

            } else if (userAnswerDTO.getClass().equals(UserAnswerMultipleType.class)) {

                UserAnswerMultipleType userAnswerMultipleType = (UserAnswerMultipleType) userAnswerDTO;

                AnswersImplMultiple rightAnswer = (AnswersImplMultiple) questions.stream()
                        .filter(q -> userAnswerMultipleType.getQuestionId() == q.getId())
                        .findAny().orElse(null).getAnswers();

                checkManyAnswersType(rightAnswer, userAnswerMultipleType.getAnswers());

            } else if (userAnswerDTO.getClass().equals(UserAnswerSingleType.class)) {

                UserAnswerSingleType userAnswerSingleType = (UserAnswerSingleType) userAnswerDTO;

                AnswersImplSingle rightAnswer = (AnswersImplSingle) questions.stream()
                        .filter(q -> userAnswerSingleType.getQuestionId() == q.getId())
                        .findAny().orElse(null).getAnswers();

                checkSingleAnswerType(rightAnswer, userAnswerSingleType.getAnswers());
            }
        }
        return "Тест номер " + testId + " обработан";
    }

    private boolean checkSingleAnswerType(AnswersImplSingle answersImplSingle, int userAnswer) {

        int rightAnswerKey = answersImplSingle.getRightAnswerKey();

        System.out.println("Right answer: " + rightAnswerKey + ", user answer: " + userAnswer
                + ", is right answered? - " + (rightAnswerKey == userAnswer)); // for testing
        return rightAnswerKey == userAnswer;
    }

    private boolean checkManyAnswersType(AnswersImplMultiple answersImplMultiple, List<Integer> userAnswer) {

        List<Integer> rightAnswersKeys = answersImplMultiple.getRightAnswersKeys();

        System.out.println("Right answer: " + rightAnswersKeys + ", user answer: " + userAnswer
                + ", is right answered? - " + (userAnswer.containsAll(rightAnswersKeys))); // for testing
        return userAnswer.containsAll(rightAnswersKeys);
    }

    private boolean checkUserInputType(AnswersImplInput answersImplInput, String userAnswer) {

        String rightAnswer = answersImplInput.getAnswer();

        System.out.println("Right answer: " + rightAnswer + ", user answer: " + userAnswer
                + ", is right answered? - " + (rightAnswer.equalsIgnoreCase(userAnswer))); // for testing
        return rightAnswer.equalsIgnoreCase(userAnswer);
    }
}
