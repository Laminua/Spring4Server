package com.example.springexercise3boot.models.test;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

import static com.example.springexercise3boot.models.test.Answers.MULTIPLE_TYPE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName(MULTIPLE_TYPE)
public class AnswersImplMultiple implements Answers {

    private Map<Integer, String> answers;

    private List<Integer> rightAnswersKeys;

    @Override
    public String toString() {
        return "" + answers + "";
    }
}
