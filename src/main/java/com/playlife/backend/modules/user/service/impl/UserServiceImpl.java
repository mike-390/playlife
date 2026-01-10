package com.playlife.backend.modules.user.service.impl;

import com.playlife.backend.config.JwtService;
import com.playlife.backend.enums.UserRole;
import com.playlife.backend.modules.user.Entity.User;
import com.playlife.backend.modules.user.dto.AuthResponse;
import com.playlife.backend.modules.user.dto.LoginRequest;
import com.playlife.backend.modules.user.dto.RegisterRequest;
import com.playlife.backend.modules.user.dto.UserResponse;
import com.playlife.backend.modules.user.mapper.UserMapper;
import com.playlife.backend.modules.user.repository.UserRepository;
import com.playlife.backend.modules.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService  jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public UserResponse register(RegisterRequest request){

        if (userRepository.existsByEmail(request.email())){
            throw new RuntimeException("Email already exists");
        }

        User user = userMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(UserRole.PLAYER);

        User savedUser = userRepository.save(user);

        UserResponse userResponse = userMapper.toResponse(savedUser);

        return userResponse;
    }

    @Override
    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        var user = userRepository.findByEmail(request.email())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return userMapper.toAuthResponse(user, jwtToken);
    }
}
