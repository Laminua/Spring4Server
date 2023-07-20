package com.example.springexercise3boot.services;

import com.example.springexercise3boot.models.test.AssignedTest;
import com.example.springexercise3boot.models.test.Stats;
import com.example.springexercise3boot.models.test.TestEndReason;
import com.example.springexercise3boot.repositories.AssignedTestsRepository;
import com.example.springexercise3boot.util.NoAttemptsLeftException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class TimeService {

    private final AssignedTestService assignedTestService;

    private final AssignedTestsRepository assignedTestsRepository;

    public void startTest(long userId, long testId) {

        AssignedTest assignedTest = assignedTestService.findAssignedTestByTestIdAndUserId(userId, testId);

        if (assignedTest.getAttempts() == assignedTest.getTest().getMaxAttempts()) {
            throw new NoAttemptsLeftException("Исчерпано максимальное количество попыток прохождения теста");
        }

        Stats stats = assignedTest.getStats();

        if (stats == null) {
            stats = new Stats();
        }

        assignedTest.setRunning(true);
        stats.setStartTime(ZonedDateTime.now());
        assignedTest.setStats(stats);
        assignedTestsRepository.save(assignedTest);
    }

    public void endTest(long userId, long testId) {

        AssignedTest assignedTest = assignedTestService.findAssignedTestByTestIdAndUserId(userId, testId);

        assignedTest.setRunning(false);
        assignedTest.getStats().setEndTime(ZonedDateTime.now());
        assignedTest.getStats().setEndReason(TestEndReason.BY_USER);
        assignedTest.setAttempts(assignedTest.getAttempts() + 1);
        assignedTestsRepository.save(assignedTest);
    }

    public boolean checkTestStatus(long userId, long testId) {

        return assignedTestService.findAssignedTestByTestIdAndUserId(userId, testId).isRunning();
    }
}
