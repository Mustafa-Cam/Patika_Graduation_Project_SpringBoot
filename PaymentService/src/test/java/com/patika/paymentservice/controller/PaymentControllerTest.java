package com.patika.paymentservice.controller;

import com.patika.paymentservice.service.PaymentService;
import com.patika.paymentservice.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private PaymentController paymentController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
    }

    @Test
    void getRandomNumber() throws Exception {
        when(paymentService.GenerateNumber()).thenReturn(12345);

        mockMvc.perform(get("/api/v1/payments/randomNumber")
                        .header("Authorization", "Bearer testtoken"))
                .andExpect(status().isOk())
                .andExpect(content().string("12345"));
    }

    // Diğer metodlar şu anda yorum satırı olarak bırakıldığı için test edilmedi.
}
