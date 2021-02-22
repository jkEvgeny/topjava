DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS meals;
DROP SEQUENCE IF EXISTS global_seq;
DROP SEQUENCE IF EXISTS meals_seq;

CREATE SEQUENCE global_seq START WITH 100000;
CREATE SEQUENCE meals_seq START WITH 200000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    email            VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    registered       TIMESTAMP           DEFAULT now() NOT NULL,
    enabled          BOOL                DEFAULT TRUE  NOT NULL,
    calories_per_day INTEGER             DEFAULT 2000  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER PRIMARY KEY NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE meals
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('meals_seq'),
    dateTime         TIMESTAMP                               NOT NULL,
    description      VARCHAR                                 NOT NULL,
    calories         INTEGER             DEFAULT 2000        NOT NULL,
    excess           BOOL                DEFAULT FALSE       NOT NULL,
    user_id          INTEGER
);
CREATE UNIQUE INDEX meals_unique_dateTime_idx ON meals (dateTime, user_id);