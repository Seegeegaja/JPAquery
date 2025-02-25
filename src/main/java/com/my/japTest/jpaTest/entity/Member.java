package com.my.japTest.jpaTest.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Member {
    @Id
    @Column(nullable = false, unique = true)
    private String memberId;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

}
