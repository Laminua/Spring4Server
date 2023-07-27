package com.example.springexercise3boot.services;

import com.example.springexercise3boot.models.test.AssignedTest;
import com.example.springexercise3boot.models.test.TestEndReason;
import com.example.springexercise3boot.repositories.AssignedTestsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class TestScheduler {

    private final AssignedTestsRepository assignedTestsRepository;

    @Scheduled(fixedRate = 60000L)
    public void stopTest() {
        log.debug("Scheduler started");

        List<AssignedTest> runningTests = assignedTestsRepository.getRunningAssignedTests();

        for (AssignedTest runningTest : runningTests) {

            long timeLimit = runningTest.getTest().getTimeRestriction();
            ZonedDateTime startTime = runningTest.getStats().getStartTime();
            ZonedDateTime endTime = startTime.plusMinutes(timeLimit);

            if (ZonedDateTime.now().isAfter(endTime)) {
                log.debug("Test id {} for user id {} has been stopped by scheduler",
                        runningTest.getTest().getId(), runningTest.getUser().getId());

                runningTest.getStats().setEndTime(endTime);
                runningTest.getStats().setEndReason(TestEndReason.BY_SCHEDULER);
                runningTest.setAttempts(runningTest.getAttempts() + 1);
                runningTest.setRunning(false);
                assignedTestsRepository.save(runningTest);
            }
        }
    }
}
