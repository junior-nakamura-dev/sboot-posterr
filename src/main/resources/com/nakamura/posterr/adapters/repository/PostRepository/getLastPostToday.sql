SELECT * from post_entity
WHERE user_id = :userId and DATE_TRUNC('day', date_created) = DATE_TRUNC('day', now() at time zone 'utc')
ORDER BY date_created DESC
LIMIT 1;
