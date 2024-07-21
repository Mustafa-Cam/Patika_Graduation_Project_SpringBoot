package com.javatechie.Service;

import com.javatechie.config.CustomUserDetailsService;
import com.javatechie.dto.AuthRequest;
import com.javatechie.entity.User;
import com.javatechie.entity.enums.RoleEnum;
import com.javatechie.repository.UserCredentialRepository;
import com.javatechie.service.AuthService;
import com.javatechie.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthServiceTest {

    @Mock
    private UserCredentialRepository repository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser_UserDoesNotExist() {
        AuthRequest authRequest = new AuthRequest("user", "password", "email@example.com", RoleEnum.USER);
        when(repository.findByUsername(authRequest.getUsername())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(authRequest.getPassword())).thenReturn("encodedPassword");

        String result = authService.saveUser(authRequest);

        verify(repository).save(any(User.class));
        assertEquals("User added to the system", result);
    }

    @Test
    public void testSaveUser_UserExists() {
        AuthRequest authRequest = new AuthRequest("user", "password", "email@example.com", RoleEnum.USER);
        when(repository.findByUsername(authRequest.getUsername())).thenReturn(Optional.of(new User()));

        String result = authService.saveUser(authRequest);

        assertEquals("Username already exists", result);
    }

    @Test
    public void testGenerateToken() {
        String username = "user";
        UserDetails userDetails = mock(UserDetails.class);
        when(customUserDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn("token");

        String token = authService.generateToken(username);

        assertEquals("token", token);
    }

    @Test
    public void testValidateToken() {
        String token = "validToken";
        doNothing().when(jwtService).validateToken(token);

        authService.validateToken(token);

        verify(jwtService).validateToken(token);
    }

    @Test
    public void testRefreshToken() {
        String username = "user";
        UserDetails userDetails = mock(UserDetails.class);
        when(customUserDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn("newToken");

        String token = authService.refreshToken(username);

        assertEquals("newToken", token);
    }
}
