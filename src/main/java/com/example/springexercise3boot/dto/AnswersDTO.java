package com.example.springexercise3boot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class AnswersDTO {
    Map<Integer, String> answers;
}
