package com.nakamura.posterr.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class User {
    @EqualsAndHashCode.Include
    private Long id;
    private String username;
    private OffsetDateTime dateJoined;
}
