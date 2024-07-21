package com.patika.userservice.controller;

import com.patika.userservice.entity.User;
import com.patika.userservice.service.UserService;
import com.patika.userservice.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void findByUsername() throws Exception {
        String username = "testuser";
        User user = new User();
        user.setUsername(username);

        when(userService.findByUsername(username)).thenReturn(user);

        mockMvc.perform(get("/api/v1/user/findbyusername/{username}", username))
                .andExpect(status().isOk());
    }

    @Test
    void getUser() throws Exception {
        String token = "Bearer testtoken";
        String username = "testuser";
        User user = new User();
        user.setUsername(username);

        when(jwtUtil.extractUsername(anyString())).thenReturn(username);
        when(userService.findByUsername(username)).thenReturn(user);

        mockMvc.perform(get("/api/v1/user")
                        .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isOk());
    }
}
