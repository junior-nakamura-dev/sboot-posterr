SELECT * from post_entity
ORDER BY date_created DESC
LIMIT :chunk OFFSET :offset
