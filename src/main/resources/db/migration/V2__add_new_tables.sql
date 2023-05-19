create table tests
(
    id               bigserial not null primary key,
    test_description varchar,
    user_id          int       references user_profile (id) on delete set null
);

create table questions
(
    id            bigserial not null primary key,
    question      varchar,
    question_type varchar,
    test_id       int       references tests (id) on delete set null,
    answers       jsonb
);

insert into tests(test_description, user_id)
VALUES ('Тест с выбором одного варианта ответа', 2),
       ('Тест с выбором нескольких вариантов ответов', 2),
       ('Тест с вводом ответа пользователем', 2),
       ('Все виды тестов', 2);

insert into questions(question, question_type, test_id, answers)
VALUES ('В каком году Гагарин полетел в космос?', 'SINGLE_ANSWER', 1,
        '{
          "className": "com.example.springexercise3boot.models.test.AnswersImplSingle",
          "answers": {
            "1": "1960",
            "2": "1961",
            "3": "1965",
            "4": "1967"
          },
          "rightAnswerKey": 2
        }')
     , ('Кто проживает на дне океана?', 'SINGLE_ANSWER', 1, '{
  "className": "com.example.springexercise3boot.models.test.AnswersImplSingle",
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
          "className": "com.example.springexercise3boot.models.test.AnswersImplMultiple",
          "answers": {
            "1": "Гелий",
            "2": "Водород",
            "3": "Азот",
            "4": "Кислород"
          },
          "rightAnswersKeys": [2,4]
        }')
     , ('Что из перечисленного является молочным продуктом?', 'MANY_ANSWERS', 2, '{
  "className": "com.example.springexercise3boot.models.test.AnswersImplMultiple",
  "answers": {
    "1": "Сыр",
    "2": "Брага",
    "3": "Творог",
    "4": "Простокваша"
  },
  "rightAnswersKeys": [1,3,4]
}');

insert into questions(question, question_type, test_id, answers)
VALUES ('Домашнее животное, умеющее лаять', 'USER_INPUT', 1,
        '{
          "className": "com.example.springexercise3boot.models.test.AnswersImplInput",
          "answer": "собака"
        }')
     , ('Живёт под полом, боится кошек', 'USER_INPUT', 1, '{
  "className": "com.example.springexercise3boot.models.test.AnswersImplInput",
  "answer": "мышь"
}');
