package com.example.springexercise3boot.models.test;

import com.example.springexercise3boot.models.user.UserProfile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "assigned_tests")
public class AssignedTests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    private UserProfile user;

    @OneToOne(fetch = FetchType.LAZY)
    private Test test;

    private boolean finished;

    private int attempts;

}
