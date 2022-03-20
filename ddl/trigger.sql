DROP TABLE IF EXISTS totalorderpackage;
DROP TRIGGER IF EXISTS insertNewOrderOfPack;
DROP TRIGGER IF EXISTS updateServiceOrder;
DROP TRIGGER IF EXISTS createNewAvailableServicePack_P1;

DROP TABLE IF EXISTS totalorderpackagewithperiod;
DROP TRIGGER IF EXISTS createNewServPackWithPeriod;
DROP TRIGGER IF EXISTS insertNewPackageWithPeriod;
DROP TRIGGER IF EXISTS updateNewPackageWithPeriod;

DROP TABLE IF EXISTS sa;
DROP TRIGGER IF EXISTS insertSale;
DROP TRIGGER IF EXISTS updateSale;
DROP TRIGGER IF EXISTS createNewAvailableServicePack_P2;

DROP TABLE IF EXISTS totalNumOptionalService;
DROP TABLE IF EXISTS AVG_numOptionServPerServPack;
DROP TRIGGER IF EXISTS createNewAvailableServicePack_P3;
DROP TRIGGER IF EXISTS insertOptionalService;
DROP TRIGGER IF EXISTS updateOptionalService;

DROP TABLE IF EXISTS pending_orders;
DROP TABLE IF EXISTS errors;
DROP TABLE IF EXISTS insolventUsers;
DROP TRIGGER IF EXISTS insertError;
DROP TRIGGER IF EXISTS updateInsolventUsers;
DROP TRIGGER IF EXISTS insertUnplaceableOrder;
DROP TRIGGER IF EXISTS updateUnplaceableOrder;

DROP TABLE IF EXISTS orderPerOptionalService;
DROP TABLE IF EXISTS bestOptionalService;
DROP TABLE IF EXISTS sales_per_package;
DROP TRIGGER IF EXISTS insertOptionalService;
DROP TRIGGER IF EXISTS insertSaleOptionalService;
DROP TRIGGER IF EXISTS updateSaleOptionalService;

CREATE TABLE totalOrderPackage(
    servicePack_id int not null primary key,
    totalOrder int default 0 not null,
    constraint totalOrderPackage_fk foreign key (servicePack_id) references available_service_package (available_service_pack_id)
);

CREATE TABLE totalOrderPackageWithPeriod(
    servicePack_id int not null,
    period_id int not null,
    totalNumber int default 0 not null,
    constraint totalOrderPackageWithPeriod_fk0 foreign key (servicePack_id) references  available_service_package (available_service_pack_id),
    constraint totalOrderPackageWithPeriod_fk1 foreign key (period_id) references period (period_id)
);


CREATE TABLE AVG_numOptionServPerServPack(
    servicePack_id int not null primary key,
    avgNum float default -1 not null,
    constraint AVG_numOptionServPerServPack_fk foreign key (servicePack_id) references available_service_package(available_service_pack_id)
);

CREATE TABLE totalNumOptionalService(
    optionalServ_id int not null primary key,
    totalNumber int default 0 not null,
    constraint totalNumOptionalService_fk foreign key (optionalServ_id) references available_service_package(available_service_pack_id)
);

CREATE TABLE errors(
    error_id int not null,
    constraint errors_fk foreign key (error_id) references error(error_id)
);

CREATE TABLE insolventUsers
(
    user_id int not null,
    constraint insolventUsers_fk0
        foreign key (user_id) references user (user_id)
);

create index insolventUsers_fk0_idx
    on insolventusers (user_id);

CREATE TABLE unplaceableOrders
(
    order_id int not null,
    constraint unplaceableOrders_fk0
        foreign key (order_id) references `order`  (order_id)
);

create index unplaceableOrders_fk0_idx
    on unplaceableOrders (order_id);

CREATE TABLE salesPerOptionalService
(
    optional_service_id int   not null
        primary key,
    sales              int default 0 not null
);

CREATE TABLE orderPerOptionalService
(
    optional_service_id int   not null,
    sales              int default 0 not null
);

CREATE TABLE bestOptionalService
(
    optional_service_id int   not null primary key,
    sales              int default 0 not null,
    constraint bestOptionalService_fk0
        foreign key (optional_service_id) references optional_service(optional_service_id)

);

