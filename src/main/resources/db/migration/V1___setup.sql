CREATE TABLE user_entity
(
    id                BIGINT PRIMARY KEY,
    username          VARCHAR(14) NOT NULL
);

CREATE TABLE following_entity
(
    user_id                BIGINT,
    user_following_id      BIGINT
);

CREATE TABLE followed_entity
(
    user_id                BIGINT,
    user_followed_id       BIGINT
);

ALTER TABLE following_entity ADD CONSTRAINT PK_USER_FOLLOWING PRIMARY KEY(user_id, user_following_id);
ALTER TABLE followed_entity  ADD CONSTRAINT PK_USER_FOLLOWED  PRIMARY KEY(user_id, user_followed_id);