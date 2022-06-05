package com.nakamura.posterr.application.ports.in.post;

import com.nakamura.posterr.application.domain.Post;

public interface CreatePostUseCase {

    void createPost(Post post);

}
