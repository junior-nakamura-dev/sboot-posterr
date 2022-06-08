package com.nakamura.posterr.application.domain;

import lombok.*;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class User {
    @EqualsAndHashCode.Include
    private Long id;
    private String username;
    private OffsetDateTime dateJoined;
    @Setter
    private boolean isFollowing;
    @Setter
    private Long amountPosts;
}
