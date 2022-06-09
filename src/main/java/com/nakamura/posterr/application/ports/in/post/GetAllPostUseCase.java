package com.nakamura.posterr.application.ports.in.post;

import com.nakamura.posterr.application.domain.Post;

import java.util.List;

public interface GetAllPostUseCase {

    List<Post> getAllPost(Long userId, int offset, int size, Long lastPostId);

    List<Post> getAllPostFromUserProfile(Long userId, int offset, int size, Long lastPostId, Long userProfileId);

    List<Post> getAllPostFromUserFollowed(Long userId, int offset, int size, Long lastPostId);

}
