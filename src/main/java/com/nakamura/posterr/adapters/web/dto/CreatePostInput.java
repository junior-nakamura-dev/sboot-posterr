package com.nakamura.posterr.adapters.web.dto;

import com.nakamura.posterr.application.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostInput {

    private String post;
    private Long postOriginalId;

    public Post toDomain(Long userId) {
        return Post
                .builder()
                .userId(userId)
                .post(post)
                .postOriginalId(postOriginalId)
                .build();
    }

}
