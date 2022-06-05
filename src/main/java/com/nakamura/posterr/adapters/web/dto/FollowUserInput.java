package com.nakamura.posterr.adapters.web.dto;

import com.nakamura.posterr.application.domain.FollowingUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FollowUserInput {

    private Long userFollowingId;

    public FollowingUser toDomain(Long userId) {
        return FollowingUser
                .builder()
                .userId(userId)
                .userFollowingId(this.userFollowingId)
                .build();
    }

}
