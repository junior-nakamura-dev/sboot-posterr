package com.nakamura.posterr.adapters.repository;

import com.nakamura.posterr.TestMocks;
import com.nakamura.posterr.adapters.repository.entity.PostEntity;
import com.nakamura.posterr.application.exception.LimitRangePostDayException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostDBAdapterTest {

    @Mock
    private PostRepository postRepositoryMock;

    @InjectMocks
    private PostDBAdapter postDBAdapter;

    @DisplayName("Create an user post with success")
    @Test
    void createPost() throws LimitRangePostDayException {
        final var postEntity = TestMocks.postEntityMock();
        when(postRepositoryMock.getLastPost(postEntity.getUserId())).thenReturn(Optional.of(PostEntity.builder().amountPostDay(1).build()));

        when(postRepositoryMock.addPost(any())).thenReturn(1L);

        postDBAdapter.createPost(postEntity.toDomain());
    }

    @DisplayName("Try create an user post but got an error because range limit")
    @Test
    void errorLimitRangeError() throws LimitRangePostDayException {
        final var postEntity = TestMocks.postEntityMock();
        when(postRepositoryMock.getLastPost(postEntity.getUserId())).thenReturn(Optional.of(PostEntity.builder().amountPostDay(5).build()));

        assertThatThrownBy(() -> postDBAdapter.createPost(postEntity.toDomain())).isInstanceOf(LimitRangePostDayException.class);
    }

}