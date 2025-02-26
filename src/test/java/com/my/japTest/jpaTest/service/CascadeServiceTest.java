package com.my.japTest.jpaTest.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CascadeServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    CascadeService service;

    @Test
    @DisplayName("cascade 설정 이전에 저장 테스트")
    void insertData() {
        service.insertData();
    }

    @Test
    @DisplayName("cascade 설정 이후 저장 테스트")
    void cascadeTest() {
        service.cascadeInsert();
    }

    @Test
    @DisplayName("삭제")
    void cascadeRemoveTest() {
        service.cascadeRemoveTest();
    }
}