package com.example.springexercise3boot.repositories;

import com.example.springexercise3boot.models.test.AssignedTests;
import com.example.springexercise3boot.models.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignedTestsRepository extends JpaRepository<AssignedTests, Long> {

    @Query("select at.test from AssignedTests at where at.user.id = :id")
    List<Test> getAssignedTestsByUserId(long id);
}
