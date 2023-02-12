CREATE SEQUENCE IF NOT EXISTS quote_sequence START WITH 10 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS vote_sequence START WITH 20 INCREMENT BY 1;

CREATE TABLE quote
(
    id                   BIGINT        NOT NULL PRIMARY KEY,
    content              VARCHAR(1000) NOT NULL,
    user_id              BIGINT        NOT NULL,
    creat_or_update_time TIMESTAMP     NOT NULL
);

CREATE TABLE vote
(
    id            BIGINT       NOT NULL PRIMARY KEY,
    vote_value         VARCHAR(100) NOT NULL,
    quote_id      BIGINT       NOT NULL,
    user_id       BIGINT       NOT NULL,
    creation_time TIMESTAMP    NOT NULL,
    CONSTRAINT fk_quote_id FOREIGN KEY (quote_id) REFERENCES quote (id)
);