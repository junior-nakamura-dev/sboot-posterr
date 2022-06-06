package com.nakamura.posterr.application.ports.out;

import com.nakamura.posterr.application.domain.Post;
import com.nakamura.posterr.application.exception.LimitRangePostDayException;

public interface PostPort {

    void createPost(Post post) throws LimitRangePostDayException;

}
