alter table tests
    add column time_restriction int default 0;

update tests
set time_restriction = 2
where id = 4;