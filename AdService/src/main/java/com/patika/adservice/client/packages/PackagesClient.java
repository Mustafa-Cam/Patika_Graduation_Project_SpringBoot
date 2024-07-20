package com.patika.adservice.client.packages;


import com.patika.adservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.patika.adservice.model.Package;

import java.util.List;

@FeignClient(value = "packageservice", url = "localhost:8084/api/v1/packages")
public interface PackagesClient {

    @PostMapping(value = "/update")
    ResponseEntity<Package> updatePackage(@RequestBody Package packages);

    @GetMapping("/userid/{userId}")
    ResponseEntity<List<Package>> getPackagesByUserId(@PathVariable User userId);

    @GetMapping
    ResponseEntity<List<Package>> getPackages(@AuthenticationPrincipal UserDetails userDetails);

    @GetMapping("/username/{findbyuser}")
    Package findpackageByUser(@PathVariable String findbyuser);


}
