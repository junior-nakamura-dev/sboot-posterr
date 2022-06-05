package com.nakamura.posterr.application.ports.in;

import com.nakamura.posterr.application.domain.FollowingUser;
import com.nakamura.posterr.application.exception.AlreadyUnfollowThisUserException;

public interface UnfollowUserUseCase {

    void unfollowUser(FollowingUser followingUser) throws AlreadyUnfollowThisUserException;

}
