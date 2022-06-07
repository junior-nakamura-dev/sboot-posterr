INSERT INTO user_entity VALUES (1, 'john-smith');
INSERT INTO user_entity VALUES (2, 'jr-nakamura');
INSERT INTO user_entity VALUES (3, 'john-mills');


INSERT INTO following_entity VALUES (1, 2);
INSERT INTO followed_entity VALUES  (2, 1);

INSERT INTO following_entity VALUES (1, 3);
INSERT INTO followed_entity VALUES  (3, 1);

INSERT INTO following_entity VALUES (2, 1);
INSERT INTO followed_entity VALUES  (1, 2);

COMMIT;

