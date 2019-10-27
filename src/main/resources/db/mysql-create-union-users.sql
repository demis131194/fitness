CREATE DATABASE IF NOT EXISTS fitness
    CHARACTER SET = utf8
    COLLATE utf8_general_ci;

USE fitness;

DROP TABLE IF EXISTS assignments;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id	         INT 							    NOT NULL AUTO_INCREMENT,
    login	 	 VARBINARY(50) 				        NOT NULL,
    password 	 VARBINARY(50) 				        NOT NULL,
    name	 	 VARCHAR(50) 					    NOT NULL,
    lastName 	 VARCHAR(50) 					    NOT NULL,
    registerDate TIMESTAMP				            NOT NULL DEFAULT now(),
    trainerId	 INT 							    ,
    role		 ENUM('CLIENT', 'TRAINER', 'ADMIN') NOT NULL DEFAULT 'CLIENT',
    active		 BOOLEAN                            NOT NULL DEFAULT true,
    discount     INT                                UNSIGNED NOT NULL DEFAULT 0 CHECK (discount >=0 AND discount <= 100),
    phone        VARCHAR(20)                       ,
    PRIMARY KEY (id),
    UNIQUE KEY login_UNIQUE (login)
);

CREATE TABLE orders
(
    id		 	    INT 	    NOT NULL AUTO_INCREMENT,
    userId	 	    INT 	    NOT NULL,
    trainerId	    INT 	    NOT NULL,
    registerDate    TIMESTAMP   NOT NULL DEFAULT now(),
    description     TEXT 	    ,
    active          BOOLEAN     NOT NULL DEFAULT true,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES users (id),
    FOREIGN KEY (trainerId) REFERENCES users (id)
);

CREATE TABLE assignments
(
    id		 	    INT 	    NOT NULL AUTO_INCREMENT,
    orderId	 	    INT 	    NOT NULL,
    userId	 	    INT 	    NOT NULL,
    trainerId	 	INT 	    NOT NULL,
    registerDate    TIMESTAMP   NOT NULL DEFAULT now(),
    exercises       TEXT 	    NOT NULL,
    nutrition       TEXT 	    NOT NULL,
    startDate       DATE 	    NOT NULL,
    endDate         DATE 	    NOT NULL,
    active          BOOLEAN     NOT NULL DEFAULT true,
    price           INT         UNSIGNED NOT NULL,
    accept          BOOLEAN     DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (orderId) REFERENCES orders (id)
);

CREATE TABLE comments
(
    id		 	    INT 	    NOT NULL AUTO_INCREMENT,
    userId	 	    INT 	    NOT NULL,
    trainerId	 	INT 	    NOT NULL,
    registerDate    TIMESTAMP   NOT NULL DEFAULT now(),
    comment         TEXT        NOT NULL,
    active          BOOLEAN     NOT NULL DEFAULT true,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES users (id),
    FOREIGN KEY (trainerId) REFERENCES users (id)
);

INSERT INTO users(login, password, name, lastName, registerDate, trainerId, role, phone)
VALUES ('denis', 'denis', 'Денис', 'Кацук', '2014-04-01 14:16:28', null, 'ADMIN', null),
       ('trainer1', 'trainer', 'Trainer-1', 'Trainer-1_Fam', '2016-04-01 12:16:28', null, 'TRAINER', '222-33-22'),
       ('trainer2', 'trainer', 'Trainer-2', 'Trainer-2_Fam', '2017-04-01 17:16:28', null, 'TRAINER', '222-44-77'),
       ('dima', 'dima', 'Dima', 'Dima_fam', default, 2, 'CLIENT', null),
       ('vasya', 'vasya', 'Vasya', 'Vasya_fam', default, 2, 'CLIENT', null),
       ('vova', 'vova', 'Vova', 'Vova_fam', default, 3, 'CLIENT', '333-22-11'),
       ('client1', 'client', 'Client-1', 'Client-1_Fam', default,  3, 'CLIENT', null);

INSERT INTO orders(userId, trainerId, description)
VALUES (4, 2, 'training-1'),
       (5, 2, 'training-2'),
       (6, 3, 'training-3'),
       (7, 3, 'training-4');

INSERT INTO assignments(orderId, userId, trainerId, exercises, nutrition, startDate, endDate, price)
VALUES (1, 4, 2, 'exercises-2', 'nutrition-1', '2019-09-01', '2019-10-01', 200),
       (2, 5, 2, 'exercises-2', 'nutrition-2', '2019-09-01', '2019-10-01', 200),
       (3, 6, 3, 'exercises-2', 'nutrition-3', '2019-09-01', '2019-10-01', 200),
       (4, 7, 3, 'exercises-2', 'nutrition-4', '2019-09-01', '2019-10-01', 200);

INSERT INTO comments(userId, trainerId, comment)
VALUES (4,2,'Comment-1'),
       (5,2,'Comment-2'),
       (6,3,'Comment-3'),
       (7,3,'Comment-4');
