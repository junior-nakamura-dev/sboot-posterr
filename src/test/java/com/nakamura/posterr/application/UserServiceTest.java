package com.nakamura.posterr.application;

import com.nakamura.posterr.TestMocks;
import com.nakamura.posterr.application.ports.out.UserPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserPort userPortMock;

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


}