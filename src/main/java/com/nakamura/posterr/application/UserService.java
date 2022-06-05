package com.nakamura.posterr.application;

import com.nakamura.posterr.adapters.repository.entity.FollowedEntity;
import com.nakamura.posterr.adapters.repository.entity.FollowingEntity;
import com.nakamura.posterr.application.domain.FollowedUser;
import com.nakamura.posterr.application.domain.FollowingUser;
import com.nakamura.posterr.application.ports.in.FollowUserUseCase;
import com.nakamura.posterr.application.ports.in.GetAllFollowedUsersUseCase;
import com.nakamura.posterr.application.ports.in.GetAllFollowingUsersUseCase;
import com.nakamura.posterr.application.ports.out.UserPort;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class UserService implements GetAllFollowingUsersUseCase, GetAllFollowedUsersUseCase, FollowUserUseCase {

    private final UserPort userPort;

    @Override
    public List<FollowingUser> getFollowingUsers(Long userId) {
        log.info("GET - /v1/user/following - UserService - get all followingUser from userId {}", userId);
        var followingEntities = userPort.getFollowingUserById(userId);

        log.info("GET - /v1/user/following - UserService - Sucess to return following users from UserPort ");
        var followingUsers = followingEntities
                .stream()
                .map(FollowingEntity::toDomain)
                .collect(Collectors.toList());

        log.info("GET - /v1/user/following - UserService - Sucess to mapping to FollowingUser");
        return followingUsers;
    }

    @Override
    public List<FollowedUser> getFollowedUsers(Long userId) {
        log.info("GET - /v1/user/followed - UserService - get all followed user from userId {}", userId);
        var followedEntities = userPort.getFollowedUserById(userId);

        log.info("GET - /v1/user/followed - UserService - Sucess to return followed users from UserPort ");
        var followedUsers= followedEntities
                .stream()
                .map(FollowedEntity::toDomain)
                .collect(Collectors.toList());

        log.info("GET - /v1/user/followed - UserService - Sucess to mapping to FollowedUser");
        return followedUsers;
    }

    @Override
    public void followUser(FollowingUser followingUser) {
        log.info("GET - /v1/user/follow - UserService - userId {} follow the user {}", followingUser.getUserId(), followingUser.getUserFollowingId());
        userPort.followUser(followingUser);

        log.info("GET - /v1/user/follow - UserService - Sucess to follow an user");
    }

}
