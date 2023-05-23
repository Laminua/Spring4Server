alter table tests
    add column max_attempts int default 3;
alter table assigned_tests
    add column finished bool default false;
alter table assigned_tests
    add column attempts int default 0;