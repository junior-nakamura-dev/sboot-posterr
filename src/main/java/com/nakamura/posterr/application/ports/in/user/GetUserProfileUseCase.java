package com.nakamura.posterr.application.ports.in.user;

import com.nakamura.posterr.application.domain.User;

public interface GetUserProfileUseCase {

    User getUserProfile(Long userId);

}
