package com.patika.paymentservice.service;


import com.patika.paymentservice.client.packages.PackageClient;
import com.patika.paymentservice.util.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PackageClient packageClient;

//    @Autowired
//    private UserClient userRepository;

//    @Autowired
//    private ProductData productData;

    private int randomNumber;

    private final PaymentProcessor paymentProcessor;

    public PaymentService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

//    @Transactional
//    public boolean processPaymentAndVerify(int userInput, int randomNumber) {
//        return paymentProcessor.processPayment(userInput, randomNumber);
//    }

//    private boolean processPayment(User user) {
//        // Ödeme işlemini burada simüle ediyoruz. Gerçek bir ödeme servisine bağlanabilirsiniz.
//
//        return true; // Ödeme başarılı.
//    }

//    private LocalDateTime calculateNewExpirationDate(User user) {
//        // Kullanıcının mevcut paketlerinin son geçerlilik tarihini bul
//        LocalDateTime latestExpiryDate = user.getPackages().stream()
//                .map(Package::getExpiryDate)
//                .max(LocalDateTime::compareTo)
//                .orElse(LocalDateTime.now());
//
//        // Yeni geçerlilik tarihini belirle
//        return latestExpiryDate.isAfter(LocalDateTime.now()) ? latestExpiryDate.plusDays(30) : LocalDateTime.now().plusDays(30);
//    }

    public int GenerateNumber(){
        return paymentProcessor.generateRandomNumber();
    }


//    public Optional<Product> findProductById(String productId) {
//        return productData.getProducts().stream()
//                .filter(product -> product.getId().equals(productId))
//                .findFirst();
//    }
//
//    public boolean processPayment(User user, Product product) {
//        // Burada gerçek ödeme işlemi gerçekleştirilir
//        // Ödeme işlemini sonra yapacağım.
//        return true;
//    }

}
