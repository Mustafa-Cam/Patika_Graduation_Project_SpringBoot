package com.patika.adservice.controller;

import com.patika.adservice.Service.AdService;
import com.patika.adservice.client.packages.service.PackageService;
import com.patika.adservice.client.user.service.UserService;
import com.patika.adservice.converter.AdMapper;
import com.patika.adservice.dto.response.AdResponse;
import com.patika.adservice.model.Ad;
import com.patika.adservice.model.enums.AdStatus;
import com.patika.adservice.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdController.class)
public class AdControllerTest {

    @MockBean
    private AdService adService;

    @MockBean
    private UserService userService;

    @MockBean
    private PackageService adPackageService;

    @MockBean
    private JwtUtil jwtUtil;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new AdController()).build();
    }

    @Test
    public void testCreateAd() throws Exception {
        Ad ad = new Ad();
        when(jwtUtil.extractUsername(anyString())).thenReturn("testuser");
        when(adPackageService.canUserPostAd(anyString())).thenReturn(true);
        when(adService.createAd(any(Ad.class), anyString(), anyInt())).thenReturn(ad);

        mockMvc.perform(post("/api/v1/ads/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Test Ad\"}")
                        .header("Authorization", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Ad"));
    }

    @Test
    public void testGetAll() throws Exception {
        AdResponse adResponse = new AdResponse();
        List<AdResponse> adResponses = Collections.singletonList(adResponse);
        when(adService.findAll()).thenReturn(Collections.singletonList(new Ad()));
        when(AdMapper.toDtoList(anyList())).thenReturn(adResponses);

        mockMvc.perform(get("/api/v1/ads/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    public void testUpdateAdStatusForAdmin() throws Exception {
        doNothing().when(adService).updateAdStatusForAdmin(anyLong(), any(AdStatus.class));

        mockMvc.perform(post("/api/v1/ads/1/statusAdmin")
                        .param("status", "ACTIVE"))
                .andExpect(status().isOk())
                .andExpect(content().string("status değisti."));
    }

    @Test
    public void testUpdateAdStatusForUser() throws Exception {
        Ad ad = new Ad();
        ad.setStatus(AdStatus.ACTIVE);
        when(adService.findById(anyLong())).thenReturn(Optional.of(ad));
        when(adService.updateAdStatusForUser(anyLong(), any(AdStatus.class))).thenReturn(ad);

        mockMvc.perform(post("/api/v1/ads/1/status")
                        .param("status", "ACTIVE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    public void testGetAdDetail() throws Exception {
        Ad ad = new Ad();
        AdResponse adResponse = new AdResponse();
        when(adService.findById(anyLong())).thenReturn(Optional.of(ad));
        when(AdMapper.toDto(any(Ad.class))).thenReturn(adResponse);

        mockMvc.perform(get("/api/v1/ads/adDetail/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testGetMyAds() throws Exception {
        Ad ad = new Ad();
        List<Ad> ads = Collections.singletonList(ad);
        List<AdResponse> adResponses = Collections.singletonList(new AdResponse());
        when(adService.findAdByUserName(anyString())).thenReturn(Optional.of(ads));
        when(AdMapper.toDtoList(anyList())).thenReturn(adResponses);

        mockMvc.perform(get("/api/v1/ads/user/testuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }
}
