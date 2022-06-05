package com.nakamura.posterr.adapters.repository;

import com.nakamura.posterr.adapters.repository.entity.FollowedEntity;
import com.nakamura.posterr.adapters.repository.entity.FollowingEntity;
import com.nakamura.posterr.application.domain.FollowingUser;
import com.nakamura.posterr.application.ports.out.UserPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.sqlobject.transaction.Transaction;
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

    @Transaction
    @Override
    public void followUser(FollowingUser followingUser) {
        log.info("GET - /v1/user/follow - UserDBAdapter - userId {} follow the user {}", followingUser.getUserId(), followingUser.getUserFollowingId());

        followingRepository.addFollowing(FollowingEntity.fromDomain(followingUser));
        log.info("GET - /v1/user/follow - UserDBAdapter - Sucess followingRepository");

        var followedUser = followingUser.toFollowedUser();
        log.info("GET - /v1/user/follow - UserDBAdapter - Sucess mapping to FollowedUser");

        followedRepository.addFollowed(FollowedEntity.fromDomain(followedUser));
        log.info("GET - /v1/user/follow - UserDBAdapter - Sucess followedRepository");
        log.info("GET - /v1/user/follow - UserDBAdapter - Sucess to follow an user");
    }

}
