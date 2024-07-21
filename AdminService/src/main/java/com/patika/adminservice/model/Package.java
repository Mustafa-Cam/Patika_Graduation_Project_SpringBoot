package com.patika.adminservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.patika.adminservice.model.enums.PackageEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private PackageEnum packageType;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int adCount;
    private LocalDateTime startDate;
    private LocalDateTime expiryDate;


}
