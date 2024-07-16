package com.patika.paymentservice.service;


import com.patika.paymentservice.Product.ProductData;
import com.patika.paymentservice.model.Package;
import com.patika.paymentservice.model.Product;
import com.patika.paymentservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PackagesRepository packageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductData productData;

    @Transactional
    public Package purchasePackage(User user) {
        // Ödeme işlemi burada yapılır. Ödeme işlemi başarılı olursa, paket kullanıcıya eklenir.
        if (!processPayment(user)) {
            throw new RuntimeException("Payment failed");
        }
        Package newPackage = new Package();
        newPackage.setUser(user);
        newPackage.setAdCount(10);
        newPackage.setExpiryDate(calculateNewExpirationDate(user));
        return packageRepository.save(newPackage);
    }


    private boolean processPayment(User user) {
        // Ödeme işlemini burada simüle ediyoruz. Gerçek bir ödeme servisine bağlanabilirsiniz.

        return true; // Ödeme başarılı.
    }

    private LocalDateTime calculateNewExpirationDate(User user) {
        // Kullanıcının mevcut paketlerinin son geçerlilik tarihini bul
        LocalDateTime latestExpiryDate = user.getPackages().stream()
                .map(Package::getExpiryDate)
                .max(LocalDateTime::compareTo)
                .orElse(LocalDateTime.now());

        // Yeni geçerlilik tarihini belirle
        return latestExpiryDate.isAfter(LocalDateTime.now()) ? latestExpiryDate.plusDays(30) : LocalDateTime.now().plusDays(30);
    }


    public Optional<Product> findProductById(String productId) {
        return productData.getProducts().stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
    }

    public boolean processPayment(User user, Product product) {
        // Burada gerçek ödeme işlemi gerçekleştirilir
        // Ödeme işlemini sonra yapacağım.
        return true;
    }

}
