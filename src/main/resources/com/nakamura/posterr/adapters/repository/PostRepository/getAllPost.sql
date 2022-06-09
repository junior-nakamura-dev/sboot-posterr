SELECT * from post_entity
WHERE 1 = 1 AND id > :lastPostId AND (:userProfileId IS NULL OR user_id = :userProfileId)
ORDER BY date_created DESC
LIMIT :chunk OFFSET :offset
