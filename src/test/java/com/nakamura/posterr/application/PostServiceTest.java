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

import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostPort postPortMock;

    @InjectMocks
    private PostService postService;

    @DisplayName("Create an user post with sucess")
    @Test
    void getFollowingUsersWithSucess() throws LimitRangePostDayException {
        final var post = TestMocks.postMock();

        doNothing().when(postPortMock).createPost(post);

        postService.createPost(post);

    }

}