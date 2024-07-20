package com.patika.packageservice.controller;


//import com.patika.packageservice.client.user.service.UserService;
import com.patika.packageservice.client.payment.PaymentClient;
import com.patika.packageservice.model.Package;
import com.patika.packageservice.model.User;
import com.patika.packageservice.model.enums.PackageEnum;
import com.patika.packageservice.service.PackageService;
import com.patika.packageservice.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private PaymentClient paymentClient;

    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("/test")
    public String TestPackage(){
        return "test is successfuly";
    }

    @PostMapping("/update")
    public ResponseEntity<Package> updatePackage(@RequestBody Package packages) {
        System.out.println("controllerdaki packages: \n" + packages);
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

    @GetMapping("/username/{findbyuser}")
    public ResponseEntity<Package> findByUser(@PathVariable String findbyuser) {
        Package packages = packageService.findPackagesByUser(findbyuser);
        System.out.println("paketcontrollerdaki paket \n"+packages);
        return ResponseEntity.ok(packages);
    }

    // PAYMENT LOGIC İLK OLARAK SATIN AL'A BASINCA BİZE RANDOM SAYI VERECEK SONRASINDA 2. KEZ SATIN AL'A BASINCA VERİFY İŞLEMİ OLACAK VE SATIN ALMA İŞLEMİ OLACAK.
//    @PostMapping("/add")
//    public ResponseEntity<Package> addPackage(@RequestParam Optional<User> userId) {
//        return ResponseEntity.ok(packageService.purchasePackage(String.valueOf(userId)));
//    }


    // Random sayı almak için endpoint
    @GetMapping("/purchase/random-number")
    public ResponseEntity<Integer> getRandomNumber(@RequestHeader("Authorization") String token) {
        try {
            String username = jwtUtil.extractUsername(token.substring(7));
            int randomNumber = packageService.getRandomNumber(username,token);
            return ResponseEntity.ok(randomNumber);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("/purchase")
    public ResponseEntity<Package> purchasePackage(@RequestHeader("Authorization") String token,@RequestParam int enteredRandomNumber, @RequestParam PackageEnum packageType) {
        try {

            String username = jwtUtil.extractUsername(token.substring(7));
            Package purchasedPackage = packageService.purchasePackage(username,enteredRandomNumber,packageType);
            return ResponseEntity.ok(purchasedPackage);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}

