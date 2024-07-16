package com.patika.adservice.model;


import jakarta.persistence.*;
import lombok.Data;
import com.patika.adservice.model.enums.AdStatus;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
//    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
    private AdStatus status = AdStatus.IN_REVIEW;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")//nullable = false
    private User user;

//    @Column(nullable = false)
    private LocalDateTime createdAt;

//    @Column(nullable = false)
    private LocalDateTime updatedAt;

//    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}

