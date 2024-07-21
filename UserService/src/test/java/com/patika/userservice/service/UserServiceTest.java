package com.patika.userservice.service;

import com.patika.userservice.entity.User;
import com.patika.userservice.repository.UserCredentialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserCredentialRepository repository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByUsername_UserExists() {
        String username = "testuser";
        User user = new User();
        user.setUsername(username);

        when(repository.findByUsername(anyString())).thenReturn(Optional.of(user));

        User result = userService.findByUsername(username);

        assertEquals(user, result);
    }

    @Test
    void findByUsername_UserNotFound() {
        when(repository.findByUsername(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.findByUsername("nonexistentuser");
        });

        assertEquals("User not found", exception.getMessage());
    }
}
