package com.patika.paymentservice.client.user;

import com.patika.paymentservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(value ="userservice", url ="localhost:8082/api/v1/user")
public interface UserClient {

    @GetMapping("/findbyusername/{username}")
    Optional<User> findByUsername(@PathVariable String username);


}
