package com.my.japTest.jpaTest.service;

import com.my.japTest.jpaTest.entity.Member;
import com.my.japTest.jpaTest.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RelationService {
    @Autowired
    EntityManager em;

    //    public void insertMemberAndTeam(){
//        Team ive = new Team();
//        ive.setTeamId("ive");
//        ive.setTeamName("아이브");
//        em.persist(ive);
//
//        Member jang = new Member();
//        jang.setMemberId("장원영");
//        jang.setName("원영");
//        jang.setTeam(ive);
//        em.persist(jang);
//
//    }
    public void insertMemberAndTeamRelation() {
        //단방향 멥핑후 저장하기
        //팀 생성후 저장하기
        Team ive = new Team();
        ive.setTeamId("ive");
        ive.setTeamName("ive");
        em.persist(ive);
        //멤버 생성 후 저장 (장원영)
        Member jang = new Member();
        jang.setMemberId("장원영");
        jang.setName("원영이");
        jang.setTeam(ive);
        em.persist(jang);
    }
    public void insertBothDirection(){
        //양방향으로 저장하기
        //뉴진스 팀 생성
        Team newJeans = new Team();
        newJeans.setTeamId("newJeans");
        newJeans.setTeamName("뉴진스");
        em.persist(newJeans);
        //하니 다니엘 생성 저장

        Member mem1 = new Member();
        mem1.setMemberId("하니");
        mem1.setName("Hani");
        mem1.setTeam(newJeans);
        //팀 리스트에 추가
        newJeans.getMemberList().add(mem1);
        em.persist(mem1);


        Member mem2 = new Member();
        mem2.setMemberId("다니엘");
        mem2.setName("Daniel");
        mem2.setTeam(newJeans);
        newJeans.getMemberList().add(mem2);
        em.persist(mem2);

    }

}
