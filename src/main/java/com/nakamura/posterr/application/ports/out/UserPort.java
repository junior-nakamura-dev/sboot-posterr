package com.nakamura.posterr.application.ports.out;

import com.nakamura.posterr.adapters.repository.entity.FollowedEntity;
import com.nakamura.posterr.adapters.repository.entity.FollowingEntity;
import com.nakamura.posterr.application.domain.FollowingUser;
import com.nakamura.posterr.application.exception.AlreadyFollowThisUserException;
import com.nakamura.posterr.application.exception.AlreadyUnfollowThisUserException;

import java.util.List;

public interface UserPort {

    List<FollowingEntity> getFollowingUserById(Long userId);

    List<FollowedEntity> getFollowedUserById(Long userId);

    void followUser(FollowingUser followingUser) throws AlreadyFollowThisUserException;

    void unfollowUser(FollowingUser followingUser) throws AlreadyUnfollowThisUserException;

}
