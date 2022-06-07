package com.nakamura.posterr.adapters.repository;

import com.nakamura.posterr.adapters.repository.entity.FollowedEntity;
import com.nakamura.posterr.adapters.repository.entity.FollowingEntity;
import com.nakamura.posterr.adapters.repository.entity.UserEntity;
import com.nakamura.posterr.application.domain.FollowingUser;
import com.nakamura.posterr.application.exception.AlreadyFollowThisUserException;
import com.nakamura.posterr.application.exception.AlreadyUnfollowThisUserException;
import com.nakamura.posterr.application.ports.out.UserPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class UserDBAdapter implements UserPort {

    private final FollowingRepository followingRepository;
    private final FollowedRepository followedRepository;
    private final UserRepository userRepository;

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

    @Transactional
    @Override
    public void followUser(FollowingUser followingUser) throws AlreadyFollowThisUserException {
        log.info("POST - /v1/user/follow - UserDBAdapter - userId {} follow the user {}", followingUser.getUserId(), followingUser.getUserFollowingId());

        final var followingEntity= followingRepository.isFollowing(followingUser.getUserId(), followingUser.getUserFollowingId());

        if (followingEntity.isPresent()) {
            log.info("POST - /v1/user/follow - UserDBAdapter - userId {} already follow the user {}", followingUser.getUserId(), followingUser.getUserFollowingId());
            throw new AlreadyFollowThisUserException();
        }

        followingRepository.addFollowing(FollowingEntity.fromDomain(followingUser));
        log.info("POST - /v1/user/follow - UserDBAdapter - Sucess followingRepository");

        var followedUser = followingUser.toFollowedUser();
        log.info("POST - /v1/user/follow - UserDBAdapter - Sucess mapping to FollowedUser");

        followedRepository.addFollowed(FollowedEntity.fromDomain(followedUser));
        log.info("POST - /v1/user/follow - UserDBAdapter - Sucess followedRepository");
        log.info("POST - /v1/user/follow - UserDBAdapter - Sucess to follow an user");
    }

    @Transactional
    @Override
    public void unfollowUser(FollowingUser followingUser) throws AlreadyUnfollowThisUserException {
        log.info("DELETE - /v1/user/unfollow - UserDBAdapter - userId {} unfollow the user {}", followingUser.getUserId(), followingUser.getUserFollowingId());

        final var followingEntity= followingRepository.isFollowing(followingUser.getUserId(), followingUser.getUserFollowingId());

        if (followingEntity.isEmpty()) {
            log.info("DELETE - /v1/user/unfollow - UserDBAdapter - userId {} already unfollow the user {}", followingUser.getUserId(), followingUser.getUserFollowingId());
            throw new AlreadyUnfollowThisUserException();
        }

        followingRepository.removeFollowing(followingEntity.get());
        log.info("DELETE - /v1/user/unfollow - UserDBAdapter - Sucess followingRepository");

        var followedUser = followingUser.toFollowedUser();
        log.info("DELETE - /v1/user/unfollow - UserDBAdapter - Sucess mapping to FollowedUser");

        followedRepository.removeFollowed(FollowedEntity.fromDomain(followedUser));
        log.info("DELETE - /v1/user/unfollow - UserDBAdapter - Sucess followedRepository");
        log.info("DELETE - /v1/user/unfollow - UserDBAdapter - Sucess to unfollow an user");
    }

    @Override
    public UserEntity getUserProfile(Long userId) {
        log.info("GET - /v1/user - UserDBAdapter - get user profile from userId {}", userId);

        var userEntity = userRepository.getUser(userId);
        log.info("GET - /v1/user - UserDBAdapter - Sucess to return User from UserRepository");

        return userEntity;
    }

}
