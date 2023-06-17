package com.example.springexercise3boot.repositories;

import com.example.springexercise3boot.models.test.Answers;
import com.example.springexercise3boot.models.test.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, Long> {

    @Query("select t.questions from Test t where t.id=:id")
    List<Question> findAllByTestId(long id);

    @Query("select q.answers from Question q where q.id=:id")
    Answers findAllByQuestionId(long id);
}
