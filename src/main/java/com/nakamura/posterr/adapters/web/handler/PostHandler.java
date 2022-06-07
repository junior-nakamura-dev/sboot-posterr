package com.nakamura.posterr.adapters.web.handler;

import com.nakamura.posterr.adapters.web.dto.CreatePostInput;
import com.nakamura.posterr.adapters.web.dto.PostOutput;
import com.nakamura.posterr.application.exception.LimitRangePostDayException;
import com.nakamura.posterr.application.ports.in.post.CreatePostUseCase;
import com.nakamura.posterr.application.ports.in.post.GetAllPostUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PostHandler {

    private final CreatePostUseCase createPostUseCase;
    private final GetAllPostUseCase getAllPostUseCase;
    private int OFFSET_RANGE;

    public PostHandler(CreatePostUseCase createPostUseCase, GetAllPostUseCase getAllPostUseCase, @Value("${pagination.offset.range}") int OFFSET_RANGE) {
        this.createPostUseCase = createPostUseCase;
        this.getAllPostUseCase = getAllPostUseCase;
        this.OFFSET_RANGE = OFFSET_RANGE;
    }

    public void createPost(Long userId, CreatePostInput createPostInput) throws LimitRangePostDayException {
        log.info("POST - /v1/post - PostHandler - User {} wants create a post", userId);

        var post = createPostInput.toDomain(userId);
        log.info("POST - /v1/post - PostHandler - Sucess to mapping to Post");

        createPostUseCase.createPost(post);
        log.info("POST - /v1/post - PostHandler - Sucess to follow an user in CreatePostUseCase");
    }

    public List<PostOutput> getPosts(Long userId, int page, int chunk, boolean seeingAll) {
        log.info("GET - /v1/post - PostHandler - Get all post to user {} page {} chunk {} seeingAll {}", userId, page, chunk, seeingAll);

        var posts = getAllPostUseCase.getAllPost(userId, (page * OFFSET_RANGE), chunk);
        log.info("GET - /v1/post - PostHandler - Sucess to retrieve posts from getAllPostUseCase");

        var postsOutput = posts.stream().map(PostOutput::fromDomain).collect(Collectors.toList());
        log.info("GET - /v1/post - PostHandler - Sucess to mapping to PostOutput");
        return postsOutput;
    }

    public List<PostOutput> getPosts(Long userId, int page, int chunk) {
        log.info("GET - /v1/post - PostHandler - Get post to user {} from user followed page {} chunk {} ", userId, page, chunk);

        var posts = getAllPostUseCase.getAllPostFromUserFollowed(userId, (page * OFFSET_RANGE), chunk);
        log.info("GET - /v1/post - PostHandler - Sucess to retrieve posts from getAllPostFromUserFollowed");

        var postsOutput = posts.stream().map(PostOutput::fromDomain).collect(Collectors.toList());
        log.info("GET - /v1/post - PostHandler - Sucess to mapping to PostOutput");
        return postsOutput;
    }

}
