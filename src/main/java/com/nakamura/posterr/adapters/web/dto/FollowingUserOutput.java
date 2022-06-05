package com.nakamura.posterr.adapters.web.dto;

import com.nakamura.posterr.application.domain.FollowingUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class FollowingUserOutput {

    private Long userId;
    private Long userFollowingId;

    public static FollowingUserOutput fromDomain(FollowingUser followingUser) {
        return FollowingUserOutput
                .builder()
                .userId(followingUser.getUserId())
                .userFollowingId(followingUser.getUserFollowingId())
                .build();
    }

}
