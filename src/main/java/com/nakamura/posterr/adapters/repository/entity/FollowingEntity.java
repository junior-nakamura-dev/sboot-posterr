package com.nakamura.posterr.adapters.repository.entity;

import com.nakamura.posterr.application.domain.FollowingUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowingEntity {

    private Long userId;

    private Long userFollowingId;

    public FollowingUser toDomain() {
        return FollowingUser
                .builder()
                .userId(this.userId)
                .userFollowingId(this.userFollowingId)
                .build();
    }

    public static FollowingEntity fromDomain(FollowingUser followingUser) {
        return FollowingEntity
                .builder()
                .userId(followingUser.getUserId())
                .userFollowingId(followingUser.getUserFollowingId())
                .build();
    }

}
