package com.nakamura.posterr.adapters.web;

import com.nakamura.posterr.adapters.web.dto.FollowingUserOutput;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserHandler userHandler;

    @GetMapping(path = "/following")
    public ResponseEntity<List<FollowingUserOutput>> getAllFollowingUser() {
        final var userId = SecurityContextMock.userId;
        return ResponseEntity.ok(userHandler.getAllFollowingUsers(userId));
    }

}
