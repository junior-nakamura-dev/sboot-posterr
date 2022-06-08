package com.nakamura.posterr.adapters.web.dto;

import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
public class FollowingUsersOutput {
    private List<FollowingUserOutput> followingUsersList;
    private int amount;

    public FollowingUsersOutput(List<FollowingUserOutput> followingUsersList) {
        final var list = Optional.ofNullable(followingUsersList).orElse(Collections.EMPTY_LIST);
        this.followingUsersList = list;
        this.amount = list.size();
    }

}
