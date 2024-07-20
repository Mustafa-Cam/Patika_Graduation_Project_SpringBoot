package com.javatechie.controller;

import com.javatechie.entity.User;
import com.javatechie.repository.UserCredentialRepository;
import com.javatechie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/findbyusername/{username}")
    Optional<User> findByUsername(@PathVariable String username){
        System.out.println("usercontrollerdaki username \n"+username);
       return userService.findByUsername(username);
    }

    @Autowired
    public void setUserCredentialRepository(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }
}
