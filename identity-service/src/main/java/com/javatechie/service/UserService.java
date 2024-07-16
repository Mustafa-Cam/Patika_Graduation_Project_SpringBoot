package com.javatechie.service;

import com.javatechie.entity.User;
import com.javatechie.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private JwtService jwtService;


    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
