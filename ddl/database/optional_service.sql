create table optional_service
(
    name        varchar(20)  not null
        primary key,
    description varchar(100) null,
    monthly_fee int          not null,
    employee_id int          not null,
    constraint fk1_employee_id
        foreign key (employee_id) references employee (id)
);

