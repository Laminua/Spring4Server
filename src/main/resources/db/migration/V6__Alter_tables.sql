truncate table questions;

alter sequence questions_id_seq restart with 1;

insert into questions(question, question_type, test_id, answers)
VALUES ('В каком году Гагарин полетел в космос?', 'SINGLE_ANSWER', 1,
        '{
          "questionType": "SINGLE_ANSWER",
          "answers": {
            "1": "1960",
            "2": "1961",
            "3": "1965",
            "4": "1967"
          },
          "rightAnswerKey": 2
        }')
     , ('Кто проживает на дне океана?', 'SINGLE_ANSWER', 1, '{
  "questionType": "SINGLE_ANSWER",
  "answers": {
    "1": "Баба-Яга",
    "2": "Морская звезда",
    "3": "Спанч Боб",
    "4": "Чудище морское"
  },
  "rightAnswerKey": 3
}');

insert into questions(question, question_type, test_id, answers)
VALUES ('Какие химические элементы входят в состав молекулы воды?', 'MANY_ANSWERS', 2,
        '{
          "questionType": "MANY_ANSWERS",
          "answers": {
            "1": "Гелий",
            "2": "Водород",
            "3": "Азот",
            "4": "Кислород"
          },
          "rightAnswersKeys": [
            2,
            4
          ]
        }')
     , ('Что из перечисленного является молочным продуктом?', 'MANY_ANSWERS', 2, '{
  "questionType": "MANY_ANSWERS",
  "answers": {
    "1": "Сыр",
    "2": "Брага",
    "3": "Творог",
    "4": "Простокваша"
  },
  "rightAnswersKeys": [
    1,
    3,
    4
  ]
}');

insert into questions(question, question_type, test_id, answers)
VALUES ('Домашнее животное, умеющее лаять', 'USER_INPUT', 3,
        '{
          "questionType": "USER_INPUT",
          "answer": "собака"
        }')
     , ('Живёт под полом, боится кошек', 'USER_INPUT', 3, '{
  "questionType": "USER_INPUT",
  "answer": "мышь"
}');

insert into questions(question, question_type, test_id, answers)
VALUES ('Какая элементарная частица не имеет массы покоя?', 'SINGLE_ANSWER', 4,
        '{
          "questionType": "SINGLE_ANSWER",
          "answers": {
            "1": "Электрон",
            "2": "Нейтрон",
            "3": "Протон",
            "4": "Фотон"
          },
          "rightAnswerKey": 4
        }'),
       ('Что из перечисленного относится к примитивным типам данных в Java?', 'MANY_ANSWERS', 4,
        '{
          "questionType": "MANY_ANSWERS",
          "answers": {
            "1": "boolean",
            "2": "String",
            "3": "int",
            "4": "char",
            "5": "Long"
          },
          "rightAnswersKeys": [
            1,
            3,
            4
          ]
        }'),
       ('Как называется геометрическая фигура с тремя углами?', 'USER_INPUT', 4,
        '{
          "questionType": "USER_INPUT",
          "answer": "треугольник"
        }')
        , ('Как называется фантастический роман Станислава Лема о планете с разумным океаном?', 'USER_INPUT', 4, '{
  "questionType": "USER_INPUT",
  "answer": "солярис"
}');