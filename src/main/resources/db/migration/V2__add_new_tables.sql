create table tests
(
    id               bigserial not null primary key,
    test_description varchar
--     user_id          int       references user_profile (id) on delete set null
);

create table assigned_tests
(
    id bigserial not null primary key,
    user_id int not null references user_profile (id),
    test_id int not null references tests (id)
);

create table questions
(
    id            bigserial not null primary key,
    question      varchar,
    question_type varchar,
    test_id       int       references tests (id) on delete set null,
    answers       jsonb
);

insert into tests(test_description)
VALUES ('Тест с выбором одного варианта ответа'),
       ('Тест с выбором нескольких вариантов ответов'),
       ('Тест с вводом ответа пользователем'),
       ('Все виды тестов');

insert into assigned_tests (user_id, test_id)
VALUES (2,1),(2,2),(2,3),(2,4);

insert into questions(question, question_type, test_id, answers)
VALUES ('В каком году Гагарин полетел в космос?', 'SINGLE_ANSWER', 1,
        '{
          "answer_type": "single",
          "answers": {
            "1": "1960",
            "2": "1961",
            "3": "1965",
            "4": "1967"
          },
          "rightAnswerKey": 2
        }')
     , ('Кто проживает на дне океана?', 'SINGLE_ANSWER', 1, '{
  "answer_type": "single",
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
          "answer_type": "multiple",
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
  "answer_type": "multiple",
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
VALUES ('Домашнее животное, умеющее лаять', 'USER_INPUT', 1,
        '{
          "answer_type": "input",
          "answer": "собака"
        }')
     , ('Живёт под полом, боится кошек', 'USER_INPUT', 1, '{
  "answer_type": "input",
  "answer": "мышь"
}');
