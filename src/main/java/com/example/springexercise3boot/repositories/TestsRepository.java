package com.example.springexercise3boot.repositories;

import com.example.springexercise3boot.models.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestsRepository extends JpaRepository<Test, Long> {

    @Query("select t from Test t join fetch t.questions where t.id=:id")
    Optional<Test> findById(long id);
}
