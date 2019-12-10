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
DROP TABLE IF EXISTS cards;

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
    login	 	 VARBINARY(50) 				        NOT NULL,
    password 	 VARCHAR(50) 				        NOT NULL,
    role 	     ENUM('ADMIN', 'TRAINER', 'CLIENT') NOT NULL DEFAULT 'CLIENT',
    profileImage VARCHAR(255) 					    NOT NULL DEFAULT '/fitness/image/user/default/avatar.png',
    verification BOOLEAN 					        NOT NULL DEFAULT false,
    active 	     BOOLEAN                            NOT NULL DEFAULT false,
    PRIMARY KEY (id),
    UNIQUE KEY login_UNIQUE (login)
);

CREATE TABLE admins
(
    adminId		 INT 							  NOT NULL,
    name	 	 VARCHAR(255) 					  NOT NULL,
    lastName 	 VARCHAR(255) 					  NOT NULL,
    mail     	 VARCHAR(255) 					  NOT NULL,
    PRIMARY KEY (adminId),
    FOREIGN KEY (adminId) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE trainers
(
    trainerId	 INT 							  NOT NULL,
    name	 	 VARCHAR(255) 					  NOT NULL,
    lastName 	 VARCHAR(255) 					  NOT NULL,
    mail     	 VARCHAR(255) 					  NOT NULL,
    registerDate DATETIME				          NOT NULL DEFAULT now(),
    phone		 VARCHAR(20)                      NOT NULL,
    active 	     BOOLEAN                          NOT NULL DEFAULT true,
    PRIMARY KEY (trainerId),
    FOREIGN KEY (trainerId) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE clients
(
    clientId	    INT 						NOT NULL,
    name	 	    VARCHAR(255) 				NOT NULL,
    lastName 	    VARCHAR(255) 				NOT NULL,
    registerDate    DATETIME				    NOT NULL DEFAULT now(),
    discount        INT                         UNSIGNED NOT NULL DEFAULT 0 CHECK (discount >=0 AND discount <=100),
    phone           VARCHAR(255)                DEFAULT NULL,
    mail     	    VARCHAR(255) 				NOT NULL,
    cash            DECIMAL(7,2)                DEFAULT 0 CHECK (cash >= 0),
    discountLevel   INT                         NOT NULL DEFAULT 0 CHECK (discountLevel >=0 AND discountLevel <=3),
    active 	        BOOLEAN                     NOT NULL DEFAULT false,
    PRIMARY KEY (clientId),
    FOREIGN KEY (clientId) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    id		 	    INT 	        NOT NULL AUTO_INCREMENT,
    clientId	 	INT 	        NOT NULL,
    trainerId	    INT 	        NOT NULL,
    registerDate    DATETIME        NOT NULL DEFAULT now(),
    exercises       TEXT 	        DEFAULT NULL,
    nutrition       TEXT 	        DEFAULT NULL,
    startDate       DATE 	        DEFAULT NULL,
    endDate         DATE 	        DEFAULT NULL,
    price           DECIMAL(6,2)    DEFAULT NULL CHECK (price >= 0),
    clientComment   TEXT            DEFAULT NULL,
    status          INT             NOT NULL DEFAULT 0 CHECK (status >=0 AND status <=5),
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
    registerDate    DATETIME    NOT NULL DEFAULT now(),
    comment         TEXT        NOT NULL,
    active          BOOLEAN     NOT NULL DEFAULT true,
    PRIMARY KEY (id),
    FOREIGN KEY (clientId) REFERENCES clients (clientId) ON DELETE CASCADE,
    FOREIGN KEY (trainerId) REFERENCES trainers (trainerId) ON DELETE CASCADE
);

CREATE TABLE cards
(
  cardNumber        VARCHAR(20)     NOT NULL CHECK (cardNumber LIKE '________________'),
  account           DECIMAL(12,2)   NOT NULL DEFAULT 0 CHECK (account >= 0),
  PRIMARY KEY (cardNumber)
);

CREATE EVENT orders_change_status_1_event
    ON SCHEDULE EVERY 1 DAY
    STARTS TIME('00:00:30')
    DO
    UPDATE orders SET status = 4 WHERE status = 3 AND CURRENT_DATE BETWEEN startDate AND endDate;

CREATE EVENT orders_change_status_2_event
    ON SCHEDULE EVERY 1 DAY
        STARTS TIME('00:00:35')
    DO
    UPDATE orders SET status = 5 WHERE status = 4 AND CURRENT_DATE > endDate;

CREATE TRIGGER change_discount_level_1 AFTER UPDATE ON orders FOR EACH ROW
BEGIN
    IF NEW.status = 5 THEN
        UPDATE clients SET clients.discount = clients.discount + 10, clients.discountLevel = 1
        WHERE clients.clientId =  OLD.clientId
          AND (SELECT COUNT(orders.clientId) FROM orders WHERE orders.clientId = OLD.clientId AND status = 2) = 3
          AND discountLevel = 0
          AND discount <= 90;
    END IF;
END;

CREATE TRIGGER change_discount_level_2 AFTER UPDATE ON orders FOR EACH ROW
BEGIN
    IF NEW.status = 5 THEN
        UPDATE clients SET clients.discount = clients.discount + 10, clients.discountLevel = 2
        WHERE clients.clientId =  OLD.clientId
          AND (SELECT COUNT(orders.clientId) FROM orders WHERE orders.clientId = OLD.clientId AND status = 2) = 6
          AND discountLevel = 1
          AND discount <= 90;
    END IF;
END;

CREATE TRIGGER change_discount_level_3 AFTER UPDATE ON orders FOR EACH ROW
BEGIN
    IF NEW.status = 5 THEN
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



INSERT INTO users(login, password, role, active)
VALUES ('admin', '21232F297A57A5A743894A0E4A801FC3', 'ADMIN', true),
       ('trainer1', '2C065AAE9FCB37B49043A5A2012B3DBF', 'TRAINER', true),
       ('trainer2', '2C065AAE9FCB37B49043A5A2012B3DBF', 'TRAINER', true),
       ('client1', '62608E08ADC29A8D6DBC9754E659F125', default, true),
       ('client2', '62608E08ADC29A8D6DBC9754E659F125', default, true),
       ('client3', '62608E08ADC29A8D6DBC9754E659F125', default, true),
       ('client4', '62608E08ADC29A8D6DBC9754E659F125', default, true);

INSERT INTO admins(adminId, name, lastName, mail)
VALUES (1, 'Денис', 'Кацук', 'admin@gmail.com');

INSERT INTO trainers(trainerId, name, lastName, registerDate, phone, mail)
VALUES (2, 'Павел', 'Бегун', '2019-07-01 20:01:17', '2223322', 'trainer1@gmail.com'),
       (3, 'Oliver', 'Might', '2019-07-01 20:16:43', '2224477', 'trainer2@gmail.com');

INSERT INTO clients(clientId, name, lastName, registerDate, discount, phone, mail, active)
VALUES (4, 'Vasya', 'Vasiliy', '2019-07-17 14:16:43', 10, '1111111', 'client1@gmail.com', true),
       (5, 'Ghost', 'Ghostman', '2019-08-04 15:20:41', default, default, 'client2@gmail.com', true),
       (6, 'Pasha', 'Pavel', '2019-10-21 10:28:02', 0, '3333333', 'client3@gmail.com', true),
       (7, 'Dima', 'Dmitry', '2019-11-27 09:51:22', 0, '4444444', 'client4@gmail.com', true);

INSERT INTO orders(clientId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price, clientComment, status, active)
VALUES (4, 2, '2019-07-19 10:16:43', 'Тренировки: №1 ...', 'Питание: ...', '2019-07-21', '2019-08-21', 135, 'Желаю кардио нагрузки', 5, 1),
       (4, 2, '2019-08-22 13:17:20', 'Тренировки: №2 ...', 'Питание: ...', '2019-08-24', '2019-08-25', 9, 'Хочу руки базуки!', 5, 1),
       (4, 2, '2019-08-30 12:16:43', 'Тренировки: №1 ...', 'Питание: ...', '2019-09-01', '2019-01-01', 135, 'Хочу руки базуки!', 5, 1),
       (4, 2, '2019-09-30 10:16:43', 'Тренировки: №3 ...', 'Питание: ...', '2019-10-01', '2019-12-31', 135, 'Comment-4', 4, 1),
       (6, 3, '2019-11-03 10:28:02', 'Тренировки: №2 ...', 'Питание: ...', '2019-11-05', '2019-12-05', 150, 'Хочу тренероваться', 4, 1),
       (7, 3, '2019-11-27 09:51:22', 'Тренировки: №3 ...', 'Питание: ...', '2019-12-20', '2020-01-20', 150, 'Comment-3', 3, 1),
       (7, 3, '2019-11-29 09:51:22', 'Тренировки: №5 ...', 'Питание: ...', '2020-01-21', '2020-07-21', 499.99, 'Хочу кубики!', 3, 1);

INSERT INTO comments(clientId, trainerId, registerDate, comment)
VALUES (4, 2, '2019-09-01 10:16:43', 'BEST!'),
       (6, 3, '2019-10-01 10:16:43', 'NORM!'),
       (6, 3, '2019-10-02 17:16:43', 'Хочу стальной пресс и кубики!!!'),
       (4, 3, '2019-11-05 17:16:43', 'Пойду ЕЩЕ!'),
       (6, 3, '2019-10-02 17:16:43', 'Пойду ЕЩЕ!'),
       (7, 3, '2019-11-02 17:16:43', 'Попробую походить'),
       (7, 3, '2019-11-27 14:18:43', 'BAD!');

INSERT INTO cards(cardNumber, account)
VALUES ('1111222233334444', 1500.25),
       ('5555666677778888', 200);