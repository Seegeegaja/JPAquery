package com.my.japTest.jpaTest.entity;

import com.my.japTest.jpaTest.constant.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String email;
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(length = 50)
    private String likeColor;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
