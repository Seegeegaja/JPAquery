package com.my.japTest.jpaTest.examEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Entertainment {
    @Id
    @Column(name = "enter_id")
    private String enterId;
    private String enterName;
    @OneToMany(mappedBy = "entertainment"
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<GirlGroup> girlGroups = new ArrayList<>();

}
