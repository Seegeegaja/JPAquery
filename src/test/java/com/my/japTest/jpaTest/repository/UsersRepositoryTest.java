package com.my.japTest.jpaTest.repository;

import com.my.japTest.jpaTest.constant.Gender;
import com.my.japTest.jpaTest.entity.Users;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//통합테스트 : 톰켓을 띄우고 전체를 실행하도록 만들고 테스트
@SpringBootTest
@Transactional
class UsersRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    @Test
    void totalCountTest() {
        //Given
        //When
        Long rowCount = usersRepository.findAll().stream().count();
        // Then
        assertThat(rowCount).isEqualTo(500);
    }

    @Test
    @DisplayName("사용자 입력 테스트")
    void userInputTest() {
        Users see = Users.builder()
                .name("씨게")
                .email("a@b.c")
                .likeColor("red")
                .gender(Gender.Male)
                .createdAt(LocalDateTime.now())
                .build();
        usersRepository.save(see);
        System.out.println("=====" + usersRepository.findAll().stream().count());

    }

    @Test
    @DisplayName("삭제 테스트")
    void userDeleteById() {
        //Given
        //When
        usersRepository.deleteById(1L);
        //Then
        Boolean exist = usersRepository.existsById(1L);
        assertThat(exist).isEqualTo(false);
    }

    @Test
    @DisplayName("업데이트 하기")
    void userUpdateTest() {

        //1번이 존재 하면
        //1번이 좋아하는색을 빨강으로 수정
        //다시 1번을 읽어서 색상갑이 빨강인걸 확인
        if (usersRepository.existsById(1L)) {
            Optional<Users> oldUser = usersRepository.findById(1L);
            Users newUser = oldUser.get();
            newUser.setLikeColor("빨강");
            //수정
            usersRepository.save(newUser);
        }
        Optional<Users> findUser = usersRepository.findById(1L);
        assertThat(findUser.get().getLikeColor()).isEqualTo("빨강");
    }

    @Test
    @DisplayName("findByName Test")
    void findByNameTest() {
//        dkurton0
        String name = "dkurton0";
        List<Users> result = usersRepository.findByname(name);
        System.out.println("=========================" + result);
        result.forEach(System.out::println);
    }

    @Test
    @DisplayName("findTop3ByLikeColor")
    void findTop3ByLikeColor() {
        usersRepository.findTop3ByLikeColor("Pink").forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByGenderAndLikeColor")
    void findByGenderAndLikeColor() {
        usersRepository.findByGenderAndLikeColor(Gender.Male, "Yellow")
                .forEach(x -> System.out.println(x));

    }

    @Test
    @DisplayName(" findByCreatedAtBetween")
    void findByCreatedAtBetween() {
        LocalDate baseDate = LocalDate.now().minusDays(1);
        //7일 전 시작 날짜
        LocalDateTime startTime = baseDate.atTime(0, 0, 0).minusDays(6);
        System.out.println(startTime);
        //어제 검색 종료 날짜시각
        LocalDateTime endTime = baseDate.atTime(23, 59, 59);
        System.out.println(endTime);
        usersRepository.findByCreatedAtBetween(startTime, endTime).forEach(x -> System.out.println("+11655+6" + x));
        //        LocalDateTime start = LocalDateTime.now().minusDays(7L);
//        LocalDateTime end = LocalDateTime.now().minusDays(1L);
//        usersRepository.findByCreatedAtBetween(start, end).forEach(x-> System.out.println(x));
//        usersRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(7L), LocalDateTime.now().minusDays(1L))
//                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName(" findByCreatedAtBetween")
    void findByCreatedAtBetween_2() {
        //지난달 자료 검색을 합니다(1/1 ~1/31)
        //이번달을 찾음
        YearMonth asd = YearMonth.now();
        //이번달의 마이너스 1달을 함
        YearMonth qwe = asd.minusMonths(1);
        //그달의 1일을 찾음
        LocalDate dsa = qwe.atDay(1);
        //그 달의 마지막 날을 찾음
        LocalDate end = qwe.atEndOfMonth();
//        시작 시간
        LocalDateTime start = dsa.atTime(0, 0, 0);
        //끝나는 시간
        LocalDateTime endT = end.atTime(23, 59, 59);
        System.out.println(start);
        System.out.println(endT);
        usersRepository.findByCreatedAtBetween(start, endT);

        LocalDate base = LocalDate.now().minusMonths(1L);
        LocalDateTime startTime = base.withDayOfMonth(1).atTime(0, 00, 0);
        LocalDateTime endTime = base.withDayOfMonth(base.lengthOfMonth()).atTime(23, 59, 59);
        System.out.println("시작 날짜임 전달 1일 " + startTime);
        System.out.println("전달 마지막일 " + endTime);

    }

    @Test
    @DisplayName("findByLikeColorIn")
    void findByLikeColorIn() {
        List<String> findColor = new ArrayList<>(
                Arrays.asList("Red", "Orange")
        );
        List<Users> users = usersRepository.findByLikeColorIn(findColor);
        for (Users x : users) {
            System.out.println(x);
        }
    }

    @Test
    @DisplayName("findByNameStartingWith")
    void findByNameStartingWith() {
        usersRepository.findByNameStartingWith("d").forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByNameEndingWith")
    void findByNameEndingWith() {
        usersRepository.findByNameEndingWith("s").forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByNameContains")
    void findByNameContains() {
        usersRepository.findByNameContains("k").forEach(x -> System.out.println(x));

    }

    @Test
    @DisplayName("findByNameLike")
    void findByNameLike() {
        usersRepository.findByNameLike("%p%").forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByIdBetweenOrderByNameDesc")
    void findByIdBetweenOrderByNameDesc() {
        usersRepository.findByIdBetweenOrderByNameDesc(1L, 10L).forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findTop10ByLikeColorOrderByGenderAscCreatedAtDesc")
    void findTop10ByLikeColorOrderByGenderAscCreatedAtDesc() {
        usersRepository.findTop10ByLikeColorOrderByGenderAscCreatedAtDesc("Orange").forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findTop20ByLikeColor")
    void findTop20ByLikeColor() {
        usersRepository.findTop20ByLikeColor("Red", getSort())
                .forEach(x -> System.out.println(x));
    }

    private Sort getSort() {
        return Sort.by(Sort.Order.asc("gender"), Sort.Order.desc("createdAt"));
//                Sort.by(Sort.Order.asc("gender"), Sort.by(Sort.Order.desc("createAt")));
    }

    @Test
    @DisplayName("findByGenderAndNameLikeOrNameLike")
    void findByGenderAndNameLikeOrNameLike() {
        usersRepository.findByGenderAndNameLikeOrNameLike(Gender.Female, "%w%", "%m%").forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByEmailLike")
    void findByEmailLike() {
        usersRepository.findByEmailLike("%net%").forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByUpdatedAtBetweenAndNameStartingWith")
    void findByUpdatedAtBetweenAndNameStartingWith() {
        LocalDate base = LocalDate.now().minusMonths(1L);
        LocalDateTime sta = base.atTime(0, 0, 0);
        LocalDateTime end = LocalDateTime.now();
        System.out.println(sta + "" + end);
        usersRepository.findByUpdatedAtBetweenAndNameStartingWith(sta, end, "j").forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByLikeColorAndGenderAndEmail")
    void findByLikeColorAndGender() {
        usersRepository.findByLikeColorAndGender("Red", Gender.Male).forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByUpdatedAtGreaterThanAndCreatedAt")
    void findByUpdatedAtLessThanAndCreatedAt() {
        LocalDateTime start = LocalDateTime.now();
        usersRepository.findByUpdatedAtLessThanAndCreatedAt(start, start);
    }

    @Test
    @DisplayName("findByGenderAndEmailLikeOrderByUpdatedAtAsc")
    void findByGenderAndEmailLikeOrderByUpdatedAtDesc() {
        usersRepository.findByGenderAndEmailLikeOrderByUpdatedAtDesc(Gender.Female, "%edu%")
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByLikeColorOrderByLikeColorAscNameDesc")
    void findByLikeColorOrderByLikeColorAscNameDesc(){
        usersRepository.findByLikeColorOrderByLikeColorAscNameDesc("Pink").forEach(x-> System.out.println(x));
    }

}