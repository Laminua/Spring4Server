package com.example.springexercise3boot.services;

import com.example.springexercise3boot.models.test.AssignedTest;
import com.example.springexercise3boot.models.test.Stats;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class TimeService {

    private final AssignedTestService assignedTestService;

    LocalTime startTime;

    LocalTime endTime;

    boolean running;

    Stats stats;

    AssignedTest assignedTest;

    Timer timer;

    public void startTest(long testId, long userId) {

        assignedTest = assignedTestService.findAssignedTestByTestIdAndUserId(userId, testId);

        startTime = LocalTime.now();
        running = true;

        stats = assignedTest.getStats();

        if (stats == null) {
            stats = new Stats();
        }

        stats.setStartTime(startTime);

        if (assignedTest.getTest().getTimeRestriction() > 0) {

            System.out.println("Timer for time-restricted test started"); // for testing
            TimerTask stopTest = new TimerTask() {
                @Override
                public void run() {
                    endTest();
                    System.out.println("Timer for time-restricted test ended"); // for testing
                }
            };

            timer = new Timer();
            long delay = assignedTest.getTest().getTimeRestriction() * 60000L;
            timer.schedule(stopTest, delay);
        }
    }

    public void endTest() {

        if (timer != null) {
            timer.cancel();
            setEndTestStatus();
        } else {
            setEndTestStatus();
        }
    }

    private void setEndTestStatus() {
        endTime = LocalTime.now();
        running = false;
        stats.setEndTime(endTime);
        assignedTest.setStats(stats);
        assignedTest.setAttempts(assignedTest.getAttempts() + 1);
        assignedTestService.update(assignedTest);
    }

    public boolean checkTestStatus(long testId, long userId) {
        if (assignedTest == null) {
            return false;
        }

        if (assignedTest.getTest().getId() == testId && assignedTest.getUser().getId() == userId) {

            if (this.running) { //for testing
                System.out.println("Test is in progress"); //for testing
            } else {
                System.out.println("Test is ended"); //for testing
            }
            return this.running;
        } else {
            return false;
        }
    }
}
