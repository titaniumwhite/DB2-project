USE dbproject2022;

CREATE TABLE purchases_per_package(
                                      availableServicePack_id int not null primary key,
                                      totalOrder int default 0 not null,
                                      constraint purchases_per_package_fk foreign key (availableServicePack_id) references available_service_package (available_service_pack_id)
);

CREATE TABLE number_of_optionals(
                                    availableServicePack_id int not null primary key,
                                    total int default 0 not null,
                                    constraint number_of_optionals_fk foreign key (availableServicePack_id) references available_service_package (available_service_pack_id)
);

CREATE TABLE purchases_per_package_and_period(
                                                 availableServicePack_id int not null,
                                                 period_id int not null,
                                                 totalNumber int default 0 not null,
                                                 constraint purchases_per_package_and_period_fk0 foreign key (availableServicePack_id) references available_service_package (available_service_pack_id),
                                                 constraint purchases_per_package_and_period_fk1 foreign key (period_id) references period(period_id)
);

CREATE INDEX purchases_per_package_and_period_fk0_idx ON purchases_per_package_and_period(availableServicePack_id);
CREATE INDEX purchases_per_package_and_period_fk1_idx ON purchases_per_package_and_period(period_id);

CREATE TABLE sales_per_package(
                                  availableServicePack_id int not null primary key,
                                  total_sales_with_optional int not null default 0,
                                  total_sales_no_optional int not null default 0,
                                  constraint sales_per_package_fk foreign key (availableServicePack_id) references available_service_package (available_service_pack_id)
);

CREATE TABLE avg_numoptionservperservpack(
                                             availableServicePack_id int not null primary key,
                                             avgNum float default 0,
                                             constraint avg_numoptionservperservpack_fk foreign key (availableServicePack_id) references available_service_package (available_service_pack_id)
);

CREATE TABLE best_optional_service(
                                      optional_service_id int not null primary key,
                                      sales int not null,
                                      constraint best_optional_service_fk foreign key (optional_service_id) references optional_service (optional_service_id)
);

CREATE TABLE optional_service_order(
                                       optional_service_id int not null primary key,
                                       optional_service_sales int not null default 0
);

CREATE TABLE sales_per_optional_service(
                                           optional_service_id int not null,
                                           optional_service_sales int not null default 0
);

### TRIGGERS ###

delimiter //
CREATE DEFINER = CURRENT_USER TRIGGER insertNewOrderOfPack AFTER INSERT ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN
        UPDATE purchases_per_package SET totalOrder = totalOrder + 1
        WHERE availableServicePack_id in (	SELECT s.available_package
                                              FROM service_pack s
                                              WHERE s.service_pack_id = New.service_package_order
        );
    end if;
end //
delimiter ;


delimiter //
CREATE DEFINER = CURRENT_USER TRIGGER updateServiceOrder AFTER UPDATE ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN
        UPDATE purchases_per_package SET totalOrder = totalOrder + 1
        WHERE availableServicePack_id IN (SELECT s.available_package FROM service_pack s WHERE s.service_pack_id = New.service_package_order);
    end if;
end //

delimiter ;

delimiter //

CREATE DEFINER = CURRENT_USER TRIGGER createNewServPackWithPeriod AFTER INSERT ON period_to_offer FOR EACH ROW
BEGIN
    INSERT INTO purchases_per_package_and_period(availableServicePack_id, period_id) VALUES (NEW.available_service_pack_id, NEW.period_id);
end //

delimiter ;

delimiter //

CREATE DEFINER = CURRENT_USER TRIGGER createNewAvailableServicePack_P1 AFTER INSERT ON available_service_package FOR EACH ROW
BEGIN
    INSERT INTO dbproject2022.purchases_per_package(availableServicePack_id) VALUES (NEW.available_service_pack_id);
end//

delimiter ;

delimiter //

CREATE DEFINER = CURRENT_USER TRIGGER insertNewPackageWithPeriod AFTER INSERT ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN
        UPDATE dbproject2022.purchases_per_package_and_period SET totalNumber = totalNumber + 1
        WHERE (availableServicePack_id, period_id) IN (SELECT s.available_package, s.period_service_pack
                                                       FROM dbproject2022.service_pack s
                                                       WHERE s.service_pack_id = NEW.service_package_order);
    end if;
end//

delimiter ;

delimiter //

