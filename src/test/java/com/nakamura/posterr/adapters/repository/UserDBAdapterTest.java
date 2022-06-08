package com.nakamura.posterr.adapters.repository;

import com.nakamura.posterr.TestMocks;
import com.nakamura.posterr.adapters.repository.entity.FollowedEntity;
import com.nakamura.posterr.adapters.repository.entity.FollowingEntity;
import com.nakamura.posterr.application.exception.AlreadyFollowThisUserException;
import com.nakamura.posterr.application.exception.AlreadyUnfollowThisUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDBAdapterTest {

    @Mock
    private FollowingRepository followingRepositoryMock;

    @Mock
    private FollowedRepository followedRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserDBAdapter userDBAdapter;

    @DisplayName("Given an userId when userId has following users then return them")
    @Test
    void getFollowingUserById200() {
        when(followingRepositoryMock.following(1L)).thenReturn(List.of(TestMocks.followingEntityMock()));

        var result = userDBAdapter.getFollowingUserById(1L);

        assertThat(result)
                .hasSize(1)
                .extracting("userId", "userFollowingId")
                .contains(tuple(1L, 2L));

    }

    @DisplayName("Given an userId when userId has followed users then return them")
    @Test
    void getFollowedUserById200() {
        when(followedRepositoryMock.followed(1L)).thenReturn(List.of(TestMocks.followedEntityMock()));

        var result = userDBAdapter.getFollowedUserById(1L);

        assertThat(result)
                .hasSize(1)
                .extracting("userId", "userFollowedId")
                .contains(tuple(1L, 2L));

    }

    @DisplayName("Given an userId when userId has followed users then return them")
    @Test
    void addFollowed() throws AlreadyFollowThisUserException {
        final var followingUser = TestMocks.followingUserMock();
        final var userId = followingUser.getUserId();
        final var userFollowingId = followingUser.getUserFollowingId();
        final var followingEntity = FollowingEntity.fromDomain(followingUser);
        final var followedEntity = FollowedEntity.fromDomain(followingUser.toFollowedUser());

        when(followingRepositoryMock.isFollowing(userId, userFollowingId)).thenReturn(Optional.empty());
        doNothing().when(followedRepositoryMock).addFollowed(followedEntity);
        doNothing().when(followingRepositoryMock).addFollowing(followingEntity);

        userDBAdapter.followUser(followingUser);
    }

    @DisplayName("Given an userId when userId has followed users then return them")
    @Test
    void alreadyFollowUserError() throws AlreadyFollowThisUserException {
        final var followingUser = TestMocks.followingUserMock();
        final var userId = followingUser.getUserId();
        final var userFollowingId = followingUser.getUserFollowingId();
        final var followingEntity = FollowingEntity.fromDomain(followingUser);

        when(followingRepositoryMock.isFollowing(userId, userFollowingId)).thenReturn(Optional.of(followingEntity));
        assertThatThrownBy(() -> userDBAdapter.followUser(followingUser)).isInstanceOf(AlreadyFollowThisUserException.class);
    }

    @DisplayName("Given an userId when userId wants unfollow an user then unfollow him")
    @Test
    void unfollow() throws AlreadyUnfollowThisUserException {
        final var followingUser = TestMocks.followingUserMock();
        final var userId = followingUser.getUserId();
        final var userFollowingId = followingUser.getUserFollowingId();
        final var followingEntity = FollowingEntity.fromDomain(followingUser);
        final var followedEntity = FollowedEntity.fromDomain(followingUser.toFollowedUser());

        when(followingRepositoryMock.isFollowing(userId, userFollowingId)).thenReturn(Optional.of(followingEntity));
        doNothing().when(followedRepositoryMock).removeFollowed(followedEntity);
        doNothing().when(followingRepositoryMock).removeFollowing(followingEntity);

        userDBAdapter.unfollowUser(followingUser);
    }

    @DisplayName("Given an userId when userId want unfollow an user but already unfollowed him then return error")
    @Test
    void alreadyUnfollowUserError() throws AlreadyUnfollowThisUserException {
        final var followingUser = TestMocks.followingUserMock();
        final var userId = followingUser.getUserId();
        final var userFollowingId = followingUser.getUserFollowingId();

        when(followingRepositoryMock.isFollowing(userId, userFollowingId)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> userDBAdapter.unfollowUser(followingUser)).isInstanceOf(AlreadyUnfollowThisUserException.class);
    }

    @DisplayName("Given an userId return the user profile")
    @Test
    void getUserProfile() {
        final var userId = 1L;
        final var userEntity = TestMocks.userEntityMock(userId);

        when(userRepositoryMock.getUser(userId)).thenReturn(userEntity);

        userDBAdapter.getUserProfile(userId);
    }

    @DisplayName("Given an userId and userFollowingId return FollowingEntity with userId follow userFollowingId ")
    @Test
    void getFollowingUser() {
        final var userId = 1L;
        final var userFollowingId = 2L;

        when(followingRepositoryMock.isFollowing(userId, userFollowingId)).thenReturn(Optional.of(FollowingEntity.builder().build()));

        userDBAdapter.isFollow(userId, userFollowingId);
    }


}