package com.example.springexercise3boot.services;

import com.example.springexercise3boot.dto.UserAnswerDTO;
import com.example.springexercise3boot.models.test.*;
import com.example.springexercise3boot.repositories.AssignedTestsRepository;
import com.example.springexercise3boot.repositories.TestsRepository;
import com.example.springexercise3boot.util.QuestionNotFoundException;
import com.example.springexercise3boot.util.TestNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckTestService {

    private final TestsRepository testsRepository;

    private final AssignedTestsRepository assignedTestsRepository;

    private final AssignedTestsService assignedTestsService;

    public String checkTest(String id, UserAnswerDTO[] response) {

        long testId = Long.parseLong(id);

        Test test = testsRepository.findById(testId)
                .orElseThrow(() -> new TestNotFoundException("No test found by given id"));

        List<Question> questions = test.getQuestions();

        long userId = Arrays.stream(response).findFirst().map(UserAnswerDTO::getUserId).orElse(0L);

        for (UserAnswerDTO userAnswerDTO : response) {
            if (userAnswerDTO.getClass().equals(UserAnswerInputType.class)) {

                UserAnswerInputType userAnswerInputType = (UserAnswerInputType) userAnswerDTO;

                AnswersImplInput rightAnswer = (AnswersImplInput) questions.stream()
                        .filter(q -> userAnswerInputType.getQuestionId() == q.getId())
                        .findFirst()
                        .orElseThrow(() -> new QuestionNotFoundException("No question found by given id")).getAnswers();

                checkUserInputType(rightAnswer, userAnswerInputType.getAnswers());

            } else if (userAnswerDTO.getClass().equals(UserAnswerMultipleType.class)) {

                UserAnswerMultipleType userAnswerMultipleType = (UserAnswerMultipleType) userAnswerDTO;

                AnswersImplMultiple rightAnswer = (AnswersImplMultiple) questions.stream()
                        .filter(q -> userAnswerMultipleType.getQuestionId() == q.getId())
                        .findAny()
                        .orElseThrow(() -> new QuestionNotFoundException("No question found by given id")).getAnswers();

                checkManyAnswersType(rightAnswer, userAnswerMultipleType.getAnswers());

            } else if (userAnswerDTO.getClass().equals(UserAnswerSingleType.class)) {

                UserAnswerSingleType userAnswerSingleType = (UserAnswerSingleType) userAnswerDTO;

                AnswersImplSingle rightAnswer = (AnswersImplSingle) questions.stream()
                        .filter(q -> userAnswerSingleType.getQuestionId() == q.getId())
                        .findAny()
                        .orElseThrow(() -> new QuestionNotFoundException("No question found by given id")).getAnswers();

                checkSingleAnswerType(rightAnswer, userAnswerSingleType.getAnswers());
            }
        }

        AssignedTests assignedTests = assignedTestsRepository.getAssignedTestsByUserIdAndTestId(userId, testId);
        assignedTests.setFinished(true);
        assignedTests.setAttempts(assignedTests.getAttempts() + 1);
        assignedTestsService.update(assignedTests);

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
