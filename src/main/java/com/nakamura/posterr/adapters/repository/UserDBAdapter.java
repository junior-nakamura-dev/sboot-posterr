package com.nakamura.posterr.adapters.repository;

import com.nakamura.posterr.adapters.repository.entity.FollowingEntity;
import com.nakamura.posterr.application.ports.out.UserPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserDBAdapter implements UserPort {

    private final FollowingRepository followingRepository;

    @Override
    public List<FollowingEntity> getFollowingUserById(Long userId) {
        return followingRepository.following(userId);
    }

}
