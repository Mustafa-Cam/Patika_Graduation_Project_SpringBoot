package com.patika.adservice.repository;


import com.patika.adservice.model.Ad;
import com.patika.adservice.model.Product;
import com.patika.adservice.model.User;
import com.patika.adservice.model.enums.AdStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Long> {
    List<Ad> findByUserAndStatus(User user, AdStatus status);

    Optional<List<Ad>> findByUser_Username(String userName);

    Optional<Ad> findByUserAndProduct(User user, Product product);

    Optional<Ad> findByProductId(int productId);

}


