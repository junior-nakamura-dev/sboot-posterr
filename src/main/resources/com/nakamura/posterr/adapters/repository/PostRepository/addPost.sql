INSERT INTO post_entity (post, post_original_id, user_id, date_created) VALUES (:post, :postOriginalId, :userId, now() at time zone 'utc');
