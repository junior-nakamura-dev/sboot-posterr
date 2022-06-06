package com.nakamura.posterr.adapters.repository;

import com.nakamura.posterr.adapters.repository.entity.PostEntity;
import com.nakamura.posterr.application.domain.Post;
import com.nakamura.posterr.application.exception.LimitRangePostDayException;
import com.nakamura.posterr.application.ports.out.PostPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class PostDBAdapter implements PostPort {

    private final PostRepository postRepository;

    @Override
    public void createPost(Post post) throws LimitRangePostDayException {
        log.info("POST - /v1/post - PostDBAdapter - Create post for user {}", post.getUserId());

        final var lastPost = postRepository.getLastPost(post.getUserId()).orElse(PostEntity.builder().amountPostDay(0).build());
        final var amountPostDay = lastPost.getAmountPostDay();

        if (amountPostDay == 5) {
            log.info("POST - /v1/post - PostDBAdapter - Limit post day");
            throw new LimitRangePostDayException();
        }

        final var postEntity= PostEntity.fromDomain(post);
        postEntity.setAmountPostDay(amountPostDay + 1);
        log.info("POST - /v1/post - PostDBAdapter - Add a more post to user");

        postRepository.addPost(postEntity);
        log.info("POST - /v1/post - PostDBAdapter - Sucess postRepository");
    }

    @Override
    public List<Post> getAllPost(Long userId, int offset) {
        log.info("GET - /v1/post - PostDBAdapter - Get all post pagination for user {} and offset {}", userId, offset);

        final var postEntities = postRepository.getAllPost(offset);
        log.info("GET - /v1/post - PostDBAdapter - Sucess to retrieve postEntites from postRepository");

        final var posts = postEntities.stream().map(PostEntity::toDomain).collect(Collectors.toList());
        log.info("GET - /v1/post - PostDBAdapter - Sucess to mapping to Post domain");
        return posts;
    }

}
