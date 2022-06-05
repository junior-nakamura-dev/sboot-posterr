package com.nakamura.posterr.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class FollowingUser {

    @EqualsAndHashCode.Include
    private Long userId;

    @EqualsAndHashCode.Include
    private Long userFollowingId;

    public FollowedUser toFollowedUser() {
        return FollowedUser
                .builder()
                .userFollowedId(userId)
                .userId(userFollowingId)
                .build();
    }

}
