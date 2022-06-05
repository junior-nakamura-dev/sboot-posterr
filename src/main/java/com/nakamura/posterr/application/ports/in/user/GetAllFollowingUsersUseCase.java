package com.nakamura.posterr.application.ports.in.user;

import com.nakamura.posterr.application.domain.FollowingUser;

import java.util.List;

public interface GetAllFollowingUsersUseCase {

    List<FollowingUser> getFollowingUsers(Long userId);
}
