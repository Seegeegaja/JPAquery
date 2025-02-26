package com.my.japTest.jpaTest.service;

import com.my.japTest.jpaTest.dto.MemberInfoDto;
import com.my.japTest.jpaTest.examEntity.GirlGroup;
import com.my.japTest.jpaTest.examEntity.IdolMember;
import jakarta.persistence.EntityManager;
import net.bytebuddy.matcher.ElementMatcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QueryServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    QueryService queryService;

    @Test
    @DisplayName("dynamicQuery:원영 찾기")
    void dynamicQuery() {
        List<IdolMember> memberList = queryService.dynamicQuery();
        memberList.stream().forEach(x -> System.out.println("=====" + x.getMemberName()));
    }

    @Test
    @DisplayName("findAllGirlGroup")
    void findAllGirlGroup() {
        List<GirlGroup> girlGroups = queryService.findAllGirlGroup();
        for (GirlGroup girlGroup : girlGroups) {
            System.out.println("GroupName : " + girlGroup.getGirlName());
            System.out.println("---------------------------------------");
            for (IdolMember member : girlGroup.getIdolMembers()) {
                System.out.println("Member : " + member.getMemberId());
            }
        }
    }

    @Test
    @DisplayName("findMemberName")
    void findMemberName() {
        List<String> nameList = queryService.findMemberName();
        nameList.stream().forEach(x -> System.out.println("name : " + x));
    }

    @Test
    @DisplayName("findPinkMemberName")
    void findPinkMemberName() {
        List<String> nameList = queryService.findPinkMemberName();
        nameList.stream().forEach(x -> System.out.println("블랙핑크 멤버 : " + x));
    }

    @Test
    @DisplayName("memberCount")
    void memberCount() {
        System.out.println("=======" + queryService.memberCount());

    }

    @Test
    @DisplayName("getMemberInfoList")
    void getMemberInfoList() {
        System.out.println(queryService.getMemberInfoList());
        List<MemberInfoDto> memberInfoDtoList = queryService.getMemberInfoList();
        memberInfoDtoList.stream().forEach(x-> System.out.println(x));
    }

}