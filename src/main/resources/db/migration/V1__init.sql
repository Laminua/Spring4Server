create table user_profile
(
    id       bigserial    not null primary key,
    username varchar(30) unique,
    role     varchar,
    name     varchar(100) not null,
    email    varchar(100) unique,
    password varchar      not null
);

insert into user_profile (username, role, name, email, password)
values ('admin', 'ROLE_ADMIN', 'Kirill', 'Kirill@email.com', 'admin'),
       ('user', 'ROLE_USER', 'Vasya', 'vasya@mail.ru', 'user');