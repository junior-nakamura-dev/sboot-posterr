INSERT INTO user_entity VALUES (1, 'john-smith', now() at time zone 'utc');
INSERT INTO user_entity VALUES (2, 'jr-nakamura', now() at time zone 'utc');
INSERT INTO user_entity VALUES (3, 'john-mills', now() at time zone 'utc');


INSERT INTO following_entity VALUES (1, 2);
INSERT INTO followed_entity VALUES  (2, 1);

INSERT INTO following_entity VALUES (1, 3);
INSERT INTO followed_entity VALUES  (3, 1);

INSERT INTO following_entity VALUES (2, 1);
INSERT INTO followed_entity VALUES  (1, 2);

COMMIT;

