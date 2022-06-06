package com.nakamura.posterr;

import com.nakamura.posterr.adapters.repository.entity.FollowedEntity;
import com.nakamura.posterr.adapters.repository.entity.FollowingEntity;
import com.nakamura.posterr.adapters.repository.entity.PostEntity;
import com.nakamura.posterr.application.domain.FollowedUser;
import com.nakamura.posterr.application.domain.FollowingUser;
import com.nakamura.posterr.application.domain.Post;

import java.time.OffsetDateTime;

public class TestMocks {

    public static FollowingEntity followingEntityMock() {
        return FollowingEntity
                .builder()
                .userId(1L)
                .userFollowingId(2L)
                .build();
    }

    public static FollowingUser followingUserMock() {
        return FollowingUser
                .builder()
                .userId(1L)
                .userFollowingId(2L)
                .build();
    }

    public static FollowedEntity followedEntityMock() {
        return FollowedEntity
                .builder()
                .userId(1L)
                .userFollowedId(2L)
                .build();
    }

    public static FollowedUser followedUserMock() {
        return FollowedUser
                .builder()
                .userId(1L)
                .userFollowedId(2L)
                .build();
    }

    public static PostEntity postEntityMock() {
        return PostEntity
                .builder()
                .userId(1L)
                .post("TEST")
                .id(1L)
                .dateCreated(OffsetDateTime.MAX)
                .build();
    }

    public static Post postMock() {
        return Post
                .builder()
                .id(1L)
                .dateCreated(OffsetDateTime.MAX)
                .userId(1L)
                .post("TEST")
                .build();
    }

}
