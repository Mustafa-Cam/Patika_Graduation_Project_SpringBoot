package com.patika.userservice.converter;

import com.patika.userservice.dto.UserResponseDto;
import com.patika.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserResponseDto toUserDto(User user){
        UserResponseDto userDto = new UserResponseDto();
        userDto.setId(user.getId()); // id alanını ayarlayın
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());

        return userDto;
    }
}
