package com.nakamura.posterr.adapters.repository;

import com.nakamura.posterr.TestMocks;
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
class UserDBAdapterTest {

    @Mock
    private FollowingRepository followingRepositoryMock;

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

}