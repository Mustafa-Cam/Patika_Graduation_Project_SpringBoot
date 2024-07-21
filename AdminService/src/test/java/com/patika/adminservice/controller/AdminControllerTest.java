package com.patika.adminservice.controller;

import com.patika.adminservice.client.AdClient;
import com.patika.adminservice.model.Ad;
import com.patika.adminservice.model.enums.AdStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class AdminControllerTest {

    @Mock
    private AdClient adClient;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMakeActive() throws Exception {
        Ad ad = new Ad();
        ad.setStatus(AdStatus.ACTIVE);
        ResponseEntity<Ad> responseEntity = ResponseEntity.ok(ad);
        Future<ResponseEntity<Ad>> futureResponse = new AsyncResult<>(responseEntity);

        when(adClient.updateAdStatusForAdmin(anyInt(), any(AdStatus.class))).thenReturn(futureResponse);

        Future<ResponseEntity<Ad>> result = adminController.makeActive(1, AdStatus.ACTIVE);

        assertEquals(responseEntity, result.get());
        verify(adClient, times(1)).updateAdStatusForAdmin(1, AdStatus.ACTIVE);
    }
}
