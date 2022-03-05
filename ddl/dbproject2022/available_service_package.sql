create table available_service_package
(
    available_service_pack_id int auto_increment
        primary key,
    name                      varchar(50) not null,
    constraint available_service_package_name_uindex
        unique (name)
);

