package com.nakamura.posterr.application;

import com.nakamura.posterr.TestMocks;
import com.nakamura.posterr.adapters.repository.entity.FollowingEntity;
import com.nakamura.posterr.application.ports.out.PostPort;
import com.nakamura.posterr.application.ports.out.UserPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserPort userPortMock;

    @Mock
    private PostPort postPortMock;

    @InjectMocks
    private UserService userService;

    @DisplayName("Given an userId when userId has following user then return them")
    @Test
    void getFollowingUsersWithSucess() {

        when(userPortMock.getFollowingUserById(1L)).thenReturn(List.of(TestMocks.followingEntityMock()));

        var result = userService.getFollowingUsers(1L);

        assertThat(result)
                .hasSize(1)
                .extracting("userId", "userFollowingId")
                .contains(tuple(1L, 2L));

    }

    @DisplayName("Given an userId when userId has followed user then return them")
    @Test
    void getFollowedUsersWithSucess() {

        when(userPortMock.getFollowedUserById(1L)).thenReturn(List.of(TestMocks.followedEntityMock()));

        var result = userService.getFollowedUsers(1L);

        assertThat(result)
                .hasSize(1)
                .extracting("userId", "userFollowedId")
                .contains(tuple(1L, 2L));

    }

    @DisplayName("When the user request to see an user profile and the user follow him")
    @Test
    void getUserProfileWhenFollowingUser() {
        final var userEntity = TestMocks.userEntityMock(2L);
        final var userId = 1L;
        final var userProfileId = 2L;

        when(userPortMock.getUserProfile(userProfileId)).thenReturn(userEntity);
        when(userPortMock.isFollow(userId, userProfileId)).thenReturn(Optional.of(FollowingEntity.builder().build()));
        when(postPortMock.countPosts(userProfileId)).thenReturn(5L);

        var result = userService.getUserProfile(userId, userProfileId);

        assertThat(List.of(result))
                .extracting("id", "username", "dateJoined", "isFollowing", "amountPosts")
                .contains(tuple(2L, "TEST", OffsetDateTime.MAX, true, 5L));

    }

    @DisplayName("When the user request to see an user profile and the user not follow him")
    @Test
    void getUserProfileWhenNotFollowingUser() {
        final var userEntity = TestMocks.userEntityMock(2L);
        final var userId = 1L;
        final var userProfileId = 2L;

        when(userPortMock.getUserProfile(userProfileId)).thenReturn(userEntity);
        when(userPortMock.isFollow(userId, userProfileId)).thenReturn(Optional.empty());
        when(postPortMock.countPosts(userProfileId)).thenReturn(5L);

        var result = userService.getUserProfile(userId, userProfileId);

        assertThat(List.of(result))
                .extracting("id", "username", "dateJoined", "isFollowing", "amountPosts")
                .contains(tuple(2L, "TEST", OffsetDateTime.MAX, false, 5L));

    }

}