package com.nakamura.posterr.application;

import com.nakamura.posterr.application.domain.Post;
import com.nakamura.posterr.application.exception.LimitRangePostDayException;
import com.nakamura.posterr.application.ports.in.post.CreatePostUseCase;
import com.nakamura.posterr.application.ports.in.post.GetAllPostUseCase;
import com.nakamura.posterr.application.ports.out.PostPort;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class AllPostService implements CreatePostUseCase, GetAllPostUseCase {

    private final PostPort postPort;

    @Override
    public void createPost(Post post) throws LimitRangePostDayException {
        log.info("POST - /v1/post - PostService - userId {} wants post something");
        postPort.createPost(post);

        log.info("POST - /v1/post - PostService - Sucess to post");
    }

    @Override
    public List<Post> getAllPost(Long userId, int offset, int size, Long lastPostId) {
        log.info("GET - /v1/post - PostService - Get all post to userId {} offset {} size {} lastPostId {}", userId, offset, size, lastPostId);
        final var posts = postPort.getAllPost(userId, offset, size, lastPostId);
        log.info("GET - /v1/post - PostService - Sucess to retrieve posts");
        return posts;
    }

    @Override
    public List<Post> getAllPostFromUserProfile(Long userId, int offset, int size, Long lastPostId, Long userProfileId) {
        log.info("GET - /v1/post - PostService - Get all post from user profile userId {} offset {} size {} lastPostId {}", userProfileId, offset, size, lastPostId);
        final var posts = postPort.getAllPostFromUserProfile(userId, offset, size, lastPostId, userProfileId);
        log.info("GET - /v1/post - PostService - Sucess to retrieve posts");
        return posts;
    }

    @Override
    public List<Post> getAllPostFromUserFollowed(Long userId, int offset, int chunk, Long lastPostId) {
        log.info("GET - /v1/post - PostService - Get all post to userId {} from user followed offset {} chunk {}", userId, offset, chunk);
        final var posts = postPort.getAllPostFromUserFollowed(userId, offset, chunk, lastPostId);
        log.info("GET - /v1/post - PostService - Sucess to retrieve posts");
        return posts;
    }

}
