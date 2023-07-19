package com.example.springexercise3boot.repositories;

import com.example.springexercise3boot.models.test.AssignedTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignedTestsRepository extends JpaRepository<AssignedTest, Long> {

    @Query("select at from AssignedTest at join fetch at.test where at.user.id = :id and at.finished = false")
    List<AssignedTest> getAssignedTestsByUserId(long id);

    @Query("select at from AssignedTest at join fetch at.test where at.isRunning = true " +
            "and at.test.timeRestriction > 0")
    List<AssignedTest> getRunningAssignedTests();

    @Query("select at from AssignedTest at join fetch at.test t join fetch t.questions " +
            "join fetch at.user where at.user.id = :userId and at.test.id = :testId")
    Optional<AssignedTest> getAssignedTestByUserIdAndTestId(long userId, long testId);
}
