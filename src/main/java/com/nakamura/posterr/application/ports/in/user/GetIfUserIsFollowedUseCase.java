package com.nakamura.posterr.application.ports.in.user;

public interface GetIfUserIsFollowedUseCase {

    boolean isFollow(Long userId, Long userFollowingId);

}
