package com.patika.adminservice.controller;


import com.patika.adminservice.client.AdClient;
import com.patika.adminservice.dto.response.AdResponse;
import com.patika.adminservice.model.Ad;
import com.patika.adminservice.model.enums.AdStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Future;


@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdClient adClient;

    @PostMapping("/makeActive/{id}")
        public Future<ResponseEntity<Ad>> makeActive(@PathVariable int id,@RequestParam AdStatus status) {
          Future<ResponseEntity<Ad>> ad = adClient.updateAdStatus(id,status);
          System.out.println("ad in admincontroller "+ad);
            return ad;
    }


}

