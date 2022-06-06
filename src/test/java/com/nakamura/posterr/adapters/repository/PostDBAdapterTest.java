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

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostDBAdapterTest {

    @Mock
    private PostRepository postRepositoryMock;

    @InjectMocks
    private PostDBAdapter postDBAdapter;

    @DisplayName("GIVEN a valid CreatePostInput WHEN user wants create a post THEN create it WITH sucess")
    @Test
    void createPost() throws LimitRangePostDayException {
        final var postEntity = TestMocks.postEntityMock();
        when(postRepositoryMock.getLastPost(postEntity.getUserId())).thenReturn(Optional.of(PostEntity.builder().amountPostDay(1).build()));

        when(postRepositoryMock.addPost(any())).thenReturn(1L);

        postDBAdapter.createPost(postEntity.toDomain());
    }

    @DisplayName("GIVEN a valid CreatePostInput WHEN user try create a post but he already create the maximum post daily THEN return LIMIT_RANGE_POST_DAY_EXCEPTION")
    @Test
    void errorLimitRangeError() {
        final var postEntity = TestMocks.postEntityMock();
        when(postRepositoryMock.getLastPost(postEntity.getUserId())).thenReturn(Optional.of(PostEntity.builder().amountPostDay(5).build()));

        assertThatThrownBy(() -> postDBAdapter.createPost(postEntity.toDomain())).isInstanceOf(LimitRangePostDayException.class);
    }

    @DisplayName("GIVEN a page number and userId WHEN User request all posts wherever post them THEN retrieve a list of post WITH sucess")
    @Test
    void getAllPost() {
        final var postEntity = TestMocks.postEntityMock();
        final var offset = 5;
        final var userId = 1L;
        when(postRepositoryMock.getAllPost(offset)).thenReturn(List.of(postEntity));

        final var result = postDBAdapter.getAllPost(userId, offset);

        assertThat(result)
                .hasSize(1)
                .extracting("id", "post", "dateCreated", "userId")
                .contains(tuple(1L, "TEST", OffsetDateTime.MAX, 1L));
    }

}