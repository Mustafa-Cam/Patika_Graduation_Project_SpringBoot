package com.javatechie.dto;

import com.javatechie.entity.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String username;
    private String password;
    private String email;
    private RoleEnum role;

    public AuthRequest(String user, String password) {
    }
}
