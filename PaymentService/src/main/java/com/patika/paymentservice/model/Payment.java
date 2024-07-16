package com.patika.paymentservice.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int adCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
