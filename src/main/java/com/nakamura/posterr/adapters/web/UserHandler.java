package com.nakamura.posterr.adapters.web;

import com.nakamura.posterr.adapters.web.dto.FollowUserInput;
import com.nakamura.posterr.adapters.web.dto.FollowedUserOutput;
import com.nakamura.posterr.adapters.web.dto.FollowingUserOutput;
import com.nakamura.posterr.application.ports.in.FollowUserUseCase;
import com.nakamura.posterr.application.ports.in.GetAllFollowedUsersUseCase;
import com.nakamura.posterr.application.ports.in.GetAllFollowingUsersUseCase;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class UserHandler {
    private final GetAllFollowingUsersUseCase getAllFollowingUsersUseCase;
    private final GetAllFollowedUsersUseCase getAllFollowedUsersUseCase;
    private final FollowUserUseCase followUserUseCase;

    public List<FollowingUserOutput> getAllFollowingUsers(Long userId) {
        log.info("GET - /v1/user/following - UserHandler - get all followingUser from userId {}", userId);

        var followingUsers= getAllFollowingUsersUseCase.getFollowingUsers(userId);

        log.info("GET - /v1/user/following - UserHandler - Sucess to return from GetAllFollowingUsersUseCase");

        var followingUsersOutput = followingUsers
                .stream()
                .map(FollowingUserOutput::fromDomain)
                .collect(Collectors.toList());

        log.info("GET - /v1/user/following - UserHandler - Sucess to mapping to FollowingUserOutput");

        return followingUsersOutput;
    }

    public List<FollowedUserOutput> getFollowedUserOutputs(Long userId) {
        log.info("GET - /v1/user/followed - get all followingUser from userId {}", userId);

        var followedUsers= getAllFollowedUsersUseCase.getFollowedUsers(userId);

        log.info("GET - /v1/user/followed - UserHandler - Sucess to return from GetAllFollowedUsersUseCase");

        var followedUserOutputs= followedUsers
                .stream()
                .map(FollowedUserOutput::fromDomain)
                .collect(Collectors.toList());

        log.info("GET - /v1/user/followed - UserHandler - Sucess to mapping to FollowedUserOutput");

        return followedUserOutputs;
    }
    public void followUser(Long userId, FollowUserInput followUserInput) {
        log.info("POST - /v1/user/follow - User {} wants to follow the user {}", userId, followUserInput.getUserFollowingId());

        var followingUser = followUserInput.toDomain(userId);
        log.info("POST - /v1/user/follow - UserHandler - Sucess to mapping to FollowingUser");

        followUserUseCase.followUser(followingUser);
        log.info("POST - /v1/user/follow - UserHandler - Sucess to follow an user in FollowUserUseCase");
    }

}
