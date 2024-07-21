package com.javatechie.service;

import com.javatechie.config.CustomUserDetails;
import com.javatechie.config.CustomUserDetailsService;
import com.javatechie.dto.AuthRequest;
import com.javatechie.entity.User;
import com.javatechie.entity.enums.RoleEnum;
import com.javatechie.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    public String saveUser(AuthRequest credential) {
        // Kullanıcı adı ile mevcut kullanıcıyı kontrol et
        Optional<User> existingUser = repository.findByUsername(credential.getUsername());

        if (existingUser.isPresent()) {
            // Eğer kullanıcı mevcutsa, uygun bir hata mesajı döndür
            return "Username already exists";
        }

        // Yeni kullanıcı oluştur
        User user = new User();
        user.setUsername(credential.getUsername());
        user.setPassword(passwordEncoder.encode(credential.getPassword()));
        user.setEmail(credential.getEmail()); // Email eklenmiş
        user.setRole(credential.getRole() != null ? credential.getRole() : RoleEnum.USER);

        // Kullanıcıyı kaydet
        repository.save(user);

        return "User added to the system";
    }


    public String generateToken(String username) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return jwtService.generateToken(userDetails);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public String refreshToken(String username) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return jwtService.generateToken(userDetails);}

}
