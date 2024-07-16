package com.patika.paymentservice.controller;


import com.patika.paymentservice.client.user.service.UserService;
import com.patika.paymentservice.model.Product;
import com.patika.paymentservice.model.User;
import com.patika.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.patika.paymentservice.model.Package;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    @PostMapping("/purchasePackage")
    public Package purchasePackage(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return paymentService.purchasePackage(user);
    }

    @PostMapping("/buyProduct")
    public ResponseEntity<String> buyProduct(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String productId) {
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = paymentService.findProductById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Ödeme işlemi senkron olarak gerçekleştiriliyor
        boolean paymentSuccessful = paymentService.processPayment(user, product);
        if (paymentSuccessful) {
            // Stok güncelleme
            product.setStock(product.getStock() - 10);
            return ResponseEntity.ok("Product purchased successfully");
        } else {
            return ResponseEntity.status(500).body("Payment failed");
        }
    }

}

