package com.patika.adservice.controller;

import com.patika.adservice.Service.AdService;
import com.patika.adservice.client.packages.service.PackageService;
import com.patika.adservice.client.user.service.UserService;
import com.patika.adservice.converter.AdMapper;
import com.patika.adservice.dto.AdResponse;
import com.patika.adservice.model.Ad;
import com.patika.adservice.model.User;
import com.patika.adservice.model.enums.AdStatus;
import com.patika.adservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

//    @Autowired
//    private AdMapper adMapper;

    @PostMapping("/{productId}")
    public ResponseEntity<Ad> createAd(@RequestBody Ad ad, @RequestHeader("Authorization") String user, @PathVariable int productId) {
            String username = jwtUtil.extractUsername(user.substring(7));
            System.out.println(username);
            // İlan yayınlama hakkı kontrolü
            if (!adPackageService.canUserPostAd(username)) {
                System.out.println("Hayır ilan veremez");
                throw new RuntimeException("User does not have a valid package to post an ad");
            }

            Ad createdAd = adService.createAd(ad, username, productId);
            return ResponseEntity.ok(createdAd);
    }

    @GetMapping("/test")
    public String test(){
        return adService.Test();
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdResponse>> getAll() {
        List<Ad> allAds = adService.findAll();
        List<AdResponse> allAdDtos = AdMapper.toDtoList(allAds);
        return ResponseEntity.ok(allAdDtos);
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<Ad> updateAdStatus(@PathVariable Long id, @RequestParam AdStatus status) {
        Ad updatedAd = adService.updateAdStatus(id, status);
        return ResponseEntity.ok(updatedAd);
    }


    @GetMapping("/user/{username}")
    public ResponseEntity<List<AdResponse>> getMyAds(@PathVariable String username) {
        Optional<List<Ad>> ads = adService.findAdByUserName(username);
        List<AdResponse> adResponses = ads.map(AdMapper::toDtoList).orElseGet(Collections::emptyList);
        return ResponseEntity.ok(adResponses);
    }



//    @GetMapping
//    public ResponseEntity<List<Ad>> getAds(@RequestParam AdStatus status) {
//        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
//        List<Ad> ads = adService.findAdsByUserAndStatus(user, status);
//        return ResponseEntity.ok(ads);
//    }

//    @GetMapping("/active")
//    public ResponseEntity<List<Ad>> getActiveAds(@AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.findByUsername(userDetails.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        List<Ad> activeAds = adService.findAdsByUserAndStatus(user, AdStatus.ACTIVE);
//        return ResponseEntity.ok(activeAds);
//    }

//    @GetMapping("/inactive")
//    public ResponseEntity<List<Ad>> getInactiveAds(@AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.findByUsername(userDetails.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        List<Ad> inactiveAds = adService.findAdsByUserAndStatus(user, AdStatus.PASSIVE);
//        return ResponseEntity.ok(inactiveAds);
//    }

}

