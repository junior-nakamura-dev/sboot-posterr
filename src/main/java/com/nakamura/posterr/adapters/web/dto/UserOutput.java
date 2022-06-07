package com.nakamura.posterr.adapters.web.dto;

import com.nakamura.posterr.application.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class UserOutput {

    private Long id;
    private String username;
    private OffsetDateTime dateJoined;

    public static UserOutput fromDomain(User user) {
        return UserOutput
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .dateJoined(user.getDateJoined())
                .build();
    }

}
