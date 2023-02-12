INSERT INTO quote (id, content, user_id, creat_or_update_time)
VALUES (1, 'inspirational quote 1', 1, current_timestamp - interval '1 23:00:0' DAY TO SECOND),
       (2, 'inspirational quote 2', 1, current_timestamp - interval '1 22:00:0' DAY TO SECOND),
       (3, 'inspirational quote 4', 1, current_timestamp - interval '1 0:00:0' DAY TO SECOND),
       (4, 'smart quote 1', 2, current_timestamp - interval '0 23:00:0' DAY TO SECOND),
       (5, 'smart quote 2', 2, current_timestamp - interval '0 20:00:0' DAY TO SECOND),
       (6, 'another inspirational quote 5', 1, current_timestamp - interval '0 15:00:0' DAY TO SECOND),
       (7, 'one more inspirational quote 6', 1, current_timestamp - interval '0 13:00:0' DAY TO SECOND),
       (8, 'one more smart quote3', 2, current_timestamp - interval '0 3:00:0' DAY TO SECOND),
       (9, 'joke', 3, current_timestamp - interval '0 1:00:0' DAY TO SECOND);