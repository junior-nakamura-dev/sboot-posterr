package com.nakamura.posterr.adapters.repository;

import com.nakamura.posterr.TestMocks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostDBAdapterTest {

    @Mock
    private PostRepository postRepositoryMock;

    @InjectMocks
    private PostDBAdapter postDBAdapter;

    @DisplayName("Create an user post with success")
    @Test
    void createPost() {
        final var postEntity = TestMocks.postEntityMock();

        when(postRepositoryMock.addPost(postEntity)).thenReturn(1L);

        postDBAdapter.createPost(postEntity.toDomain());
    }

}