CREATE DEFINER = CURRENT_USER TRIGGER updateNewPackageWithPeriod AFTER UPDATE ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN
        UPDATE dbproject2022.purchases_per_package_and_period SET totalNumber = totalNumber + 1
        WHERE (availableServicePack_id, period_id) IN (SELECT s.available_package, s.period_service_pack
                                                       FROM dbproject2022.service_pack s
                                                       WHERE s.service_pack_id = NEW.service_package_order);
    end if;
end//

delimiter ;

delimiter //

CREATE DEFINER = CURRENT_USER TRIGGER createNewAvailableServicePack_P2 AFTER INSERT ON available_service_package FOR EACH ROW
BEGIN
    INSERT INTO sales_per_package(availableServicePack_id) VALUES (New.available_service_pack_id);
end//
delimiter ;

delimiter //
CREATE DEFINER = CURRENT_USER TRIGGER insertOptionalService AFTER INSERT ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN
        DELETE FROM
            number_of_optionals t WHERE t.availableServicePack_id IN (SELECT s.available_package
                                                                      FROM service_pack s
                                                                      WHERE s.service_pack_id = NEW.service_package_order);

        INSERT INTO number_of_optionals
        SELECT a.available_service_pack_id, count(*)
        FROM `order` AS o
                 JOIN dbproject2022.service_pack AS s ON o.service_package_order = s.service_pack_id
                 JOIN dbproject2022.available_service_package AS a ON s.available_package = a.available_service_pack_id
                 JOIN dbproject2022.optional_services_selected AS os ON os.service_pack_id = o.service_package_order
        WHERE o.isPlaceable = true and a.available_service_pack_id IN (SELECT s.available_package
                                                                       FROM service_pack s
                                                                       WHERE s.service_pack_id = NEW.service_package_order)
        GROUP BY a.available_service_pack_id;

        DELETE FROM
            AVG_numOptionServPerServPack WHERE availableServicePack_id IN (SELECT s.available_package
                                                                           FROM service_pack s
                                                                           WHERE s.service_pack_id = NEW.service_package_order);

        INSERT INTO AVG_numOptionServPerServPack
        SELECT t.availableServicePack_id, IFNULL((o2.total / t.totalOrder), 0.0)
        FROM dbproject2022.purchases_per_package AS t
                 LEFT OUTER JOIN dbproject2022.number_of_optionals AS o2 ON t.availableServicePack_id = o2.availableServicePack_id
        WHERE t.availableServicePack_id IN (SELECT s.available_package
                                            FROM service_pack s
                                            WHERE s.service_pack_id = NEW.service_package_order);
    end if;
end //
delimiter ;

delimiter //
CREATE DEFINER = CURRENT_USER TRIGGER updateOptionalService AFTER UPDATE ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN
        DELETE FROM
            number_of_optionals t WHERE t.availableServicePack_id IN (SELECT s.available_package
                                                                      FROM service_pack s
                                                                      WHERE s.service_pack_id = NEW.service_package_order);

        INSERT INTO number_of_optionals
        SELECT a.available_service_pack_id, count(*)
        FROM `order` AS o
                 JOIN dbproject2022.service_pack AS s ON o.service_package_order = s.service_pack_id
                 JOIN dbproject2022.available_service_package AS a ON s.available_package = a.available_service_pack_id
                 JOIN dbproject2022.optional_services_selected AS os ON os.service_pack_id = o.service_package_order
        WHERE o.isPlaceable = true and a.available_service_pack_id IN (SELECT s.available_package
                                                                       FROM service_pack s
                                                                       WHERE s.service_pack_id = NEW.service_package_order)
        GROUP BY a.available_service_pack_id;

        DELETE FROM
            AVG_numOptionServPerServPack WHERE availableServicePack_id IN (SELECT s.available_package
                                                                           FROM service_pack s
                                                                           WHERE s.service_pack_id = NEW.service_package_order);

        INSERT INTO AVG_numOptionServPerServPack
        SELECT t.availableServicePack_id, IFNULL((o2.total / t.totalOrder), 0.0)
        FROM dbproject2022.purchases_per_package AS t
                 LEFT OUTER JOIN dbproject2022.number_of_optionals AS o2 ON t.availableServicePack_id = o2.availableServicePack_id
        WHERE t.availableServicePack_id IN (SELECT s.available_package
                                            FROM service_pack s
                                            WHERE s.service_pack_id = NEW.service_package_order);
    end if;
end //
delimiter ;

