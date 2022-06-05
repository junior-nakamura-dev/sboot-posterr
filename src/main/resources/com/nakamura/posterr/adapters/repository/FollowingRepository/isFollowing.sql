SELECT fi.user_id, fi.user_following_id FROM following_entity fi
INNER JOIN followed_entity fe ON (fi.user_id = fe.user_followed_id and fi.user_following_id = fe.user_id)
WHERE fi.user_id = :userId and fi.user_following_id = :userFollowingId;
