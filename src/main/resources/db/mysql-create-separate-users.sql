SET GLOBAL event_scheduler = ON;

CREATE DATABASE IF NOT EXISTS fitness
    CHARACTER SET = utf8
    COLLATE utf8_general_ci;

USE fitness;

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS trainers;
DROP TABLE IF EXISTS admins;
DROP TABLE IF EXISTS users;

DROP EVENT IF EXISTS orders_change_status_1_event;
DROP EVENT IF EXISTS orders_change_status_2_event;
DROP TRIGGER IF EXISTS change_discount_level_1;
DROP TRIGGER IF EXISTS change_discount_level_2;
DROP TRIGGER IF EXISTS change_discount_level_3;
DROP TRIGGER IF EXISTS delete_user;
DROP TRIGGER IF EXISTS restore_user;



CREATE TABLE users
(
    id		 	 INT 							    NOT NULL AUTO_INCREMENT,
    login	 	 VARBINARY(255) 				    NOT NULL,
    password 	 VARBINARY(255) 				    NOT NULL,
    role 	     ENUM('ADMIN', 'TRAINER', 'CLIENT') NOT NULL DEFAULT 'CLIENT',
    active 	     BOOLEAN                            NOT NULL DEFAULT true,
    PRIMARY KEY (id),
    UNIQUE KEY login_UNIQUE (login)
);

