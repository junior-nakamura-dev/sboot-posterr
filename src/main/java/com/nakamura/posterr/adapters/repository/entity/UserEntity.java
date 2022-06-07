package com.nakamura.posterr.adapters.repository.entity;

import com.nakamura.posterr.application.domain.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserEntity {

    private Long id;

    @Pattern(regexp = "^[\\p{Alnum}]{1,14}$")
    private String username;

    @NotNull
    private OffsetDateTime dateJoined;

    public User toDomain() {
        return User
                .builder()
                .id(this.id)
                .username(this.username)
                .dateJoined(this.dateJoined)
                .build();
    }

}
