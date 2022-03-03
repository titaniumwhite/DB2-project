create table optional_service_service_pack
(
    service_pack int         not null,
    name         varchar(20) not null,
    constraint related_optional_service_service_pack
        unique (service_pack, name),
    constraint optional_service_fk
        foreign key (name) references optional_service (name),
    constraint service_pack_fk2
        foreign key (service_pack) references service_pack (id)
);

