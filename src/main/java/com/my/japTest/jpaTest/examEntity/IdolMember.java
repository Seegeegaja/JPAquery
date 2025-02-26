package com.my.japTest.jpaTest.examEntity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class IdolMember {
    @Id
    @Column(name = "member_id")
    private String memberId;
    private String memberName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "girl_id")
    private GirlGroup girlGroup;

}
