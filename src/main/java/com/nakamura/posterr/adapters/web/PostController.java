package com.nakamura.posterr.adapters.web;

import com.nakamura.posterr.adapters.web.dto.CreatePostInput;
import com.nakamura.posterr.adapters.web.handler.PostHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/post")
@AllArgsConstructor
@Slf4j
@Validated
public class PostController {

    private final PostHandler postHandler;

    @PostMapping
    public ResponseEntity followUser(@RequestBody @Valid CreatePostInput createPostInput) {
        final var userId = SecurityContextMock.userId;
        log.info("POST - /v1/post - Start process to follow user");

        postHandler.createPost(userId, createPostInput);

        log.info("POST - /v1/post - Sucess - 204", userId);
        return ResponseEntity.noContent().build();
    }

}
