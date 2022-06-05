package com.nakamura.posterr.adapters.repository.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntity {

    private Long id;

    @Pattern(regexp = "^[\\p{Alnum}]{1,14}$")
    private String username;

    @NotNull
    private OffsetDateTime dateJoined;

}
