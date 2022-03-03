create table user
(
    username   varchar(10) not null
        primary key,
    first_name varchar(30) not null,
    last_name  varchar(30) not null,
    email      varchar(50) not null,
    password   varchar(30) not null
);

