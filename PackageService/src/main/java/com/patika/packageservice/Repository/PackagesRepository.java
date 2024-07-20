package com.patika.packageservice.Repository;

import com.patika.packageservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.patika.packageservice.model.Package;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PackagesRepository extends JpaRepository<Package, Long> {

    Package findByUser(User user);

    List<Package> findByUserOrderByExpiryDateDesc(User user);

    Optional<Package> findTopByUserOrderByExpiryDateDesc(User user);

    Optional<Package> findTopByUserAndExpiryDateAfterOrderByExpiryDateAsc(User user, LocalDateTime expiryDate);
}

