package com.patika.adservice.client.packages.service;

import com.patika.adservice.client.packages.PackagesClient;
import com.patika.adservice.client.user.UserClient;
import com.patika.adservice.model.User;
import com.patika.adservice.repository.PackagesRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.patika.adservice.model.Package;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class PackageService {

    private final PackagesClient packagesClient;
    private final UserClient userClient;

    @Autowired
    private PackagesRepository packagesRepository;


    public Package findPackagesByUser(String user) {
        System.out.println("findPackagesByUser is worked"+user);
        return packagesClient.findpackageByUser(user);
    }

    public void updatePackage(Package pack) {
        System.out.println("pack in savepackage service \n"+pack);
        if (pack.getUser() == null) {
            throw new IllegalArgumentException("User must not be null");
        }
        packagesClient.updatePackage(pack);
    }

    public boolean canUserPostAd(String username) {
        Package userPackage = findPackagesByUser(username);
        System.out.println("clientPackage: \n" + userPackage);
        System.out.println(userPackage != null && userPackage.getExpiryDate().isAfter(LocalDateTime.now()) && userPackage.getAdCount() > 0);
        return userPackage != null && userPackage.getExpiryDate().isAfter(LocalDateTime.now()) && userPackage.getAdCount() > 0;
    }

    public void decrementAdCount(String username) {
        Package userPackage = findPackagesByUser(username);
        Optional<User> optionalUser = userClient.findByUsername(username);
        System.out.println("decrementAdCount is worked" +optionalUser);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (userPackage != null && userPackage.getExpiryDate().isAfter(LocalDateTime.now()) && userPackage.getAdCount() > 0) {
                userPackage.setAdCount(userPackage.getAdCount() - 1);
//                userPackage.setUser(user);
                System.out.println("userpackage in decrementAdCount \n"+userPackage);
                updatePackage(userPackage);
//                packagesRepository.save(userPackage);
            }
        } else {
            // Handle case where user is not found
            System.out.println("User not found for username: " + username);
        }
    }


}
