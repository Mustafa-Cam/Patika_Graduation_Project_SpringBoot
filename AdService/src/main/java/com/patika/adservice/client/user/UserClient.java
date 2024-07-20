package com.patika.adservice.client.user;

import com.patika.adservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(value ="userservice", url ="localhost:8085/api/v1/user")
public interface UserClient {

    @GetMapping("/findbyusername/{username}")
    Optional<User> findByUsername(@PathVariable String username);

}
