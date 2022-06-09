package com.nakamura.posterr.adapters.web.dto;

import com.nakamura.posterr.application.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostInput {

    @Length(message = "Invalid Post - Length must be between 1 and 777", min = 1, max = 777)
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
