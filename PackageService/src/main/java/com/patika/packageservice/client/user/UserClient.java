package com.patika.packageservice.client.user;

import com.patika.packageservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(value ="packageuserservice", url ="localhost:8085/api/v1/user")
public interface UserClient {

    @GetMapping("/findbyusername/{username}")
    Optional<User> findByUsername(@PathVariable String username);


}
