package com.my.japTest.jpaTest.service;

import com.my.japTest.jpaTest.dto.MemberInfoDto;
import com.my.japTest.jpaTest.examEntity.GirlGroup;
import com.my.japTest.jpaTest.examEntity.IdolMember;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class QueryService {
    @Autowired
    EntityManager em;
    //동적 쿼리 생성(ive에서 장원영이만 검색)
    public List<IdolMember> dynamicQuery() {
        String sql = "SELECT idol FROM IdolMember idol " +
                "WHERE idol.memberName = :name ";
        //타입드퀘리
        TypedQuery<IdolMember> query = em.createQuery(sql, IdolMember.class).setParameter("name", "원영");
        List<IdolMember> memberList = query.getResultList();
        return memberList;
    }
    public List<GirlGroup> findAllGirlGroup() {
        String sql = "SELECT g FROM GirlGroup AS g";
        Query query = em.createQuery(sql);
        List<GirlGroup> girlGroups = query.getResultList();
        return girlGroups;
    }
    //GirlGroup에서 멤버 name만 검색 해서 찾아오기
    public List<String> findMemberName(){
        String sql = " SELECT m.memberName FROM IdolMember AS m";
        TypedQuery<String> query = em.createQuery(sql, String.class);
        List<String> nameList = query.getResultList();
        return nameList;
    }

    //멤버중에 블랙 핑크 소속의 이름만
    public List<String> findPinkMemberName() {
        String sql = "SELECT m.memberName From IdolMember m " +
                "WHERE m.girlGroup.girlName = :groupName ";
        TypedQuery<String> query = em.createQuery(sql, String.class).setParameter("groupName", "블핑");
        return query.getResultList();
    }

    public Long memberCount() {
        String sql = "SELECT COUNT(m) FROM IdolMember m " + "WHERE m.girlGroup.girlName = :groupName";
        Query query = em.createQuery(sql);
        query.setParameter("groupName", "아이브");
        Long result = (Long) query.getSingleResult();
        return result;
    }
    //DTO(MemberInfo) 로 받기
    //name groupName enterName

    public List<MemberInfoDto> getMemberInfoList() {
        String sql = "SELECT NEW " + "com.my.japTest.jpaTest.dto.MemberInfoDto" +
                "(m.memberName , m.girlGroup.girlName , m.girlGroup.entertainment.enterName)" +
                " FROM IdolMember AS m ";
        TypedQuery<MemberInfoDto> query = em.createQuery(sql, MemberInfoDto.class);
        return query.getResultList();
    }
}
