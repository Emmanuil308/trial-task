INSERT INTO users_table (id, email, password, creation_date)
VALUES (1, 'some1@gmail.com', '1', current_timestamp - interval '2 0:00:0' DAY TO SECOND),
       (2, 'some2@gmail.com', '1', current_timestamp - interval '1 0:00:0' DAY TO SECOND),
       (3, 'some3@gmail.com', '1', current_timestamp - interval '0 5:00:0' DAY TO SECOND);