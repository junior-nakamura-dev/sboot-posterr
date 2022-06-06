package com.nakamura.posterr.application;

import com.nakamura.posterr.application.domain.Post;
import com.nakamura.posterr.application.exception.LimitRangePostDayException;
import com.nakamura.posterr.application.ports.in.post.CreatePostUseCase;
import com.nakamura.posterr.application.ports.out.PostPort;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class PostService implements CreatePostUseCase {

    private final PostPort postPort;

    @Override
    public void createPost(Post post) throws LimitRangePostDayException {
        log.info("POST - /v1/post - PostService - userId {} wants post something");
        postPort.createPost(post);

        log.info("POST - /v1/post - PostService - Sucess to post");
    }
}