CREATE TABLE admins
(
    adminId		 INT 							  NOT NULL,
    name	 	 VARCHAR(255) 					  NOT NULL,
    lastName 	 VARCHAR(255) 					  NOT NULL,
    PRIMARY KEY (adminId),
    FOREIGN KEY (adminId) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE trainers
(
    trainerId	 INT 							  NOT NULL,
    name	 	 VARCHAR(255) 					  NOT NULL,
    lastName 	 VARCHAR(255) 					  NOT NULL,
    registerDate TIMESTAMP				          NOT NULL DEFAULT now(),
    phone		 VARCHAR(20)                      NOT NULL,
    active 	     BOOLEAN                          NOT NULL DEFAULT true,
    PRIMARY KEY (trainerId),
    FOREIGN KEY (trainerId) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE clients
(
    clientId	    INT 							 NOT NULL,
    name	 	    VARCHAR(255) 				     NOT NULL,
    lastName 	    VARCHAR(255) 					 NOT NULL,
    registerDate    TIMESTAMP				         NOT NULL DEFAULT now(),
    discount        INT                              UNSIGNED NOT NULL DEFAULT 0 CHECK (discount >=0 AND discount <=100),
    phone           VARCHAR(255)                     DEFAULT NULL,
    cash            DECIMAL(7,2)                     DEFAULT 0 CHECK (cash >= 0),
    discountLevel   INT                              NOT NULL DEFAULT 0 CHECK (discountLevel >=0 AND discountLevel <=3),
    active 	        BOOLEAN                          NOT NULL DEFAULT true,
    PRIMARY KEY (clientId),
    FOREIGN KEY (clientId) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    id		 	    INT 	        NOT NULL AUTO_INCREMENT,
    clientId	 	INT 	        NOT NULL,
    trainerId	    INT 	        NOT NULL,
    registerDate    TIMESTAMP       NOT NULL DEFAULT now(),
    exercises       TEXT 	        DEFAULT NULL,
    nutrition       TEXT 	        DEFAULT NULL,
    startDate       DATE 	        DEFAULT NULL,
    endDate         DATE 	        DEFAULT NULL,
    price           DECIMAL(6,2)    DEFAULT NULL CHECK (price >= 0),
    clientComment   TEXT            DEFAULT NULL,
    status          INT             DEFAULT 0 CHECK (status >=0 AND status <=2),
    accept          BOOLEAN         DEFAULT NULL,
    active          BOOLEAN         NOT NULL DEFAULT true,
    PRIMARY KEY (id),
    FOREIGN KEY (clientId) REFERENCES clients (clientId) ON DELETE CASCADE,
    FOREIGN KEY (trainerId) REFERENCES trainers (trainerId) ON DELETE CASCADE
);

CREATE TABLE comments
(
    id		 	    INT 	    NOT NULL AUTO_INCREMENT,
    clientId	 	INT 	    NOT NULL,
    trainerId	 	INT 	    NOT NULL,
    registerDate    TIMESTAMP   NOT NULL DEFAULT now(),
    comment         TEXT        NOT NULL,
    active          BOOLEAN     NOT NULL DEFAULT true,
    PRIMARY KEY (id),
    FOREIGN KEY (clientId) REFERENCES clients (clientId) ON DELETE CASCADE,
    FOREIGN KEY (trainerId) REFERENCES trainers (trainerId) ON DELETE CASCADE
);

CREATE EVENT orders_change_status_1_event
    ON SCHEDULE EVERY 1 DAY
    STARTS TIME('00:00:30')
    DO
    UPDATE orders SET status = 1 WHERE accept = true AND status = 0 AND CURRENT_DATE BETWEEN startDate AND endDate;

CREATE EVENT orders_change_status_2_event
    ON SCHEDULE EVERY 1 DAY
        STARTS TIME('00:00:35')
    DO
    UPDATE orders SET status = 2 WHERE accept = true AND status = 1 AND CURRENT_DATE > endDate;

CREATE TRIGGER change_discount_level_1 AFTER UPDATE ON orders FOR EACH ROW
BEGIN
    IF NEW.status = 2 THEN
        UPDATE clients SET clients.discount = clients.discount + 10, clients.discountLevel = 1
        WHERE clients.clientId =  OLD.clientId
          AND (SELECT COUNT(orders.clientId) FROM orders WHERE orders.clientId = OLD.clientId AND status = 2) = 3
          AND discountLevel = 0
          AND discount <= 90;
    END IF;
END;

CREATE TRIGGER change_discount_level_2 AFTER UPDATE ON orders FOR EACH ROW
BEGIN
    IF NEW.status = 2 THEN
        UPDATE clients SET clients.discount = clients.discount + 10, clients.discountLevel = 2
        WHERE clients.clientId =  OLD.clientId
          AND (SELECT COUNT(orders.clientId) FROM orders WHERE orders.clientId = OLD.clientId AND status = 2) = 6
          AND discountLevel = 1
          AND discount <= 90;
    END IF;
END;

CREATE TRIGGER change_discount_level_3 AFTER UPDATE ON orders FOR EACH ROW
BEGIN
    IF NEW.status = 2 THEN
        UPDATE clients SET clients.discount = clients.discount + 10, clients.discountLevel = 3
        WHERE clients.clientId =  OLD.clientId
          AND (SELECT COUNT(orders.clientId) FROM orders WHERE orders.clientId = OLD.clientId AND status = 2) = 10
          AND discountLevel = 2
          AND discount <= 90;
    END IF;
END;

CREATE TRIGGER delete_user AFTER UPDATE ON users FOR EACH ROW
BEGIN
    IF NEW.active = false AND NEW.role = 'TRAINER' THEN
        UPDATE trainers SET active = false WHERE trainerId = NEW.id;
        UPDATE comments SET active = false WHERE trainerId = NEW.id;
        UPDATE orders SET active = false WHERE trainerId = NEW.id;
    END IF;
    IF NEW.active = false AND NEW.role = 'CLIENT' THEN
        UPDATE clients SET active = false WHERE clientId = NEW.id;
        UPDATE orders SET active = false WHERE clientId = NEW.id;
    END IF;
END;

CREATE TRIGGER restore_user AFTER UPDATE ON users FOR EACH ROW
BEGIN
    IF NEW.active = true AND NEW.role = 'TRAINER' THEN
        UPDATE trainers SET active = true WHERE trainerId = NEW.id;
        UPDATE comments SET active = true WHERE trainerId = NEW.id;
        UPDATE orders SET active = true WHERE trainerId = NEW.id;
    END IF;
    IF NEW.active = true AND NEW.role = 'CLIENT' THEN
        UPDATE clients SET active = true WHERE clientId = NEW.id;
        UPDATE orders SET active = false WHERE trainerId = NEW.id;
    END IF;
END;



INSERT INTO users(login, password, role)
VALUES ('admin', 'admin', 'ADMIN'),
       ('trainer1', 'trainer', 'TRAINER'),
       ('trainer2', 'trainer', 'TRAINER'),
       ('client1', 'client', default),
       ('client2', 'client', default),
       ('client3', 'client', default),
       ('client4', 'client', default);

INSERT INTO admins(adminId, name, lastName)
VALUES (1, 'Денис', 'Кацук');

INSERT INTO trainers(trainerId, name, lastName, registerDate, phone)
VALUES (2, 'trainer_Name_1', 'trainer_LastName_1', '2014-08-01 20:01:17', '222-33-22'),
       (3, 'trainer_Name_2', 'trainer_LastName_2', '2014-08-01 20:16:43', '222-44-77');

INSERT INTO clients(clientId, name, lastName, registerDate, discount, phone)
VALUES (4, 'Vasya', 'Vasiliy', '2015-08-01 14:16:43', 10, '111-11-11'),
       (5, 'Ghost', 'Ghostman', '2015-10-04 15:20:41', default, default),
       (6, 'Pasha', 'Pavel', '2016-02-21 10:28:02', 5, '333-33-33'),
       (7, 'Dima', 'Dmitry', '2016-05-27 09:51:22', 0, '444-44-44');

INSERT INTO orders(clientId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price, clientComment, status, accept, active)
VALUES (4, 2, '2015-08-21 10:16:43', 'Training-1! cl-tr : 4-2', 'Nutrition-1', '2015-08-21', '2016-08-21', 100, 'Comment-1', 0, null, 1),
       (6, 3, '2016-03-21 10:28:02', 'Training-2! cl-tr : 6-3', 'Nutrition-2', '2016-03-21', '2017-03-21', 250, 'Comment-2', 1, false, 1),
       (7, 3, '2016-06-01 09:51:22', 'Training-3! cl-tr : 7-3', 'Nutrition-3', '2016-06-01', '2017-06-01', 150, 'Comment-3', 2, true, 1);

INSERT INTO comments(clientId, trainerId, registerDate, comment)
VALUES (4, 2, '2015-09-22 10:16:43', 'BEST!'),
       (6, 3, '2016-04-24 10:16:43', 'NORM!'),
       (7, 3, '2016-07-04 14:18:43', 'BAD!');