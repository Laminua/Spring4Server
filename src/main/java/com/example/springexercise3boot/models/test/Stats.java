package com.example.springexercise3boot.models.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stats {

    LocalTime startTime;

    LocalTime endTime;

    int rightAnswersCount;

    int gainedScore;
}
