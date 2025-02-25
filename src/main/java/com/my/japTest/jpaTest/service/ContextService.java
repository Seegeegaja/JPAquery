package com.my.japTest.jpaTest.service;

import com.my.japTest.jpaTest.entity.Member;
import com.my.japTest.jpaTest.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Transactional
public class ContextService {
    @Autowired
    MemberRepository repository;
    @Autowired
    EntityManager em;

    public Member memberInsert(){
        Member member = new Member();
        member.setMemberId("씨게가자");
        member.setName("씨게");
        //persist = 디비에 저장 해주세요
        em.persist(member);
        Member m = em.find(Member.class, "씨게가자");
        return m;
    }
    public void transactionTest(){
        Member hong = new Member();
        hong.setMemberId("홍길동");
        hong.setName("길동");

        Member lee = new Member();
        lee.setMemberId("이순신");
        lee.setName("순신");

        em.persist(hong);
        em.persist(lee);

        em.flush();
    }
    public void saveTest(){
        Member see = repository.findById("씨게가자").get();
        see.setName("싸고");
        repository.save(see);
    }
    public void dirtyCheckingTest(){
        //영속성 컨텍스트에서 데이터 조회
        Member member = em.find(Member.class, "씨게가자");
        member.setName("나는쎄게가자");
    }
    public void  removeMember(){
        Member member = em.find(Member.class, "이순신");
        em.remove(member);
    }
}
