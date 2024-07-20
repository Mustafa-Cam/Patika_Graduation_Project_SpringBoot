package com.patika.userservice.controller;


import com.patika.userservice.dto.UserResponseDto;
import com.patika.userservice.entity.User;
import com.patika.userservice.repository.UserCredentialRepository;
import com.patika.userservice.service.UserService;
import com.patika.userservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public void setUserCredentialRepository(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }

    @GetMapping("/findbyusername/{username}")
    User findByUsername(@PathVariable String username){
        System.out.println("usercontrollerdaki username \n"+username);
       return userService.findByUsername(username);
    }

    @GetMapping()
    User getUser(@RequestHeader("Authorization") String token){
        String username = jwtUtil.extractUsername(token.substring(7));
        return userService.findByUsername(username);
    }




}
