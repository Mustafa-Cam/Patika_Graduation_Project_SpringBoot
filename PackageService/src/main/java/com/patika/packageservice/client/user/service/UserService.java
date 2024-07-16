package com.patika.packageservice.client.user.service;



import com.patika.packageservice.client.user.UserClient;
import com.patika.packageservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserClient userClient;


    public Optional<User> findByUsername(String username) {
        return userClient.findByUsername(username);
    }

}

