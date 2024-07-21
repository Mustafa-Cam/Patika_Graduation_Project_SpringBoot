package com.patika.adservice.service;

import com.patika.adservice.Service.AdService;
import com.patika.adservice.Service.ProductService;
import com.patika.adservice.client.packages.service.PackageService;
import com.patika.adservice.client.user.service.UserService;
import com.patika.adservice.model.Ad;
import com.patika.adservice.model.Product;
import com.patika.adservice.model.User;
import com.patika.adservice.model.enums.AdStatus;
import com.patika.adservice.repository.AdRepository;
import com.patika.adservice.repository.ProductRepository;
import com.patika.adservice.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdServiceTest {

    @InjectMocks
    private AdService adService;

    @Mock
    private AdRepository adRepository;

    @Mock
    private PackageService adPackageService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    private Ad ad;
    private User user;
    private Product product;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testuser");

        product = new Product();
        product.setId(1);

        ad = new Ad();
        ad.setId(1L);
        ad.setUser(user);
        ad.setProduct(product);
        ad.setStatus(AdStatus.IN_REVIEW);
    }

    @Test
    void testCreateAdSuccess() {
        when(adPackageService.canUserPostAd(anyString())).thenReturn(true);
        when(adRepository.findByProductId(anyInt())).thenReturn(Optional.empty());
        when(userService.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(adRepository.save(any(Ad.class))).thenReturn(ad);

        Ad createdAd = adService.createAd(ad, "testuser", 1);

        assertNotNull(createdAd);
        assertEquals(AdStatus.IN_REVIEW, createdAd.getStatus());
        verify(adPackageService).decrementAdCount(anyString());
    }

    @Test
    void testCreateAdUserNotFound() {
        when(userService.findByUsername(anyString())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            adService.createAd(ad, "testuser", 1);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testCreateAdProductAlreadyAdvertised() {
        when(adRepository.findByProductId(anyInt())).thenReturn(Optional.of(ad));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            adService.createAd(ad, "testuser", 1);
        });

        assertEquals("Product already has an ad", exception.getMessage());
    }

    @Test
    void testUpdateAdStatusForUser() {
        when(adRepository.findById(anyLong())).thenReturn(Optional.of(ad));
        when(adRepository.save(any(Ad.class))).thenReturn(ad);

        Ad updatedAd = adService.updateAdStatusForUser(1L, AdStatus.ACTIVE);

        assertNotNull(updatedAd);
        assertEquals(AdStatus.ACTIVE, updatedAd.getStatus());
    }

    @Test
    void testFindById() {
        when(adRepository.findById(anyLong())).thenReturn(Optional.of(ad));

        Optional<Ad> foundAd = adService.findById(1L);

        assertTrue(foundAd.isPresent());
        assertEquals(ad.getId(), foundAd.get().getId());
    }

    @Test
    void testFindAll() {
        List<Ad> ads = List.of(ad);
        when(adRepository.findAll()).thenReturn(ads);

        List<Ad> foundAds = adService.findAll();

        assertNotNull(foundAds);
        assertFalse(foundAds.isEmpty());
    }

    @Test
    void testDeleteById() {
        doNothing().when(adRepository).deleteById(anyLong());

        adService.deleteById(1L);

        verify(adRepository, times(1)).deleteById(anyLong());
    }
}
