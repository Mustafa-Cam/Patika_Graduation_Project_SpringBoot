package com.patika.packageservice.service;

import com.patika.packageservice.Repository.PackagesRepository;
import com.patika.packageservice.Repository.UserCredentialRepository;
import com.patika.packageservice.client.user.service.UserService;
import com.patika.packageservice.model.User;
import com.patika.packageservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.patika.packageservice.model.Package;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PackageService {
    @Autowired
    private PackagesRepository packagesRepository;

    @Autowired
    private UserCredentialRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Transactional
    public Package purchasePackage(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user already has a package
        Package existingPackage = packagesRepository.findByUser(user);

        if (existingPackage != null) {
            // If user already has a package, update it
            existingPackage.setAdCount(existingPackage.getAdCount() + 10); // Increase ad count
            existingPackage.setExpiryDate(existingPackage.getExpiryDate().plusMonths(1)); // Extend expiry date by 1 month

            return packagesRepository.save(existingPackage);
        }

        Package newPackage = new Package();
        newPackage.setUser(user);
        newPackage.setAdCount(10);
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
}
