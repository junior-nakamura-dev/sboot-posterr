package com.nakamura.posterr.adapters.repository.entity;

import com.nakamura.posterr.application.domain.FollowedUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowedEntity {

    private Long userId;

    private Long userFollowedId;

    public FollowedUser toDomain() {
        return FollowedUser
                .builder()
                .userId(this.userId)
                .userFollowedId(this.userFollowedId)
                .build();
    }
}