delimiter //
CREATE DEFINER = CURRENT_USER TRIGGER insertSale AFTER INSERT ON `order` FOR EACH ROW
BEGIN
    DECLARE a, b int;
    IF NEW.isPlaceable = true THEN
        SELECT s.cost, s.total_cost_optional_services INTO a, b
        FROM service_pack s
        WHERE s.service_pack_id = NEW.service_package_order;

        UPDATE sales_per_package spp
        SET spp.total_sales_with_optional = spp.total_sales_with_optional + a + b, spp.total_sales_no_optional = spp.total_sales_no_optional + a
        WHERE spp.availableServicePack_id IN ( SELECT s1.available_package
                                               FROM  service_pack s1
                                               WHERE s1.service_pack_id = NEW.service_package_order);
    end if;
end //
delimiter ;

delimiter //
CREATE DEFINER = CURRENT_USER TRIGGER updateSale AFTER UPDATE ON `order` FOR EACH ROW
BEGIN
    DECLARE a, b int;
    IF NEW.isPlaceable = true THEN
        SELECT s.cost, s.total_cost_optional_services INTO a, b
        FROM service_pack s
        WHERE s.service_pack_id = NEW.service_package_order;

        UPDATE sales_per_package spp
        SET spp.total_sales_with_optional = spp.total_sales_with_optional + a + b, spp.total_sales_no_optional = spp.total_sales_no_optional + a
        WHERE spp.availableServicePack_id IN ( SELECT s1.available_package
                                               FROM  service_pack s1
                                               WHERE s1.service_pack_id = NEW.service_package_order);
    end if;
end //

delimiter ;

delimiter //
CREATE DEFINER = CURRENT_USER TRIGGER createNewAvailableServicePack_P3 AFTER INSERT ON available_service_package FOR EACH ROW
BEGIN
    INSERT INTO avg_numoptionservperservpack(availableServicePack_id)
    VALUES (NEW.available_service_pack_id);
end //

delimiter ;

delimiter //
CREATE DEFINER = CURRENT_USER TRIGGER insertSaleOptionalService AFTER INSERT ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN DELETE FROM optional_service_order;
    INSERT INTO optional_service_order
    SELECT os.optional_service_id, (os.monthly_fee * p.duration)
    FROM `order` o
             JOIN service_pack s ON s.service_pack_id = o.service_package_order
             JOIN optional_services_selected oss ON oss.service_pack_id = s.service_pack_id
             JOIN period p ON p.period_id = s.period_service_pack
             JOIN optional_service os ON os.optional_service_id = oss.optional_service_id
    WHERE s.service_pack_id = NEW.service_package_order;

    UPDATE sales_per_optional_service s, optional_service_order ops
    SET s.optional_service_sales = s.optional_service_sales + ops.optional_service_sales
    WHERE s.optional_service_id = ops.optional_service_id;

    DELETE FROM best_optional_service;
    INSERT INTO best_optional_service
    SELECT s1.optional_service_id, s1.optional_service_sales
    FROM sales_per_optional_service s1
    WHERE s1.optional_service_id is not null AND s1.optional_service_sales IN (SELECT MAX(s2.optional_service_sales) FROM sales_per_optional_service s2);
    END IF;
end //

delimiter ;

delimiter //
CREATE DEFINER = CURRENT_USER TRIGGER updateSaleOptionalService AFTER UPDATE ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN DELETE FROM optional_service_order;
    INSERT INTO optional_service_order
    SELECT os.optional_service_id, (os.monthly_fee * p.duration)
    FROM `order` o
             JOIN service_pack s ON s.service_pack_id = o.service_package_order
             JOIN optional_services_selected oss ON oss.service_pack_id = s.service_pack_id
             JOIN period p ON p.period_id = s.period_service_pack
             JOIN optional_service os ON os.optional_service_id = oss.optional_service_id
    WHERE s.service_pack_id = NEW.service_package_order;

    UPDATE sales_per_optional_service s, optional_service_order ops
    SET s.optional_service_sales = s.optional_service_sales + ops.optional_service_sales
    WHERE s.optional_service_id = ops.optional_service_id;

    DELETE FROM best_optional_service;
    INSERT INTO best_optional_service
    SELECT s1.optional_service_id, s1.optional_service_sales
    FROM sales_per_optional_service s1
    WHERE s1.optional_service_id is not null AND s1.optional_service_sales IN (SELECT MAX(s2.optional_service_sales) FROM sales_per_optional_service s2);
    END IF;
end //
delimiter ;

delimiter //
CREATE DEFINER = CURRENT_USER TRIGGER insertOptionalServiceToBeSold AFTER INSERT ON optional_service FOR EACH ROW
BEGIN
    INSERT INTO sales_per_optional_service(optional_service_id)
    VALUES(NEW.optional_service_id);
end //

delimiter ;