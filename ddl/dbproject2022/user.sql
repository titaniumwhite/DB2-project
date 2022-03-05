create table user
(
    user_id           int auto_increment
        primary key,
    username          varchar(50)          not null,
    name              varchar(50)          not null,
    surname           varchar(50)          not null,
    email             varchar(100)         not null,
    password          varchar(50)          not null,
    isInsolvent       tinyint(1) default 0 not null,
    totFailedAttempts int        default 0 not null,
    constraint user_email_uindex
        unique (email),
    constraint user_user_id_uindex
        unique (user_id),
    constraint user_username_uindex
        unique (username)
);

