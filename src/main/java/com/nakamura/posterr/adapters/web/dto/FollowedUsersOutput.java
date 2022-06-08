package com.nakamura.posterr.adapters.web.dto;

import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
public class FollowedUsersOutput {

    private List<FollowedUserOutput> followedUserList;
    private int amount;

    public FollowedUsersOutput(List<FollowedUserOutput> followedUserList) {
        final var list = Optional.ofNullable(followedUserList).orElse(Collections.EMPTY_LIST);
        this.followedUserList = list;
        this.amount = list.size();
    }

}
