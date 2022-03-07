USE dbproject2022;

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


create table available_service_package
(
    available_service_pack_id int auto_increment
        primary key,
    name                      varchar(50) not null,
    constraint available_service_package_name_uindex
        unique (name)
);


create table period
(
    period_id   int auto_increment
        primary key,
    duration    int not null,
    monthly_fee int not null
);


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

create table optional_service
(
    optional_service_id int auto_increment
        primary key,
    name                varchar(100) not null,
    monthly_fee         int          not null,
    constraint optional_service_monthly_fee_uindex
        unique (monthly_fee)
);


create table service
(
    service_id        int auto_increment
        primary key,
    type              varchar(50) not null,
    num_of_minutes    int         null,
    num_of_SMS        int         null,
    num_of_giga       int         null,
    fee_extra_minutes int         null,
    fee_extra_sms     int         null,
    fee_extra_giga    int         null, 
    available_service_package int null,
    constraint available_service_package__fk
        foreign key (available_service_package) references available_service_package (available_service_pack_id)
);







