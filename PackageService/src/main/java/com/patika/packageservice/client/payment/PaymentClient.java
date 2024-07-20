package com.patika.packageservice.client.payment;


import com.patika.packageservice.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(value ="payment-service", url ="localhost:8086/api/v1/payments")
public interface PaymentClient {

    @GetMapping("/randomNumber")
    int getRandomNumber(@RequestHeader("Authorization")String token); // Payment servisinden random sayı almak için GET isteği

    @PostMapping("/payment/verify")
    ResponseEntity<Boolean> verifyPayment(@RequestBody PaymentRequest request);
}

