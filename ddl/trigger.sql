CREATE TABLE totalOrderPackage(
    servicePack_id int not null primary key,
    totalOrder int default 0 not null,
    constraint numTotalOrderPackage_fk foreign key (servicePack_id) references available_service_package (available_service_pack_id)
);

CREATE DEFINER = CURRENT_USER TRIGGER updateServiceOrder AFTER UPDATE ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN
        UPDATE dbproject2022.totalorderpackage SET totalOrder = totalOrder + 1
        WHERE servicePack_id IN (SELECT s.available_package FROM dbproject2022.service_pack s WHERE s.service_pack_id = New.service_package_order);
    end if;
end;

CREATE DEFINER = CURRENT_USER TRIGGER insertNewOrderOfPack AFTER INSERT ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN
        UPDATE totalOrderPackage SET totalOrder = totalOrder + 1
        WHERE servicePack_id in (SELECT s.available_package FROM service_pack s WHERE s.service_pack_id = New.service_package_order);
    end if;
end;

CREATE DEFINER = CURRENT_USER TRIGGER createNewAvailableServicePack AFTER INSERT ON available_service_package FOR EACH ROW
BEGIN
    INSERT INTO dbproject2022.totalorderpackage(servicePack_id) VALUES (NEW.available_service_pack_id);
end;