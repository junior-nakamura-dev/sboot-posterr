package com.nakamura.posterr.adapters.web;

import com.nakamura.posterr.TestMocks;
import com.nakamura.posterr.adapters.web.dto.CreatePostInput;
import com.nakamura.posterr.adapters.web.handler.PostHandler;
import com.nakamura.posterr.application.domain.Post;
import com.nakamura.posterr.application.exception.LimitRangePostDayException;
import com.nakamura.posterr.application.ports.in.post.CreatePostUseCase;
import com.nakamura.posterr.application.ports.in.post.GetAllPostUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostHandlerTest {

    @Mock
    private CreatePostUseCase createPostUseCase;

    @Mock
    private GetAllPostUseCase getAllPostUseCase;

    private int OFF_SET_RANGE = 5;

    private PostHandler postHandler;

    @BeforeEach
    void setup() {
        postHandler = new PostHandler(createPostUseCase, getAllPostUseCase, OFF_SET_RANGE);
    }

    @DisplayName("GIVEN an CreatePostInput WHEN the input is valid then create a post")
    @Test
    void createPostWithSuccess() throws LimitRangePostDayException {
        final var createPostInput = new CreatePostInput("TEST", null);

        doNothing().when(createPostUseCase).createPost(any(Post.class));

        postHandler.createPost(1L, createPostInput);
    }

    @DisplayName("GIVEN a page number and userId WHEN User request posts wherever post them THEN retrieve a list of post WITH sucess")
    @Test
    void getAllPostWithSucess() {
        final var post = TestMocks.postMock();
        final var userId = 1L;
        final var chunk = 5;
        final var lastPostId = 1L;

        when(getAllPostUseCase.getAllPost(userId, OFF_SET_RANGE, chunk, lastPostId)).thenReturn(List.of(post));

        var result = postHandler.getPosts(userId, 1, chunk, true, lastPostId);

        assertThat(result)
                .hasSize(1)
                .extracting("id", "post", "dateCreated", "userId", "postOriginalId")
                .contains(tuple(1L, "TEST", OffsetDateTime.MAX, 1L, null));
    }

    @DisplayName("GIVEN a page number and userId WHEN User request posts from user that him followed THEN retrieve a list of post WITH sucess")
    @Test
    void getAllPostFromUserFollowedWithSucess() {
        final var post = TestMocks.postMock();
        final var userId = 1L;
        final var chunk = 5;
        final var lastPostId = 1L;

        when(getAllPostUseCase.getAllPostFromUserFollowed(userId, OFF_SET_RANGE, chunk, lastPostId)).thenReturn(List.of(post));

        var result = postHandler.getPosts(userId, 1, chunk, lastPostId);

        assertThat(result)
                .hasSize(1)
                .extracting("id", "post", "dateCreated", "userId", "postOriginalId")
                .contains(tuple(1L, "TEST", OffsetDateTime.MAX, 1L, null));
    }

}