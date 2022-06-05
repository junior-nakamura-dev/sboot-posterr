package com.nakamura.posterr.application.ports.out;

import com.nakamura.posterr.application.domain.Post;

public interface PostPort {

    void createPost(Post post);

}
