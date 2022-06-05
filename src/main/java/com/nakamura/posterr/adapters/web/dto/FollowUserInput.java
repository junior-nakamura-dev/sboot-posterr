package com.nakamura.posterr.adapters.web.dto;

import com.nakamura.posterr.application.domain.FollowingUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FollowUserInput {

    @NotNull(message = "userFollowingId cannot be null")
    private Long userFollowingId;

    public FollowingUser toDomain(Long userId) {
        return FollowingUser
                .builder()
                .userId(userId)
                .userFollowingId(this.userFollowingId)
                .build();
    }

}
