package com.nakamura.posterr.adapters.web.handler;

import com.nakamura.posterr.adapters.web.dto.FollowUserInput;
import com.nakamura.posterr.adapters.web.dto.FollowedUserOutput;
import com.nakamura.posterr.adapters.web.dto.FollowingUserOutput;
import com.nakamura.posterr.application.domain.FollowingUser;
import com.nakamura.posterr.application.exception.AlreadyFollowThisUserException;
import com.nakamura.posterr.application.exception.AlreadyUnfollowThisUserException;
import com.nakamura.posterr.application.exception.CantFollowYourselfException;
import com.nakamura.posterr.application.ports.in.user.FollowUserUseCase;
import com.nakamura.posterr.application.ports.in.user.GetAllFollowedUsersUseCase;
import com.nakamura.posterr.application.ports.in.user.GetAllFollowingUsersUseCase;
import com.nakamura.posterr.application.ports.in.user.UnfollowUserUseCase;
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
    private final UnfollowUserUseCase unfollowUserUseCase;

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

    public void followUser(Long userId, FollowUserInput followUserInput) throws CantFollowYourselfException, AlreadyFollowThisUserException {
        log.info("POST - /v1/user/follow - User {} wants to follow the user {}", userId, followUserInput.getUserFollowingId());

        if (userId.equals(followUserInput.getUserFollowingId())) {
            log.info("POST - /v1/user/follow - You cant follow yourself", userId, followUserInput.getUserFollowingId());
            throw new CantFollowYourselfException();
        }

        var followingUser = followUserInput.toDomain(userId);
        log.info("POST - /v1/user/follow - UserHandler - Sucess to mapping to FollowingUser");

        followUserUseCase.followUser(followingUser);
        log.info("POST - /v1/user/follow - UserHandler - Sucess to follow an user in FollowUserUseCase");
    }

    public void unfollowUser(Long userId, Long userFollowedId) throws AlreadyUnfollowThisUserException {
        log.info("DELETE - /v1/user/unfollow - User {} wants to unfollow the user {}", userId, userFollowedId);

        var followingUser = new FollowingUser(userId, userFollowedId);
        log.info("DELETE - /v1/user/unfollow - UserHandler - Sucess to mapping to FollowingUser");

        unfollowUserUseCase.unfollowUser(followingUser);
        log.info("DELETE - /v1/user/unfollow - UserHandler - Sucess to follow an user in FollowUserUseCase");
    }

}
