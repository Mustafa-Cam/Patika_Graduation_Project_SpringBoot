package com.patika.adservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Burda manytoone ile user tablomuzda belirttiğimiz alanlar ile ilişki kuruyoruz.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int adCount;

    private LocalDateTime startDate;
    private LocalDateTime expiryDate;

    // Getters and Setters
}

