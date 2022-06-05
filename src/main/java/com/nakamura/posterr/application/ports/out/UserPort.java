package com.nakamura.posterr.application.ports.out;

import com.nakamura.posterr.adapters.repository.entity.FollowedEntity;
import com.nakamura.posterr.adapters.repository.entity.FollowingEntity;

import java.util.List;

public interface UserPort {

    List<FollowingEntity> getFollowingUserById(Long userId);

    List<FollowedEntity> getFollowedUserById(Long userId);

}
