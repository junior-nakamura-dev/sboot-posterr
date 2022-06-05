package com.nakamura.posterr.application.ports.in.user;

import com.nakamura.posterr.application.domain.FollowedUser;

import java.util.List;

public interface GetAllFollowedUsersUseCase {

    List<FollowedUser> getFollowedUsers(Long userId);
}
