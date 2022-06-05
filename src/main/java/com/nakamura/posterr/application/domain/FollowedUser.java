package com.nakamura.posterr.application.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class FollowedUser {

    private Long userId;
    private Long userFollowedId;

}
