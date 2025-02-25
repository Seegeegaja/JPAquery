package com.my.japTest.jpaTest.repository;

import com.my.japTest.jpaTest.constant.Gender;
import com.my.japTest.jpaTest.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
//여성의 이름 중 "w"또는 "m"을 포함하는 자료를 검색하시오.
    List<Users> findByGenderAndNameLikeOrNameLike(Gender gender, String name,String asd);
//이메일에 net을 포함하는 데이터 건수를 출력하시오.
    List<Users> findByEmailLike(String email);
//가장 최근 한달이내에 업데이트된 자료 중 이름 첫자가 "J"인 자료를 출력하시오.
//  List<Users> findByUpdatedAtBetweenAndNameLike(LocalDateTime start, LocalDateTime end, String name);
    List<Users> findByUpdatedAtBetweenAndNameStartingWith(LocalDateTime start, LocalDateTime end, String name);
//가장 최근 생성된 자료 10건을 ID, 이름, 성별, 생성일 만 출력하시오.
    List<Users> findTop10ByOrderByCreatedAtDesc();
//"Red"를 좋아하는 남성 이메일 계정 중 사이트를 제외한 계정만 출력하시오.
    List<Users> findByLikeColorAndGender(String color, Gender gender);

    //갱신일이 생성일 이전인 잘못된 데이터를 출력하시오.
//    select * from users where updated_at < created_at;
//    List<Users> findByUpdatedAtLessthenCreatedAt();

    //이메일에 edu를 갖는 여성 데이터를 가장 최근 데이터부터 보이도록 출력하시오.
    List<Users> findByGenderAndEmailContainsOrderByCreatedAtDesc(Gender gender, String email);
    //이메일에 edu를 갖는 여성 데이터를 가장 최근 데이터부터 보이도록 출력하시오.
    List<Users> findByGenderAndEmailLikeOrderByUpdatedAtDesc(Gender gender, String email);

    // 좋아하는 색상(Pink)별로 오름차순 정렬하고 같은 색상 데이터는 이름의 내림차순으로 출력하시오.
    List<Users> findByOrderByLikeColorAscNameDesc();
    List<Users> findByLikeColorOrderByLikeColorAscNameDesc(String color);


    //    주어진 아이디 보다 큰 데이터를 내림차수으로 검색 후 페이징 처리
    Page<Users> findByIdGreaterThanOrderByIdDesc(Long id, Pageable pageable);

    Page<Users> findByGenderOrderByIdDesc(Gender gender, Pageable pageable);
}
