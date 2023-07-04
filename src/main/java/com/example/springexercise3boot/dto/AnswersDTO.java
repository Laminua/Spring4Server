package com.example.springexercise3boot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class AnswersDTO {
    Map<Integer, String> answers;
}
