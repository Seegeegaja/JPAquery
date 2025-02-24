package com.my.japTest.jpaTest.repository;

import com.my.japTest.jpaTest.constant.Gender;
import com.my.japTest.jpaTest.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    //이름으로 검색하는 쿼리 메소드 생성
    List<Users> findByname(String name);

    //색상값을 받아서 그중에 처음 발견되는 3개 데이터 출력
    List<Users> findTop3ByLikeColor(String color);

    //남자이고 색상이 Yellow
    List<Users> findByGenderAndLikeColor(Gender gender, String color);

    //범위검색
    //After,Before : 날짜 시간 검색 할때 사용,"=" 를 포함하지 않음
    //관계연산자 : >= 를 안쓰고 GreaterThenEquals(크거나 같다)
    //Between 연산자
    //최근 7일 이내 자료를 일어오고 싶음(오늘 빼고 어제부터 7일)
    List<Users> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    //Null / IsNotNull

    //In 구문
    List<Users> findByLikeColorIn(List<String> colors);

    //문저열 검색 관련 구문
    //Like(%검색어%) , Contains(검색어) , StartingWith(시작하는 문자) , EndingWith(끝나는 문자)
    List<Users> findByNameStartingWith(String name);

    List<Users> findByNameEndingWith(String name);

    List<Users> findByNameContains(String name);

    List<Users> findByNameLike(String name);

    //Sorting 하고 순서대로 ... Asc 오름차순 (Ascending) , Desc(Descending)
    //1~10사이의 아이디를 내림차순
    List<Users> findByIdBetweenOrderByNameDesc(Long start, Long end);

    //오렌지 색상중 상위 10개 검색해서 성별의 오름차순 만든사간의 내림차순
    List<Users> findTop10ByLikeColorOrderByGenderAscCreatedAtDesc(String color);

    //List<Users> findTop10ByLikeColorOrderByGenderAscCreatedAtDesc(String name);
//    같은걸 다른 방식으로
    List<Users> findTop20ByLikeColor(String color, Sort sort);

    List<Users> findByGenderAndNameLikeOrNameLike(Gender gender, String name,String asd);

    List<Users> findByEmailLike(String email);

    List<Users> findByUpdatedAtBetweenAndNameStartingWith(LocalDateTime start, LocalDateTime end, String name);

    List<Users> findByLikeColorAndGender(String color, Gender gender);

    List<Users> findByUpdatedAtLessThanAndCreatedAt(LocalDateTime start, LocalDateTime end);

    List<Users> findByGenderAndEmailLikeOrderByUpdatedAtDesc(Gender gender, String email);

    List<Users> findByLikeColorOrderByLikeColorAscNameDesc(String color);
}
