CREATE SEQUENCE IF NOT EXISTS quote_sequence START WITH 10 INCREMENT BY 1;

CREATE TABLE quote
(id BIGINT NOT NULL PRIMARY KEY ,
content VARCHAR(1000) NOT NULL,
user_id BIGINT NOT NULL,
creat_or_update_time TIMESTAMP NOT NULL);
