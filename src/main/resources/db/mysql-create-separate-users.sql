CREATE DATABASE IF NOT EXISTS fitness
    CHARACTER SET = utf8
    COLLATE utf8_general_ci;

USE fitness;

DROP TABLE IF EXISTS assignments;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS trainers;
DROP TABLE IF EXISTS admins;
DROP TABLE IF EXISTS users;



CREATE TABLE users
(
    id		 	 INT 							    NOT NULL AUTO_INCREMENT,
    login	 	 VARBINARY(255) 				    NOT NULL,
    password 	 VARBINARY(255) 				    NOT NULL,
    role 	     ENUM('ADMIN', 'TRAINER', 'CLIENT') NOT NULL DEFAULT 'CLIENT',
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
    active 	     BOOLEAN                            NOT NULL DEFAULT true,
    PRIMARY KEY (trainerId),
    FOREIGN KEY (trainerId) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE clients
(
    clientId	 INT 							  NOT NULL,
    name	 	 VARCHAR(255) 					  NOT NULL,
    lastName 	 VARCHAR(255) 					  NOT NULL,
    registerDate TIMESTAMP				          NOT NULL DEFAULT now(),
    hisTrainerId INT 							  ,
    discount     INT                              UNSIGNED NOT NULL DEFAULT 0 CHECK (discount >=0 AND discount <=100),
    phone        VARCHAR(255)                     ,
    active 	     BOOLEAN                            NOT NULL DEFAULT true,
    PRIMARY KEY (clientId),
    FOREIGN KEY (clientId) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (hisTrainerId) REFERENCES trainers (trainerId) ON DELETE CASCADE
);

CREATE TABLE orders
(
    id		 	    INT 	    NOT NULL AUTO_INCREMENT,
    clientId	 	INT 	    NOT NULL,
    trainerId	    INT 	    NOT NULL,
    registerDate    TIMESTAMP   NOT NULL DEFAULT now(),
    description     TEXT 	    ,
    active          BOOLEAN     NOT NULL DEFAULT true,
    PRIMARY KEY (id),
    FOREIGN KEY (clientId) REFERENCES clients (clientId) ON DELETE CASCADE,
    FOREIGN KEY (trainerId) REFERENCES trainers (trainerId) ON DELETE CASCADE
);

CREATE TABLE assignments
(
    id		 	    INT 	        NOT NULL AUTO_INCREMENT,
    orderId	 	    INT 	        NOT NULL,
    clientId	    INT 	        NOT NULL,
    trainerId	    INT 	        NOT NULL,
    registerDate    TIMESTAMP       NOT NULL DEFAULT now(),
    exercises       TEXT 	        NOT NULL,
    nutrition       TEXT 	        NOT NULL,
    startDate       DATE 	        NOT NULL,
    endDate         DATE 	        NOT NULL,
    active          BOOLEAN         NOT NULL DEFAULT true,
    price           DECIMAL(6,2)    UNSIGNED NOT NULL,
    userComment     TEXT            DEFAULT NULL,
    accept          BOOLEAN         DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (orderId) REFERENCES orders (id) ON DELETE CASCADE,
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
    PRIMARY KEY (id),
    FOREIGN KEY (clientId) REFERENCES clients (clientId) ON DELETE CASCADE,
    FOREIGN KEY (trainerId) REFERENCES trainers (trainerId) ON DELETE CASCADE
);

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

INSERT INTO clients(clientId, name, lastName, registerDate, hisTrainerId, discount, phone)
VALUES (4, 'Vasya', 'Vasiliy', '2015-08-01 14:16:43', 2, 10, '111-11-11'),
       (5, 'Ghost', 'Ghostman', '2015-10-04 15:20:41', null, default, null),
       (6, 'Pasha', 'Pavel', '2016-02-21 10:28:02', 3, 5, '333-33-33'),
       (7, 'Dima', 'Dmitry', '2016-05-27 09:51:22', 3, 0, '444-44-44');

INSERT INTO orders(clientId, trainerId, registerDate, description)
VALUES (4, 2, '2015-08-21 10:16:43', 'I wanna training! cl-tr : 4-2'),
       (6, 3, '2016-03-21 10:28:02', 'I wanna training! cl-tr : 6-3'),
       (7, 3, '2016-06-01 09:51:22', 'I wanna training! cl-tr : 7-3');

INSERT INTO assignments(orderId, clientId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price)
VALUES (1, 4, 2, '2015-08-22 10:16:43', 'Your exercises! tr-cl : 2-4', 'Your nutrition1', '2015-08-25', '2015-10-25', 200),
       (2, 6, 3, '2016-03-22 10:28:02', 'Your exercises! tr-cl : 3-6', 'Your nutrition2', '2016-03-24', '2016-05-22', 200),
       (3, 7, 3, '2016-06-02 09:51:22', 'Your exercises! tr-cl : 3-7', 'Your nutrition3', '2016-06-04', '2016-09-04', 250);

INSERT INTO comments(clientId, trainerId, registerDate, comment)
VALUES (4, 2, '2015-09-22 10:16:43', 'BEST!'),
       (6, 3, '2016-04-24 10:16:43', 'NORM!'),
       (7, 3, '2016-07-04 14:18:43', 'BAD!');