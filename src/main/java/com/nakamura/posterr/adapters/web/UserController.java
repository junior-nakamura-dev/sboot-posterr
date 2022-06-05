package com.nakamura.posterr.adapters.web;

import com.nakamura.posterr.adapters.web.dto.FollowedUserOutput;
import com.nakamura.posterr.adapters.web.dto.FollowingUserOutput;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserHandler userHandler;

    @GetMapping(path = "/following")
    public ResponseEntity<List<FollowingUserOutput>> getAllFollowingUser() {
        final var userId = SecurityContextMock.userId;
        log.info("GET - /v1/user/following - get all followingUser from userId {}", userId);

        var result = userHandler.getAllFollowingUsers(userId);

        log.info("GET - /v1/user/following - Sucess - 200", userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/followed")
    public ResponseEntity<List<FollowedUserOutput>> getAllFollowedUser() {
        final var userId = SecurityContextMock.userId;
        log.info("GET - /v1/user/followed - get all followedUser from userId {}", userId);

        var result = userHandler.getFollowedUserOutputs(userId);

        log.info("GET - /v1/user/followed - Sucess - 200", userId);
        return ResponseEntity.ok(result);
    }

}
