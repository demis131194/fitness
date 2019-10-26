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



CREATE TABLE admins
(
    id		 	 INT 							  UNSIGNED NOT NULL AUTO_INCREMENT,
    login	 	 VARBINARY(255) 				  NOT NULL,
    password 	 VARBINARY(255) 				  NOT NULL,
    name	 	 VARCHAR(255) 					  NOT NULL,
    lastName 	 VARCHAR(255) 					  NOT NULL,
    role		 ENUM('ADMIN')                    NOT NULL DEFAULT 'ADMIN',
    PRIMARY KEY (id),
    UNIQUE KEY login_UNIQUE (login)
);

CREATE TABLE trainers
(
    id	 INT 							  NOT NULL AUTO_INCREMENT,
    login	 	 VARBINARY(255) 				  NOT NULL,
    password 	 VARBINARY(255) 				  NOT NULL,
    name	 	 VARCHAR(255) 					  NOT NULL,
    lastName 	 VARCHAR(255) 					  NOT NULL,
    registerDate TIMESTAMP				          NOT NULL DEFAULT now(),
    role		 ENUM('TRAINER')                  NOT NULL DEFAULT 'TRAINER',
    phone		 VARCHAR(20)                      NOT NULL,
    active		 BOOLEAN                          NOT NULL DEFAULT true,
    PRIMARY KEY (id),
    UNIQUE KEY login_UNIQUE (login)
);

CREATE TABLE clients
(
    id	 INT 							  NOT NULL AUTO_INCREMENT,
    login	 	 VARBINARY(255) 				  NOT NULL,
    password 	 VARBINARY(255) 				  NOT NULL,
    name	 	 VARCHAR(255) 					  NOT NULL,
    lastName 	 VARCHAR(255) 					  NOT NULL,
    registerDate TIMESTAMP				          NOT NULL DEFAULT now(),
    trainerId	 INT 							  NOT NULL,
    role		 ENUM('CLIENT')                   NOT NULL DEFAULT 'CLIENT',
    active		 BOOLEAN                          NOT NULL DEFAULT true,
    discount     INT                              UNSIGNED NOT NULL DEFAULT 0 CHECK (discount >=0 AND discount <=100),
    phone        VARCHAR(255)                     ,
    PRIMARY KEY (id),
    UNIQUE KEY `login_UNIQUE` (`login`),
    FOREIGN KEY (trainerId) REFERENCES trainers (id)
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
    FOREIGN KEY (userId) REFERENCES clients (id),
    FOREIGN KEY (trainerId) REFERENCES trainers (id)
);

CREATE TABLE assignments
(
    id		 	    INT 	    NOT NULL AUTO_INCREMENT,
    orderId	 	    INT 	    NOT NULL,
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
    clientId	 	INT 	    NOT NULL,
    trainerId	 	INT 	    NOT NULL,
    registerDate    TIMESTAMP   NOT NULL DEFAULT now(),
    comment         TEXT        NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (clientId) REFERENCES clients (id),
    FOREIGN KEY (trainerId) REFERENCES trainers (id)
);

INSERT INTO admins(login, password, name, lastName)
VALUES ('denis', 'denis', 'Денис', 'Кацук');

INSERT INTO trainers(login, password, name, lastName, phone)
VALUES ('trainer-1', 'trainer', 'trainer-1', 'trainer-1_Fam', '222-33-22'),
       ('trainer-2', 'trainer', 'trainer-2', 'trainer-2_Fam', '222-44-77');

INSERT INTO clients(login, password, name, lastName, trainerId, phone)
VALUES ('vasya', 'vasya', 'Vasya', 'Vasya_fam', 1, '222-33-22'),
       ('dima', 'dima', 'Dima', 'Dima_fam', 1, null);