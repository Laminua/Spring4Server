alter table tests
    add column max_attempts int not null default 3;
alter table assigned_tests
    add column finished bool not null default false;
alter table assigned_tests
    add column attempts int not null default 0;