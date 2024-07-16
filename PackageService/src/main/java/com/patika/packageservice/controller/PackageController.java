package com.patika.packageservice.controller;


import com.patika.packageservice.client.user.service.UserService;
import com.patika.packageservice.model.Package;
import com.patika.packageservice.model.User;
import com.patika.packageservice.service.PackageService;
import com.patika.packageservice.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("/test")
    public String TestPackage(){
        return "test is successfuly";
    }

    @PostMapping
    public ResponseEntity<Package> createPackage(@RequestBody Package packages) {
        return ResponseEntity.ok(packageService.savePackage(packages));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<Package>> getPackagesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(packageService.findById(userId));
    }

//    @GetMapping
//    public ResponseEntity<List<Package>> getPackages(@RequestHeader("Authorization") String token) {
//        User user = userService.findByUsername(userDetails.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        List<Package> packages = packageService.findPackagesByUser(user);
//        return ResponseEntity.ok(packages);
//    }

//    @GetMapping("/{findbyuser}")
//    public ResponseEntity<List<Package>> findByUser(@PathVariable User user) {
//        List<Package> packages = packageService.findPackagesByUser(user);
//        return ResponseEntity.ok(packages);
//    }

    @PostMapping("/add")
    public ResponseEntity<Package> addPackage(@RequestParam Optional<User> userId) {
        return ResponseEntity.ok(packageService.purchasePackage(String.valueOf(userId)));
    }

    @PostMapping("/purchase")
    public ResponseEntity<Package> purchasePackage(@RequestHeader("Authorization") String token) {
        try {
            String username = jwtUtil.extractUsername(token.substring(7));
            Package purchasedPackage = packageService.purchasePackage(username);
            return ResponseEntity.ok(purchasedPackage);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}

