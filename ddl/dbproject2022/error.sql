create table error
(
    error_id   int auto_increment
        primary key,
    tot_number int      not null,
    timestamp  datetime not null,
    user_error int      not null,
    constraint error_user__fk
        foreign key (user_error) references user (user_id)
);

