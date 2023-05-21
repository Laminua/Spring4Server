package com.example.springexercise3boot.models.test;

import com.example.springexercise3boot.models.user.UserProfile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "assigned_tests")
public class AssignedTests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    private UserProfile user;

    @OneToOne(fetch = FetchType.EAGER)
    private Test test;
}
