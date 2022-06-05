package com.nakamura.posterr.adapters.web;

import com.nakamura.posterr.adapters.web.dto.FollowingUserOutput;
import com.nakamura.posterr.application.ports.in.GetAllFollowingUsersUseCase;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserHandler {
    private final GetAllFollowingUsersUseCase getAllFollowingUsersUseCase;

    public List<FollowingUserOutput> getAllFollowingUsers(Long userId) {
        var followingUsers= getAllFollowingUsersUseCase.getFollowingUsers(userId);
        return followingUsers
                .stream()
                .map(FollowingUserOutput::fromDomain)
                .collect(Collectors.toList());
    }

}
