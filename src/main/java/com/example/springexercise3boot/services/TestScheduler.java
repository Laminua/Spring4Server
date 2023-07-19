package com.example.springexercise3boot.services;

import com.example.springexercise3boot.models.test.AssignedTest;
import com.example.springexercise3boot.repositories.AssignedTestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestScheduler {

    private final AssignedTestsRepository assignedTestsRepository;

    @Scheduled(fixedRate = 6000L)
    public void stopTest() {

        List<AssignedTest> runningTests = assignedTestsRepository.getRunningAssignedTests();

        for (AssignedTest runningTest : runningTests) {

            long timeLimit = runningTest.getTest().getTimeRestriction();
            ZonedDateTime startTime = runningTest.getStats().getStartTime();
            ZonedDateTime endTime = startTime.plusMinutes(timeLimit);

            if (ZonedDateTime.now().isAfter(endTime)) {
                runningTest.getStats().setEndTime(endTime);
                runningTest.setRunning(false);
                assignedTestsRepository.save(runningTest);
            }
        }
    }
}
