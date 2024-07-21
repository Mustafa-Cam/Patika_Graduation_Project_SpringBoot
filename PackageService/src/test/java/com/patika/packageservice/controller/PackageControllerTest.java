package com.patika.packageservice.controller;

import com.patika.packageservice.model.Package;
import com.patika.packageservice.model.enums.PackageEnum;
import com.patika.packageservice.service.PackageService;
import com.patika.packageservice.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PackageControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PackageService packageService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private PackageController packageController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(packageController).build();
    }

    @Test
    public void testTestPackage() throws Exception {
        mockMvc.perform(get("/api/v1/packages/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("test is successfuly"));
    }

    @Test
    public void testUpdatePackage() throws Exception {
        Package packages = new Package();
        when(packageService.savePackage(any(Package.class))).thenReturn(packages);

        mockMvc.perform(post("/api/v1/packages/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"packageId\": 1, \"packageName\": \"Test Package\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.packageId").value(1))
                .andExpect(jsonPath("$.packageName").value("Test Package"));
    }

    @Test
    public void testGetPackagesByUserId() throws Exception {
        Optional<Package> packages = Optional.of(new Package());
        when(packageService.findById(anyLong())).thenReturn(packages);

        mockMvc.perform(get("/api/v1/packages/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindByUser() throws Exception {
        Package packages = new Package();
        when(packageService.findPackagesByUser(anyString())).thenReturn(packages);

        mockMvc.perform(get("/api/v1/packages/username/testUser"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRandomNumber() throws Exception {
        String token = "Bearer testToken";
        when(jwtUtil.extractUsername(anyString())).thenReturn("testUser");
        when(packageService.getRandomNumber(anyString(), anyString())).thenReturn(123);

        mockMvc.perform(get("/api/v1/packages/purchase/random-number")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().string("123"));
    }

    @Test
    public void testPurchasePackage() throws Exception {
        String token = "Bearer testToken";
        Package packages = new Package();
        when(jwtUtil.extractUsername(anyString())).thenReturn("testUser");
        when(packageService.purchasePackage(anyString(), anyInt(), any(PackageEnum.class))).thenReturn(packages);

        mockMvc.perform(post("/api/v1/packages/purchase")
                        .header("Authorization", token)
                        .param("enteredRandomNumber", "123")
                        .param("packageType", "BASIC"))
                .andExpect(status().isOk());
    }
}
