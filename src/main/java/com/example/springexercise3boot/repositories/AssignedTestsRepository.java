package com.example.springexercise3boot.repositories;

import com.example.springexercise3boot.models.test.AssignedTests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignedTestsRepository extends JpaRepository<AssignedTests, Long> {

    @Query("select at from AssignedTests at join fetch at.test where at.user.id = :id")
    List<AssignedTests> getAssignedTestsByUserId(long id);

    AssignedTests getAssignedTestsByUserIdAndTestId(long userId, long testId);
}
