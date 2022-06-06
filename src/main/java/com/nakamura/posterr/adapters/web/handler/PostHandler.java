package com.nakamura.posterr.adapters.web.handler;

import com.nakamura.posterr.adapters.web.dto.CreatePostInput;
import com.nakamura.posterr.application.exception.LimitRangePostDayException;
import com.nakamura.posterr.application.ports.in.post.CreatePostUseCase;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class PostHandler {

    private final CreatePostUseCase createPostUseCase;

    public void createPost(Long userId, CreatePostInput createPostInput) throws LimitRangePostDayException {
        log.info("POST - /v1/post - PostHandler - User {} wants create a post", userId);

        var post = createPostInput.toDomain(userId);
        log.info("POST - /v1/post - PostHandler - Sucess to mapping to Post");

        createPostUseCase.createPost(post);
        log.info("POST - /v1/post - PostHandler - Sucess to follow an user in CreatePostUseCase");
    }

}
