package com.nakamura.posterr.adapters.web.dto;

import com.nakamura.posterr.application.domain.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class PostOutput {

    private Long id;
    private String post;
    private OffsetDateTime dateCreated;
    private Long userId;

    public static PostOutput fromDomain(Post post) {
        return PostOutput
                .builder()
                .id(post.getId())
                .post(post.getPost())
                .dateCreated(post.getDateCreated())
                .userId(post.getUserId())
                .build();
    }

}
