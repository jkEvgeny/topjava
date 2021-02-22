DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE meals_seq RESTART WITH 200000;


INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals ( datetime, description, calories)
VALUES (now(), 'Завтрак', 500),
       (now()+'1 minute', 'Обед', 1000),
       (now()+'2 minute', 'Ужин', 500),
       (now()+'3 minute','Еда на граничное значение', 100),
       (now()+'4 minute','Завтрак', 1000),
       (now()+'5 minute','Обед', 500),
       (now()+'6 minute','Ужин', 410),
       (now()+'2 minute','Админ ланч', 510),
       (now()+'3 minute','Админ ужин', 1500);
