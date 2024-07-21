package com.javatechie.Controller;

import com.javatechie.controller.AuthController;
import com.javatechie.dto.AuthRequest;
import com.javatechie.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void testAddNewUser() throws Exception {
        AuthRequest authRequest = new AuthRequest("user", "password");

        when(authService.saveUser(authRequest)).thenReturn("User registered");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType("application/json")
                        .content("{\"username\":\"user\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered"))
                .andReturn();
    }

    @Test
    public void testGetToken() throws Exception {
        AuthRequest authRequest = new AuthRequest("user", "password");
        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authService.generateToken("user")).thenReturn("token");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/token")
                        .contentType("application/json")
                        .content("{\"username\":\"user\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("token"))
                .andReturn();
    }

    @Test
    public void testGetRefreshToken() throws Exception {
        AuthRequest authRequest = new AuthRequest("user", "password");
        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authService.refreshToken("user")).thenReturn("newToken");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/refreshToken")
                        .contentType("application/json")
                        .content("{\"username\":\"user\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("newToken"))
                .andReturn();
    }

    @Test
    public void testValidateToken() throws Exception {
        String token = "validToken";

        doNothing().when(authService).validateToken(token);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/auth/validate")
                        .param("token", token))
                .andExpect(status().isOk())
                .andExpect(content().string("Token is valid"))
                .andReturn();
    }
}
