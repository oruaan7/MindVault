package com.mindvault.auth.service;

import com.mindvault.auth.dto.LoginRequest;
import com.mindvault.auth.dto.LoginResponse;
import com.mindvault.auth.exception.InvalidCredentialsException;
import com.mindvault.auth.jwt.JwtService;
import com.mindvault.user.entity.User;
import com.mindvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
            .orElseThrow(InvalidCredentialsException::new);

        boolean validPassword = passwordEncoder.matches(
            request.password(),
            user.getPassword()
        );

        if (!validPassword) {
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}
