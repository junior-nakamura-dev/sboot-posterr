package com.nakamura.posterr.application.ports.in.post;

import com.nakamura.posterr.application.domain.Post;
import com.nakamura.posterr.application.exception.LimitRangePostDayException;

public interface CreatePostUseCase {

    void createPost(Post post) throws LimitRangePostDayException;

}
