package com.my.japTest.jpaTest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long c_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "p_id")
    private Parent parent;
}
