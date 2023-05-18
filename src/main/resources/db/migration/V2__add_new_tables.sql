create table tests
(
    id               bigserial not null primary key,
    test_description varchar,
    user_id          int       references user_profile (id) on delete set null
);

create table questions
(
    id bigserial not null primary key,
    question varchar,
    question_type varchar,
    test_id int references questions(id) on delete set null,
    answers jsonb
)