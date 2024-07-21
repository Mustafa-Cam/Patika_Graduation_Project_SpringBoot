package com.patika.paymentservice.service;

import com.patika.paymentservice.client.packages.PackageClient;
import com.patika.paymentservice.util.PaymentProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PackageClient packageClient;

    @Mock
    private PaymentProcessor paymentProcessor;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService(paymentProcessor);
    }

    @Test
    void generateNumber() {
        int expectedNumber = 12345;
        when(paymentProcessor.generateRandomNumber()).thenReturn(expectedNumber);

        int actualNumber = paymentService.GenerateNumber();

        assertEquals(expectedNumber, actualNumber);
    }

}
