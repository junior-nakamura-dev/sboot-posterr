package com.nakamura.posterr;

import com.nakamura.posterr.adapters.repository.entity.FollowingEntity;
import com.nakamura.posterr.application.domain.FollowingUser;

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

}
