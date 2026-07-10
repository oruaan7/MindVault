package com.mindvault.user.service;

import com.mindvault.user.dto.CreateUserRequest;
import com.mindvault.user.entity.User;
import com.mindvault.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {

        CreateUserRequest request =
            new CreateUserRequest(
                "Ruan",
                "ruan@email.com",
                "12345678"
            );

        when(userRepository.existsByEmail(request.email()))
            .thenReturn(true);

        assertThrows(
            IllegalArgumentException.class,
            () -> userService.create(request)
        );

        verify(userRepository, never()).save(any(User.class));
    }
}
