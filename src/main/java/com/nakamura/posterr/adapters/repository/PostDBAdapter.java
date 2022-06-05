package com.nakamura.posterr.adapters.repository;

import com.nakamura.posterr.adapters.repository.entity.PostEntity;
import com.nakamura.posterr.application.domain.Post;
import com.nakamura.posterr.application.ports.out.PostPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class PostDBAdapter implements PostPort {

    private final PostRepository postRepository;

    @Override
    public void createPost(Post post) {
        log.info("POST - /v1/post - PostDBAdapter - Create post for user {}", post.getUserId());

        postRepository.addPost(PostEntity.fromDomain(post));
        log.info("POST - /v1/post - PostDBAdapter - Sucess postRepository");
    }
}
