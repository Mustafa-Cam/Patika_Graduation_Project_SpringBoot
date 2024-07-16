package com.patika.adservice.repository;


import com.patika.adservice.model.Ad;
import com.patika.adservice.model.User;
import com.patika.adservice.model.enums.AdStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {
    List<Ad> findByUserAndStatus(User user, AdStatus status);
}


