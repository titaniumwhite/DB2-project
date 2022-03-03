create table `dbproject2022.order`
(
    id            int auto_increment
        primary key,
    creation_date date        not null,
    user_id       varchar(10) not null,
    period_id     int         not null,
    servicepk_id  int         not null,
    constraint fk_period_id
        foreign key (period_id) references period (duration),
    constraint fk_servicepk_id
        foreign key (servicepk_id) references service_pack (id),
    constraint fk_user_id
        foreign key (user_id) references user (username)
);

