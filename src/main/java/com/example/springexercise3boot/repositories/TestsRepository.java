package com.example.springexercise3boot.repositories;

import com.example.springexercise3boot.models.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestsRepository extends JpaRepository<Test, Integer> {

}
