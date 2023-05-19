package com.example.springexercise3boot.models.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "assigned_tests")
public class AssignedTests {
    @Id
    @Column(name = "user_id")
    private int user_id;

    @Column(name = "test_id")
    private int test_id;
}
