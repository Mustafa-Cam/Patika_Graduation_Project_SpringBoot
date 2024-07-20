package com.patika.userservice.dto;

import com.patika.userservice.entity.enums.RoleEnum;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class UserResponseDto {
    private int id; // id alanı eklendi
    private String username;
    private String email;

}
