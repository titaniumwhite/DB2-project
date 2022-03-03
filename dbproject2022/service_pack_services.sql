create table service_pack_services
(
    service_pack    int         not null,
    type            varchar(15) not null,
    sms             varchar(10) null,
    minutes         varchar(10) null,
    gigas           varchar(10) null,
    fee_extra_gigas int         null,
    constraint service_pack_services_unique
        unique (service_pack, type),
    constraint service_pack_fk
        foreign key (service_pack) references service_pack (id),
    constraint services_fk
        foreign key (type) references service (type)
);

