package com.patika.userservice.service;


import com.patika.userservice.converter.UserMapper;
import com.patika.userservice.dto.UserResponseDto;
import com.patika.userservice.entity.User;
import com.patika.userservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCredentialRepository repository;

    public User findByUsername(String username) {
          Optional<User> user = repository.findByUsername(username);
            System.out.println("user servis uygulamasındaki findbyusername den gelen user \n"+user);
          if(user.isEmpty()) {
              throw new RuntimeException("User not found");
          }

        return user.get();
    }

}
