package com.example.springexercise3boot.models.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tests")
public class Test {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "test_description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private List<Question> questions;

    @Column(name = "max_attempts")
    private int max_attempts;

    @Column(name = "max_score")
    private int max_score;

    @Column(name = "time_restriction")
    private int timeRestriction;
}
