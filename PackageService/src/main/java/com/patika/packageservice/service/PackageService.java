package com.patika.packageservice.service;

import com.patika.packageservice.Repository.PackagesRepository;
import com.patika.packageservice.Repository.UserCredentialRepository;
//import com.patika.packageservice.client.user.service.UserService;
import com.patika.packageservice.client.payment.PaymentClient;
import com.patika.packageservice.model.User;
import com.patika.packageservice.model.enums.PackageEnum;
import com.patika.packageservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.patika.packageservice.model.Package;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.patika.packageservice.model.enums.PackageEnum.*;

@Service
public class PackageService {

    private int randomNumber;

    @Autowired
    private PackagesRepository packagesRepository;

    @Autowired
    private UserCredentialRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PaymentClient paymentClient;


    // Random sayı almak için metod
    public int getRandomNumber(String username,String token) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        randomNumber = paymentClient.getRandomNumber(token);
        return randomNumber;
    }


    // şimdilik paket alma işlemi burda olsun sonrasınca payment'e taşıyacağım.
    @Transactional
    public Package purchasePackage(String username, int enteredRandomNumber, PackageEnum packageType) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (randomNumber != enteredRandomNumber) {
            throw new RuntimeException("Yanlış sayı girdiniz! Lütfen doğru sayıyı giriniz.");
        }

        // Check if the user already has a package
        Package existingPackage = packagesRepository.findByUser(user);

        // Define ad count increase based on package type
        int adCountIncrease = 0;

        if (existingPackage != null) {
            // If user already has a package, update it
            switch (packageType) {
                case SMALL:
                    adCountIncrease = 10;
                    break;
                case MEDIUM:
                    adCountIncrease = 20;
                    break;
                case BIG:
                    adCountIncrease = 30;
                    break;
            }

            existingPackage.setAdCount(existingPackage.getAdCount() + adCountIncrease); // Increase ad count
            existingPackage.setExpiryDate(existingPackage.getExpiryDate().plusMonths(1)); // Extend expiry date by 1 month

            return packagesRepository.save(existingPackage);
        }

        // New package creation
        Package newPackage = new Package();
        newPackage.setUser(user);
        newPackage.setPackageType(packageType); // Set the package type

        // Define the initial ad count based on the package type
        switch (packageType) {
            case SMALL:
                newPackage.setAdCount(10);
                break;
            case MEDIUM:
                newPackage.setAdCount(20);
                break;
            case BIG:
                newPackage.setAdCount(30);
                break;
        }

        newPackage.setStartDate(LocalDateTime.now());
        newPackage.setExpiryDate(calculateNewExpirationDate());

        return packagesRepository.save(newPackage);
    }


    private LocalDateTime calculateNewExpirationDate() {
        return LocalDateTime.now().plusMonths(1);
    }

    private LocalDateTime calculateNewExpirationDate(User user) {
        Package latestPackage = packagesRepository.findTopByUserOrderByExpiryDateDesc(user)
                .orElse(null);

        LocalDateTime latestExpiration = (latestPackage != null) ? latestPackage.getExpiryDate() : LocalDateTime.now();
        return latestExpiration.isAfter(LocalDateTime.now()) ? latestExpiration.plusMonths(1) : LocalDateTime.now().plusMonths(1);
    }

    public Optional<Package> findById(Long id) {
        return packagesRepository.findById(id);
    }

    public List<Package> findAll() {
        return packagesRepository.findAll();
    }

    public void deleteById(Long id) {
        packagesRepository.deleteById(id);
    }

    public Package savePackage(Package pack) {
//        if (pack.getUser() == null) {
//            throw new IllegalArgumentException("User must not be null");
//        }
        return packagesRepository.save(pack);
    }

    // Bunu feign client ile kullanacağız.
    public boolean canUserPostAd(User user) {
        Package latestPackage = packagesRepository.findTopByUserOrderByExpiryDateDesc(user)
                .orElse(null);

        return (latestPackage != null && latestPackage.getExpiryDate().isAfter(LocalDateTime.now()) && latestPackage.getAdCount() > 0);
    }

    public void decrementAdCount(User user) {
        Package packageToUpdate = packagesRepository.findTopByUserAndExpiryDateAfterOrderByExpiryDateAsc(user, LocalDateTime.now())
                .orElse(null);

        if (packageToUpdate != null && packageToUpdate.getAdCount() > 0) {
            packageToUpdate.setAdCount(packageToUpdate.getAdCount() - 1);
            savePackage(packageToUpdate);
        }
    }

    public Package findPackagesByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(" \n paket servisdeki findPackagesByUser \n"+packagesRepository.findByUser(user));
       return  packagesRepository.findByUser(user);

    }
}
