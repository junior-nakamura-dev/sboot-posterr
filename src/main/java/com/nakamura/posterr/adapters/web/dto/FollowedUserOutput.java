package com.nakamura.posterr.adapters.web.dto;

import com.nakamura.posterr.application.domain.FollowedUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class FollowedUserOutput {

    private Long userId;
    private Long userFollowingId;

    public static FollowedUserOutput fromDomain(FollowedUser followedUser) {
        return FollowedUserOutput
                .builder()
                .userId(followedUser.getUserId())
                .userFollowingId(followedUser.getUserFollowedId())
                .build();
    }

}
