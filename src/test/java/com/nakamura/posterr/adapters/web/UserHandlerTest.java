package com.nakamura.posterr.adapters.web;

import com.nakamura.posterr.TestMocks;
import com.nakamura.posterr.adapters.web.handler.UserHandler;
import com.nakamura.posterr.application.ports.in.user.GetAllFollowedUsersUseCase;
import com.nakamura.posterr.application.ports.in.user.GetAllFollowingUsersUseCase;
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
class UserHandlerTest {

    @Mock
    private GetAllFollowingUsersUseCase getAllFollowingUsersUseCaseMock;

    @Mock
    private GetAllFollowedUsersUseCase getAllFollowedUsersUseCaseMock;

    @InjectMocks
    private UserHandler userHandler;

    @DisplayName("Given an userId when userId has following user then return them")
    @Test
    void getAllFollowingUsersWithSucess() {
        when(getAllFollowingUsersUseCaseMock.getFollowingUsers(1L)).thenReturn(List.of(TestMocks.followingUserMock()));

        var result = userHandler.getAllFollowingUsers(1L);

        assertThat(result)
                .hasSize(1)
                .extracting("userId", "userFollowingId")
                .contains(tuple(1L, 2L));
    }

    @DisplayName("Given an userId when userId has followed user then return them")
    @Test
    void getAllFollowedUsersWithSucess() {
        when(getAllFollowedUsersUseCaseMock.getFollowedUsers(1L)).thenReturn(List.of(TestMocks.followedUserMock()));

        var result = userHandler.getFollowedUserOutputs(1L);

        assertThat(result)
                .hasSize(1)
                .extracting("userId", "userFollowedId")
                .contains(tuple(1L, 2L));
    }

}