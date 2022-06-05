package com.nakamura.posterr.application.ports.in.user;

import com.nakamura.posterr.application.domain.FollowingUser;
import com.nakamura.posterr.application.exception.AlreadyFollowThisUserException;

public interface FollowUserUseCase {

    void followUser(FollowingUser followingUser) throws AlreadyFollowThisUserException;

}
