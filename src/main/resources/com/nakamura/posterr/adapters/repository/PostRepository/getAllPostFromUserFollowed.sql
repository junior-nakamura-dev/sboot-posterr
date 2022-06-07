SELECT pe.* from post_entity pe
WHERE pe.user_id IN (SELECT user_following_id FROM following_entity WHERE user_id = :userId)
ORDER BY pe.date_created DESC
LIMIT :chunk OFFSET :offset
