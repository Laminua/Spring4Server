package com.example.springexercise3boot.models.test;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(name = "questions")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Question {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "question")
    private String question;

    @Column(name = "question_type")
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @Type(type = "jsonb")
    @Column(columnDefinition = "answers")
    private Answers answers;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private Test owner;
}
