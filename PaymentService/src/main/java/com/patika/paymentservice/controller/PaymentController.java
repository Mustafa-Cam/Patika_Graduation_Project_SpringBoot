package com.patika.paymentservice.controller;


import com.patika.paymentservice.service.PaymentService;
import com.patika.paymentservice.util.JwtUtil;
import com.patika.paymentservice.util.PaymentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

//    @Autowired
//    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/randomNumber")
    public int getRandomNumber(@RequestHeader("Authorization") String token ){
        return paymentService.GenerateNumber();
    }


//    @PostMapping("/purchasePackage")
//    public Package purchasePackage(@RequestHeader("Authorization") String token) {
//        String username = jwtUtil.extractUsername(token.substring(7));
//
//        User user = userService.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        return paymentService.processPaymentAndVerify(user);
//    }




//    @PostMapping("/buyProduct")
//    public ResponseEntity<String> buyProduct(@RequestHeader("Authorization") String token, @RequestParam String productId) {
//        User user = userService.findByUsername(userDetails.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        Product product = paymentService.findProductById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        // Ödeme işlemi senkron olarak gerçekleştiriliyor
//        boolean paymentSuccessful = paymentService.processPayment(user, product);
//        if (paymentSuccessful) {
//            // Stok güncelleme
//            product.setStock(product.getStock() - 10);
//            return ResponseEntity.ok("Product purchased successfully");
//        } else {
//            return ResponseEntity.status(500).body("Payment failed");
//        }
//    }

}

