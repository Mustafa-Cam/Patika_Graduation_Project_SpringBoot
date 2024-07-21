package com.patika.adminservice.client;

import com.patika.adminservice.model.Ad;
import com.patika.adminservice.model.enums.AdStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.Future;

@FeignClient(value ="adserviceadmin", url ="localhost:8083/api/v1/ads")

public interface AdClient {

    @Async
    @PostMapping("/{id}/statusAdmin")
    Future<ResponseEntity<Ad>> updateAdStatusForAdmin(@PathVariable long id, @RequestParam AdStatus status);

}
