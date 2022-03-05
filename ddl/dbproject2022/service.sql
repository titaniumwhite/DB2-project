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
    fee_extra_giga    int         null
);

