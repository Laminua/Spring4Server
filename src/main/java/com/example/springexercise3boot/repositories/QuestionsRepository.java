package com.example.springexercise3boot.repositories;

import com.example.springexercise3boot.models.test.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Question, Integer> {

}
