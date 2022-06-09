package com.nakamura.posterr.application;

import com.nakamura.posterr.TestMocks;
import com.nakamura.posterr.application.exception.LimitRangePostDayException;
import com.nakamura.posterr.application.ports.out.PostPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostPort postPortMock;

    @InjectMocks
    private AllPostService postService;

    @DisplayName("GIVEN a valid CreatePostInput WHEN user wants create a post THEN create it WITH sucess")
    @Test
    void createPostWithSucess() throws LimitRangePostDayException {
        final var post = TestMocks.postMock();

        doNothing().when(postPortMock).createPost(post);

        postService.createPost(post);

    }

    @DisplayName("GIVEN a page number and userId WHEN User request posts from wherever user  THEN retrieve a list of post WITH sucess")
    @Test
    void getAllPostWhereverWhoPostThem() {
        final var post = TestMocks.postMock();
        final var userId = 1L;
        final var offset = 1;
        final var chunk = 5;
        final var lastPostId = 1L;

        when(postPortMock.getAllPost(userId, offset, chunk, lastPostId)).thenReturn(List.of(post));

        final var result = postService.getAllPost(userId, offset, chunk, lastPostId);

        assertThat(result)
                .hasSize(1)
                .extracting("id", "post", "dateCreated", "userId", "postOriginalId")
                .contains(tuple(1L, "TEST", OffsetDateTime.MAX, 1L, null));
    }

    @DisplayName("GIVEN a page number and userId WHEN User request posts from user profile  THEN retrieve a list of post WITH sucess")
    @Test
    void getAllPostFromUserProfile() {
        final var post = TestMocks.postMock();
        final var userId = 1L;
        final var offset = 1;
        final var chunk = 5;
        final var lastPostId = 1L;
        final var userProfileId = 2L;

        when(postPortMock.getAllPostFromUserProfile(userId, offset, chunk, lastPostId, userProfileId)).thenReturn(List.of(post));

        final var result = postService.getAllPostFromUserProfile(userId, offset, chunk, lastPostId, userProfileId);

        assertThat(result)
                .hasSize(1)
                .extracting("id", "post", "dateCreated", "userId", "postOriginalId")
                .contains(tuple(1L, "TEST", OffsetDateTime.MAX, 1L, null));
    }

    @DisplayName("GIVEN a page number and userId WHEN User request posts from followed user  THEN retrieve a list of post WITH sucess")
    @Test
    void getAllPostFromFollowedUser() {
        final var post = TestMocks.postMock();
        final var userId = 1L;
        final var offset = 1;
        final var chunk = 5;
        final var lastPostId = 1L;

        when(postPortMock.getAllPostFromUserFollowed(userId, offset, chunk, lastPostId)).thenReturn(List.of(post));

        final var result = postService.getAllPostFromUserFollowed(userId, offset, chunk, lastPostId);

        assertThat(result)
                .hasSize(1)
                .extracting("id", "post", "dateCreated", "userId", "postOriginalId")
                .contains(tuple(1L, "TEST", OffsetDateTime.MAX, 1L, null));
    }

}