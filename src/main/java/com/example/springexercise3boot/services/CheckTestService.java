package com.example.springexercise3boot.services;

import com.example.springexercise3boot.dto.UserAnswerDTO;
import com.example.springexercise3boot.models.test.*;
import com.example.springexercise3boot.repositories.AssignedTestsRepository;
import com.example.springexercise3boot.util.NoAttemptsLeftException;
import com.example.springexercise3boot.util.QuestionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckTestService {

    private final AssignedTestService assignedTestService;

    private final AssignedTestsRepository assignedTestsRepository;

    Map<Long, Question> questionsMap;

    public String checkTest(long testId, UserAnswerDTO dto) {

        long userId = dto.getUserId();

        AssignedTest assignedTest = assignedTestService.findAssignedTestByTestIdAndUserId(userId, testId);

        Test test = assignedTest.getTest();

        int testMaxAttempts = test.getMax_attempts(); // 0 means unlimited

        int usedAttempts = assignedTest.getAttempts();

        if (testMaxAttempts > 0 && testMaxAttempts == usedAttempts) {
            throw new NoAttemptsLeftException("Исчерпано максимальное количество попыток прохождения теста");
        }

        questionsMap = test.getQuestions().stream()
                .collect(Collectors.toMap(Question::getId, Function.identity()));

        List<UserAnswer> userAnswers = dto.getUserAnswers();

        long questionId;

        for (UserAnswer userAnswer : userAnswers) {

            questionId = validateQuestionId(userAnswer.getQuestionId());

            switch (userAnswer.getQuestionType()) {

                case SINGLE_ANSWER:
                    checkSingleAnswerTypeQuestion(questionId, userAnswer);
                    break;

                case MANY_ANSWERS:
                    checkManyAnswersTypeQuestion(questionId, userAnswer);
                    break;

                case USER_INPUT:
                    checkUserInputTypeQuestion(questionId, userAnswer);
                    break;
            }
        }


        // ToDo for statistics
        assignedTest.setFinished(true);
        assignedTest.setAttempts(assignedTest.getAttempts() + 1);
        assignedTestsRepository.save(assignedTest);

        return "Тест с id " + testId + " для пользователя с id " + userId + " успешно обработан";
    }

    private boolean checkSingleAnswerTypeQuestion(long questionId,
                                                  UserAnswer userAnswer) {

        UserAnswerSingleType userAnswerSingleType = (UserAnswerSingleType) userAnswer;

        AnswersImplSingle answersImplSingle = (AnswersImplSingle) questionsMap.get(questionId).getAnswers();

        int rightAnswerKey = answersImplSingle.getRightAnswerKey();

        int userAnswerToCheck = userAnswerSingleType.getAnswer();

        System.out.println("Right answer: " + rightAnswerKey + ", user answer: " + userAnswerToCheck
                + ", is right answered? - " + (rightAnswerKey == userAnswerToCheck)); // for testing

        return rightAnswerKey == userAnswerToCheck;
    }

    private boolean checkManyAnswersTypeQuestion(long questionId,
                                                 UserAnswer userAnswer) {

        UserAnswerMultipleType userAnswerMultipleType = (UserAnswerMultipleType) userAnswer;

        AnswersImplMultiple rightAnswer = (AnswersImplMultiple) questionsMap
                .get(questionId).getAnswers();

        Set<Integer> rightAnswersSet = new HashSet<>(rightAnswer.getRightAnswersKeys());
        Set<Integer> userAnswersSet = new HashSet<>(userAnswerMultipleType.getAnswer());

        System.out.println("Right answer: " + rightAnswersSet + ", user answer: " + userAnswersSet
                + ", is right answered? - " + rightAnswersSet.equals(userAnswersSet)); // for testing

        return rightAnswersSet.equals(userAnswersSet);
    }

    private boolean checkUserInputTypeQuestion(long questionId, UserAnswer userAnswer) {

        UserAnswerInputType userAnswerInputType = (UserAnswerInputType) userAnswer;

        AnswersImplInput answersImplInput = (AnswersImplInput) questionsMap.get(questionId).getAnswers();

        String rightAnswer = answersImplInput.getAnswer();

        String userAnswerToCheck = userAnswerInputType.getAnswer().trim();

        System.out.println("Right answer: " + rightAnswer + ", user answer: " + userAnswerToCheck
                + ", is right answered? - " + (rightAnswer.equalsIgnoreCase(userAnswerToCheck))); // for testing

        return rightAnswer.equalsIgnoreCase(userAnswerToCheck);
    }

    private long validateQuestionId(long questionId) {
        if (!questionsMap.containsKey(questionId)) {
            throw new QuestionNotFoundException("There's no question with id " + questionId + " in current test");
        }
        return questionId;
    }
}
