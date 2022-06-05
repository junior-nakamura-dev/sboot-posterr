package com.nakamura.posterr.adapters.repository.entity;

import com.nakamura.posterr.application.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostEntity {

    private Long id;
    private String post;
    private Long postOriginalId;
    private Long userId;
    private OffsetDateTime dateCreated;

    public Post toDomain() {
        return Post
                .builder()
                .id(this.id)
                .post(this.post)
                .postOriginalId(this.postOriginalId)
                .userId(this.userId)
                .dateCreated(this.dateCreated)
                .build();
    }

    public static PostEntity fromDomain(Post post) {
        return PostEntity
                .builder()
                .post(post.getPost())
                .postOriginalId(post.getPostOriginalId())
                .userId(post.getUserId())
                .dateCreated(post.getDateCreated())
                .build();
    }

}
