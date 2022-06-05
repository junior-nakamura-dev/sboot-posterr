package com.nakamura.posterr.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class FollowingUser {

    private Long userId;
    private Long userFollowingId;

    public FollowedUser toFollowedUser() {
        return FollowedUser
                .builder()
                .userFollowedId(userId)
                .userId(userFollowingId)
                .build();
    }

}
