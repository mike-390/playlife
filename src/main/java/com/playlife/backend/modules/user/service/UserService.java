package com.playlife.backend.modules.user.service;

import com.playlife.backend.modules.user.dto.RegisterRequest;
import com.playlife.backend.modules.user.dto.UserResponse;

public interface UserService {
    UserResponse register(RegisterRequest request);
}
