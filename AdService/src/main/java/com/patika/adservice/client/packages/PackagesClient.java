package com.patika.adservice.client.packages;


import com.patika.adservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.patika.adservice.model.Package;

import java.util.List;

@FeignClient(value = "packageservice", url = "localhost:8084/api/v1/packages")
public interface PackagesClient {


    @PostMapping
    ResponseEntity<Package> createPackage(@RequestBody Package packages);

    @GetMapping("/{userId}")
    ResponseEntity<List<Package>> getPackagesByUserId(@PathVariable User userId);

    @GetMapping
    ResponseEntity<List<Package>> getPackages(@AuthenticationPrincipal UserDetails userDetails);

    @GetMapping("/{findbyuser}")
    List<Package> findpackageByUser(@PathVariable String findbyuser);


}
