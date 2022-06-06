SELECT * from post_entity
WHERE user_id = :userId
ORDER BY date_created DESC
LIMIT 1;
