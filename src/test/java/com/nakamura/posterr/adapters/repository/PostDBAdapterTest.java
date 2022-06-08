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
        when(postRepositoryMock.getLastPostToday(postEntity.getUserId())).thenReturn(Optional.of(PostEntity.builder().amountPostDay(1).build()));

        when(postRepositoryMock.addPost(any())).thenReturn(1L);

        postDBAdapter.createPost(postEntity.toDomain());
    }

    @DisplayName("GIVEN a valid CreatePostInput WHEN user try create a post but he already create the maximum post daily THEN return LIMIT_RANGE_POST_DAY_EXCEPTION")
    @Test
    void errorLimitRangeError() {
        final var postEntity = TestMocks.postEntityMock();
        when(postRepositoryMock.getLastPostToday(postEntity.getUserId())).thenReturn(Optional.of(PostEntity.builder().amountPostDay(5).build()));

        assertThatThrownBy(() -> postDBAdapter.createPost(postEntity.toDomain())).isInstanceOf(LimitRangePostDayException.class);
    }

    @DisplayName("GIVEN a page number and userId WHEN User request posts wherever post them THEN retrieve a list of post WITH sucess")
    @Test
    void getAllPost() {
        final var postEntity = TestMocks.postEntityMock();
        final var offset = 5;
        final var userId = 1L;
        final var size = 5;
        final Long lastPostId = null;

        when(postRepositoryMock.getAllPost(offset, size, lastPostId)).thenReturn(List.of(postEntity));

        final var result = postDBAdapter.getAllPost(userId, offset, size, lastPostId);

        assertThat(result)
                .hasSize(1)
                .extracting("id", "post", "dateCreated", "userId")
                .contains(tuple(1L, "TEST", OffsetDateTime.MAX, 1L));
    }

    @DisplayName("GIVEN a page number and userId WHEN User request posts from user followed THEN retrieve a list of post WITH sucess")
    @Test
    void getAllPostFromUserFollowed() {
        final var postEntity = TestMocks.postEntityMock();
        final var offset = 5;
        final var userId = 1L;
        final var chunk = 5;
        final Long lastPostId = null;
        when(postRepositoryMock.getAllPostFromUserFollowed(userId, offset, chunk, lastPostId)).thenReturn(List.of(postEntity));

        final var result = postDBAdapter.getAllPostFromUserFollowed(userId, offset, chunk, lastPostId);

        assertThat(result)
                .hasSize(1)
                .extracting("id", "post", "dateCreated", "userId", "postOriginalId")
                .contains(tuple(1L, "TEST", OffsetDateTime.MAX, 1L, null));
    }

    @DisplayName("GIVEN a userId return amount posts from this user")
    @Test
    void getAmountPostsFromUser() {
        final var userId = 1L;
        when(postRepositoryMock.countUserPosts(userId)).thenReturn(5L);

        final var result = postDBAdapter.countPosts(userId);

        assertThat(result).isEqualTo(5L);
    }

}