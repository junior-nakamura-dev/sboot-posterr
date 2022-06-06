package com.nakamura.posterr.adapters.web;

import com.nakamura.posterr.adapters.web.dto.CreatePostInput;
import com.nakamura.posterr.adapters.web.handler.PostHandler;
import com.nakamura.posterr.application.exception.LimitRangePostDayException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/post")
@AllArgsConstructor
@Slf4j
@Validated
public class PostController {

    private final PostHandler postHandler;

    @PostMapping
    public ResponseEntity createPost(@RequestBody @Valid CreatePostInput createPostInput) {
        try {
            final var userId = SecurityContextMock.userId;
            log.info("POST - /v1/post - Start process to follow user");

            postHandler.createPost(userId, createPostInput);

            log.info("POST - /v1/post - Sucess - 204", userId);
            return ResponseEntity.noContent().build();
        } catch (LimitRangePostDayException businessException) {
            throw new ResponseStatusException(businessException.getExceptionStatusCode(), businessException.getExceptionDescription());
        }
    }

    @GetMapping
    public ResponseEntity getPosts(@RequestParam(defaultValue = "0") int page) {
        try {
            final var userId = SecurityContextMock.userId;
            log.info("GET - /v1/post - Start process to get list of posts to user");

            final var postOutputs = postHandler.getPosts(userId, page);

            log.info("GET - /v1/post - Sucess - 200", userId);
            return ResponseEntity.ok(postOutputs);
        } catch (LimitRangePostDayException businessException) {
            throw new ResponseStatusException(businessException.getExceptionStatusCode(), businessException.getExceptionDescription());
        }
    }

}
