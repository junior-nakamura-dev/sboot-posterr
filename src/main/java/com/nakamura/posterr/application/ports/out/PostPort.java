package com.nakamura.posterr.application.ports.out;

import com.nakamura.posterr.application.domain.Post;
import com.nakamura.posterr.application.exception.LimitRangePostDayException;

import java.util.List;

public interface PostPort {

    void createPost(Post post) throws LimitRangePostDayException;

    List<Post> getAllPost(Long userId, int offset, int size, Long lastPostId, Long userProfileId);

    List<Post> getAllPostFromUserFollowed(Long userId, int offset, int size, Long lastPostId);

    Long countPosts(Long userId);

}
