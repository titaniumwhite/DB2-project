create table `order`
(
    order_id              int auto_increment
        primary key,
    timestamp_creation    datetime   not null,
    total_cost            int        not null,
    isPlaceable           tinyint(1) null,
    user_order            int        not null,
    service_package_order int        not null,
    constraint order_service_pack__fk
        foreign key (service_package_order) references service_pack (service_pack_id),
    constraint order_user__fk
        foreign key (user_order) references user (user_id)
);

