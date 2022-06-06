package com.nakamura.posterr.adapters.repository.entity;

import com.nakamura.posterr.application.domain.Post;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class PostEntity {

    @EqualsAndHashCode.Include
    private Long id;
    private String post;
    private Long postOriginalId;
    private Long userId;
    private OffsetDateTime dateCreated;
    private int amountPostDay;

    public Post toDomain() {
        return Post
                .builder()
                .id(this.id)
                .post(this.post)
                .postOriginalId(this.postOriginalId)
                .userId(this.userId)
                .dateCreated(this.dateCreated)
                .amountPostDay(this.amountPostDay)
                .build();
    }

    public static PostEntity fromDomain(Post post) {
        return PostEntity
                .builder()
                .post(post.getPost())
                .postOriginalId(post.getPostOriginalId())
                .userId(post.getUserId())
                .dateCreated(post.getDateCreated())
                .amountPostDay(post.getAmountPostDay())
                .build();
    }

    public void setAmountPostDay(int amountPostDay) {
        this.amountPostDay = amountPostDay;
    }

}
