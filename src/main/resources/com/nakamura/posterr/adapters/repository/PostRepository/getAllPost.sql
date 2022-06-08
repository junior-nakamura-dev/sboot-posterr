SELECT * from post_entity
WHERE 1 = 1 AND id > :lastPostId
ORDER BY date_created DESC
LIMIT :chunk OFFSET :offset
