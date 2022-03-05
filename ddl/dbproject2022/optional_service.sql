create table optional_service
(
    optional_service_id int auto_increment
        primary key,
    name                varchar(100) not null,
    monthly_fee         int          not null,
    constraint optional_service_monthly_fee_uindex
        unique (monthly_fee)
);

