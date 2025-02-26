package com.my.japTest.jpaTest.examEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class GirlGroup {
    @Id
    @Column(name = "girl_id")
    private String girlId;
    private String girlName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enter_id")
    private Entertainment entertainment;
    @OneToMany(mappedBy = "girlGroup"
            , fetch = FetchType.EAGER
            ,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<IdolMember> idolMembers = new ArrayList<>();
}
