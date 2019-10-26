CREATE DATABASE IF NOT EXISTS fitness
    CHARACTER SET = utf8
    COLLATE utf8_general_ci;

USE fitness;

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;

INSERT INTO users(login, password, name, lastName, registerDate, role)
VALUES ('denis', 'denis', 'Денис', 'Кацук', '2014-01-10 13:00:00', 'admin'),
       ('vasya', 'vasya','Вася', 'Василий', '2015-03-10 21:32:12', 'trainer'),
       ('dima', 'dima','Дима', 'Дмитрий', '2016-07-16 15:14:00', 'client'),
       ('vova', 'vova','Вова', 'Вован', '2016-08-19 17:52:42', 'client');

INSERT INTO orders(userId, startDate, endDate, price, pay, description)
VALUES (1, '2014-01-10', '2014-02-10', 200, true, 'None_1'),
       (1, '2014-02-10', '2014-03-10', 210, true, 'None_1'),
       (1, '2014-03-10', '2014-04-10', 220, false, 'None_1'),
       (1, '2014-04-10', '2014-05-10', 225, false, 'None_1'),
       (2, '2014-01-12', '2014-02-12', 150, true, 'None_2'),
       (2, '2014-02-12', '2014-03-12', 150, true, 'None_2'),
       (2, '2014-03-12', '2014-04-12', 150, true, 'None_2'),
       (2, '2014-04-12', '2014-05-12', 150, false, 'None_2'),
       (2, '2014-05-12', '2014-06-12', 160, false, 'None_2');