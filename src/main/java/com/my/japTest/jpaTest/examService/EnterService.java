package com.my.japTest.jpaTest.examService;

import com.my.japTest.jpaTest.examEntity.Entertainment;
import com.my.japTest.jpaTest.examEntity.GirlGroup;
import com.my.japTest.jpaTest.examEntity.IdolMember;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EnterService {
    @Autowired
    EntityManager em;

    //1.지수가 속한 걸그룹 이름과 엔터 이름
    public void search() {
        IdolMember jisu = em.find(IdolMember.class, "지수");
        System.out.println(" 지수의 그룹이름은 : " + jisu.getGirlGroup().getGirlName());
        System.out.println("회사 이름은 : " + jisu.getGirlGroup().getEntertainment().getEnterName());

    }
    //cascade가 아닌것이여
    public void insertData(){
        Entertainment entertainment1 = new Entertainment();
        entertainment1.setEnterId("starship");
        entertainment1.setEnterName("스타쉽");
        em.persist(entertainment1);
        Entertainment entertainment2 = new Entertainment();
        entertainment2.setEnterId("YG");
        entertainment2.setEnterName("와이지");
        em.persist(entertainment2);

        GirlGroup girlGroup1 = new GirlGroup();
        girlGroup1.setGirlId("ive");
        girlGroup1.setGirlName("아이브");
        girlGroup1.setEntertainment(entertainment1);
        entertainment1.getGirlGroups().add(girlGroup1);
        em.persist(girlGroup1);
        GirlGroup girlGroup2 = new GirlGroup();
        girlGroup2.setGirlId("blackPink");
        girlGroup2.setGirlName("블핑");
        girlGroup2.setEntertainment(entertainment2);
        entertainment2.getGirlGroups().add(girlGroup2);
        em.persist(girlGroup2);

        IdolMember ahn = new IdolMember();
        ahn.setMemberId("안유진");
        ahn.setMemberName("유진");
        girlGroup1.getIdolMembers().add(ahn);
        ahn.setGirlGroup(girlGroup1);
        em.persist(ahn);
        IdolMember jang = new IdolMember();
        jang.setMemberId("장원영");
        jang.setMemberName("원영");
        jang.setGirlGroup(girlGroup1);
        girlGroup1.getIdolMembers().add(jang);
        em.persist(jang);

        IdolMember jeni = new IdolMember();
        jeni.setMemberId("제니");
        jeni.setMemberName("째니");
        jeni.setGirlGroup(girlGroup2);
        girlGroup2.getIdolMembers().add(jeni);
        em.persist(jeni);
        IdolMember jisu = new IdolMember();
        jisu.setMemberId("지수");
        jisu.setMemberName("지수다");
        jisu.setGirlGroup(girlGroup2);
        girlGroup2.getIdolMembers().add(jisu);
        em.persist(jisu);

    }
    //cascade인것이여
    public void insert() {
        IdolMember ahn = new IdolMember();
        IdolMember jang = new IdolMember();
        IdolMember jeni = new IdolMember();
        IdolMember jisu = new IdolMember();

        GirlGroup ive = new GirlGroup();
        GirlGroup blackPink = new GirlGroup();

        Entertainment starship = new Entertainment();
        Entertainment yg = new Entertainment();

        ahn.setMemberId("안유진");
        ahn.setMemberName("유진");
        ahn.setGirlGroup(ive);
        ive.getIdolMembers().add(ahn);


        jang.setMemberId("장원영");
        jang.setMemberName("원영");
        jang.setGirlGroup(ive);
        ive.getIdolMembers().add(jang);

        jeni.setMemberId("제니");
        jeni.setMemberName("쩨니");
        jeni.setGirlGroup(blackPink);
        blackPink.getIdolMembers().add(jeni);

        jisu.setMemberId("지수");
        jisu.setMemberName("찌쑤");
        jisu.setGirlGroup(blackPink);
        blackPink.getIdolMembers().add(jisu);

        ive.setGirlId("ive");
        ive.setGirlName("아이브");
        ive.setEntertainment(starship);
        starship.getGirlGroups().add(ive);

        blackPink.setGirlId("blackPink");
        blackPink.setGirlName("블핑");
        blackPink.setEntertainment(yg);
        starship.getGirlGroups().add(blackPink);

        starship.setEnterId("starship");
        starship.setEnterName("스타쉽");

        yg.setEnterId("YG");
        yg.setEnterName("와이지");

        em.persist(starship);
        em.persist(yg);

    }
    public void searchGroupMember(String groupName){
        GirlGroup girlGroup = em.find(GirlGroup.class, groupName);
        for (IdolMember idolMember : girlGroup.getIdolMembers()) {
            System.out.println("멤버 아이디 : " + idolMember.getMemberId());
            System.out.println("멤버 이름은 : " + idolMember.getMemberName());

        }

    }

    //ive에 가을 추가
    public void insertMembers() {
        IdolMember gaul = new IdolMember();
        GirlGroup girlGroup = em.find(GirlGroup.class, "ive");

        gaul.setMemberId("가을");
        gaul.setMemberName("까을");

        girlGroup.getIdolMembers().add(gaul);
        gaul.setGirlGroup(girlGroup);

    }


}
