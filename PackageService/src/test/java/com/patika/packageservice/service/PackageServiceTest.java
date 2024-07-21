package com.patika.packageservice.service;

import com.patika.packageservice.Repository.PackagesRepository;
import com.patika.packageservice.Repository.UserCredentialRepository;
import com.patika.packageservice.client.payment.PaymentClient;
import com.patika.packageservice.model.Package;
import com.patika.packageservice.model.User;
import com.patika.packageservice.model.enums.PackageEnum;
import com.patika.packageservice.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PackageServiceTest {

    @Mock
    private PackagesRepository packagesRepository;

    @Mock
    private UserCredentialRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PaymentClient paymentClient;

    @InjectMocks
    private PackageService packageService;

    private User user;
    private Package aPackage;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testuser");

        aPackage = new Package();
        aPackage.setUser(user);
        aPackage.setPackageType(PackageEnum.SMALL);
        aPackage.setAdCount(10);
        aPackage.setStartDate(LocalDateTime.now());
        aPackage.setExpiryDate(LocalDateTime.now().plusMonths(1));
    }

    @Test
    void getRandomNumber() {
        String token = "Bearer token";
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(paymentClient.getRandomNumber(anyString())).thenReturn(12345);

        int randomNumber = packageService.getRandomNumber("testuser", token);

        assertEquals(12345, randomNumber);
        verify(userRepository, times(1)).findByUsername("testuser");
        verify(paymentClient, times(1)).getRandomNumber(token);
    }

    @Test
    void purchasePackage() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(packagesRepository.findByUser(any(User.class))).thenReturn(aPackage);
        when(packagesRepository.save(any(Package.class))).thenReturn(aPackage);

        packageService.getRandomNumber("testuser", "Bearer token"); // set random number
        Package result = packageService.purchasePackage("testuser", 12345, PackageEnum.MEDIUM);

        assertNotNull(result);
        assertEquals(30, result.getAdCount()); // 10 existing + 20 MEDIUM
        assertEquals(PackageEnum.SMALL, result.getPackageType());
        verify(packagesRepository, times(1)).save(any(Package.class));
    }

    @Test
    void findById() {
        when(packagesRepository.findById(anyLong())).thenReturn(Optional.of(aPackage));

        Optional<Package> result = packageService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(aPackage, result.get());
        verify(packagesRepository, times(1)).findById(1L);
    }

    @Test
    void findAll() {
        packageService.findAll();
        verify(packagesRepository, times(1)).findAll();
    }

    @Test
    void deleteById() {
        doNothing().when(packagesRepository).deleteById(anyLong());

        packageService.deleteById(1L);

        verify(packagesRepository, times(1)).deleteById(1L);
    }

    @Test
    void savePackage() {
        when(packagesRepository.save(any(Package.class))).thenReturn(aPackage);

        Package result = packageService.savePackage(aPackage);

        assertNotNull(result);
        assertEquals(aPackage, result);
        verify(packagesRepository, times(1)).save(aPackage);
    }

    @Test
    void canUserPostAd() {
        when(packagesRepository.findTopByUserOrderByExpiryDateDesc(any(User.class)))
                .thenReturn(Optional.of(aPackage));

        boolean result = packageService.canUserPostAd(user);

        assertTrue(result);
    }

    @Test
    void decrementAdCount() {
        when(packagesRepository.findTopByUserAndExpiryDateAfterOrderByExpiryDateAsc(any(User.class), any(LocalDateTime.class)))
                .thenReturn(Optional.of(aPackage));

        packageService.decrementAdCount(user);

        assertEquals(9, aPackage.getAdCount());
        verify(packagesRepository, times(1)).save(aPackage);
    }

    @Test
    void findPackagesByUser() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(packagesRepository.findByUser(any(User.class))).thenReturn(aPackage);

        Package result = packageService.findPackagesByUser("testuser");

        assertNotNull(result);
        assertEquals(aPackage, result);
        verify(userRepository, times(1)).findByUsername("testuser");
        verify(packagesRepository, times(1)).findByUser(user);
    }
}
