package com.nakamura.posterr.adapters.web;

import com.nakamura.posterr.TestMocks;
import com.nakamura.posterr.adapters.web.dto.CreatePostInput;
import com.nakamura.posterr.adapters.web.handler.PostHandler;
import com.nakamura.posterr.application.ports.in.post.CreatePostUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class PostHandlerTest {

    @Mock
    private CreatePostUseCase createPostUseCase;

    @InjectMocks
    private PostHandler postHandler;

    @DisplayName("Create an user post with success")
    @Test
    void createPostWithSuccess() {
        final var post = TestMocks.postMock();
        final var createPostInput = new CreatePostInput("TEST", null);

        doNothing().when(createPostUseCase).createPost(post);

        postHandler.createPost(1L, createPostInput);
    }

}