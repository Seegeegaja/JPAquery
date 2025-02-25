package com.my.japTest.jpaTest.service;

import com.my.japTest.jpaTest.entity.Member;
import com.my.japTest.jpaTest.entity.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RelationServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    RelationService relationService;

    //    @Test
//    @DisplayName("insertMemberAndTeam")
//    void insertMemberAndTeam(){
//        relationService.insertMemberAndTeam();
//    }
//    @Test
//    @DisplayName("아이디가 장원영의 팀의 이름 찾기 테스트")
//    void findTeamId(){
//        Member jang = em.find(Member.class, "장원영");
//        Team team = em.find(Team.class, jang.getTeamId());
//        System.out.println("팀이름 : "+team.getTeamName());
//    }
    @Test
    @DisplayName("단방향 연관관계 설정후 저장하기")
    void insertMemberAndTeamRelation() {
        relationService.insertMemberAndTeamRelation();
    }

    @Test
    @DisplayName("아이디가 장원영의 팀의 이름 찾기 테스트")
    void findTeamId() {
        Member jang = em.find(Member.class, "장원영");
        System.out.println("팀이름 : " + jang.getTeam().getTeamName());
    }

    @Test
    @DisplayName("양방향 저장")
    void insertBothDirection() {
        relationService.insertBothDirection();
    }

    @Test
    @DisplayName("뉴진스의 멤버들 이름 출력하기")
    void displayNewJeansMember(){
        Team newJeans = em.find(Team.class, "newJeans");
        List<Member> members = newJeans.getMemberList();
        for (Member member : members) {
            System.out.println("================= "+member.getName());
        }
    }
}