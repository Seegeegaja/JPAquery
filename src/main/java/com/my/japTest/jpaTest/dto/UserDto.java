package com.my.japTest.jpaTest.dto;

import com.my.japTest.jpaTest.constant.Gender;
import com.my.japTest.jpaTest.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor

public class UserDto {
    private Long id;
    private String name;
    private Gender gender;
    private LocalDateTime createdAt;

    public static UserDto fromEntity(Users users){
        return new UserDto(
                users.getId(),
                users.getName(),
                users.getGender(),
                users.getCreatedAt()
        );
    }
}
