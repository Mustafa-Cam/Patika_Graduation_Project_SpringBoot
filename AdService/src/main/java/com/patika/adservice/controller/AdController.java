package com.patika.adservice.controller;

import com.patika.adservice.Service.AdService;
import com.patika.adservice.client.packages.service.PackageService;
import com.patika.adservice.client.user.service.UserService;
import com.patika.adservice.model.Ad;
import com.patika.adservice.model.User;
import com.patika.adservice.model.enums.AdStatus;
import com.patika.adservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ads")
public class AdController {
    @Autowired
    private AdService adService;

    @Autowired
    private UserService userService;

    @Autowired
    private PackageService adPackageService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/{productId}")
    public ResponseEntity<Ad> createAd(@RequestBody Ad ad, @RequestHeader("Authorization") String user, @PathVariable int productId) {
        String username = jwtUtil.extractUsername(user.substring(7));


        // İlan yayınlama hakkı kontrolü
        if (!adPackageService.canUserPostAd(user)) {
            throw new RuntimeException("User does not have a valid package to post an ad");
        }

        Ad createdAd = adService.createAd(ad, user, productId);
        return ResponseEntity.ok(createdAd);
    }

    @GetMapping("/test")
    public String test(){
        return adService.Test();
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<Ad> updateAdStatus(@PathVariable Long id, @RequestParam AdStatus status) {
        Ad updatedAd = adService.updateAdStatus(id, status);
        return ResponseEntity.ok(updatedAd);
    }

    @GetMapping
    public ResponseEntity<List<Ad>> getAds(@RequestParam AdStatus status, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        List<Ad> ads = adService.findAdsByUserAndStatus(user, status);
        return ResponseEntity.ok(ads);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Ad>> getActiveAds(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Ad> activeAds = adService.findAdsByUserAndStatus(user, AdStatus.ACTIVE);
        return ResponseEntity.ok(activeAds);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<Ad>> getInactiveAds(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Ad> inactiveAds = adService.findAdsByUserAndStatus(user, AdStatus.PASSIVE);
        return ResponseEntity.ok(inactiveAds);
    }

}

