package com.example.springexercise3boot.models.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stats {

    ZonedDateTime startTime;

    ZonedDateTime endTime;

    int rightAnswersCount;

    int gainedScore;
}
