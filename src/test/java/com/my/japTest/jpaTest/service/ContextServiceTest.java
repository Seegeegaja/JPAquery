package com.my.japTest.jpaTest.service;

import com.my.japTest.jpaTest.entity.Member;
import com.my.japTest.jpaTest.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContextServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    ContextService contextService;
    @Autowired
    MemberRepository repository;

    @Test
    @DisplayName("1차 캐시 테스트")
    void firstCash() {
        Member member = contextService.memberInsert();
        System.out.println(member);
    }

    @Test
    @DisplayName("데이터 영속성 보장 테스트")
    void persistContextText() {
        Member a = em.find(Member.class, "씨게가자");
        Member b = em.find(Member.class, "씨게가자");
        System.out.println(a.equals(b));
        Member c = repository.findById("씨게가자").get();
        Member d = repository.findById("씨게가자").get();
        System.out.println(c.equals(d));
        System.out.println(a.equals(d));
    }

    @Test
    @DisplayName("transaction Commit 테스트")
    void transactionTest() {
        contextService.transactionTest();
    }

    @Test
    @DisplayName("Repo-dirtyCheckingTest")
    void dirtyCheckingRepoTest() {
        contextService.saveTest();
    }

    @Test
    @DisplayName("dirtyCheckingTest")
    void dirtyCheckingTest() {
        contextService.dirtyCheckingTest();
    }

    @Test
    @DisplayName("memberRemoveTest")
    void memberRemoveTest(){
        contextService.removeMember();
    }

}