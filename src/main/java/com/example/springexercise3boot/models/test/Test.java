package com.example.springexercise3boot.models.test;

import com.example.springexercise3boot.models.user.UserProfile;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tests")
public class Test {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "test_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserProfile owner;

    @OneToMany(mappedBy = "owner")
    private List<Question> questions;
}
