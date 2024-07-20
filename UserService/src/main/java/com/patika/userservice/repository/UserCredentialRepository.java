package com.patika.userservice.repository;


import com.patika.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
