package com.patika.packageservice.Repository;

import com.patika.packageservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
