package com.my.japTest.jpaTest.service;

import com.my.japTest.jpaTest.entity.Child;
import com.my.japTest.jpaTest.entity.Parent;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CascadeService {
    @Autowired
    EntityManager em;
    public void cascadeInsert(){
        Child child_1 = new Child();
        Child child_2 = new Child();

        Parent parent = new Parent();
        child_1.setParent(parent);
        child_2.setParent(parent);
        parent.getChildList().add(child_1);
        parent.getChildList().add(child_2);
        //부모만 저장
        em.persist(parent);
    }

    //부모객체 1개에 자식객체 2개를 담어서 저장

    public void insertData() {
        Parent parent = new Parent();
        em.persist(parent);

        Child c1 = new Child();
        c1.setParent(parent);
        parent.getChildList().add(c1);
        em.persist(c1);

        Child c2 = new Child();
        c2.setParent(parent);
        parent.getChildList().add(c2);
        em.persist(c2);
    }
    public void cascadeRemoveTest() {
        Parent parent = em.find(Parent.class, 52L);
        em.remove(parent);
    }
}
