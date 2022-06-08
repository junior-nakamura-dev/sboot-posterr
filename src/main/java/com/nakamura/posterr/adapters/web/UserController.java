package com.nakamura.posterr.adapters.web;

import com.nakamura.posterr.adapters.web.dto.FollowUserInput;
import com.nakamura.posterr.adapters.web.dto.FollowedUsersOutput;
import com.nakamura.posterr.adapters.web.dto.FollowingUsersOutput;
import com.nakamura.posterr.adapters.web.dto.UserOutput;
import com.nakamura.posterr.adapters.web.handler.UserHandler;
import com.nakamura.posterr.application.exception.AlreadyFollowThisUserException;
import com.nakamura.posterr.application.exception.AlreadyUnfollowThisUserException;
import com.nakamura.posterr.application.exception.CantFollowYourselfException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
@Slf4j
@Validated
public class UserController {

    private final UserHandler userHandler;

    @GetMapping("/{userProfileId}")
    public ResponseEntity<UserOutput> getUserProfile(@PathVariable Long userProfileId) {
        final var userId = SecurityContextMock.userId;
        log.info("GET - /v1/user - user {} seeing user profile {}", userId, userProfileId);

        var result = userHandler.getUserProfile(userId, userProfileId);

        log.info("GET - /v1/user - Sucess - 200");
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/{userId}/following")
    public ResponseEntity<FollowingUsersOutput> getAllFollowingUser(@PathVariable Long userId) {
        log.info("GET - /v1/user/following - get all followingUser from userId {}", userId);

        var result = userHandler.getAllFollowingUsers(userId);

        log.info("GET - /v1/user/following - Sucess - 200", userId);
        return ResponseEntity.ok(new FollowingUsersOutput(result));
    }

    @GetMapping(path = "/{userId}/followed")
    public ResponseEntity<FollowedUsersOutput> getAllFollowedUser(@PathVariable Long userId) {
        log.info("GET - /v1/user/followed - get all followedUser from userId {}", userId);

        var result = userHandler.getFollowedUserOutputs(userId);

        log.info("GET - /v1/user/followed - Sucess - 200", userId);
        return ResponseEntity.ok(new FollowedUsersOutput(result));
    }

    @PostMapping(path = "/follow")
    public ResponseEntity followUser(@RequestBody @Valid FollowUserInput followUserInput) {
        try {
            final var userId = SecurityContextMock.userId;
            log.info("POST - /v1/user/follow - Start process to follow user");

            userHandler.followUser(userId, followUserInput);

            log.info("POST - /v1/user/follow - Sucess - 204", userId);
            return ResponseEntity.noContent().build();
        } catch (CantFollowYourselfException | AlreadyFollowThisUserException businessException) {
            throw new ResponseStatusException(businessException.getExceptionStatusCode(), businessException.getExceptionDescription());
        }
    }

    @DeleteMapping(path = "/unfollow/{userFollowedId}")
    public ResponseEntity unfollowUser(@PathVariable @NotNull Long userFollowedId) {
        try {
            final var userId = SecurityContextMock.userId;
            log.info("DELETE - /v1/user/unfollow - Start process to follow user");

            userHandler.unfollowUser(userId, userFollowedId);

            log.info("POST - /v1/user/follow - Sucess - 204", userId);
            return ResponseEntity.noContent().build();
        } catch (AlreadyUnfollowThisUserException businessException) {
            throw new ResponseStatusException(businessException.getExceptionStatusCode(), businessException.getExceptionDescription());
        }
    }

}
