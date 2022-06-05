package com.nakamura.posterr.adapters.repository;

import com.nakamura.posterr.adapters.repository.entity.FollowedEntity;
import com.nakamura.posterr.adapters.repository.entity.FollowingEntity;
import com.nakamura.posterr.application.ports.out.UserPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class UserDBAdapter implements UserPort {

    private final FollowingRepository followingRepository;
    private final FollowedRepository followedRepository;

    @Override
    public List<FollowingEntity> getFollowingUserById(Long userId) {
        log.info("GET - /v1/user/following - UserDBAdapter - get all followingUser from userId {}", userId);
        var followingEntities = followingRepository.following(userId);

        log.info("GET - /v1/user/following - UserDBAdapter - Sucess to return followingUser from FollowingRepository");
        return followingEntities;
    }

    @Override
    public List<FollowedEntity> getFollowedUserById(Long userId) {
        log.info("GET - /v1/user/followed - UserDBAdapter - get all followedUser from userId {}", userId);

        var followedEntities = followedRepository.followed(userId);
        log.info("GET - /v1/user/followed - UserDBAdapter - Sucess to return followingUser from FollowedRepository");

        return followedEntities;
    }

}
