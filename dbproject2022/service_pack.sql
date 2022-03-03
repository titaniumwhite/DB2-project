create table service_pack
(
    id          int auto_increment
        primary key,
    name        varchar(30) not null,
    monthly_fee int         not null,
    employee_id int         not null,
    constraint fk2_employee_id
        foreign key (employee_id) references employee (id)
);

