package com.patika.adservice.client.packages.service;

import com.patika.adservice.client.packages.PackagesClient;
import com.patika.adservice.model.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.patika.adservice.model.Package;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class PackageService {

    private final PackagesClient packagesClient;


    public List<Package> findPackagesByUser(String user) {
        return packagesClient.findpackageByUser(user);
    }

    public ResponseEntity<Package> savePackage(Package pack) {
        return packagesClient.createPackage(pack);
    }

    public boolean canUserPostAd(String username) {
        List<Package> packages = findPackagesByUser(username);
        return packages.stream()
                .anyMatch(pack -> pack.getExpiryDate().isAfter(LocalDateTime.now()) && pack.getAdCount() > 0);
    }

    public void decrementAdCount(String user) {
        List<Package> packages = findPackagesByUser(user);
        for (Package pack : packages) {
            if (pack.getExpiryDate().isAfter(LocalDateTime.now()) && pack.getAdCount() > 0) {
                pack.setAdCount(pack.getAdCount() - 1);
                savePackage(pack);
                break;
            }
        }
    }
}
