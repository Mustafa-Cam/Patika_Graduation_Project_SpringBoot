package com.patika.packageservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.patika.packageservice.model.enums.RoleEnum;
import com.patika.packageservice.model.Ad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role = RoleEnum.USER; // Default role

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Ad> advertisements;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // JSON dönüşümünde User alanını görmezden gelmek için
    private List<Package> packages;
}

