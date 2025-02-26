package com.my.japTest.jpaTest.examService;

import com.my.japTest.jpaTest.entity.Member;
import com.my.japTest.jpaTest.entity.Team;
import com.my.japTest.jpaTest.examEntity.Entertainment;
import com.my.japTest.jpaTest.examEntity.GirlGroup;
import com.my.japTest.jpaTest.examEntity.IdolMember;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnterServiceTest {
    @Autowired
    EnterService service;
    @Autowired
    EntityManager em;

    //    @Test
//    @DisplayName("insertMember")
//    void insertData() {
//        service.insertData();
//
//    }
    @Test
    @DisplayName("저장")
    void insert() {
        service.insert();
    }

    //    @Test
//    @DisplayName("뉴진스의 멤버들 이름 출력하기")
//    void displayNewJeansMember(){
//        Team newJeans = em.find(Team.class, "newJeans");
//        List<Member> members = newJeans.getMemberList();
//        for (Member member : members) {
//            System.out.println("================= "+member.getName());
//        }
//    }
//}
    @Test
    @DisplayName("출력")
    void print() {
        IdolMember jisu = em.find(IdolMember.class, "지수");
        System.out.println(jisu.getMemberId() + "는 " + jisu.getGirlGroup().getGirlId() + "라는 그룹이고" + jisu.getGirlGroup().getEntertainment().getEnterId() + "엔터테이먼트에서소속입니다");
    }

    @Test
    @DisplayName("출력2")
    void print_2() {
        Entertainment entertainment = em.find(Entertainment.class, "YG");
        List<GirlGroup> girlGroups = entertainment.getGirlGroups();
        Entertainment entertainment1 = em.find(Entertainment.class, "starship");
        List<GirlGroup> girlGroups1 = entertainment1.getGirlGroups();
        for (GirlGroup girlGroup : girlGroups) {
            System.out.println(girlGroup.getGirlName());
            List<IdolMember> idolMembers = girlGroup.getIdolMembers();
            for (IdolMember idolMember : idolMembers) {
                System.out.println("==============" + idolMember.getMemberId());
            }
        }

        for (GirlGroup girlGroup : girlGroups1) {
            System.out.println(girlGroup.getGirlId());
            List<IdolMember> idolMembers = girlGroup.getIdolMembers();
            for (IdolMember idolMember : idolMembers) {
                System.out.println("===============" + idolMember.getMemberId());
            }
        }
    }

    @Test
    @DisplayName("블렉핑크")
    void searchGroupMember() {
        String findGroup = "blackPink";
        service.searchGroupMember(findGroup);
        String findGroups = "ive";
        service.searchGroupMember(findGroups);
    }

    @Test
    @DisplayName("추가")
    void insertMembers() {
        service.insertMembers();
    }
}