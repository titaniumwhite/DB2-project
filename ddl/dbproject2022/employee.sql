create table employee
(
    employee_id int auto_increment
        primary key,
    email       varchar(50) not null,
    password    varchar(50) not null,
    constraint employee_email_uindex
        unique (email),
    constraint employee_employee_id_uindex
        unique (employee_id)
);

