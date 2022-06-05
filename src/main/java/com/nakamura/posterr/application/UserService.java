package com.nakamura.posterr.application;

import com.nakamura.posterr.adapters.repository.entity.FollowingEntity;
import com.nakamura.posterr.application.domain.FollowingUser;
import com.nakamura.posterr.application.ports.in.GetAllFollowingUsersUseCase;
import com.nakamura.posterr.application.ports.out.UserPort;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService implements GetAllFollowingUsersUseCase {

    private final UserPort userPort;

    @Override
    public List<FollowingUser> getFollowingUsers(Long userId) {
        var followingEntities = userPort.getFollowingUserById(userId);
        return followingEntities
                .stream()
                .map(FollowingEntity::toDomain)
                .collect(Collectors.toList());
    }
}
