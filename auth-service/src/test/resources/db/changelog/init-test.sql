CREATE SEQUENCE IF NOT EXISTS user_sequence START WITH 4 INCREMENT BY 1;

CREATE TABLE users_table
(id BIGINT NOT NULL PRIMARY KEY ,
email VARCHAR(320) NOT NULL,
password VARCHAR(320) NOT NULL,
creation_date TIMESTAMP NOT NULL);