CREATE TABLE sales_per_package(
      sales_per_package_id int not null,
      total_sales_with_optional int not null,
      total_sales_no_optional int not null,
      constraint sales_per_package_fk foreign key (sales_per_package_id) references available_service_package (available_service_pack_id)
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

CREATE DEFINER = CURRENT_USER TRIGGER createNewAvailableServicePack_P1 AFTER INSERT ON available_service_package FOR EACH ROW
BEGIN
    INSERT INTO dbproject2022.totalorderpackage(servicePack_id) VALUES (NEW.available_service_pack_id);
end;

CREATE DEFINER = CURRENT_USER TRIGGER createNewServPackWithPeriod AFTER INSERT ON period_to_offer FOR EACH ROW
BEGIN
    INSERT INTO totalOrderPackageWithPeriod(servicePack_id, period_id) VALUES (NEW.available_service_pack_id, NEW.period_id);
end;

CREATE DEFINER = CURRENT_USER TRIGGER insertNewPackageWithPeriod AFTER INSERT ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN
        UPDATE dbproject2022.totalorderpackagewithperiod SET totalNumber = totalNumber + 1
        WHERE (servicePack_id, period_id) IN (SELECT s.available_package, s.period_service_pack
                                              FROM dbproject2022.service_pack s
                                              WHERE s.service_pack_id = NEW.service_package_order);
    end if;
end;

CREATE DEFINER = CURRENT_USER TRIGGER updateNewPackageWithPeriod AFTER UPDATE ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN
        UPDATE dbproject2022.totalorderpackagewithperiod SET totalNumber = totalNumber + 1
        WHERE (servicePack_id, period_id) IN (SELECT s.available_package, s.period_service_pack
                                              FROM dbproject2022.service_pack s
                                              WHERE s.service_pack_id = NEW.service_package_order);
    end if;
end;


CREATE DEFINER = CURRENT_USER TRIGGER createNewAvailableServicePack_P2 AFTER INSERT ON available_service_package FOR EACH ROW
BEGIN
    INSERT INTO dbproject2022.average_optional_products_perpackage(average_optional_products_perpackage_id) VALUES (New.available_service_pack_id);
end;

CREATE DEFINER = CURRENT_USER TRIGGER insertOptionalService AFTER INSERT ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN
        DELETE FROM
                    totalNumOptionalService t WHERE t.optionalServ_id IN (SELECT s.available_package
                                                                          FROM service_pack s
                                                                          WHERE s.service_pack_id = NEW.service_package_order);

        INSERT INTO totalNumOptionalService
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
                    AVG_numOptionServPerServPack WHERE servicePack_id IN (SELECT s.available_package
                                                                          FROM service_pack s
                                                                          WHERE s.service_pack_id = NEW.service_package_order);

        INSERT INTO AVG_numOptionServPerServPack
        SELECT t.servicePack_id, IFNULL((o2.average / t.totalOrder), 0.0)
        FROM dbproject2022.totalorderpackage AS t
             LEFT OUTER JOIN dbproject2022.totalNumOptionalService AS o2 ON t.servicePack_id = o2.optionalServ_id
        WHERE t.servicePack_id IN (SELECT s.available_package
                                   FROM service_pack s
                                   WHERE s.service_pack_id = NEW.service_package_order);
    end if;
end;

CREATE DEFINER = CURRENT_USER TRIGGER updateOptionalService AFTER UPDATE ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN
        DELETE FROM
            totalNumOptionalService t WHERE t.optionalServ_id IN (SELECT s.available_package
                                                                  FROM service_pack s
                                                                  WHERE s.service_pack_id = NEW.service_package_order);

        INSERT INTO totalNumOptionalService
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
            AVG_numOptionServPerServPack WHERE servicePack_id IN (SELECT s.available_package
                                                                  FROM service_pack s
                                                                  WHERE s.service_pack_id = NEW.service_package_order);

        INSERT INTO AVG_numOptionServPerServPack
        SELECT t.servicePack_id, IFNULL((o2.average / t.totalOrder), 0.0)
        FROM dbproject2022.totalorderpackage AS t
                 LEFT OUTER JOIN dbproject2022.totalNumOptionalService AS o2 ON t.servicePack_id = o2.optionalServ_id
        WHERE t.servicePack_id IN (SELECT s.available_package
                                   FROM service_pack s
                                   WHERE s.service_pack_id = NEW.service_package_order);
    end if;
end;

CREATE DEFINER = CURRENT_USER TRIGGER insertError AFTER INSERT ON error FOR EACH ROW
BEGIN
    INSERT INTO errors VALUES (NEW.error_id);
end;

CREATE DEFINER = CURRENT_USER TRIGGER insertInsolventUser AFTER UPDATE ON user FOR EACH ROW
BEGIN
    IF NEW.isInsolvent = true AND
       NEW.user_id NOT IN (SELECT user_id FROM insolventusers) THEN
        INSERT INTO insolventUsers VALUE (NEW.user_id);

    ELSE
        IF NEW.isInsolvent = false AND
           NEW.user_id IN (SELECT user_id FROM insolventusers) THEN
            DELETE FROM insolventUsers i WHERE i.user_id = NEW.user_id;
        END if;
    END IF;
end;

CREATE DEFINER = CURRENT_USER TRIGGER updateUnplaceableOrder AFTER UPDATE ON `order` FOR EACH ROW
BEGIN
    IF (NEW.isPlaceable = false) THEN
        IF (NEW.service_package_order NOT IN (SELECT order_id FROM unplaceableOrders)) THEN
            INSERT INTO unplaceableOrders (SELECT s.service_pack_id
                                           FROM service_pack s
                                           WHERE NEW.service_package_order = s.service_pack_id);
        END if;
    ELSE
        IF (NEW.service_package_order IN (SELECT order_id FROM unplaceableOrders)) THEN
            DELETE FROM unplaceableOrders u WHERE u.order_id = NEW.service_package_order;
        END if;
    END if;
end;

CREATE DEFINER = CURRENT_USER TRIGGER insertUnplaceableOrder AFTER INSERT ON `order` FOR EACH ROW
BEGIN
    IF (NEW.isPlaceable = false) THEN
        IF (NEW.service_package_order NOT IN (SELECT order_id FROM unplaceableOrders)) THEN
            INSERT INTO unplaceableOrders (SELECT s.service_pack_id
                                            FROM service_pack s
                                            WHERE NEW.service_package_order = s.service_pack_id);
        end if;
    ELSE
        IF (NEW.service_package_order in (SELECT  ``.`order`.order_id FROM unplaceableOrders)) THEN
            DELETE FROM unplaceableOrders s
            WHERE s.order_id = NEW.service_package_order;
        end if;
    end if;
end;

CREATE DEFINER = CURRENT_USER TRIGGER insertOptionalServiceBestSold AFTER INSERT ON `order` FOR EACH ROW
BEGIN
    DELETE FROM salesPerOptionalService;
    INSERT INTO salesPerOptionalService

        SELECT os.optional_service_id, (os.monthly_fee * p.duration)
        FROM `order` o
            JOIN service_pack sp on sp.service_pack_id = o.service_package_order
            JOIN optional_services_selected i on i.service_pack_id = sp.service_pack_id
            JOIN optional_service os on os.optional_service_id = i.optional_service_id
            JOIN period p on p.period_id = sp.period_service_pack
        WHERE sp.service_pack_id = NEW.service_package_order;
    UPDATE orderPerOptionalService o, salesPerOptionalService s
    SET o.sales = o.sales + s.sales
    WHERE o.optional_service_id = s.optional_service_id;

    DELETE FROM bestOptionalService;
    INSERT INTO  bestOptionalService
        SELECT o1.optional_service_id, o1.sales
        FROM orderPerOptionalService o1
        WHERE o1.optional_service_id
                  IN (SELECT MAX(s2.sales) FROM salesPerOptionalService s2);

end;

CREATE DEFINER = CURRENT_USER TRIGGER updateOptionalServiceBestSold AFTER UPDATE ON `order` FOR EACH ROW
BEGIN
    DELETE FROM salesPerOptionalService;
    INSERT INTO salesPerOptionalService

    SELECT os.optional_service_id, (os.monthly_fee * p.duration)
    FROM `order` o
             JOIN service_pack sp on sp.service_pack_id = o.service_package_order
             JOIN optional_services_selected i on i.service_pack_id = sp.service_pack_id
             JOIN optional_service os on os.optional_service_id = i.optional_service_id
             JOIN period p on p.period_id = sp.period_service_pack
    WHERE sp.service_pack_id = NEW.service_package_order;
    UPDATE orderPerOptionalService o, salesPerOptionalService s
    SET o.sales = o.sales + s.sales
    WHERE o.optional_service_id = s.optional_service_id;

    DELETE FROM bestOptionalService;
    INSERT INTO  bestOptionalService
    SELECT o1.optional_service_id, o1.sales
    FROM orderPerOptionalService o1
    WHERE o1.optional_service_id
              IN (SELECT MAX(s2.sales) FROM salesPerOptionalService s2);

end;

CREATE DEFINER = CURRENT_USER TRIGGER insertOptionalService AFTER INSERT ON optional_service FOR EACH ROW
BEGIN
    INSERT INTO salesPerOptionalService(optional_service_id)
    VALUES(NEW.optional_service_id);
end;

CREATE DEFINER = CURRENT_USER TRIGGER insertSale AFTER INSERT ON `order` FOR EACH ROW
BEGIN
    DECLARE a, b int;
    IF NEW.isPlaceable = true THEN
        SELECT s.cost, s.total_cost_optional_services INTO a, b
        FROM service_pack s
        WHERE s.service_pack_id = NEW.service_package_order;

        UPDATE sales_per_package spp
        SET spp.total_sales_with_optional = spp.total_sales_with_optional + a + b, spp.total_sales_no_optional = spp.total_sales_no_optional + a
        WHERE spp.sales_per_package_id IN ( SELECT s1.available_package
                                            FROM  service_pack s1
                                            WHERE s1.service_pack_id = NEW.service_package_order);
    end if;
end;

CREATE DEFINER = CURRENT_USER TRIGGER updateSale AFTER UPDATE ON `order` FOR EACH ROW
BEGIN
    DECLARE a, b int;
    IF NEW.isPlaceable = true THEN
        SELECT s.cost, s.total_cost_optional_services INTO a, b
        FROM service_pack s
        WHERE s.service_pack_id = NEW.service_package_order;

        UPDATE sales_per_package spp
        SET spp.total_sales_with_optional = spp.total_sales_with_optional + a + b, spp.total_sales_no_optional = spp.total_sales_no_optional + a
        WHERE spp.sales_per_package_id IN ( SELECT s1.available_package
                                            FROM  service_pack s1
                                            WHERE s1.service_pack_id = NEW.service_package_order);
    end if;
end;

CREATE DEFINER = CURRENT_USER TRIGGER createNewAvailableServicePack_P3 AFTER INSERT ON available_service_package FOR EACH ROW
BEGIN
    INSERT INTO dbproject2022.average_optional_products_perpackage(average_optional_products_perpackage_id)
    VALUES (NEW.available_service_pack_id);
end;

CREATE DEFINER = CURRENT_USER TRIGGER updateInsolventUsers AFTER UPDATE ON user FOR EACH ROW
BEGIN
    IF NEW.isInsolvent = true THEN
        IF(NEW.user_id NOT IN (SELECT iu.user_id FROM insolventUsers iu)) THEN
            INSERT INTO insolventUsers VALUES (NEW.user_id);
        end if;
    end if;
end;

CREATE DEFINER = CURRENT_USER TRIGGER insertSaleOptionalService AFTER INSERT ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN DELETE FROM orderPerOptionalService; INSERT INTO orderPerOptionalService
        SELECT os.optional_service_id, (os.monthly_fee * p.duration)
        FROM `order` o
        JOIN service_pack s ON s.service_pack_id = o.service_package_order
        JOIN optional_services_selected oss ON oss.service_pack_id = s.service_pack_id
        JOIN period p ON p.period_id = s.period_service_pack
        JOIN optional_service os ON os.optional_service_id = oss.optional_service_id
        WHERE s.service_pack_id = NEW.service_package_order;

        UPDATE salesPerOptionalService s, orderPerOptionalService ops
        SET s.sales = s.sales + ops.sales
        WHERE s.optional_service_id = ops.optional_service_id;

        DELETE FROM bestOptionalService;
        INSERT INTO bestOptionalService
        SELECT s1.optional_service_id, s1.sales
        FROM salesPerOptionalService s1
        WHERE s1.optional_service_id is not null AND s1.sales IN (SELECT MAX(S2.sales) FROM salesPerOptionalService s2);
    END IF;
end;

CREATE DEFINER = CURRENT_USER TRIGGER updateSaleOptionalService AFTER UPDATE ON `order` FOR EACH ROW
BEGIN
    IF NEW.isPlaceable = true THEN DELETE FROM orderPerOptionalService; INSERT INTO orderPerOptionalService
                                                                        SELECT os.optional_service_id, (os.monthly_fee * p.duration)
                                                                        FROM `order` o
                                                                                 JOIN service_pack s ON s.service_pack_id = o.service_package_order
                                                                                 JOIN optional_services_selected oss ON oss.service_pack_id = s.service_pack_id
                                                                                 JOIN period p ON p.period_id = s.period_service_pack
                                                                                 JOIN optional_service os ON os.optional_service_id = oss.optional_service_id
                                                                        WHERE s.service_pack_id = NEW.service_package_order;

    UPDATE salesPerOptionalService s, orderPerOptionalService ops
    SET s.sales = s.sales + ops.sales
    WHERE s.optional_service_id = ops.optional_service_id;

    DELETE FROM bestOptionalService;
    INSERT INTO bestOptionalService
    SELECT s1.optional_service_id, s1.sales
    FROM salesPerOptionalService s1
    WHERE s1.optional_service_id is not null AND s1.sales IN (SELECT MAX(S2.sales) FROM salesPerOptionalService s2);
    END IF;
end;