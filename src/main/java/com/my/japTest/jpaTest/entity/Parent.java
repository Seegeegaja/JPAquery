package com.my.japTest.jpaTest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long p_id;

    @OneToMany(mappedBy = "parent"
            ,fetch = FetchType.EAGER
            ,cascade = {CascadeType.PERSIST , CascadeType.REMOVE})
     private List<Child> childList = new ArrayList<>();
}
