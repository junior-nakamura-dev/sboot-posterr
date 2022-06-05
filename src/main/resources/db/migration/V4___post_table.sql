CREATE TABLE post_entity
(
    id                BIGSERIAL PRIMARY KEY,
    post              TEXT NOT NULL,
    user_id           BIGINT NOT NULL,
    post_original_id  BIGINT,
    date_created      TIMESTAMP NOT NULL
);

ALTER TABLE post_entity ADD CONSTRAINT FK_REPOST FOREIGN KEY(post_original_id) REFERENCES post_entity(id);
