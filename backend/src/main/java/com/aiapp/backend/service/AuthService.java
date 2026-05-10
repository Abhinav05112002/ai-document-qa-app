package com.aiapp.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aiapp.backend.dto.AuthResponse;
import com.aiapp.backend.dto.LoginRequest;
import com.aiapp.backend.dto.RegisterRequest;
import com.aiapp.backend.entity.User;
import com.aiapp.backend.repository.UserRepository;
import com.aiapp.backend.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public String register(RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(
                    passwordEncoder.encode(
                        request.getPassword()
                    )
                )
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid password");
        }

        String token = jwtService
                .generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}
