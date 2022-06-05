package com.nakamura.posterr.application.ports.in;

import com.nakamura.posterr.application.domain.FollowingUser;

public interface FollowUserUseCase {

    void followUser(FollowingUser followingUser);

}
