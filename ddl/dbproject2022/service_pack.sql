create table service_pack
(
    service_pack_id              int auto_increment
        primary key,
    start_date                   date          not null,
    end_date                     date          not null,
    cost                         int           not null,
    total_cost_optional_services int default 0 not null,
    available_package            int           not null,
    period_service_pack          int           not null,
    user_service_package         int           not null,
    constraint service_pack_available_service_package__fk
        foreign key (available_package) references available_service_package (available_service_pack_id),
    constraint service_pack_period__fk
        foreign key (period_service_pack) references period (period_id),
    constraint service_pack_user__fk
        foreign key (user_service_package) references user (user_id)
);

