package com.nakamura.posterr.adapters.web.dto;

import com.nakamura.posterr.application.domain.FollowingUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class FollowingUserOutput {

    @NotNull
    private Long userId;

    @NotNull
    private Long userFollowingId;

    public static FollowingUserOutput fromDomain(FollowingUser followingUser) {
        return FollowingUserOutput
                .builder()
                .userId(followingUser.getUserId())
                .userFollowingId(followingUser.getUserFollowingId())
                .build();
    }

}
