package com.nakamura.posterr.adapters.web;

import com.nakamura.posterr.adapters.web.dto.FollowingUserOutput;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserController {

    private final UserHandler userHandler;

    @GetMapping(path = "/following/{userId}")
    public ResponseEntity<List<FollowingUserOutput>> getAllFollowingUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userHandler.getAllFollowingUsers(userId));
    }

}
